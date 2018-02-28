-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 27, 2017 at 01:52 PM
-- Server version: 10.1.20-MariaDB
-- PHP Version: 7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id155858_user`
--

-- --------------------------------------------------------

--
-- Table structure for table `clock`
--

CREATE TABLE `clock` (
  `employeeId` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `todaysDate` date NOT NULL,
  `startShift` time NOT NULL,
  `endShift` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `clock`
--

INSERT INTO `clock` (`employeeId`, `todaysDate`, `startShift`, `endShift`) VALUES
('mehreen', '2017-04-26', '04:03:24', '04:16:57'),
('raymond', '2017-04-26', '11:37:34', '11:37:34'),
('sam', '2017-04-26', '04:20:16', '04:20:16');

-- --------------------------------------------------------

--
-- Table structure for table `ingredients`
--

CREATE TABLE `ingredients` (
  `IngredientName` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `Type` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `Cal` varchar(5) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `ingredients`
--

INSERT INTO `ingredients` (`IngredientName`, `Type`, `Cal`) VALUES
('Beef', 'Meat', '9'),
('Butter', 'Dairy', '8'),
('Carrot', 'Vegetable', '53'),
('Chicken', 'Meat', '292'),
('Cod', 'Fish', ' 69.8'),
('Cottage Cheese', 'Dairy', '106'),
('Fusilli', 'Pasta', '349'),
('Lettuce', 'Vegetable', '5.2'),
('Linguine', 'Pasta', '210'),
('Milk', 'Dairy', '110'),
('Penne', 'Pasta', '173'),
('Pork', 'Meat', '206'),
('Salmon', 'Fish', '98.8'),
('Shrimp', 'Fish', '6'),
('Tomato', 'Vegetable', '16.2');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `itemID` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Category` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `Name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Ingredients` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Allergy` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Vegan` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `Vegetarian` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `Cal` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `Spicy` int(2) NOT NULL,
  `Servings` int(2) NOT NULL,
  `Price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`itemID`, `Category`, `Name`, `Ingredients`, `Allergy`, `Vegan`, `Vegetarian`, `Cal`, `Spicy`, `Servings`, `Price`) VALUES
('500', 'Starters', 'Butter', '[30 g of Butter]', 'Gluten', 'No', 'No', '240.0', 2, 2, 2),
('item00001', 'Starters', 'Garlic Herb Fries', 'Garlic, Mixed Herbs', 'Gluten - yes, Cheese - yes', 'No', 'No', '468 cal', 0, 1, 4.35),
('item00002', 'Starters', 'Cheesy Garlic Bread', 'Garlic bread, mozzarella cheese', 'Gluten - yes, Cheese - yes', 'No', 'No', '386 cal', 0, 1, 5.55),
('item00003', 'Salad', 'Chicken Caesar salad', 'Chicken, Caeser dressing', 'Anchovy paste, Fish sauce', 'No', 'No', '482 cal', 0, 1, 3.65),
('item00004', 'Salad', 'Southern Salad', 'Sweetcorn, roast bell pepper, red onion, cherry tomatoes', '', 'Yes', 'Yes', '412 cal', 0, 1, 3.65),
('item00005', 'Burgers', 'BBQ burger - Beef', 'Bacon, cheese, BBQ sauce', 'Gluten - yes, Nuts - no', 'No', 'No', '1942 cal', 0, 1, 5.95),
('item00006', 'Pasta', 'Penne Pesto', 'Basil pesto, tomatoes, black olives, pine nuts, parmesan.', 'Gluten - yes, Nuts - yes', 'Yes', 'Yes', '568 cal', 0, 1, 4.35),
('item00007', 'Dessert', 'Sticky Toffee Pudding', 'Toffee', 'Dairy', 'No', 'No', '652 cal', 0, 1, 6.58),
('item00008', 'Soft Drinks', 'Pepsi', '', '', 'Yes', 'Yes', '150 cal', 0, 1, 1),
('Test', 'Starters', 'Test', '[3 cup chopped Carrot]', 'None', 'Yes', 'No', '159.0', 1, 1, 2.4),
('Test 02', 'Starters', 'Burger Test', 'Test, Burger', 'Meat', 'Yes', 'No', '315 Cal', 2, 1, 4.55);

-- --------------------------------------------------------

--
-- Table structure for table `notes`
--

CREATE TABLE `notes` (
  `noteId` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `dateWritten` datetime NOT NULL,
  `employeeId` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `notes`
--

INSERT INTO `notes` (`noteId`, `title`, `content`, `dateWritten`, `employeeId`) VALUES
('1a1dc924', 'five', 'five', '2017-04-15 00:00:13', 'Mehreen'),
('4606c439', 'rms', 'rms', '2017-04-15 19:02:14', 'Mehreen'),
('5e31608c', 'four', 'four', '2017-04-15 00:00:12', 'Mehreen'),
('658c7708', 'six', 'six', '2017-04-15 00:00:14', 'Mehreen'),
('7b72928b', 'test', 'test', '2017-04-26 10:38:48', 'Mehreen'),
('e1bc0e0d', 'gdvh', '', '2017-04-22 13:52:26', 'test'),
('e3b15ff6', 'two', 'two', '2017-04-15 00:00:11', 'Mehreen'),
('fb6fe39b', 'davif', 'daci', '2017-04-22 13:51:26', 'raymond');

-- --------------------------------------------------------

--
-- Table structure for table `orderItems`
--

CREATE TABLE `orderItems` (
  `itemID` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Price` double NOT NULL,
  `datePlaced` datetime NOT NULL,
  `quantity` int(11) NOT NULL,
  `orderId` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

