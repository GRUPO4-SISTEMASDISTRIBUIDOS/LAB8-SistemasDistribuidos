/*
Created: 15/06/2024
Modified: 15/06/2024
Model: MySQL 8.0
Database: MySQL 8.0
*/

-- Create tables section -------------------------------------------------

-- Table Departamentos

CREATE TABLE `Departamentos`
(
  `IdDepartamento` Bigint NOT NULL,
  `Nombre` Char(200) NOT NULL,
  `Telefono` Varchar(20),
  `Fax` Varchar(20)
)
;

ALTER TABLE `Departamentos` ADD PRIMARY KEY (`IdDepartamento`)
;

-- Table Ingenieros

CREATE TABLE `Ingenieros`
(
  `IdIngeniero` Int NOT NULL,
  `Nombre` Char(50) NOT NULL,
  `Especialidad` Char(100),
  `Cargo` Char(100)
)
;

ALTER TABLE `Ingenieros` ADD PRIMARY KEY (`IdIngeniero`)
;

-- Table Proyectos

CREATE TABLE `Proyectos`
(
  `IdProyecto` Int NOT NULL,
  `Nombre` Char(200) NOT NULL,
  `FechaInicio` Date NOT NULL,
  `FechaFin` Date,
  `IdDepartamento` Bigint
)
;

CREATE INDEX `IX_Relationship1` ON `Proyectos` (`IdDepartamento`)
;

ALTER TABLE `Proyectos` ADD PRIMARY KEY (`IdProyecto`)
;

-- Table Asignaciones

CREATE TABLE `Asignaciones`
(
  `IdProyecto` Int NOT NULL,
  `IdIngeniero` Int NOT NULL,
  `FechaAsignacion` Date NOT NULL
)
;

ALTER TABLE `Asignaciones` ADD PRIMARY KEY (`IdProyecto`, `IdIngeniero`)
;

-- Create foreign keys (relationships) section -------------------------------------------------

ALTER TABLE `Proyectos` ADD CONSTRAINT `Relationship1` FOREIGN KEY (`IdDepartamento`) REFERENCES `Departamentos` (`IdDepartamento`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Asignaciones` ADD CONSTRAINT `Relationship2` FOREIGN KEY (`IdProyecto`) REFERENCES `Proyectos` (`IdProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Asignaciones` ADD CONSTRAINT `Relationship3` FOREIGN KEY (`IdIngeniero`) REFERENCES `Ingenieros` (`IdIngeniero`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

