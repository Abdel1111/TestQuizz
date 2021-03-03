CREATE TABLE IF NOT EXISTS `event_log` (
  `id` varchar(45) unsigned NOT NULL,
  `duration` int(10) varchar(45) DEFAULT NULL,
  `host` varchar(45) DEFAULT NULL,
  `type_log` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;