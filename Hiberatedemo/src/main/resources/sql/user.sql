SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` int AUTO_INCREMENT PRIMARY KEY ,
   `sno` varchar(100) NOT NULL DEFAULT '',
   `name` varchar(100) NOT NULL,
   `sex` varchar(8) DEFAULT NULL,
   `age` int(3) DEFAULT NULL,
   `dept_no` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;