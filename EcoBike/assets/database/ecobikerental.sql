-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 31, 2021 lúc 03:09 AM
-- Phiên bản máy phục vụ: 10.4.14-MariaDB
-- Phiên bản PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `ecobikerental`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bike`
--

CREATE TABLE `bike` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `type` varchar(45) NOT NULL,
  `weight` int(11) NOT NULL,
  `license_plate` varchar(45) NOT NULL,
  `manuafacturing_date` date NOT NULL,
  `cost` int(11) NOT NULL,
  `producer` varchar(150) NOT NULL,
  `battery` int(11) NOT NULL,
  `load_cycles` int(11) DEFAULT NULL,
  `time_remaining` int(11) DEFAULT NULL,
  `id_station` int(11) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `bike`
--

INSERT INTO `bike` (`id`, `name`, `type`, `weight`, `license_plate`, `manuafacturing_date`, `cost`, `producer`, `battery`, `load_cycles`, `time_remaining`, `id_station`, `status`) VALUES
(1, 'Xe đạp địa hình 24MTB_ACTION ', 'standard', 2, 'STD-01', '2019-12-14', 100000, 'ACTION', 0, NULL, NULL, 1, 0),
(2, 'Xe đạp địa hình 24MTB_ACTION ', 'electric', 2, 'E-02', '2019-12-14', 100000, 'ACTION', 0, NULL, NULL, 2, 0),
(3, 'Xe đạp địa hình 24MTB_ACTION ', 'twin', 2, 'TW-01', '2019-12-14', 100000, 'ACTION', 0, 0, 0, 1, 1),
(4, 'Xe đạp địa hình 24MTB_ACTION ', 'electric', 2, 'E-01', '2019-12-14', 100000, 'ACTION', 0, NULL, NULL, 3, 0),
(5, 'Xe đạp địa hình 24MTB_ACTION ', 'electric', 2, 'E-01', '2019-12-14', 100000, 'ACTION', 0, NULL, NULL, 4, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `id_bike` int(11) NOT NULL,
  `deposit_card_number` varchar(150) NOT NULL,
  `payment_card_number` varchar(150) DEFAULT NULL,
  `id_station_rent` int(11) NOT NULL,
  `id_station_return` int(11) DEFAULT NULL,
  `rent_time` timestamp NULL DEFAULT NULL,
  `return_time` timestamp NULL DEFAULT current_timestamp(),
  `deposit_price` int(11) NOT NULL,
  `rent_price` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `payment`
--

INSERT INTO `payment` (`id`, `id_bike`, `deposit_card_number`, `payment_card_number`, `id_station_rent`, `id_station_return`, `rent_time`, `return_time`, `deposit_price`, `rent_price`, `status`) VALUES
(4, 1, '10', NULL, 1, NULL, '2021-12-11 17:00:00', '2021-12-29 02:55:26', 400, NULL, 0),
(5, 1, 'kscq2_group19_2021', NULL, 1, NULL, '2021-12-29 17:00:00', '2021-12-30 02:53:09', 550000, NULL, 1),
(6, 1, '1', NULL, 1, NULL, '2020-12-11 17:00:01', '2021-12-30 02:58:40', 1000, NULL, 1),
(7, 1, 'kscq2_group19_2021', NULL, 1, NULL, '2021-12-29 17:00:00', '2021-12-30 03:16:57', 550000, NULL, 1),
(8, 1, 'kscq2_group19_2021', NULL, 1, NULL, '2021-12-29 17:00:00', '2021-12-30 03:19:48', 550000, NULL, 1),
(9, 1, 'kscq2_group19_2021', NULL, 1, NULL, '2021-12-29 17:00:00', '2021-12-30 03:27:51', 550000, NULL, 1),
(10, 1, 'kscq2_group19_2021', NULL, 1, NULL, '2021-12-29 17:00:00', '2021-12-30 03:28:41', 550000, NULL, 1),
(11, 1, '1', NULL, 1, NULL, '2021-12-12 03:40:00', '2021-12-30 03:40:46', 1000, NULL, 1),
(12, 1, '1', NULL, 1, NULL, '2021-12-12 03:44:55', '2021-12-30 03:45:59', 1, NULL, 1),
(13, 1, 'kscq2_group19_2021', NULL, 1, NULL, '2021-12-30 03:46:55', '2021-12-30 03:46:55', 550000, NULL, 1),
(14, 1, 'kscq2_group19_2021', NULL, 1, NULL, '2021-12-30 03:49:27', '2021-12-30 03:49:27', 550000, NULL, 1),
(15, 1, 'kscq2_group19_2021', NULL, 1, NULL, '2021-12-30 03:54:11', '2021-12-30 03:54:11', 400000, NULL, 1),
(16, 3, 'kscq2_group19_2021', NULL, 1, NULL, '2021-12-30 04:01:01', '2021-12-30 04:01:01', 550000, NULL, 1),
(17, 3, 'kscq2_group19_2021', NULL, 1, NULL, '2021-12-30 07:33:06', '2021-12-30 07:33:06', 550000, NULL, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `station`
--

CREATE TABLE `station` (
  `id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `address` varchar(128) NOT NULL,
  `num_of_bikes` int(11) NOT NULL,
  `num_of_ebikes` int(11) NOT NULL,
  `num_of_twinbikes` int(11) NOT NULL,
  `num_of_empty_docks` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `station`
