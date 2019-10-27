DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS video;

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_alt` datetime NOT NULL,
  `dat_ins` datetime NOT NULL,
  `login` varchar(150) NOT NULL,
  `perfil` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_LOGIN` (`login`)
);

CREATE TABLE `video` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_alt` datetime NOT NULL,
  `dat_ins` datetime NOT NULL,
  `data` datetime NOT NULL,
  `duracao` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`) 
);