--
-- Dumping data for table `orderItems`
--

INSERT INTO `orderItems` (`itemID`, `Name`, `Price`, `datePlaced`, `quantity`, `orderId`, `status`) VALUES
('item00001', 'Garlic Herb Fries', 4.35, '2017-04-22 00:28:14', 2, '466451eb', 'Pending'),
('item00001', 'Garlic Herb Fries', 4.35, '2017-04-25 22:08:25', 1, '9245fd47', 'Pending'),
('item00001', 'Garlic Herb Fries', 4.35, '2017-04-19 18:26:10', 1, '968b0552', 'Pending'),
('item00001', 'Garlic Herb Fries', 4.35, '2017-03-30 11:04:12', 1, '98ff566f', 'Completed'),
('item00001', 'Garlic Herb Fries', 4.35, '2017-04-26 10:23:13', 1, 'd0c12ec3', 'Completed'),
('item00001', 'Garlic Herb Fries', 4.35, '2017-04-24 17:11:19', 1, 'dbdb977d', 'Completed'),
('item00001', 'Garlic Herb Fries', 4.35, '2017-04-25 23:09:35', 1, 'f8085747', 'Pending'),
('item00002', 'Cheesy Garlic Bread', 5.55, '2017-04-22 00:28:16', 1, '466451eb', 'Pending'),
('item00002', 'Cheesy Garlic Bread', 5.55, '2017-04-25 22:08:26', 1, '9245fd47', 'Pending'),
('item00002', 'Cheesy Garlic Bread', 5.55, '2017-04-19 18:26:10', 1, '968b0552', 'Pending'),
('item00002', 'Cheesy Garlic Bread', 5.55, '2017-03-30 11:04:12', 1, '98ff566f', 'Completed'),
('item00002', 'Cheesy Garlic Bread', 5.55, '2017-04-26 10:23:14', 1, 'd0c12ec3', 'Completed'),
('item00002', 'Cheesy Garlic Bread', 5.55, '2017-04-24 17:11:19', 1, 'dbdb977d', 'Pending'),
('item00002', 'Cheesy Garlic Bread', 5.55, '2017-04-25 23:09:36', 1, 'f8085747', 'Pending'),
('item00003', 'Chicken Caesar salad', 3.65, '2017-04-24 17:11:34', 1, '35986afd', 'Pending'),
('item00003', 'Chicken Caesar salad', 3.65, '2017-04-22 00:07:08', 1, '59bc7f06', 'Pending'),
('item00003', 'Chicken Caesar salad', 3.65, '2017-04-25 22:08:26', 1, '9245fd47', 'Pending'),
('item00003', 'Chicken Caesar salad', 3.65, '2017-04-19 18:26:11', 1, '968b0552', 'Pending'),
('item00003', 'Chicken Caesar salad', 3.65, '2017-03-30 11:04:12', 1, '98ff566f', 'Completed'),
('item00003', 'Chicken Caesar salad', 3.65, '2017-03-30 11:04:18', 1, 'd934c605', 'Pending'),
('item00003', 'Chicken Caesar salad', 3.65, '2017-04-24 17:11:20', 1, 'dbdb977d', 'Pending'),
('item00003', 'Chicken Caesar salad', 3.65, '2017-04-25 23:09:36', 1, 'f8085747', 'Pending'),
('item00004', 'Southern Salad', 3.65, '2017-04-22 00:08:43', 1, '0df4e810', 'Pending'),
('item00004', 'Southern Salad', 3.65, '2017-04-24 17:11:35', 1, '35986afd', 'Pending'),
('item00004', 'Southern Salad', 3.65, '2017-03-30 11:04:18', 1, 'd934c605', 'Pending'),
('item00005', 'BBQ burger - Beef', 5.95, '2017-04-24 17:11:35', 1, '35986afd', 'Pending'),
('item00005', 'BBQ burger - Beef', 5.95, '2017-04-22 00:07:10', 1, '59bc7f06', 'Pending'),
('item00005', 'BBQ burger - Beef', 5.95, '2017-03-30 11:04:19', 1, 'd934c605', 'Pending'),
('item00006', 'Penne Pesto', 4.35, '2017-04-22 00:27:38', 1, 'ec732fb0', 'Pending'),
('item00007', 'Sticky Toffee Pudding', 6.58, '2017-04-22 00:08:38', 1, '0df4e810', 'Pending'),
('item00007', 'Sticky Toffee Pudding', 6.58, '2017-03-30 11:04:26', 1, '13d5fcf0', 'Pending'),
('item00007', 'Sticky Toffee Pudding', 6.58, '2017-04-26 10:32:42', 1, '8db5df08', 'Completed'),
('item00007', 'Sticky Toffee Pudding', 6.58, '2017-04-22 00:27:37', 1, 'ec732fb0', 'Pending'),
('item00008', 'Pepsi', 1, '2017-04-22 00:08:37', 1, '0df4e810', 'Pending'),
('item00008', 'Pepsi', 1, '2017-03-30 11:04:26', 1, '13d5fcf0', 'Pending'),
('item00008', 'Pepsi', 1, '2017-04-26 10:32:40', 1, '8db5df08', 'Completed'),
('Test 02', 'Burger Test', 4.55, '2017-03-30 11:04:27', 1, '13d5fcf0', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderId` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `employeeId` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `tableNo` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `datePlaced` datetime NOT NULL,
  `totalPrice` double NOT NULL,
  `status` varchar(25) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderId`, `employeeId`, `tableNo`, `datePlaced`, `totalPrice`, `status`) VALUES
