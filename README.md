# jwt_security


CREATE TABLE `member` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `role` enum('USER','ADMIN') NOT NULL DEFAULT 'USER',
  `password` varchar(250) NOT NULL,
  `registDatetime` datetime(2) NOT NULL DEFAULT current_timestamp(2),
  `updateDatetime` datetime(2) DEFAULT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
