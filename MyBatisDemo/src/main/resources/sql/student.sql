SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
                           `sno` varchar(100) NOT NULL DEFAULT '',
                           `name` varchar(100) NOT NULL,
                           `sex` varchar(8) DEFAULT NULL,
                           `age` int(3) DEFAULT NULL,
                           `dept_no` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `student` VALUES ('20171508', '李勇', '男', '20', '2601');
INSERT INTO `student` VALUES ('20171509', '刘娟', '女', '19', '2602');


ALTER TABLE `student` ADD id INT AUTO_INCREMENT PRIMARY KEY;