CREATE TABLE `video` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_alt` datetime NOT NULL,
  `dat_ins` datetime NOT NULL,
  `data` datetime NOT NULL,
  `duracao` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
