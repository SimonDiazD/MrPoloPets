-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mrpolopets
-- ------------------------------------------------------
-- Server version	8.0.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` varchar(50) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `direccion_contacto` varchar(150) DEFAULT NULL,
  `numero_contacto` varchar(50) DEFAULT NULL,
  `id_mascotas_cargo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES ('C001','Ana García','Av. Siempre Viva 123','3001234567','M001'),('C002','Carlos Ruiz','Calle 45 #20-10','3109876543','M002'),('C003','Maria Lopez','Carrera 10 #5-8','3205551122','M003, M004'),('C004','Pedro Pérez','Carrera 70 #32-15','3114445566','M001'),('C005','Laura Gómez','Calle 100 #15-20','3001112233','M004'),('C006','Diego Torres','Carrera 7 #45-10','3159988776','M005'),('C007','Sofía Vergara','Avenida El Dorado #26','3201239876','M006'),('C009','Shakira Vegana','Copacabana','3201239876','M005');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;

--
-- Table structure for table `consultas`
--

DROP TABLE IF EXISTS `consultas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultas` (
  `id_consulta` varchar(50) NOT NULL,
  `fecha` date DEFAULT NULL,
  `sintomas` text,
  `tratamiento` text,
  `id_mascota` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_consulta`),
  KEY `id_mascota` (`id_mascota`),
  CONSTRAINT `consultas_ibfk_1` FOREIGN KEY (`id_mascota`) REFERENCES `mascotas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultas`
--

/*!40000 ALTER TABLE `consultas` DISABLE KEYS */;
INSERT INTO `consultas` VALUES ('CS01','2025-11-20','Vómito y mareo','Suero oral y dieta blanda por 3 días','M001'),('CS02','2025-11-25','Chequeo general','Vacuna de refuerzo aplicada','M002'),('CS03','2025-11-26','Cojera pata trasera','Radiografía y antiinflamatorios','M001');
/*!40000 ALTER TABLE `consultas` ENABLE KEYS */;

--
-- Table structure for table `mascotas`
--

DROP TABLE IF EXISTS `mascotas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mascotas` (
  `id` varchar(50) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `raza` varchar(100) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `vacunas` varchar(255) DEFAULT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `peso` varchar(20) DEFAULT NULL,
  `esta_adoptado` tinyint(1) DEFAULT '0',
  `lugar_origen` varchar(100) DEFAULT NULL,
  `genero` varchar(20) DEFAULT NULL,
  `fecha_ingreso` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mascotas`
--

/*!40000 ALTER TABLE `mascotas` DISABLE KEYS */;
INSERT INTO `mascotas` VALUES ('M001','Firulais','Labrador',3,'Todas','Perro','25kg',0,'Refugio Central','Macho','2023-10-01'),('M002','Michi','Siamés',2,'Rabia','Gato','4kg',1,'Calle','Hembra','2023-11-15'),('M003','Rex','Pastor Alemán',5,'Completas','Perro','30kg',0,'Abandono','Macho','2023-09-20');
/*!40000 ALTER TABLE `mascotas` ENABLE KEYS */;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas` (
  `id_venta` varchar(50) NOT NULL,
  `fecha` date DEFAULT NULL,
  `valor_venta` decimal(10,2) DEFAULT NULL,
  `id_cliente` varchar(50) DEFAULT NULL,
  `id_mascota` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_mascota` (`id_mascota`),
  CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`),
  CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`id_mascota`) REFERENCES `mascotas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES ('V001','2025-11-20',150000.00,'C001','M001'),('V002','2025-11-21',45000.50,'C002','M002'),('V003','2025-11-22',80000.00,'C001','M001');
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;

--
-- Dumping routines for database 'mrpolopets'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-02 15:30:12
