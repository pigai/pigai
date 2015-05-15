/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : pigai

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2015-05-16 01:51:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `courseName` varchar(50) NOT NULL,
  `courseIntr` text,
  `teacherId` int(11) unsigned zerofill NOT NULL,
  `createTime` datetime NOT NULL,
  `teacherName` varchar(50) NOT NULL,
  `finished` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`courseId`),
  KEY `course_teacherId` (`teacherId`),
  CONSTRAINT `course_teacherId` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`teacherId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for courseware
-- ----------------------------
DROP TABLE IF EXISTS `courseware`;
CREATE TABLE `courseware` (
  `coursewareId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `courseId` int(11) unsigned zerofill NOT NULL,
  `createTime` datetime NOT NULL,
  `coursewareName` varchar(255) NOT NULL,
  `fileId` int(11) unsigned zerofill NOT NULL,
  PRIMARY KEY (`coursewareId`),
  KEY `courseware_courseId` (`courseId`),
  KEY `courseware_fileId` (`fileId`),
  CONSTRAINT `courseware_fileId` FOREIGN KEY (`fileId`) REFERENCES `fileinfo` (`fileId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `courseware_courseId` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='课件';

-- ----------------------------
-- Table structure for fileinfo
-- ----------------------------
DROP TABLE IF EXISTS `fileinfo`;
CREATE TABLE `fileinfo` (
  `fileId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `fileName` varchar(255) NOT NULL,
  `filePath` varchar(255) NOT NULL,
  `fileSize` double DEFAULT NULL,
  `fileType` varchar(255) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`fileId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='上传文件';

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `homeworkId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `introduction` varchar(50) NOT NULL,
  `courseId` int(11) unsigned zerofill NOT NULL,
  `score` int(11) NOT NULL DEFAULT '0',
  `deadline` datetime NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`homeworkId`),
  KEY `homework_courseId` (`courseId`),
  CONSTRAINT `homework_courseId` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `managerId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`managerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for selectcourse
-- ----------------------------
DROP TABLE IF EXISTS `selectcourse`;
CREATE TABLE `selectcourse` (
  `selectId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `courseId` int(11) unsigned zerofill NOT NULL,
  `studentId` int(11) unsigned zerofill NOT NULL,
  `grade` int(11) DEFAULT '0',
  `createTime` datetime NOT NULL,
  `courseName` varchar(255) NOT NULL,
  PRIMARY KEY (`selectId`),
  KEY `select_courseId` (`courseId`),
  KEY `select_studentId` (`studentId`),
  CONSTRAINT `select_studentId` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `select_courseId` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `studentId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `studentNo` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '真实姓名',
  `school` varchar(50) NOT NULL,
  `college` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`studentId`),
  KEY `studentId` (`studentId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for submitrecord
-- ----------------------------
DROP TABLE IF EXISTS `submitrecord`;
CREATE TABLE `submitrecord` (
  `submitId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `homeworkId` int(11) unsigned zerofill NOT NULL,
  `fileId` int(11) unsigned zerofill NOT NULL,
  `studentId` int(11) unsigned zerofill NOT NULL,
  `score` int(11) NOT NULL DEFAULT '0',
  `createTime` datetime NOT NULL,
  `studentName` varchar(255) NOT NULL,
  `correct` tinyint(1) NOT NULL,
  PRIMARY KEY (`submitId`),
  KEY `submit_homeworkId` (`homeworkId`),
  KEY `submit_fileId` (`fileId`),
  KEY `submit_studentId` (`studentId`),
  CONSTRAINT `submit_studentId` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `submit_fileId` FOREIGN KEY (`fileId`) REFERENCES `fileinfo` (`fileId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `submit_homeworkId` FOREIGN KEY (`homeworkId`) REFERENCES `homework` (`homeworkId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacherId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `teacherNo` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `school` varchar(50) NOT NULL,
  `college` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`teacherId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
