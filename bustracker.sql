-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 14, 2024 at 04:20 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bustracker`
--

-- --------------------------------------------------------

--
-- Table structure for table `appuser`
--

CREATE TABLE `appuser` (
  `userID` int(10) NOT NULL,
  `email` varchar(250) DEFAULT NULL,
  `password` varchar(200) NOT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `phoneNum` int(11) DEFAULT NULL,
  `roleID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appuser`
--

INSERT INTO `appuser` (`userID`, `email`, `password`, `nickname`, `phoneNum`, `roleID`) VALUES
(0, 'dhanisha@yahoo.com', '123456', 'Dhanisha', 166001634, 103),
(4321, 'zulaikha@yahoo.com', '123456', 'siti zulaikha', 1162162724, 101),
(5024, 'syahira_azmi@gmail.com', '123456', 'syahira', 179806121, 103),
(5578, 'amirul@gmail.com', '123456', 'amirul', 156668855, 103),
(6789, 'pavithran@yahoo.com', '123456', 'Pavithran', 103089545, 102);

-- --------------------------------------------------------

--
-- Table structure for table `bus`
--

CREATE TABLE `bus` (
  `busID` int(11) NOT NULL,
  `capacity` int(11) DEFAULT NULL CHECK (`capacity` > 0),
  `userID` int(11) DEFAULT NULL,
  `scheduleID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `roleID` int(11) NOT NULL,
  `roleName` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`roleID`, `roleName`) VALUES
(101, 'Admin'),
(102, 'Bus Driver'),
(103, 'Student');

-- --------------------------------------------------------

--
-- Table structure for table `route`
--

CREATE TABLE `route` (
  `routeID` int(11) NOT NULL,
  `routeName` varchar(250) DEFAULT NULL,
  `startLocation` varchar(250) DEFAULT NULL,
  `endLocation` varchar(250) DEFAULT NULL,
  `distance` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `scheduleID` int(11) NOT NULL,
  `departureTime` time DEFAULT NULL,
  `arrivalTime` time DEFAULT NULL,
  `routeID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `timeestimation`
--

CREATE TABLE `timeestimation` (
  `estimationID` int(11) NOT NULL,
  `estimatedTime` time DEFAULT NULL,
  `scheduleID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appuser`
--
ALTER TABLE `appuser`
  ADD PRIMARY KEY (`userID`),
  ADD KEY `roleID` (`roleID`);

--
-- Indexes for table `bus`
--
ALTER TABLE `bus`
  ADD PRIMARY KEY (`busID`),
  ADD KEY `userID` (`userID`),
  ADD KEY `scheduleID` (`scheduleID`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`roleID`);

--
-- Indexes for table `route`
--
ALTER TABLE `route`
  ADD PRIMARY KEY (`routeID`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`scheduleID`),
  ADD KEY `routeID` (`routeID`);

--
-- Indexes for table `timeestimation`
--
ALTER TABLE `timeestimation`
  ADD PRIMARY KEY (`estimationID`),
  ADD KEY `scheduleID` (`scheduleID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appuser`
--
ALTER TABLE `appuser`
  ADD CONSTRAINT `appuser_ibfk_1` FOREIGN KEY (`roleID`) REFERENCES `role` (`roleID`);

--
-- Constraints for table `bus`
--
ALTER TABLE `bus`
  ADD CONSTRAINT `bus_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `appuser` (`userID`),
  ADD CONSTRAINT `bus_ibfk_2` FOREIGN KEY (`scheduleID`) REFERENCES `schedule` (`scheduleID`);

--
-- Constraints for table `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`routeID`) REFERENCES `route` (`routeID`);

--
-- Constraints for table `timeestimation`
--
ALTER TABLE `timeestimation`
  ADD CONSTRAINT `timeestimation_ibfk_1` FOREIGN KEY (`scheduleID`) REFERENCES `schedule` (`scheduleID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
