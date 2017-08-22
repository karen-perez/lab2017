CREATE DATABASE `tpi_g6`;
USE tpi_g6;
CREATE TABLE `localidades` 
( `idLocalidad` int(11) NOT NULL AUTO_INCREMENT,
  `Localidad` varchar(45) NOT NULL,
    PRIMARY KEY (`idLocalidad`)

);

CREATE TABLE `tipoContacto` 
( `idContacto` int(11) NOT NULL AUTO_INCREMENT,
  `TipoContacto` varchar(45) NOT NULL,
    PRIMARY KEY (`idContacto`)

);


CREATE TABLE `personas` 
(
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
   `Apellido` varchar(45) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `Mail` varchar(50) NOT NULL,
  `FechaNac` DATE NOT NULL,
  `Calle` varchar(50) NOT NULL,
  `Altura` varchar(8) NOT NULL,
  `Piso` varchar(3),
  `Depto` varchar(3),
  `idLocalidad` INT NOT NULL,
  `IdContacto` INT NOT NULL,
  
  PRIMARY KEY (`idPersona`),
  FOREIGN KEY (`idLocalidad`) REFERENCES localidades(`idLocalidad`),
  FOREIGN KEY (`idContacto`) REFERENCES tipoContacto(`idContacto`)

);


INSERT INTO tipoContacto(TipoContacto) VALUES('TRABAJO');
INSERT INTO tipoContacto(TipoContacto) VALUES('FAMILIA');
INSERT INTO tipoContacto(TipoContacto) VALUES('AMIGOS');
INSERT INTO tipoContacto(TipoContacto) VALUES('UNIVERSIDAD');
INSERT INTO tipoContacto(TipoContacto) VALUES('FAVORITOS');