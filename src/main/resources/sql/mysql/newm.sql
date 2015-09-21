/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50527
Source Host           : 127.0.0.1:3306
Source Database       : newm

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2015-01-23 16:56:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_app_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_app_user`;
CREATE TABLE `sys_app_user` (
  `USER_ID` varchar(100) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `LAST_LOGIN` varchar(255) DEFAULT NULL,
  `IP` varchar(100) DEFAULT NULL,
  `STATUS` varchar(32) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `PHONE` varchar(100) DEFAULT NULL,
  `SFID` varchar(100) DEFAULT NULL,
  `START_TIME` varchar(100) DEFAULT NULL,
  `END_TIME` varchar(100) DEFAULT NULL,
  `YEARS` int(10) DEFAULT NULL,
  `NUMBER` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_app_user
-- ----------------------------
INSERT INTO `sys_app_user` VALUES ('04061c818cbb4acca2828c1b00c0bbb6', 'sdas', 'bcbe3365e6ac95ea2c0343a2395834dd', '213', '', '1f69adef545845a1820362e78aa0297b', '', '', '1', '123', '18755555555', '123', '', '', '0', '222', 'sadfsdf@qq.com');
INSERT INTO `sys_app_user` VALUES ('4f366368ffbd4cc0a55c246c4be48660', 'sdf', 'ff1ccf57e98c817df1efcd9fe44a8aeb', 'wewwwww', '', '1f69adef545845a1820362e78aa0297b', '', '', '1', '', '', '', '', '', '1', 'erwesss', 'werwer@qq.com');

-- ----------------------------
-- Table structure for `sys_gl_qx`
-- ----------------------------
DROP TABLE IF EXISTS `sys_gl_qx`;
CREATE TABLE `sys_gl_qx` (
  `GL_ID` varchar(100) NOT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `FX_QX` int(10) DEFAULT NULL,
  `FW_QX` int(10) DEFAULT NULL,
  `QX1` int(10) DEFAULT NULL,
  `QX2` int(10) DEFAULT NULL,
  `QX3` int(10) DEFAULT NULL,
  `QX4` int(10) DEFAULT NULL,
  PRIMARY KEY (`GL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_gl_qx
-- ----------------------------
INSERT INTO `sys_gl_qx` VALUES ('1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `sys_gl_qx` VALUES ('1f69adef545845a1820362e78aa0297b', '7', '1', '1', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('2', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `sys_gl_qx` VALUES ('2408deb2a0264f0c875379ce5be9d504', '4', '1', '1', '1', '1', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('2b11067b577d4f2c95d16c5e5b162367', '6', '0', '1', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('7eef6f5fd223482cab2e6585d32c5bb2', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('7f797e6f32664ee583ef439b561e1fc8', '4', '1', '1', '1', '1', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('c7fe3229a6874c9789821a9cbf2726d0', '6', '0', '1', '1', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('e2aacbad8b1d43e5b99d9aac7b677e32', '1', '1', '1', '1', '1', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('fe836964ef65423e8e0488bae7f731e2', '4', '1', '1', '1', '1', '0', '0');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `MENU_ID` int(11) NOT NULL COMMENT '菜单id',
  `MENU_NAME` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `MENU_URL` varchar(255) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `MENU_ORDER` varchar(32) DEFAULT NULL,
  `MENU_ICON` varchar(30) DEFAULT NULL,
  `MENU_TYPE` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '#', '0', '1', 'icon-desktop', '1');
INSERT INTO `sys_menu` VALUES ('2', '组织管理', 'role.do', '1', '2', null, '1');
INSERT INTO `sys_menu` VALUES ('4', '会员管理', 'happuser/listUsers.do', '1', '4', null, '1');
INSERT INTO `sys_menu` VALUES ('5', '系统用户', 'user/listUsers.do', '1', '3', null, '1');
INSERT INTO `sys_menu` VALUES ('6', '信息管理', '#', '0', '2', 'icon-list-alt', '2');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ROLE_ID` varchar(100) NOT NULL,
  `ROLE_NAME` varchar(100) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `ADD_QX` varchar(10) DEFAULT NULL,
  `DEL_QX` varchar(10) DEFAULT NULL,
  `EDIT_QX` varchar(10) DEFAULT NULL,
  `CHA_QX` varchar(10) DEFAULT NULL,
  `QX_ID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '1014', '0', '1', '1', '1', '1', '1');