('13d5fcf0', 'Mehreen', 'C', '2017-03-30 11:04:37', 12.13, 'Pending'),
('35986afd', 'mehreen', 'B', '2017-04-24 17:11:42', 13.25, 'Pending'),
('8db5df08', 'Mehreen', 'A', '2017-04-26 10:32:48', 7.58, 'Completed'),
('968b0552', 'mehreen', 'A', '2017-04-19 18:26:15', 13.55, 'Completed'),
('98ff566f', 'Mehreen', 'A', '2017-03-30 11:04:15', 13.55, 'Completed'),
('d0c12ec3', 'Mehreen', 'A', '2017-04-26 10:23:20', 9.9, 'Completed'),
('d934c605', 'Mehreen', 'B', '2017-03-30 11:04:24', 13.25, 'Pending'),
('dbdb977d', 'mehreen', 'A', '2017-04-24 17:11:32', 13.55, 'Pending'),
('f8085747', 'mehreen', 'A', '2017-04-25 23:09:39', 13.55, 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `date` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tableNo` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bookID` varchar(11) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`name`, `date`, `time`, `tableNo`, `phone`, `bookID`) VALUES
('David', '21/04/17', '20:00', '6', '08512344', '1f491c3d'),
('David two', '23/04/17', '16:00', '2', '08613131', '8703c79b'),
('Mehreen', '24/04/1998', '06:35', '15', '123456789', '89'),
('mirza', '20/06/17', '20:40', '19', '08515151', 'f222b50a');

