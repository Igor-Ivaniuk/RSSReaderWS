DROP TABLE IF EXISTS `rss`.`news_entry`;
DROP TABLE IF EXISTS `rss`.`news_channel`;

CREATE TABLE  `rss`.`news_channel` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `link` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


CREATE TABLE  `rss`.`news_entry` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `rating` int(10) unsigned NOT NULL,
  `datePublished` datetime NOT NULL,
  `text` text,
  `channel` int(10) unsigned DEFAULT NULL,
  `link` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `channel` (`channel`),
  CONSTRAINT `channel` FOREIGN KEY (`channel`) REFERENCES `news_channel` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=301 DEFAULT CHARSET=utf8;