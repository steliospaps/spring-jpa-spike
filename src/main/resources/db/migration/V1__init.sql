CREATE TABLE `ITEM` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uniq` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq` (`uniq`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;