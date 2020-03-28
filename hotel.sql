/*
Navicat MySQL Data Transfer

Source Server         : mysql_connect
Source Server Version : 50605
Source Host           : localhost:3306
Source Database       : hotel

Target Server Type    : MYSQL
Target Server Version : 50605
File Encoding         : 65001

Date: 2020-03-28 09:02:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `checkroom`
-- ----------------------------
DROP TABLE IF EXISTS `checkroom`;
CREATE TABLE `checkroom` (
  `CheckIn` int(11) NOT NULL AUTO_INCREMENT,
  `inDate` date DEFAULT NULL,
  `outDate` date DEFAULT NULL,
  `CustId` int(11) DEFAULT NULL,
  `TypeId` int(11) DEFAULT NULL,
  `TotalCost` int(11) DEFAULT NULL,
  `roomNumb` int(11) DEFAULT NULL,
  `state` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`CheckIn`),
  KEY `Customer_for` (`CustId`),
  KEY `RoomType_for` (`TypeId`),
  KEY `Room_for` (`roomNumb`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of checkroom
-- ----------------------------
INSERT INTO `checkroom` VALUES ('1', '2019-09-03', '2019-09-16', '92', '1', '0', '102', 'CUR');
INSERT INTO `checkroom` VALUES ('2', '2019-09-01', '2019-09-01', '123', '1', '0', '101', 'out');
INSERT INTO `checkroom` VALUES ('3', '2019-09-01', '2019-09-02', '123', '1', '0', '120', 'out');
INSERT INTO `checkroom` VALUES ('4', '2019-09-01', '2019-09-02', '2132', '1', '0', '104', 'in');
INSERT INTO `checkroom` VALUES ('5', '2019-09-01', '2019-09-09', '1231', '1', '0', '103', 'in');
INSERT INTO `checkroom` VALUES ('6', '2019-10-11', '2019-10-21', '74646', '2', '0', '10', 'IN');

-- ----------------------------
-- Table structure for `customers`
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `CustId` int(11) NOT NULL DEFAULT '0',
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `PostalCode` varchar(20) DEFAULT NULL,
  `Mobile` varchar(15) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `Nationality` varchar(30) DEFAULT NULL,
  `Dob` date DEFAULT NULL,
  `IDtype` varchar(20) DEFAULT NULL,
  `IdNumb` varchar(20) DEFAULT NULL,
  `Gender` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`CustId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES ('1', 'new', 'new', 'new', 'new', 'new', 'new', 'new', '2019-09-09', 'new', 'new', 'Female');
INSERT INTO `customers` VALUES ('2', 'FirstName', 'lastName', 'Address', 'PostalCode', 'Mobile', 'email', 'Nationality', '2012-02-20', 'IDtype', 'IdNumb', 'Female');
INSERT INTO `customers` VALUES ('21', 'HER', 'HER', 'HER', 'HER', '12133223', 'her@gmail.com', 'Oceania', '2002-09-01', 'Student ID', 'YNU2019', 'Male');
INSERT INTO `customers` VALUES ('90', 'etrer', 'wrtrrt', 'rttrt', 'rttt', '566656', 'rtrrertretr', 'Asia', '2019-09-01', 'Passport', 'rt45455', 'Male');
INSERT INTO `customers` VALUES ('92', 'jgghj', 'yiuyiu', 'tiuyuiy', '11515', '117177171', 'ihuhkjhkjh', 'Asia', '2019-09-04', 'Passport', 'tytwtw1', 'Female');
INSERT INTO `customers` VALUES ('121', 'TY', 'TY', 'TY', 'TY', '1999', 'ty@gmail.com', 'Africa', '1982-12-15', 'National ID', 'HJJ22', 'Male');
INSERT INTO `customers` VALUES ('122', 'YVON', 'YVON', 'YVON', 'QT11', '1829818918', 'yvon@gmail.com', 'Africa', '2019-09-01', 'Driver License', '341', 'Male');
INSERT INTO `customers` VALUES ('123', 'qqwee', 'qweqwe', 'qeqw', '23wew', '12331', 'asdad', 'Asia', '2019-09-02', 'Passport', 's2312', 'Male');
INSERT INTO `customers` VALUES ('211', 'yves', 'yves', 'yves', 'yves', '165432', 'yves@gmail.com', 'Europe', '1979-09-02', 'Voter\'s ID', 'ew233', 'Male');
INSERT INTO `customers` VALUES ('1213', 'HIM', 'HIM', 'HIM', 'HIM', '123121212', 'him@gmail.com', 'Asia', '2001-09-03', 'Passport', 'w12', 'Male');
INSERT INTO `customers` VALUES ('1231', 'qweqwe', 'qweqw', 'asdasd', '23ww', '11144', 'sads', 'Asia', '2019-09-02', 'Passport', 'w21', 'Male');
INSERT INTO `customers` VALUES ('2132', 'qweq', 'qeqwe', 'qweqwe', 's234', '123313', 'asfdsda', 'Asia', '2019-09-01', 'Passport', 'e2332', 'Male');
INSERT INTO `customers` VALUES ('74646', 'HDHJDH', '74874', 'HRRY', '7TUTY', '6YTYQ6RUTU', 'YRUR', 'Africa', '2019-10-10', 'Passport', 'RRU', 'Female');

-- ----------------------------
-- Table structure for `login`
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `id` smallint(6) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login
-- ----------------------------
INSERT INTO `login` VALUES ('1', 'yvon', '1234');
INSERT INTO `login` VALUES ('2', 'yves', '1234');
INSERT INTO `login` VALUES ('3', 'admin', '1234');
INSERT INTO `login` VALUES ('5', 'yvon', '2019');

-- ----------------------------
-- Table structure for `rooms`
-- ----------------------------
DROP TABLE IF EXISTS `rooms`;
CREATE TABLE `rooms` (
  `id` smallint(6) NOT NULL,
  `numb` int(11) NOT NULL,
  `typeId` smallint(6) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `TypID_for` (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rooms
-- ----------------------------
INSERT INTO `rooms` VALUES ('0', '10', '1', 'Unavailable');
INSERT INTO `rooms` VALUES ('1', '101', '1', 'Available');
INSERT INTO `rooms` VALUES ('2', '102', '1', 'Available');
INSERT INTO `rooms` VALUES ('3', '103', '1', 'Available');
INSERT INTO `rooms` VALUES ('4', '104', '1', 'Available');
INSERT INTO `rooms` VALUES ('5', '15', '1', 'Available');
INSERT INTO `rooms` VALUES ('6', '16', '1', 'Available');
INSERT INTO `rooms` VALUES ('7', '17', '1', 'Available');
INSERT INTO `rooms` VALUES ('8', '18', '1', 'Available');
INSERT INTO `rooms` VALUES ('9', '19', '1', 'Available');
INSERT INTO `rooms` VALUES ('10', '110', '1', 'Available');
INSERT INTO `rooms` VALUES ('11', '201', '2', 'Available');
INSERT INTO `rooms` VALUES ('12', '202', '2', 'Available');
INSERT INTO `rooms` VALUES ('13', '113', '1', 'Available');
INSERT INTO `rooms` VALUES ('14', '114', '1', 'Available');
INSERT INTO `rooms` VALUES ('15', '115', '1', 'Available');
INSERT INTO `rooms` VALUES ('16', '116', '1', 'Available');
INSERT INTO `rooms` VALUES ('17', '117', '1', 'Available');
INSERT INTO `rooms` VALUES ('18', '118', '1', 'Available');
INSERT INTO `rooms` VALUES ('19', '119', '1', 'Available');
INSERT INTO `rooms` VALUES ('20', '120', '1', 'Available');
INSERT INTO `rooms` VALUES ('21', '121', '1', 'Available');
INSERT INTO `rooms` VALUES ('22', '122', '1', 'Available');
INSERT INTO `rooms` VALUES ('23', '123', '1', 'Available');
INSERT INTO `rooms` VALUES ('24', '124', '1', 'Available');
INSERT INTO `rooms` VALUES ('25', '125', '1', 'Available');
INSERT INTO `rooms` VALUES ('26', '126', '1', 'Available');
INSERT INTO `rooms` VALUES ('27', '127', '1', 'Available');
INSERT INTO `rooms` VALUES ('28', '128', '1', 'Available');
INSERT INTO `rooms` VALUES ('29', '129', '1', 'Available');
INSERT INTO `rooms` VALUES ('30', '230', '2', 'Available');
INSERT INTO `rooms` VALUES ('31', '231', '2', 'Available');
INSERT INTO `rooms` VALUES ('32', '232', '2', 'Available');
INSERT INTO `rooms` VALUES ('33', '233', '2', 'Available');
INSERT INTO `rooms` VALUES ('34', '234', '2', 'Available');
INSERT INTO `rooms` VALUES ('35', '235', '2', 'Available');
INSERT INTO `rooms` VALUES ('36', '236', '2', 'Available');
INSERT INTO `rooms` VALUES ('37', '237', '2', 'Available');
INSERT INTO `rooms` VALUES ('38', '238', '2', 'Available');
INSERT INTO `rooms` VALUES ('39', '239', '2', 'Available');
INSERT INTO `rooms` VALUES ('40', '240', '2', 'Available');
INSERT INTO `rooms` VALUES ('41', '241', '2', 'Available');
INSERT INTO `rooms` VALUES ('42', '242', '2', 'Available');
INSERT INTO `rooms` VALUES ('43', '243', '2', 'Available');
INSERT INTO `rooms` VALUES ('44', '244', '2', 'Available');
INSERT INTO `rooms` VALUES ('45', '245', '2', 'Available');
INSERT INTO `rooms` VALUES ('46', '246', '2', 'Available');
INSERT INTO `rooms` VALUES ('47', '247', '2', 'Available');
INSERT INTO `rooms` VALUES ('48', '248', '2', 'Available');
INSERT INTO `rooms` VALUES ('49', '249', '2', 'Available');
INSERT INTO `rooms` VALUES ('50', '250', '2', 'Available');
INSERT INTO `rooms` VALUES ('51', '251', '2', 'Available');
INSERT INTO `rooms` VALUES ('52', '252', '2', 'Available');
INSERT INTO `rooms` VALUES ('53', '253', '2', 'Available');
INSERT INTO `rooms` VALUES ('54', '254', '2', 'Available');
INSERT INTO `rooms` VALUES ('55', '255', '2', 'Available');
INSERT INTO `rooms` VALUES ('56', '256', '2', 'Available');
INSERT INTO `rooms` VALUES ('57', '257', '2', 'Available');
INSERT INTO `rooms` VALUES ('58', '258', '2', 'Available');
INSERT INTO `rooms` VALUES ('59', '259', '2', 'Available');
INSERT INTO `rooms` VALUES ('60', '260', '2', 'Available');
INSERT INTO `rooms` VALUES ('61', '261', '2', 'Available');
INSERT INTO `rooms` VALUES ('62', '262', '2', 'Available');
INSERT INTO `rooms` VALUES ('63', '263', '2', 'Available');
INSERT INTO `rooms` VALUES ('64', '264', '2', 'Available');
INSERT INTO `rooms` VALUES ('65', '265', '2', 'Available');
INSERT INTO `rooms` VALUES ('66', '266', '2', 'Available');
INSERT INTO `rooms` VALUES ('67', '267', '2', 'Available');
INSERT INTO `rooms` VALUES ('68', '268', '2', 'Available');
INSERT INTO `rooms` VALUES ('69', '269', '2', 'Available');
INSERT INTO `rooms` VALUES ('70', '470', '3', 'Available');
INSERT INTO `rooms` VALUES ('71', '471', '3', 'Available');
INSERT INTO `rooms` VALUES ('72', '472', '3', 'Available');
INSERT INTO `rooms` VALUES ('73', '473', '3', 'Available');
INSERT INTO `rooms` VALUES ('74', '474', '3', 'Available');
INSERT INTO `rooms` VALUES ('75', '475', '3', 'Available');
INSERT INTO `rooms` VALUES ('76', '476', '3', 'Available');
INSERT INTO `rooms` VALUES ('77', '477', '3', 'Available');
INSERT INTO `rooms` VALUES ('78', '478', '3', 'Available');
INSERT INTO `rooms` VALUES ('79', '479', '3', 'Available');
INSERT INTO `rooms` VALUES ('80', '480', '3', 'Available');
INSERT INTO `rooms` VALUES ('81', '481', '3', 'Available');
INSERT INTO `rooms` VALUES ('82', '482', '3', 'Available');
INSERT INTO `rooms` VALUES ('83', '483', '3', 'Available');
INSERT INTO `rooms` VALUES ('84', '484', '3', 'Available');
INSERT INTO `rooms` VALUES ('85', '485', '3', 'Available');
INSERT INTO `rooms` VALUES ('86', '486', '3', 'Available');
INSERT INTO `rooms` VALUES ('87', '487', '3', 'Available');
INSERT INTO `rooms` VALUES ('88', '488', '3', 'Available');
INSERT INTO `rooms` VALUES ('89', '489', '3', 'Available');
INSERT INTO `rooms` VALUES ('90', '490', '3', 'Available');
INSERT INTO `rooms` VALUES ('91', '491', '3', 'Available');
INSERT INTO `rooms` VALUES ('92', '492', '3', 'Available');
INSERT INTO `rooms` VALUES ('93', '493', '3', 'Available');
INSERT INTO `rooms` VALUES ('94', '494', '3', 'Available');
INSERT INTO `rooms` VALUES ('95', '495', '3', 'Available');
INSERT INTO `rooms` VALUES ('96', '496', '3', 'Available');
INSERT INTO `rooms` VALUES ('97', '497', '3', 'Available');
INSERT INTO `rooms` VALUES ('98', '498', '3', 'Available');
INSERT INTO `rooms` VALUES ('99', '499', '3', 'Available');
INSERT INTO `rooms` VALUES ('100', '4100', '3', 'Available');
INSERT INTO `rooms` VALUES ('101', '4101', '3', 'Available');
INSERT INTO `rooms` VALUES ('102', '4102', '3', 'Available');
INSERT INTO `rooms` VALUES ('103', '4103', '3', 'Available');
INSERT INTO `rooms` VALUES ('104', '4104', '3', 'Available');
INSERT INTO `rooms` VALUES ('105', '4105', '3', 'Available');
INSERT INTO `rooms` VALUES ('106', '4106', '3', 'Available');
INSERT INTO `rooms` VALUES ('107', '4107', '3', 'Available');
INSERT INTO `rooms` VALUES ('108', '4108', '3', 'Available');
INSERT INTO `rooms` VALUES ('109', '4109', '3', 'Available');
INSERT INTO `rooms` VALUES ('110', '5110', '4', 'Available');
INSERT INTO `rooms` VALUES ('111', '5111', '4', 'Available');
INSERT INTO `rooms` VALUES ('112', '5112', '4', 'Available');
INSERT INTO `rooms` VALUES ('113', '5113', '4', 'Available');
INSERT INTO `rooms` VALUES ('114', '5114', '4', 'Available');
INSERT INTO `rooms` VALUES ('115', '5115', '4', 'Available');
INSERT INTO `rooms` VALUES ('116', '5116', '4', 'Available');
INSERT INTO `rooms` VALUES ('117', '5117', '4', 'Available');
INSERT INTO `rooms` VALUES ('118', '5118', '4', 'Available');
INSERT INTO `rooms` VALUES ('119', '5119', '4', 'Available');
INSERT INTO `rooms` VALUES ('120', '5120', '4', 'Available');
INSERT INTO `rooms` VALUES ('121', '5121', '4', 'Available');
INSERT INTO `rooms` VALUES ('122', '5122', '4', 'Available');
INSERT INTO `rooms` VALUES ('123', '5123', '4', 'Available');
INSERT INTO `rooms` VALUES ('124', '5124', '4', 'Available');
INSERT INTO `rooms` VALUES ('125', '5125', '4', 'Available');
INSERT INTO `rooms` VALUES ('126', '5126', '4', 'Available');
INSERT INTO `rooms` VALUES ('127', '5127', '4', 'Available');
INSERT INTO `rooms` VALUES ('128', '5128', '4', 'Available');
INSERT INTO `rooms` VALUES ('129', '5129', '4', 'Available');
INSERT INTO `rooms` VALUES ('130', '5130', '4', 'Available');
INSERT INTO `rooms` VALUES ('131', '5131', '4', 'Available');
INSERT INTO `rooms` VALUES ('132', '5132', '4', 'Available');
INSERT INTO `rooms` VALUES ('133', '5133', '4', 'Available');
INSERT INTO `rooms` VALUES ('134', '5134', '4', 'Available');
INSERT INTO `rooms` VALUES ('135', '5135', '4', 'Available');
INSERT INTO `rooms` VALUES ('136', '5136', '4', 'Available');
INSERT INTO `rooms` VALUES ('137', '5137', '4', 'Available');
INSERT INTO `rooms` VALUES ('138', '5138', '4', 'Available');
INSERT INTO `rooms` VALUES ('139', '5139', '4', 'Available');
INSERT INTO `rooms` VALUES ('140', '5140', '4', 'Available');
INSERT INTO `rooms` VALUES ('141', '5141', '4', 'Available');
INSERT INTO `rooms` VALUES ('142', '5142', '4', 'Available');
INSERT INTO `rooms` VALUES ('143', '5143', '4', 'Available');
INSERT INTO `rooms` VALUES ('144', '5144', '4', 'Available');
INSERT INTO `rooms` VALUES ('145', '5145', '4', 'Available');
INSERT INTO `rooms` VALUES ('146', '5146', '4', 'Available');
INSERT INTO `rooms` VALUES ('147', '5147', '4', 'Available');
INSERT INTO `rooms` VALUES ('148', '5148', '4', 'Available');
INSERT INTO `rooms` VALUES ('149', '5149', '4', 'Available');
INSERT INTO `rooms` VALUES ('150', '5150', '4', 'Available');
INSERT INTO `rooms` VALUES ('151', '5151', '4', 'Available');
INSERT INTO `rooms` VALUES ('152', '5152', '4', 'Available');
INSERT INTO `rooms` VALUES ('153', '5153', '4', 'Available');
INSERT INTO `rooms` VALUES ('154', '5154', '4', 'Available');
INSERT INTO `rooms` VALUES ('155', '5155', '4', 'Available');
INSERT INTO `rooms` VALUES ('156', '5156', '4', 'Available');
INSERT INTO `rooms` VALUES ('157', '5157', '4', 'Available');
INSERT INTO `rooms` VALUES ('158', '5158', '4', 'Available');
INSERT INTO `rooms` VALUES ('159', '5159', '4', 'Available');
INSERT INTO `rooms` VALUES ('160', '5160', '4', 'Available');
INSERT INTO `rooms` VALUES ('161', '5161', '4', 'Available');
INSERT INTO `rooms` VALUES ('162', '5162', '4', 'Available');
INSERT INTO `rooms` VALUES ('163', '5163', '4', 'Available');
INSERT INTO `rooms` VALUES ('164', '5164', '4', 'Available');
INSERT INTO `rooms` VALUES ('165', '5165', '4', 'Available');
INSERT INTO `rooms` VALUES ('166', '5166', '4', 'Available');
INSERT INTO `rooms` VALUES ('167', '5167', '4', 'Available');
INSERT INTO `rooms` VALUES ('168', '5168', '4', 'Available');
INSERT INTO `rooms` VALUES ('169', '5169', '4', 'Available');

-- ----------------------------
-- Table structure for `roomtype`
-- ----------------------------
DROP TABLE IF EXISTS `roomtype`;
CREATE TABLE `roomtype` (
  `TypeID` smallint(6) NOT NULL,
  `typename` varchar(20) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  PRIMARY KEY (`TypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roomtype
-- ----------------------------
INSERT INTO `roomtype` VALUES ('1', 'Single', '1000');
INSERT INTO `roomtype` VALUES ('2', 'Double', '1500');
INSERT INTO `roomtype` VALUES ('3', 'Suit', '2000');
INSERT INTO `roomtype` VALUES ('4', 'Family', '2500');

-- ----------------------------
-- View structure for `cost_view`
-- ----------------------------
DROP VIEW IF EXISTS `cost_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cost_view` AS select `checkroom`.`CheckIn` AS `CheckIn`,((`checkroom`.`outDate` - `checkroom`.`inDate`) * `roomtype`.`Price`) AS `Cost`,`checkroom`.`roomNumb` AS `roomNumb`,`checkroom`.`state` AS `state` from ((`checkroom` join `rooms` on((`rooms`.`numb` = `checkroom`.`roomNumb`))) join `roomtype` on((`roomtype`.`TypeID` = `rooms`.`typeId`))) where (`checkroom`.`state` in ('CUR','out')) ;

-- ----------------------------
-- Function structure for `func_customer`
-- ----------------------------
DROP FUNCTION IF EXISTS `func_customer`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `func_customer`(id int) RETURNS varchar(200) CHARSET utf8
    READS SQL DATA
begin
declare rslt varchar(200);
set rslt = (select concat_ws(space(1), lastname, firstname) from customers 
where custID = id);

return rslt;
end
;;
DELIMITER ;