-- --------------------------------------------------------

--
-- Table structure for table `roster`
--

CREATE TABLE `roster` (
  `Id` int(11) NOT NULL,
  `shiftId` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `employeeId` varchar(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `roster`
--

INSERT INTO `roster` (`Id`, `shiftId`, `startTime`, `endTime`, `employeeId`) VALUES
(1, '20/3/2017', '09:00:00', '12:00:00', 'Mehreen'),
(2, '20/3/2017', '09:00:00', '12:00:00', 'Raymond'),
(3, '18/3/2017', '13:00:00', '18:00:00', 'Mirza'),
(4, '19/3/2017', '12:00:00', '22:00:00', 'David'),
(5, '27/3/2017', '22:00:00', '23:00:00', 'David'),
(6, '27/3/2017', '22:00:00', '23:00:00', 'Sam'),
(8, '27/3/2017', '17:00:00', '23:00:00', 'Mehreen'),
(9, '21/3/2017', '05:00:00', '05:00:00', 'David'),
(12, '23/3/2017', '17:00:00', '21:00:00', 'sams000392'),
(13, '28/3/2017', '01:00:00', '04:00:00', 'Raymond'),
(14, '28/3/2017', '00:00:00', '05:00:00', 'David'),
(15, '29/3/2017', '09:00:00', '20:00:00', 'Raymond');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `employeeId` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `firstname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `surname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `job` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `dob` date NOT NULL,
  `number` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `level` tinyint(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`employeeId`, `password`, `firstname`, `surname`, `job`, `dob`, `number`, `level`) VALUES
('David', 'test', 'David', 'Moy', 'Owner', '1995-08-14', '0800000000', 0),
('Mehreen', 'test', 'Mehreen', 'Malik', 'Waiter', '1995-08-14', '0800000000', 2),
('Raymond', 'test', 'Raymond', 'Wright', 'Head chef', '1972-04-01', '0899999999', 4),
('Sam', 'test', 'Sam', 'Norris', 'Host', '1998-03-16', '0833333333', 1),
('sams000392', 'test', 'David', 'Haight', 'Day managaer', '1991-02-11', '0822222222', 0),
('test', 'test', 'Sarah', 'Pickle', 'Night manager', '1982-01-30', '0811111111', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clock`
--
ALTER TABLE `clock`
  ADD PRIMARY KEY (`employeeId`);

--
-- Indexes for table `ingredients`
--
ALTER TABLE `ingredients`
  ADD PRIMARY KEY (`IngredientName`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`itemID`);

--
-- Indexes for table `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`noteId`);

--
-- Indexes for table `orderItems`
--
ALTER TABLE `orderItems`
  ADD PRIMARY KEY (`itemID`,`orderId`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderId`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `roster`
--
ALTER TABLE `roster`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`employeeId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `roster`
--
ALTER TABLE `roster`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
