/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : oicq

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 25/12/2020 09:54:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dw_user
-- ----------------------------
DROP TABLE IF EXISTS `dw_user`;
CREATE TABLE `dw_user`  (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_password` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_email` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_sex` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'M',
  `user_birthday` varchar(12) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_avatar` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_trades` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_registertime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_email`(`user_email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10020 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dw_user
-- ----------------------------
INSERT INTO `dw_user` VALUES (10001, '却却却却却', 'e10adc3949ba59abbe56e057f20f883e', 'test@qq.com', 'M', '2002-09-22', 'http://a1.qpic.cn/psc?/V12vfXNT3gNOiA/ruAMsa53pVQWN7FLK88i5ofRyGOtBNLtPm1bPe.Scp2eMRQGFsx4TVv1PMT..SY0Q29egPJyLVVZez3Td3.uaxbEvmX2GyEVfS7R5TeoOzY!/b&ek=1&kp=1&pt=0&bo=wAPAAwAAAAABFzA!&tl=3&vuin=1501862055&tm=1608364800&sce=60-1-1&rf=viewer_4', 's1mple就是我', '2020-12-18 02:25:12');
INSERT INTO `dw_user` VALUES (10002, 'GodFly', 'e10adc3949ba59abbe56e057f20f883e', 'i@ireson.cn', 'M', '2001-09-06', 'http://m.qpic.cn/psc?/V12vfXNT3gNOiA/45NBuzDIW489QBoVep5mccOUMZoJomsvNg8b4rtJ21lTglFi.Jwgm83zApSeEvW3uc9iFzci90l1EazKqTHPBpqiWVB11R.h0d9R.BJ2tdA!/b&bo=9AH0AQAAAAABFzA!&rf=viewer_4', '我是创', '2020-12-18 07:33:27');
INSERT INTO `dw_user` VALUES (10003, '我不是挂', 'e10adc3949ba59abbe56e057f20f883e', 'qian', 'M', '2001-05-06', 'http://a1.qpic.cn/psc?/V12vfXNT3gNOiA/ruAMsa53pVQWN7FLK88i5ofRyGOtBNLtPm1bPe.Scp28rr38JdSH0ZxALIsplYS2bv2Ff77Isz3qEd9*YB3AyjvWkEyZaetnVgDhRu83KjY!/b&ek=1&kp=1&pt=0&bo=LAEsAQAAAAABFzA!&tl=3&vuin=1501862055&tm=1608364800&sce=60-1-1&rf=viewer_4', '哈哈哈哈', '2020-12-18 09:13:41');
INSERT INTO `dw_user` VALUES (10004, 'curry', 'e10adc3949ba59abbe56e057f20f883e', '12223', 'M', '', 'http://m.qpic.cn/psc?/V12vfXNT3gNOiA/45NBuzDIW489QBoVep5mcZ.Vp2M8wiigyC1QvshqmCzUBlzyzey5NDVcItF6azMEhwnYlW3B.PHhphEvtv5l5E33lR6WHKUeUbxAQz9KHs8!/b&bo=9AHyAQAAAAABFzY!&rf=viewer_4', '', '2020-12-18 13:31:22');
INSERT INTO `dw_user` VALUES (10005, 's1mple', 'e10adc3949ba59abbe56e057f20f883e', 'CSD', 'M', '', 'http://m.qpic.cn/psc?/V12vfXNT3gNOiA/45NBuzDIW489QBoVep5mcZ.Vp2M8wiigyC1QvshqmCxc61NUZ*0k5YmpdDMNhn3G*70oBC.40nQSTNQJqEnY1s7c3L*XgPLRupLKOiPg.0k!/b&bo=9AE1AgAAAAABF*I!&rf=viewer_4', '', '2020-12-18 09:53:40');
INSERT INTO `dw_user` VALUES (10006, 'niko', 'e10adc3949ba59abbe56e057f20f883e', 'Hadestack', 'M', '', 'http://m.qpic.cn/psc?/V12vfXNT3gNOiA/45NBuzDIW489QBoVep5mcZ.Vp2M8wiigyC1QvshqmCwnwnqw.yLWLxjZkea.hG4CZCDwtthsiUh*JGQMeq.DCcrWkyW9ghDhEmayXFcsY8Q!/b&bo=9AHNAQAAAAABFwk!&rf=viewer_4', '', '2020-12-18 09:53:40');
INSERT INTO `dw_user` VALUES (10007, 'shox', 'e10adc3949ba59abbe56e057f20f883e', 'Hran', 'M', '', 'http://m.qpic.cn/psc?/V12vfXNT3gNOiA/45NBuzDIW489QBoVep5mcZ.Vp2M8wiigyC1QvshqmCxSN8JQ2yD4XQaMjFwO2pvHqYfPHlgcF5rlsBBS*GPieAm9HZTVWYBjW*k9mCqKXuk!/b&bo=PwFaAQAAAAABF1U!&rf=viewer_4', '', '2020-12-18 09:53:40');
INSERT INTO `dw_user` VALUES (10008, 'zwyoo', 'e10adc3949ba59abbe56e057f20f883e', 'Jrotty', 'M', '', 'http://m.qpic.cn/psc?/V12vfXNT3gNOiA/45NBuzDIW489QBoVep5mcbp2xrO3c03QDM.wHoXU9xYx2DeJMnr2Yv9msFg3wVyrEyoj*mUtQflo2RkgECwVbBysQ1VXWYss8T2e6iUDrxA!/b&bo=UAFtAQAAAAABFw0!&rf=viewer_4', '', '2020-12-18 09:53:40');
INSERT INTO `dw_user` VALUES (10009, '冀北', 'e10adc3949ba59abbe56e057f20f883e', 'SXB', 'M', '', 'http://m.qpic.cn/psc?/V12vfXNT3gNOiA/45NBuzDIW489QBoVep5mcbp2xrO3c03QDM.wHoXU9xYvBBjUuwNpoC4i5Yea2tSS8VMqhOcymB2ATnJVBgytL2Y.4zKD6zbYRMp96f0Qu7U!/b&bo=QAFAAQAAAAABFzA!&rf=viewer_4', '', '2020-12-18 09:53:40');
INSERT INTO `dw_user` VALUES (10010, '小明', 'e10adc3949ba59abbe56e057f20f883e', '你好马化腾', 'M', '', 'http://m.qpic.cn/psc?/V12vfXNT3gNOiA/45NBuzDIW489QBoVep5mcbp2xrO3c03QDM.wHoXU9xaRDI*vJiPp4T47pYOLY7KPVT6cG8wLy7ub8LdMWCHNmGd1BYPIm7gLoupyI*QLTkI!/b&bo=kAGQAQAAAAABFzA!&rf=viewer_4', '', '2020-12-18 09:53:40');
INSERT INTO `dw_user` VALUES (10011, '小朱', 'e10adc3949ba59abbe56e057f20f883e', '充钱使你更强', 'M', '', 'http://m.qpic.cn/psc?/V12vfXNT3gNOiA/45NBuzDIW489QBoVep5mcbp2xrO3c03QDM.wHoXU9xZE8YoKv5JzkvwcBEGLcphVm2XCOdQFKOvIIWEuYMNkFR5oBnBWaz3S1iQF0O3abQM!/b&bo=ZAFlAQAAAAABFzE!&rf=viewer_4', '', '2020-12-18 09:53:40');
INSERT INTO `dw_user` VALUES (10012, '小陈', 'e10adc3949ba59abbe56e057f20f883e', '买把龙狙他不香吗', 'M', '', 'http://m.qpic.cn/psc?/V12vfXNT3gNOiA/45NBuzDIW489QBoVep5mca2RRkItdA*Z5fWfusHambbfriEu.k0yn1*KWXoaFONAgeR*aHMd30GyK5HAs7YndF.kwbq0iRSZmNUbZOREVZE!/b&bo=LAEsAQAAAAABFzA!&rf=viewer_4', '', '2020-12-18 09:53:40');
INSERT INTO `dw_user` VALUES (10013, '老6', 'e10adc3949ba59abbe56e057f20f883e', '给我起把狙', 'M', '', 'http://m.qpic.cn/psc?/V12vfXNT3gNOiA/45NBuzDIW489QBoVep5mca2RRkItdA*Z5fWfusHambaFzlbqiXPym.UPxnRTo36GQvkCtSGHXyT9WFtZRnlxEBNdE4oAcMbDCoX1xnPkDpA!/b&bo=TwFRAQAAAAABFy4!&rf=viewer_4', '', '2020-12-18 09:53:40');

-- ----------------------------
-- Table structure for dw_userchat
-- ----------------------------
DROP TABLE IF EXISTS `dw_userchat`;
CREATE TABLE `dw_userchat`  (
  `uchat_id` int(10) NOT NULL AUTO_INCREMENT,
  `uchat_fromid` int(10) NOT NULL,
  `uchat_toid` int(10) NOT NULL,
  `uchat_message` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `uchat_datetime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`uchat_id`) USING BTREE,
  INDEX `uchat_fromid`(`uchat_fromid`) USING BTREE,
  INDEX `uchat_toid`(`uchat_toid`) USING BTREE,
  CONSTRAINT `dw_userchat_ibfk_1` FOREIGN KEY (`uchat_fromid`) REFERENCES `dw_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `dw_userchat_ibfk_2` FOREIGN KEY (`uchat_toid`) REFERENCES `dw_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dw_userchat
-- ----------------------------
INSERT INTO `dw_userchat` VALUES (82, 10001, 10002, '你好\n', '2020-12-18 14:34:13');
INSERT INTO `dw_userchat` VALUES (83, 10002, 10001, '我不好', '2020-12-18 14:34:43');
INSERT INTO `dw_userchat` VALUES (84, 10001, 10002, '啊这', '2020-12-18 14:35:07');
INSERT INTO `dw_userchat` VALUES (85, 10001, 10002, '你可太帅了\n', '2020-12-18 15:32:54');
INSERT INTO `dw_userchat` VALUES (86, 10001, 10002, '1111', '2020-12-19 13:45:48');
INSERT INTO `dw_userchat` VALUES (87, 10002, 10001, '1111', '2020-12-19 13:45:51');
INSERT INTO `dw_userchat` VALUES (88, 10001, 10002, '1111', '2020-12-19 13:46:06');
INSERT INTO `dw_userchat` VALUES (89, 10001, 10002, '创飞在吗', '2020-12-19 13:46:18');
INSERT INTO `dw_userchat` VALUES (90, 10002, 10001, '我不在啊\n', '2020-12-19 13:46:27');
INSERT INTO `dw_userchat` VALUES (91, 10002, 10001, '你好啊创飞！！！', '2020-12-24 19:46:09');
INSERT INTO `dw_userchat` VALUES (92, 10001, 10002, '你也好\n', '2020-12-24 19:46:43');
INSERT INTO `dw_userchat` VALUES (93, 10001, 10002, '123', '2020-12-24 19:57:21');
INSERT INTO `dw_userchat` VALUES (94, 10002, 10001, '1234', '2020-12-24 19:57:22');

-- ----------------------------
-- Table structure for dw_useruser
-- ----------------------------
DROP TABLE IF EXISTS `dw_useruser`;
CREATE TABLE `dw_useruser`  (
  `myself` int(10) NOT NULL,
  `myfriend` int(10) NOT NULL,
  PRIMARY KEY (`myself`, `myfriend`) USING BTREE,
  INDEX `myself`(`myself`) USING BTREE,
  INDEX `myfriend`(`myfriend`) USING BTREE,
  CONSTRAINT `dw_useruser_ibfk_1` FOREIGN KEY (`myself`) REFERENCES `dw_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `dw_useruser_ibfk_2` FOREIGN KEY (`myfriend`) REFERENCES `dw_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dw_useruser
-- ----------------------------
INSERT INTO `dw_useruser` VALUES (10001, 10002);
INSERT INTO `dw_useruser` VALUES (10001, 10003);
INSERT INTO `dw_useruser` VALUES (10001, 10004);
INSERT INTO `dw_useruser` VALUES (10001, 10005);
INSERT INTO `dw_useruser` VALUES (10001, 10006);
INSERT INTO `dw_useruser` VALUES (10001, 10007);
INSERT INTO `dw_useruser` VALUES (10001, 10008);
INSERT INTO `dw_useruser` VALUES (10001, 10009);
INSERT INTO `dw_useruser` VALUES (10001, 10010);
INSERT INTO `dw_useruser` VALUES (10001, 10011);
INSERT INTO `dw_useruser` VALUES (10001, 10012);
INSERT INTO `dw_useruser` VALUES (10001, 10013);
INSERT INTO `dw_useruser` VALUES (10002, 10001);
INSERT INTO `dw_useruser` VALUES (10002, 10003);
INSERT INTO `dw_useruser` VALUES (10002, 10004);
INSERT INTO `dw_useruser` VALUES (10002, 10005);
INSERT INTO `dw_useruser` VALUES (10002, 10006);
INSERT INTO `dw_useruser` VALUES (10002, 10007);
INSERT INTO `dw_useruser` VALUES (10002, 10008);
INSERT INTO `dw_useruser` VALUES (10002, 10009);
INSERT INTO `dw_useruser` VALUES (10002, 10010);
INSERT INTO `dw_useruser` VALUES (10002, 10011);
INSERT INTO `dw_useruser` VALUES (10002, 10012);
INSERT INTO `dw_useruser` VALUES (10002, 10013);
INSERT INTO `dw_useruser` VALUES (10003, 10001);
INSERT INTO `dw_useruser` VALUES (10003, 10004);
INSERT INTO `dw_useruser` VALUES (10003, 10005);
INSERT INTO `dw_useruser` VALUES (10003, 10006);
INSERT INTO `dw_useruser` VALUES (10003, 10007);
INSERT INTO `dw_useruser` VALUES (10003, 10008);
INSERT INTO `dw_useruser` VALUES (10003, 10009);
INSERT INTO `dw_useruser` VALUES (10003, 10010);
INSERT INTO `dw_useruser` VALUES (10003, 10011);
INSERT INTO `dw_useruser` VALUES (10003, 10012);
INSERT INTO `dw_useruser` VALUES (10003, 10013);
INSERT INTO `dw_useruser` VALUES (10004, 10001);
INSERT INTO `dw_useruser` VALUES (10004, 10005);
INSERT INTO `dw_useruser` VALUES (10004, 10006);
INSERT INTO `dw_useruser` VALUES (10004, 10007);
INSERT INTO `dw_useruser` VALUES (10004, 10008);
INSERT INTO `dw_useruser` VALUES (10004, 10009);
INSERT INTO `dw_useruser` VALUES (10004, 10010);
INSERT INTO `dw_useruser` VALUES (10004, 10011);
INSERT INTO `dw_useruser` VALUES (10004, 10012);
INSERT INTO `dw_useruser` VALUES (10004, 10013);
INSERT INTO `dw_useruser` VALUES (10005, 10001);
INSERT INTO `dw_useruser` VALUES (10005, 10006);
INSERT INTO `dw_useruser` VALUES (10005, 10007);
INSERT INTO `dw_useruser` VALUES (10005, 10008);
INSERT INTO `dw_useruser` VALUES (10005, 10009);
INSERT INTO `dw_useruser` VALUES (10005, 10010);
INSERT INTO `dw_useruser` VALUES (10005, 10011);
INSERT INTO `dw_useruser` VALUES (10005, 10012);
INSERT INTO `dw_useruser` VALUES (10005, 10013);
INSERT INTO `dw_useruser` VALUES (10006, 10001);
INSERT INTO `dw_useruser` VALUES (10006, 10007);
INSERT INTO `dw_useruser` VALUES (10006, 10008);
INSERT INTO `dw_useruser` VALUES (10006, 10009);
INSERT INTO `dw_useruser` VALUES (10006, 10010);
INSERT INTO `dw_useruser` VALUES (10006, 10011);
INSERT INTO `dw_useruser` VALUES (10006, 10012);
INSERT INTO `dw_useruser` VALUES (10006, 10013);

-- ----------------------------
-- View structure for view_useruser
-- ----------------------------
DROP VIEW IF EXISTS `view_useruser`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_useruser` AS select `dw_useruser`.`myself` AS `myself`,`dw_useruser`.`myfriend` AS `myfriend`,`dw_user`.`user_name` AS `user_name`,`dw_user`.`user_avatar` AS `user_avatar`,`dw_user`.`user_trades` AS `user_trades` from (`dw_user` join `dw_useruser`) where (`dw_user`.`user_id` = `dw_useruser`.`myfriend`) order by `dw_useruser`.`myself`;

SET FOREIGN_KEY_CHECKS = 1;
