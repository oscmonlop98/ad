-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 14-02-2020 a las 00:10:06
-- Versión del servidor: 8.0.17
-- Versión de PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbprueba`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL,
  `contrasenya` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `contrasenya`, `nombre`, `usuario`) VALUES
(1, 'oscar', 'Oscar', NULL),
(2, 'pepe', 'pepe', NULL),
(13, 'admin', 'Admin', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `director`
--

CREATE TABLE `director` (
  `id` bigint(20) NOT NULL,
  `edad` int(11) NOT NULL,
  `nacionalidad` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE `genero` (
  `id` bigint(20) NOT NULL,
  `genero` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id` bigint(20) NOT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `importe` decimal(19,2) DEFAULT NULL,
  `cliente` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id`, `fecha`, `importe`, `cliente`) VALUES
(1, '2020-02-13 09:45:16.355950', '30.00', 1),
(15, '2020-02-13 13:39:48.599171', '27.00', 2),
(16, '2020-02-14 08:09:49.874594', '126.00', 13);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidolinea`
--

CREATE TABLE `pedidolinea` (
  `id` bigint(20) NOT NULL,
  `importe` decimal(19,2) DEFAULT NULL,
  `precio` decimal(19,2) DEFAULT NULL,
  `unidades` decimal(19,2) DEFAULT NULL,
  `pedido` bigint(20) DEFAULT NULL,
  `articulo` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pedidolinea`
--

INSERT INTO `pedidolinea` (`id`, `importe`, `precio`, `unidades`, `pedido`, `articulo`) VALUES
(1, '30.00', '15.00', '2.00', 1, 1),
(18, '15.00', '15.00', '1.00', 15, 1),
(19, '12.00', '12.00', '1.00', 15, 3),
(20, '15.00', '15.00', '1.00', 16, 1),
(21, '12.00', '12.00', '1.00', 16, 3),
(22, '99.00', '99.00', '1.00', 16, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pelicula`
--

CREATE TABLE `pelicula` (
  `id` bigint(20) NOT NULL,
  `director` varchar(255) DEFAULT NULL,
  `duracion` varchar(255) DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `precio` decimal(19,2) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pelicula`
--

INSERT INTO `pelicula` (`id`, `director`, `duracion`, `genero`, `precio`, `titulo`) VALUES
(1, 'Christopher Nolan', '112', 'Ciencia Ficcion', '15.00', 'Interestelar'),
(3, 'asd', '222', 'asdasd', '12.00', 'titanic'),
(4, 'Angeles', '200', 'Miedo', '99.00', 'Hobbit');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `director`
--
ALTER TABLE `director`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKms2p9jd0eqq4erc8y3hr91ouv` (`cliente`);

--
-- Indices de la tabla `pedidolinea`
--
ALTER TABLE `pedidolinea`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7kpyp9916h7qokkar19fq2dpu` (`pedido`),
  ADD KEY `FKpd9hph89vnah26fxil0ab06p6` (`articulo`);

--
-- Indices de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `director`
--
ALTER TABLE `director`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `genero`
--
ALTER TABLE `genero`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `pedidolinea`
--
ALTER TABLE `pedidolinea`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FKms2p9jd0eqq4erc8y3hr91ouv` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`id`);

--
-- Filtros para la tabla `pedidolinea`
--
ALTER TABLE `pedidolinea`
  ADD CONSTRAINT `FK7kpyp9916h7qokkar19fq2dpu` FOREIGN KEY (`pedido`) REFERENCES `pedido` (`id`),
  ADD CONSTRAINT `FKpd9hph89vnah26fxil0ab06p6` FOREIGN KEY (`articulo`) REFERENCES `pelicula` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
