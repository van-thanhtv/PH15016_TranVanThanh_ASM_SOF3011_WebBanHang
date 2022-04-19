-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 20, 2022 at 12:30 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Websales`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `nameCategory` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `nameCategory`) VALUES
(2, 'Áo'),
(3, 'Quần'),
(4, 'Giày');

-- --------------------------------------------------------

--
-- Table structure for table `color`
--

CREATE TABLE `color` (
  `id` int(11) NOT NULL,
  `nameColor` varchar(20) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `color`
--

INSERT INTO `color` (`id`, `nameColor`) VALUES
(2, 'Red'),
(3, 'Red'),
(4, 'Black');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `address` varchar(100) CHARACTER SET utf8 NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 NOT NULL,
  `birthday` date NOT NULL,
  `numberPhone` varchar(20) CHARACTER SET utf8 NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1,
  `sex` int(11) NOT NULL DEFAULT 0,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `address`, `email`, `birthday`, `numberPhone`, `status`, `sex`, `name`) VALUES
(1, 'Nghe an', 'thanhtvph15015@gmail.com', '2002-10-10', '0385613085', 0, 1, 'Trằn Văn Thành'),
(4, 'Quảng Ninh', 'thanhtvph15016@fpt.edu.vn', '0202-02-01', '0385613084', 0, 1, 'Phạm Đức Mạnh 2');

-- --------------------------------------------------------

--
-- Table structure for table `Orders`
--

CREATE TABLE `Orders` (
  `id` int(11) NOT NULL,
  `id_users` int(11) DEFAULT NULL,
  `id_customer` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT 0,
  `dateCreate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Orders`
--

INSERT INTO `Orders` (`id`, `id_users`, `id_customer`, `status`, `dateCreate`) VALUES
(2, NULL, 1, 2, '2022-04-12'),
(3, NULL, 1, 1, '2022-04-12'),
(4, NULL, 1, 2, '2022-04-12'),
(5, NULL, 1, 2, '2022-04-14'),
(6, NULL, 1, 1, '2022-04-14'),
(7, NULL, 1, 1, '2022-04-14'),
(8, NULL, 4, 0, '2022-04-14'),
(9, NULL, 4, 0, '2022-04-14'),
(10, NULL, 1, 0, '2022-04-19'),
(11, NULL, 1, 2, '2022-04-19');

-- --------------------------------------------------------

--
-- Table structure for table `ordersDetail`
--

CREATE TABLE `ordersDetail` (
  `id` int(11) NOT NULL,
  `id_Orders` int(11) NOT NULL,
  `id_productDetails` int(11) DEFAULT NULL,
  `quantily` int(11) NOT NULL DEFAULT 1,
  `price` double DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ordersDetail`
--

INSERT INTO `ordersDetail` (`id`, `id_Orders`, `id_productDetails`, `quantily`, `price`, `status`) VALUES
(3, 2, 3, 2, 68, 0),
(4, 2, 4, 1, 500000, 0),
(5, 3, 3, 2, 30000, 0),
(6, 4, 11, 2, 86, 0),
(7, 5, 6, 2, 1000000, 0),
(8, 6, 2, 2, 2600, 0),
(9, 7, 2, 3, 3900, 0),
(10, 7, 3, 1, 15000, 0),
(11, 8, 2, 3, 3900, 0),
(12, 8, 3, 1, 15000, 0),
(13, 9, 6, 1, 500000, 0),
(14, 10, 2, 1, 1300, 0),
(15, 11, 2, 2, 2600, 0);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `nameProduct` varchar(50) CHARACTER SET utf8 NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0,
  `id_category` int(11) NOT NULL,
  `describes` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `img` varchar(100) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `nameProduct`, `status`, `id_category`, `describes`, `img`) VALUES
