package com.epg.act.util;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ResourceBundle;
import java.util.StringTokenizer;


@Slf4j
public class FtpUtils {
    public enum Instance {
        PIC("pic");
        private String instance;

        Instance(String instance) {
            this.instance = instance;
        }

        /**
         * ftp客户端
         *
         * @return FTPClient
         * @throws IOException e
         */
        private FTPClient getFTP() throws IOException {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("ftp");
            String url = resourceBundle.getString(instance + ".url");
            String username = resourceBundle.getString(instance + ".username");
            String password = resourceBundle.getString(instance + ".password");
            int port = resourceBundle.containsKey(instance + ".port") ? Integer.valueOf(resourceBundle.getString(instance + ".port")) : 21;
            @Cleanup("disconnect") FTPClient ftpClient = new FTPClient();
            ftpClient.connect(url, StringUtils.isEmpty(port) ? 21 : port);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            return ftpClient;
        }
    }

    /**
     * ftp上传单个文件
     *
     * @param ftp         ftp实例
     * @param directory   上传至ftp的路径名不包括ftp地址
     * @param inputStream 要上传的文件流
     * @param destName    上传至ftp后存储的文件名
     * @return Boolean
     * @throws IOException e
     */
    public static boolean upload(FtpUtils.Instance ftp, String directory, InputStream inputStream, String destName) throws IOException {
        FTPClient ftpClient = ftp.getFTP();
        try {
            if (!ftpClient.changeWorkingDirectory(directory)) {
                makeDirectory(ftpClient, directory);
                // 当前工作目录指定到Dir目录下
                ftpClient.cwd(directory);
            }
            // 设置文件类型（二进制）
            return ftpClient.storeFile(directory + "/" + destName, inputStream);
        } catch (NumberFormatException e) {
            log.error("[err ftp port]" + e.getMessage());
            throw e;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public static boolean upload(FtpUtils.Instance ftp, String directory, String srcFileName, String destName) throws IOException {
        return upload(ftp, directory, new FileInputStream(srcFileName), destName);
    }

    public static boolean upload(FtpUtils.Instance ftp, String directory, File srcFile, String destName) throws IOException {
        return upload(ftp, directory, new FileInputStream(srcFile), destName);
    }


    /**
     * FTP单个文件下载
     *
     * @param ftp          ftp实例
     * @param directory    要下载的文件所在ftp的路径名不包含ftp地址
     * @param destFileName 要下载的文件名
     * @param downloadName 下载后锁存储的文件名全路径
     */
    public static boolean download(FtpUtils.Instance ftp, String directory, String destFileName, String downloadName) throws IOException {
        FTPClient ftpClient = ftp.getFTP();
        try {
            // 设置文件类型（二进制）
            ftpClient.changeWorkingDirectory(directory);
            return ftpClient.retrieveFile(destFileName, new FileOutputStream(downloadName));
        } catch (NumberFormatException e) {
            throw e;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
    }


    /**
     * @param ftp       ftp实例
     * @param directory 要删除的文件所在ftp的路径名不包含ftp地址
     * @param fileName  要删除的文件名
     * @return Boolean
     * @throws IOException e
     */
    public static boolean remove(FtpUtils.Instance ftp, String directory, String fileName) throws IOException {
        FTPClient ftpClient = ftp.getFTP();
        try {
            ftpClient.changeWorkingDirectory(directory);
            return ftpClient.deleteFile(fileName);//删除远程文件
        } catch (NumberFormatException e) {
            throw e;
        } catch (IOException e) {
            throw new IOException("连接ftp服务器失败！", e);
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
    }

    /**
     * @param directory 要创建的目录所在ftp的路径名不包含ftp地址
     * @return Boolean
     * @throws IOException e
     */
    private static boolean makeDirectory(FTPClient ftpClient, String directory) throws IOException {
        boolean flag = false;
        if (!StringUtils.isEmpty(directory)) {
            StringTokenizer tokenizer = new StringTokenizer(directory, "/");
            StringBuilder pathName = new StringBuilder();
            while (tokenizer.hasMoreTokens()) {
                pathName.append("/").append(tokenizer.nextToken());
                flag = ftpClient.makeDirectory(pathName.toString());
            }
        }
        return flag;
    }

    /**
     * @param ftp         ftp实例
     * @param directory   要重命名的文件所在ftp的路径名不包含ftp地址
     * @param oldFileName 要重命名的文件名
     * @param newFileName 重命名后的文件名
     * @throws IOException e
     */
    public static boolean rename(FtpUtils.Instance ftp, String directory, String oldFileName, String newFileName) throws IOException {
        FTPClient ftpClient = ftp.getFTP();
        try {
            ftpClient.changeWorkingDirectory(directory);
            return ftpClient.rename(oldFileName, newFileName);//重命名远程文件
        } catch (NumberFormatException e) {
            throw e;
        } catch (IOException e) {
            throw new IOException("连接ftp服务器失败！", e);
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
    }

    /**
     * @param ftp          ftp实例
     * @param directory    要重命名的目录所在ftp的路径名不包含ftp地址
     * @param deldirectory 要删除的目录名
     * @return boolean
     * @throws IOException e
     */
    public static boolean removeDirecotory(FtpUtils.Instance ftp, String directory, String deldirectory) throws IOException {
        FTPClient ftpClient = ftp.getFTP();
        try {
            ftpClient.changeWorkingDirectory(directory);
            return ftpClient.removeDirectory(deldirectory);//删除目录
        } catch (NumberFormatException e) {
            throw e;
        } catch (IOException e) {
            throw new IOException("连接ftp服务器失败！", e);
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
    }

    /**
     * @param ftp       ftp实例
     * @param directory 目录
     * @return 目录
     * @throws IOException e
     */
    public static String[] list(FtpUtils.Instance ftp, String directory) throws IOException {
        FTPClient ftpClient = ftp.getFTP();
        try {
            return ftpClient.listNames();
        } catch (NumberFormatException e) {
            throw e;
        } catch (IOException e) {
            throw new IOException("连接ftp服务器失败！", e);
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
    }
}