--

INSERT INTO `station` (`id`, `name`, `address`, `num_of_bikes`, `num_of_ebikes`, `num_of_twinbikes`, `num_of_empty_docks`) VALUES
(1, 'HUST', '1 Dai Co Viet, Hai Ba Trung, Ha Noi', 20, 20, 20, 20),
(2, 'UET', '144 Xuan Thuy, Dich Vong Hau, Cau Giay, Ha Noi', 25, 20, 20, 20),
(3, 'FPT', 'Hoa Lac, Thach That, Ha Noi', 30, 30, 30, 30),
(4, 'PTIT', '122 Hoang Quoc Viet, Cau Giay, Ha Noi', 35, 20, 35, 0),
(5, 'NEU', '207 Giai Phong, Dong Tam, Ha Noi', 30, 20, 20, 0),
(6, 'FTU', 'Chua Lang, Dong Da, Ha Noi', 25, 25, 25, 25),
(7, 'HMU', '1 Ton That Tung, Dong Da, Ha Noi', 20, 30, 30, 30),
(8, 'HLU', '87 Nguyen Chi Thanh, Thanh Cong, Dong Da, Ha Noi', 25, 35, 35, 35),
(9, 'ULIS', 'Pham Van Dong, Dich Vong Hau, Cau Giay, Ha Noi', 30, 30, 30, 30),
(10, 'HNUE', '144 Xuan Thuy, Dich Vong Hau, Cau Giay, Ha Noi', 35, 20, 30, 20),
(11, 'MTA', '236 Hoang Quoc Viet, Co Nhue, Bac Tu Liem, Ha Noi', 30, 20, 20, 20),
(12, 'HUMG', '18 Pho Vien, Duc Thang, Bac Tu Liem, Ha Noi', 25, 20, 20, 20);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bike`
--
ALTER TABLE `bike`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_station` (`id_station`);

--
-- Chỉ mục cho bảng `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_station_rent` (`id_station_rent`),
  ADD KEY `id_station_return` (`id_station_return`),
  ADD KEY `id_bike` (`id_bike`);

--
-- Chỉ mục cho bảng `station`
--
ALTER TABLE `station`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `bike`
--
ALTER TABLE `bike`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT cho bảng `station`
--
ALTER TABLE `station`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `bike`
--
ALTER TABLE `bike`
  ADD CONSTRAINT `fk_bike_station` FOREIGN KEY (`id_station`) REFERENCES `station` (`id`);

--
-- Các ràng buộc cho bảng `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `fk1_payment_station` FOREIGN KEY (`id_station_rent`) REFERENCES `station` (`id`),
  ADD CONSTRAINT `fk2_payment_station` FOREIGN KEY (`id_station_return`) REFERENCES `station` (`id`),
  ADD CONSTRAINT `fk_payment_bike` FOREIGN KEY (`id_bike`) REFERENCES `bike` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