INSERT INTO `sys_role` VALUES ('1f69adef545845a1820362e78aa0297b', '初级会员', '242', '7', '0', '0', '0', '0', '1f69adef545845a1820362e78aa0297b');
INSERT INTO `sys_role` VALUES ('2', '超级管理员', '1014', '1', '1', '0', '0', '1', '2');
INSERT INTO `sys_role` VALUES ('2408deb2a0264f0c875379ce5be9d504', '经理', '54', '4', '1', '0', '0', '0', '2408deb2a0264f0c875379ce5be9d504');
INSERT INTO `sys_role` VALUES ('2b11067b577d4f2c95d16c5e5b162367', '白银客户', '18', '6', '0', '0', '0', '0', '2b11067b577d4f2c95d16c5e5b162367');
INSERT INTO `sys_role` VALUES ('4', '用户组', '54', '0', '0', '0', '0', '0', null);
INSERT INTO `sys_role` VALUES ('6', '客户组', '18', '0', '1', '1', '1', '1', null);
INSERT INTO `sys_role` VALUES ('7', '会员组', '242', '0', '0', '0', '0', '1', null);
INSERT INTO `sys_role` VALUES ('7eef6f5fd223482cab2e6585d32c5bb2', null, null, null, '0', '0', '0', '0', '7eef6f5fd223482cab2e6585d32c5bb2');
INSERT INTO `sys_role` VALUES ('7f797e6f32664ee583ef439b561e1fc8', '组长', '198', '4', '1', '0', '0', '0', '7f797e6f32664ee583ef439b561e1fc8');
INSERT INTO `sys_role` VALUES ('c7fe3229a6874c9789821a9cbf2726d0', '黄金客户', '210', '6', '0', '1', '0', '0', 'c7fe3229a6874c9789821a9cbf2726d0');
INSERT INTO `sys_role` VALUES ('e2aacbad8b1d43e5b99d9aac7b677e32', '测试', '1014', '1', '1', '0', '1', '1', 'e2aacbad8b1d43e5b99d9aac7b677e32');
INSERT INTO `sys_role` VALUES ('fe836964ef65423e8e0488bae7f731e2', '员工', '54', '4', '0', '0', '0', '0', 'fe836964ef65423e8e0488bae7f731e2');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` varchar(100) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `LAST_LOGIN` varchar(255) DEFAULT NULL,
  `IP` varchar(100) DEFAULT NULL,
  `STATUS` varchar(32) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `SKIN` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL,
  `NUMBER` varchar(100) DEFAULT NULL,
  `PHONE` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'c4ca4238a0b923820dcc509a6f75849b', '系统管理员', '1133671055321055258374707980945218933803269864762743594642571294', '1', '2015-01-23 04:43:45', '127.0.0.1', '0', '最高统治者', 'skin-1', 'admin@main.com', '001', '18765810586');
INSERT INTO `sys_user` VALUES ('1568af4e2213408c85f8bc8a4575a3f2', 'wangguo', '202cb962ac59075b964b07152d234b70', '王国', '', '2', '', '', '0', '王国', 'default', 'zhassssan@www.com', 'www', '18765825222');
INSERT INTO `sys_user` VALUES ('538b3f7a8db74630826e0472e60c97a6', 'wangwu', '202cb962ac59075b964b07152d234b70', '王五', '', '2', '', '', '0', '王五', 'default', 'wangwu@163.com', '225', null);
INSERT INTO `sys_user` VALUES ('8a0e9babd9184db187fb14f4e90f6c3a', 'zhangsan', 'c4ca4238a0b923820dcc509a6f75849b', '张三', '', 'e2aacbad8b1d43e5b99d9aac7b677e32', '2014-12-30 04:57:13', '127.0.0.1', '0', '小张', 'default', 'zhangsan2222@www.com', '1101', '18765822222');
INSERT INTO `sys_user` VALUES ('ec1f7a63af81431f93c2d30e9004f7fd', 'asdasd', '4eae35f1b35977a00ebd8086c259d4c9', 'www', '', '2408deb2a0264f0c875379ce5be9d504', '', '', '0', 'sdfsd', 'default', 'wqeqwe@qq.com', '4554', '15858586856');
INSERT INTO `sys_user` VALUES ('f0805ccabcf74f1f9b7b2ce374223ad3', 'sdas', 'c4ca4238a0b923820dcc509a6f75849b', '1', '', '2', '', '', '0', '', 'default', 'sadasd@qq.com', 'sss', '18665812255');

-- ----------------------------
-- Table structure for `sys_user_qx`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_qx`;
CREATE TABLE `sys_user_qx` (
  `U_ID` varchar(100) NOT NULL,
  `C1` int(10) DEFAULT NULL,
  `C2` int(10) DEFAULT NULL,
  `C3` int(10) DEFAULT NULL,
  `C4` int(10) DEFAULT NULL,
  `Q1` int(10) DEFAULT NULL,
  `Q2` int(10) DEFAULT NULL,
  `Q3` int(10) DEFAULT NULL,
  `Q4` int(10) DEFAULT NULL,
  PRIMARY KEY (`U_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_qx
-- ----------------------------
INSERT INTO `sys_user_qx` VALUES ('1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `sys_user_qx` VALUES ('1f69adef545845a1820362e78aa0297b', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('2', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `sys_user_qx` VALUES ('2408deb2a0264f0c875379ce5be9d504', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('2b11067b577d4f2c95d16c5e5b162367', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('7eef6f5fd223482cab2e6585d32c5bb2', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('7f797e6f32664ee583ef439b561e1fc8', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('c7fe3229a6874c9789821a9cbf2726d0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('e2aacbad8b1d43e5b99d9aac7b677e32', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('fe836964ef65423e8e0488bae7f731e2', '0', '0', '0', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for `sys_zidian`
-- ----------------------------
DROP TABLE IF EXISTS `sys_zidian`;
CREATE TABLE `sys_zidian` (
  `ZD_ID` varchar(100) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `BIANMA` varchar(100) DEFAULT NULL,
  `ORDY_BY` int(10) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `JB` int(10) DEFAULT NULL,
  `P_BM` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ZD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_zidian
-- ----------------------------
INSERT INTO `sys_zidian` VALUES ('1e1c2e99159d4ecca7ca40b3568c81ee', '分类', 'FL', '2', '0', '1', 'FL');
INSERT INTO `sys_zidian` VALUES ('29355c3a8c544ee09f9b5438b8398fc2', '行政部', '003', '3', 'c067fdaf51a141aeaa56ed26b70de863', '2', 'BM_003');
INSERT INTO `sys_zidian` VALUES ('3472dcfdf6f743fd8a770f80279e2c6b', '研发部', '004', '4', 'c067fdaf51a141aeaa56ed26b70de863', '2', 'BM_004');
INSERT INTO `sys_zidian` VALUES ('98c3e4e64bb04dc29e509b2574a864f2', '人事部', '001', '1', 'c067fdaf51a141aeaa56ed26b70de863', '2', 'BM_001');
INSERT INTO `sys_zidian` VALUES ('9b19c0e5d9cb4568921bcafc71dc49ad', '市场部', '002', '2', 'c067fdaf51a141aeaa56ed26b70de863', '2', 'BM_002');
INSERT INTO `sys_zidian` VALUES ('c067fdaf51a141aeaa56ed26b70de863', '部门', 'BM', '1', '0', '1', 'BM');
