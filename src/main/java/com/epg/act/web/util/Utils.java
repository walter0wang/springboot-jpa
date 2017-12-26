package com.epg.act.web.util;

import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;

import static java.lang.System.nanoTime;

public class Utils {

    public static boolean checkPicExt(String picName) {
        if (StringUtils.isEmpty(picName)) return false;
        String ext = picName.substring(picName.lastIndexOf(".") + 1, picName.length());
        return Arrays.stream(Constants.PIC_EXT).anyMatch(ext::equals);
    }

    /**
     * 生成图片名称
     *
     * @return java.util.String
     */
    public static String generatePicName(String ext) {
        return MessageFormat.format("IMG_EGG_{0}_{1}.{2}",
                DateUtil.format(new Date(), DateUtil.Pattern.DATE_PATTERN_NO_JOIN), nanoTime(), ext);
    }

    /**
     * 校验金蛋图片类型
     *
     * @param type
     * @return
     */
    public static boolean checkEggPicType(Integer type) {
        return type != null && Constants.PIC_TYPE_STR.contains(type.toString());
    }


    public static void main(String[] args) {
        String a = "asdsad.ppc.ddd.jpg";
        System.out.println(checkPicExt(a));
        System.out.println(nanoTime());
        System.out.println(checkEggPicType(3));
    }

}
