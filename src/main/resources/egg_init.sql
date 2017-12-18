/*
Navicat MySQL Data Transfer

Source Server         : epg-158
Source Server Version : 50617
Source Host           : 61.181.152.158:3306
Source Database       : egg

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2017-12-01 15:57:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for CHANNEL
-- ----------------------------
DROP TABLE IF EXISTS `CHANNEL`;
CREATE TABLE `CHANNEL` (
  `NUM` smallint(6) NOT NULL COMMENT '频道号',
  `NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '频道名称',
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='频道表';

-- ----------------------------
-- Table structure for CHANNEL_ACTIV
-- ----------------------------
DROP TABLE IF EXISTS `CHANNEL_ACTIV`;
CREATE TABLE `CHANNEL_ACTIV` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `CHANNEL_NUM` smallint(6) DEFAULT NULL COMMENT '频道号',
  `ACTIV_ID` int(11) DEFAULT NULL COMMENT '金蛋活动id',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='频道活动表';

-- ----------------------------
-- Table structure for EGG_ACTIV
-- ----------------------------
DROP TABLE IF EXISTS `EGG_ACTIV`;
CREATE TABLE `EGG_ACTIV` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '活动名称',
  `PIC_TPL_ID` int(11) DEFAULT NULL COMMENT '图片模板主键',
  `PRIZE_TPL_ID` int(11) DEFAULT NULL COMMENT '奖品模板主键',
  `STATUS` tinyint(4) DEFAULT NULL COMMENT '状态（-1:删除 0: 创建中 1:已发布）',
  `START_DATE` datetime DEFAULT NULL COMMENT '开始日期',
  `END_DATE` datetime DEFAULT NULL COMMENT '结束日期',
  `WEEKS` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '生效周(0 为固定时间)',
  `START_TIME` time DEFAULT NULL COMMENT '开始时间（周生效时启用）',
  `END_TIME` time DEFAULT NULL COMMENT '结束时间（周生效时启用）',
  `TIME_NUM` tinyint(4) DEFAULT NULL COMMENT '时间点个数',
  `TIME_BETWEEN` tinyint(4) DEFAULT NULL COMMENT '时间点间隔',
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
  `UPDATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '编辑人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='金蛋活动表';

-- ----------------------------
-- Table structure for EGG_PIC
-- ----------------------------
DROP TABLE IF EXISTS `EGG_PIC`;
CREATE TABLE `EGG_PIC` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '图片名称',
  `PIC_TPL_ID` int(11) DEFAULT NULL COMMENT '图片模板id',
  `TYPE` tinyint(4) DEFAULT NULL COMMENT '图片类型（1：打开 2：完整 3：砸 4：预告 5：提示 6：中奖未绑定 7：中奖已绑定 8：未中奖）',
  `UPLOAD_NAME` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '上传名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='模板图片表';

-- ----------------------------
-- Table structure for EGG_PIC_TPL
-- ----------------------------
DROP TABLE IF EXISTS `EGG_PIC_TPL`;
CREATE TABLE `EGG_PIC_TPL` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `NAME` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '模板名称',
  `TYPE` tinyint(4) DEFAULT NULL COMMENT '模板类型 （1:标清 2:高清）',
  `NUM` int(11) DEFAULT NULL COMMENT '图片数量',
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `UPDATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='金蛋图片模板表';

-- ----------------------------
-- Table structure for EGG_PRIZE
-- ----------------------------
DROP TABLE IF EXISTS `EGG_PRIZE`;
CREATE TABLE `EGG_PRIZE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '奖品名称',
  `TOTAL_MONEY` int(11) DEFAULT NULL COMMENT '投放金额（单位：分）',
  `MAX_MONEY` int(11) DEFAULT NULL COMMENT '最大金额(单位：分)',
  `MIN_MONEY` int(11) DEFAULT NULL COMMENT '最小金额(单位：分)',
  `CHANCE` float DEFAULT NULL COMMENT '中奖率(百分之XX)',
  `CREATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `UPDATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='金蛋奖品表';

-- ----------------------------
-- Table structure for EGG_TIME
-- ----------------------------
DROP TABLE IF EXISTS `EGG_TIME`;
CREATE TABLE `EGG_TIME` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ACTIV_ID` int(11) DEFAULT NULL COMMENT '活动主键id',
  `CHANNEL_NUM` smallint(6) DEFAULT NULL COMMENT '频道号',
  `WEEK` int(1) DEFAULT NULL COMMENT '周',
  `DATETIME` datetime DEFAULT NULL COMMENT '时点',
  `PIC_TPL_ID` int(11) DEFAULT NULL COMMENT '图片模板主键 （若为空 则无定制配置）',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后编辑时间',
  `UPDATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '编辑人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='金蛋时点';

-- ----------------------------
-- Table structure for PICTURE
-- ----------------------------
DROP TABLE IF EXISTS `PICTURE`;
CREATE TABLE `PICTURE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `UPLOAD_NAME` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '上传名称',
  `NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '图片名称',
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='图片表';