(2, 'Áo gió', 0, 2, '', 'ao1.jpeg'),
(3, 'Áo khoác', 0, 2, '', 'khoac2.jpeg'),
(4, 'Quần âu', 0, 3, '', 'quan1.jpeg'),
(5, 'Giày nike', 0, 4, '', 'giaynik1.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `productDetails`
--

CREATE TABLE `productDetails` (
  `id` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `id_size` int(11) NOT NULL,
  `id_color` int(11) NOT NULL,
  `id_provided` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0,
  `price` double NOT NULL,
  `Amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `productDetails`
--

INSERT INTO `productDetails` (`id`, `id_product`, `id_size`, `id_color`, `id_provided`, `status`, `price`, `Amount`) VALUES
(2, 2, 3, 2, 2, 0, 1300, 0),
(3, 3, 1, 2, 1, 0, 15000, 0),
(4, 4, 2, 4, 3, 0, 34, 0),
(6, 5, 8, 2, 4, 0, 500000, 0),
(11, 4, 3, 2, 4, 0, 43, 1);

-- --------------------------------------------------------

--
-- Table structure for table `provided`
--

CREATE TABLE `provided` (
  `id` int(11) NOT NULL,
  `address` varchar(50) CHARACTER SET utf8 NOT NULL,
  `nameProvided` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `provided`
--

INSERT INTO `provided` (`id`, `address`, `nameProvided`) VALUES
(1, 'Hà Nội', 'ELISE'),
(2, 'Hải Dương', 'VASCARA'),
(3, 'Hải Phòng', 'JUNO'),
(4, 'Nin Bình', 'GUMAC'),
(5, 'Thanh Hóa', 'IVY MODA'),
(6, 'Mỹ', 'Nike'),
(8, 'Khu ba', 'nai ki');

-- --------------------------------------------------------

--
-- Table structure for table `size`
--

CREATE TABLE `size` (
  `id` int(11) NOT NULL,
  `nameSize` varchar(10) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `size`
--

INSERT INTO `size` (`id`, `nameSize`) VALUES
(8, '41'),
(9, '43'),
(3, 'L'),
(4, 'Thanh ne'),
(5, 'Thanh oiu'),
(1, 'X'),
(2, 'XL');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 NOT NULL,
  `passwordUser` varchar(200) CHARACTER SET utf8 NOT NULL,
  `numberPhone` varchar(20) CHARACTER SET utf8 NOT NULL,
  `sex` int(11) NOT NULL DEFAULT 0,
  `birthday` date NOT NULL,
  `address` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `avatar` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `isAdmin` tinyint(1) NOT NULL DEFAULT 0,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `passwordUser`, `numberPhone`, `sex`, `birthday`, `address`, `avatar`, `isAdmin`, `status`) VALUES
(1, 'Trằn Văn Thành', 'vanthanh10012k@gmail.com', '$2a$10$WLVSJCZ.84jtncApbBE4WO7F2pq/IG./nuYo5YIkbzLmJWZoD3wsW', '0385613085', 1, '2002-01-10', 'Nghe an', 'giaynik1.jpeg', 1, 0),
(13, 'Phạm Đức Mạnh 2', 'thanhtvph15015@gmail.com', '$2a$10$SY1Gd.0iA55QdFJVU6Hdsu0OkjUcGRitH2qntcNSmsgIEevsmQBoa', '1212212', 1, '2002-12-21', 'Quảng Ninh', 'Screenshot_20220330_135900.png', 0, 0),
(14, 'Trằn Văn Thành2', 'thanhtvph15016@fpt.edu.vn', '$2a$10$adIHUmxVHsaWJogkcNofbueppNUJL85UEkHR/KjYY9gnLO2Pjvty6', '12312314141', 1, '2002-10-20', 'Nghe an', 'khoac4.jpeg', 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `color`
--
ALTER TABLE `color`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `customer_numberPhone_uindex` (`numberPhone`);

--
-- Indexes for table `Orders`
--
ALTER TABLE `Orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Orders_customer_id_fk` (`id_customer`),
  ADD KEY `Orders_users_id_fk` (`id_users`);

--
-- Indexes for table `ordersDetail`
--
ALTER TABLE `ordersDetail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ordersDetail_Orders_id_fk` (`id_Orders`),
  ADD KEY `ordersDetail_productDetails_id_fk` (`id_productDetails`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `product_nameProduct_uindex` (`nameProduct`),
  ADD KEY `product_category_id_fk` (`id_category`);

--
-- Indexes for table `productDetails`
--
ALTER TABLE `productDetails`
  ADD PRIMARY KEY (`id`),
  ADD KEY `productDetails_product_id_fk` (`id_product`),
  ADD KEY `productDetails_color_id_fk` (`id_color`),
  ADD KEY `productDetails_provided_id_fk` (`id_provided`),
  ADD KEY `productDetails_size_id_fk` (`id_size`);

--
-- Indexes for table `provided`
--
ALTER TABLE `provided`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `size`
--
ALTER TABLE `size`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `size_nameSize_uindex` (`nameSize`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_numberPhone_uindex` (`numberPhone`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `color`
--
ALTER TABLE `color`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `Orders`
--
ALTER TABLE `Orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `ordersDetail`
--
ALTER TABLE `ordersDetail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `productDetails`
--
ALTER TABLE `productDetails`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51855;

--
-- AUTO_INCREMENT for table `provided`
--
ALTER TABLE `provided`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `size`
--
ALTER TABLE `size`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Orders`
--
ALTER TABLE `Orders`
  ADD CONSTRAINT `Orders_customer_id_fk` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `Orders_users_id_fk` FOREIGN KEY (`id_users`) REFERENCES `users` (`id`);

--
-- Constraints for table `ordersDetail`
--
ALTER TABLE `ordersDetail`
  ADD CONSTRAINT `ordersDetail_Orders_id_fk` FOREIGN KEY (`id_Orders`) REFERENCES `Orders` (`id`),
  ADD CONSTRAINT `ordersDetail_productDetails_id_fk` FOREIGN KEY (`id_productDetails`) REFERENCES `productDetails` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_category_id_fk` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`);

--
-- Constraints for table `productDetails`
--
ALTER TABLE `productDetails`
  ADD CONSTRAINT `productDetails_color_id_fk` FOREIGN KEY (`id_color`) REFERENCES `color` (`id`),
  ADD CONSTRAINT `productDetails_product_id_fk` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `productDetails_provided_id_fk` FOREIGN KEY (`id_provided`) REFERENCES `provided` (`id`),
  ADD CONSTRAINT `productDetails_size_id_fk` FOREIGN KEY (`id_size`) REFERENCES `size` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
