-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 17, 2015 at 02:06 PM
-- Server version: 5.6.25
-- PHP Version: 5.6.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `snbdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `avatar_dtl`
--

CREATE TABLE IF NOT EXISTS `avatar_dtl` (
  `userId` varchar(10) DEFAULT NULL,
  `avatar_name` varchar(45) DEFAULT NULL,
  `avatar_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `avatar_dtl`
--

INSERT INTO `avatar_dtl` (`userId`, `avatar_name`, `avatar_id`) VALUES
('5669353ae6', '1.png', 'pi5669353a'),
('566a494fb9', '1.png', 'pi566a494f'),
('566a72cf45', '1.png', 'pi566a72cf'),
('566c5fc1af', '1.png', 'pi566c5fc1');

-- --------------------------------------------------------

--
-- Table structure for table `badge_dtl`
--

CREATE TABLE IF NOT EXISTS `badge_dtl` (
  `userId` varchar(10) DEFAULT NULL,
  `fromUserId` varchar(10) DEFAULT NULL,
  `voteBadge` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `badge_dtl`
--

INSERT INTO `badge_dtl` (`userId`, `fromUserId`, `voteBadge`) VALUES
('566a72cf45', '5669353ae6', '1'),
('566a72cf45', '5669353ae6', '2'),
('566a72cf45', '5669353ae6', '1'),
('566a72cf45', '5669353ae6', '1'),
('566a72cf45', '5669353ae6', '1'),
('566a72cf45', '5669353ae6', '1'),
('566a494fb9', '5669353ae6', '1'),
('566a494fb9', '5669353ae6', '1'),
('566a494fb9', '5669353ae6', '1'),
('566a494fb9', '5669353ae6', '2'),
('566a494fb9', '5669353ae6', '3');

-- --------------------------------------------------------

--
-- Table structure for table `comment_dtl`
--

CREATE TABLE IF NOT EXISTS `comment_dtl` (
  `commentContent` varchar(100) DEFAULT NULL,
  `commentDate` datetime DEFAULT NULL,
  `commentType` varchar(1) DEFAULT NULL,
  `postId` varchar(10) NOT NULL,
  `userId` varchar(10) NOT NULL,
  `commentId` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment_dtl`
--

INSERT INTO `comment_dtl` (`commentContent`, `commentDate`, `commentType`, `postId`, `userId`, `commentId`) VALUES
('Boang c jason!!!', '2015-12-01 00:00:00', '1', '566a3b9960', '566a72cf45', '1'),
('edi wow', '2015-12-03 00:00:00', '1', '5669a57f3b', '566c5fc1af', '2');

-- --------------------------------------------------------

--
-- Table structure for table `company_dtl`
--

CREATE TABLE IF NOT EXISTS `company_dtl` (
  `userId` varchar(10) DEFAULT NULL,
  `company_name` varchar(50) DEFAULT NULL,
  `company_businessType` varchar(20) DEFAULT NULL,
  `company_yearFounded` year(4) DEFAULT NULL,
  `company_lName` varchar(30) DEFAULT NULL,
  `company_fName` varchar(30) DEFAULT NULL,
  `company_midInit` varchar(2) DEFAULT NULL,
  `company_about` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `conference_dtl`
--

CREATE TABLE IF NOT EXISTS `conference_dtl` (
  `msgId` varchar(10) NOT NULL,
  `dateSent` datetime DEFAULT NULL,
  `userId` varchar(10) DEFAULT NULL,
  `msgContent` varchar(255) DEFAULT NULL,
  `groupId` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `conference_ext`
--

CREATE TABLE IF NOT EXISTS `conference_ext` (
  `conExtId` varchar(10) NOT NULL,
  `timeStarted` datetime DEFAULT NULL,
  `timeEnded` datetime DEFAULT NULL,
  `conId` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `group_ext`
--

CREATE TABLE IF NOT EXISTS `group_ext` (
  `groupId` varchar(10) NOT NULL,
  `userId` varchar(10) DEFAULT NULL,
  `addedDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_ext`
--

INSERT INTO `group_ext` (`groupId`, `userId`, `addedDate`) VALUES
('gi566a3a50', '5669353ae6', '0000-00-00 00:00:00'),
('gi566a73ca', '566a72cf45', '0000-00-00 00:00:00'),
('gi566c30ef', '5669353ae6', '0000-00-00 00:00:00'),
('gi566a3a50', '566a494fb9', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `group_md`
--

CREATE TABLE IF NOT EXISTS `group_md` (
  `groupId` varchar(10) NOT NULL,
  `groupname` varchar(30) DEFAULT NULL,
  `groupdescription` varchar(255) DEFAULT NULL,
  `groupCoverPic` varchar(45) DEFAULT NULL,
  `userId` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_md`
--

INSERT INTO `group_md` (`groupId`, `groupname`, `groupdescription`, `groupCoverPic`, `userId`) VALUES
('gi566a3a50', 'pokemon', 'pokemon', 'defaultcover.png', '5669353ae6'),
('gi566a73ca', 'sadasd', 'asdasd', 'defaultcover.png', '566a72cf45'),
('gi566c30ef', 'group 2', 'wala lang', 'defaultcover.png', '5669353ae6');

-- --------------------------------------------------------

--
-- Table structure for table `investor_dtl`
--

CREATE TABLE IF NOT EXISTS `investor_dtl` (
  `investorId` varchar(10) NOT NULL,
  `postId` varchar(10) DEFAULT NULL,
  `userId` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `location_dtl`
--

CREATE TABLE IF NOT EXISTS `location_dtl` (
  `locationId` varchar(10) NOT NULL,
  `userId` varchar(10) DEFAULT NULL,
  `location_address1` varchar(255) DEFAULT NULL,
  `location_address2` varchar(255) DEFAULT NULL,
  `location_city` varchar(30) DEFAULT NULL,
  `location_prov` varchar(45) DEFAULT NULL,
  `location_zipcode` varchar(10) DEFAULT NULL,
  `location_country` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `location_dtl`
--

INSERT INTO `location_dtl` (`locationId`, `userId`, `location_address1`, `location_address2`, `location_city`, `location_prov`, `location_zipcode`, `location_country`) VALUES
('li5669353a', '5669353ae6', 'qweqwe', 'wqewqe', 'Cebu City', 'Region 7', '6015', 'Ph'),
('li566a494f', '566a494fb9', 'asdasdas', 'sadasd', 'sadasd', 'asdsad', '6015', 'Sadasd'),
('li566a72cf', '566a72cf45', 'wdasdsad', 'asdasd', 'asdasd', 'asdasd', '6015', 'sadasdas'),
('li566c5fc1', '566c5fc1af', 'qdwqdqwdsadas', 'asdasdasd', 'asdasd', 'asdasd', '6015', 'Qqwewq');

-- --------------------------------------------------------

--
-- Table structure for table `msg_dtl`
--

CREATE TABLE IF NOT EXISTS `msg_dtl` (
  `msgId` varchar(10) NOT NULL,
  `userId` varchar(10) DEFAULT NULL,
  `msg_fromUserId` varchar(10) DEFAULT NULL,
  `msg_Content` varchar(255) DEFAULT NULL,
  `msg_Date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `report_dtl`
--

CREATE TABLE IF NOT EXISTS `report_dtl` (
  `reportId` varchar(10) NOT NULL,
  `userId` varchar(10) DEFAULT NULL,
  `fromUserId` varchar(10) DEFAULT NULL,
  `reportContent` varchar(255) DEFAULT NULL,
  `reportDate` datetime DEFAULT NULL,
  `reportStat` varchar(1) DEFAULT NULL,
  `reportType` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `upvote_dtl`
--

CREATE TABLE IF NOT EXISTS `upvote_dtl` (
  `voteStat` varchar(1) NOT NULL,
  `voteType` varchar(1) DEFAULT NULL,
  `postId` varchar(10) NOT NULL,
  `userId` varchar(10) NOT NULL,
  `voteId` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `upvote_dtl`
--

INSERT INTO `upvote_dtl` (`voteStat`, `voteType`, `postId`, `userId`, `voteId`) VALUES
('1', '3', '566a3b9960', '566a72cf45', '20'),
('1', '1', '5669a57f3b', '566a72cf45', '21'),
('1', '1', '566a3f3d4a', '5669353ae6', '566a3fc9f0eea'),
('1', '1', '566a49605e', '566a494fb9', '566a49766543e'),
('1', '1', '566a45083a', '5669353ae6', '566a4f0704ff9'),
('1', '1', '566a44d95e', '5669353ae6', '566a4f226f182'),
('1', '1', '566a44bda8', '5669353ae6', '566a509a9f7e1'),
('1', '1', '566a445a79', '5669353ae6', '566a50abf0925'),
('1', '1', '566a44543d', '5669353ae6', '566a53077b794'),
('1', '1', '566a43e69d', '5669353ae6', '566a534bc378f'),
('1', '1', '566a437720', '5669353ae6', '566a539a52ccb'),
('1', '1', '566a43971d', '5669353ae6', '566a5416bc4c0'),
('1', '1', '566a3e393d', '5669353ae6', '566a5469b3339'),
('1', '1', '566a43ef89', '5669353ae6', '566a550d1cd1a'),
('1', '1', '566a43dca4', '5669353ae6', '566a551098e7f'),
('1', '1', '566a3ec983', '5669353ae6', '566a551487a3f'),
('1', '1', '566a3ea9e0', '5669353ae6', '566a5d6762fce'),
('1', '1', '5669a57f3b', '5669353ae6', '566a5d6cafc42'),
('1', '1', '5669a5eeb8', '5669353ae6', '566a5d6fb1c8d'),
('1', '1', '566a3b9960', '5669353ae6', '566a5d7a0577b'),
('1', '1', '566a5d9c66', '5669353ae6', '566a5dafc389c'),
('1', '1', '566a72f3d0', '566a72cf45', '566a72f8481b1'),
('1', '1', '566a3e98c8', '5669353ae6', '566a8b2566472'),
('1', '1', '566a726345', '5669353ae6', '566a8cb042adf'),
('1', '1', '5670306215', '5669353ae6', '567043aae9bbd'),
('1', '1', '56705ccd6c', '5669353ae6', '56705ecfa8cd6'),
('1', '1', '56705cc096', '5669353ae6', '56705edae9e6a'),
('1', '1', '56705bd2de', '5669353ae6', '567062ac64556'),
('1', '1', '5670530c66', '5669353ae6', '567062c92f44c'),
('1', '1', '5670536279', '5669353ae6', '5670630235311'),
('1', '1', '566a72f3d0', '5669353ae6', '567078451122b'),
('1', '1', '4', '5669353ae6', '567078a04c06d'),
('1', '1', '1', '5669353ae6', '567078c6baabd'),
('1', '1', '566a49605e', '5669353ae6', '5670792567083'),
('1', '1', '6', '5669353ae6', '56707a02d491d'),
('1', '1', '5', '5669353ae6', '56707a5ccd867'),
('1', '1', '2', '5669353ae6', '56707ad55d31e'),
('1', '1', '5670837088', '5669353ae6', '567149d12ed85'),
('1', '1', '56708347d3', '5669353ae6', '567149d42a3f7'),
('1', '1', '567053a71e', '5669353ae6', '567149d973136'),
('1', '1', '56708338bf', '5669353ae6', '56714a8961c1d'),
('1', '1', '567083553e', '5669353ae6', '56714ab736f0a'),
('1', '1', '5670594c7b', '5669353ae6', '56714ad815382'),
('1', '1', '56705bdae1', '5669353ae6', '56729f11c85b0'),
('1', '1', '56716b2e97', '5669353ae6', '56729f1ba9bb5'),
('1', '1', '56716b253a', '5669353ae6', '5672a0c49ba57'),
('1', '1', '56716aee3d', '5669353ae6', '5672a4b71ce33');

-- --------------------------------------------------------

--
-- Table structure for table `userpost`
--

CREATE TABLE IF NOT EXISTS `userpost` (
  `postId` varchar(10) NOT NULL,
  `postContent` varchar(255) DEFAULT NULL,
  `postDate` datetime DEFAULT NULL,
  `postType` varchar(10) DEFAULT NULL,
  `postTitle` varchar(45) DEFAULT NULL,
  `userId` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userpost`
--

INSERT INTO `userpost` (`postId`, `postContent`, `postDate`, `postType`, `postTitle`, `userId`) VALUES
('1', 'wala lang', '2015-12-01 00:00:00', '2', 'hello', '566a72cf45'),
('2', 'hi', '2015-12-03 00:00:00', '2', 'wer', '566a494fb9'),
('3', 'boang c jason', '2015-12-02 00:00:00', '2', 'fdghghg', '5669353ae6'),
('4', 'ty', '2015-12-05 00:00:00', '2', 'fgdfgfg', '566a72cf45'),
('5', 'go!', '2015-12-06 00:00:00', '2', 'sdfsd', '566a494fb9'),
('5669a57f3b', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam pulvinar augue vel turpis posuere, sit amet vehicula ipsum ornare. Cras in pellentesque augue. Morbi vel velit eros. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cu', '2015-12-10 17:17:03', '1', 'asdasd', '5669353ae6'),
('5669a5eeb8', 'Maecenas lobortis egestas orci eu ultrices. Quisque maximus purus eu mauris efficitur, eget laoreet elit fermentum. Proin faucibus lacinia feugiat. Proin ut convallis orci, ut mollis libero. Donec aliquet nulla sed mattis pretium. Sed ultrices sapien ut d', '2015-12-10 17:18:54', '1', 'Pokemon', '5669353ae6'),
('566a3b9960', 'awdasd', '2015-12-11 03:57:29', '1', 'waeawe', '5669353ae6'),
('566a3e393d', 'asdasda', '2015-12-11 04:08:41', '1', 'asdasd', '5669353ae6'),
('566a3e98c8', 'asdasd', '2015-12-11 04:10:16', '1', 'asdasd', '5669353ae6'),
('566a3ea9e0', 'asdasd', '2015-12-11 04:10:33', '1', 'asdasd', '5669353ae6'),
('566a3ec983', 'asdasdasd', '2015-12-11 04:11:05', '1', 'asdasd', '5669353ae6'),
('566a3f3d4a', 'asdasdas', '2015-12-11 04:13:01', '1', 'asdasdas', '5669353ae6'),
('566a437720', 'asdsad', '2015-12-11 04:31:03', '1', 'asdasd', '5669353ae6'),
('566a43971d', 'asdsad', '2015-12-11 04:31:35', '1', 'sadasd', '5669353ae6'),
('566a43dca4', 'asdasd', '2015-12-11 04:32:44', '1', 'asdasd', '5669353ae6'),
('566a43e69d', 'oasidasdasd', '2015-12-11 04:32:54', '1', 'jason', '5669353ae6'),
('566a43ef89', 'asdasdasdasd', '2015-12-11 04:33:03', '1', 'asdasd', '5669353ae6'),
('566a44543d', 'asdasd', '2015-12-11 04:34:44', '1', 'asdasd', '5669353ae6'),
('566a445a79', 'asdsad', '2015-12-11 04:34:50', '1', 'asdas', '5669353ae6'),
('566a44bda8', 'asdasd', '2015-12-11 04:36:29', '1', 'asdasd', '5669353ae6'),
('566a44d95e', 'asdasd', '2015-12-11 04:36:57', '1', 'asdasd', '5669353ae6'),
('566a45083a', 'asdasdsad', '2015-12-11 04:37:44', '1', 'asdsad', '5669353ae6'),
('566a49605e', 'asdasdasd', '2015-12-11 04:56:16', '1', 'dsasdasd', '566a494fb9'),
('566a5d9c66', 'asdsd', '2015-12-11 06:22:36', '1', 'sdasd', '5669353ae6'),
('566a726345', 'sadasdas', '2015-12-11 07:51:15', '1', 'asdasd', '5669353ae6'),
('566a72f3d0', 'asdasd', '2015-12-11 07:53:39', '1', 'sadasd', '566a72cf45'),
('5670306215', 'asdsad', '2015-12-15 16:23:14', '1', 'asdasd', '5669353ae6'),
('56704c7cc9', 'asdasdsa', '2015-12-15 18:23:08', '1', 'asdas', '5669353ae6'),
('5670530c66', 'asdasd', '2015-12-15 18:51:08', '1', 'asdsadas', '5669353ae6'),
('5670536279', 'aeawe', '2015-12-15 18:52:34', '1', 'aweawe', '5669353ae6'),
('567053a71e', 'asdasdasd', '2015-12-15 18:53:43', '1', 'sadasd', '5669353ae6'),
('567055f328', 'asdasd', '2015-12-15 19:03:31', '1', 'asdasdas', '5669353ae6'),
('5670594c7b', 'asdasdasd', '2015-12-15 19:17:48', '1', 'sadasdasd', '5669353ae6'),
('56705bd2de', 'asdasdasd', '2015-12-15 19:28:34', '1', 'asdsad', '5669353ae6'),
('56705bdae1', 'pokemon', '2015-12-15 19:28:42', '1', 'pokemon', '5669353ae6'),
('56705cc096', 'ASDASd', '2015-12-15 19:32:32', '1', 'Pokemon', '5669353ae6'),
('56705ccd6c', 'Jason', '2015-12-15 19:32:45', '1', 'Pitogo', '5669353ae6'),
('56708338bf', 'asdasdas', '2015-12-15 22:16:40', '1', 'asdasd', '5669353ae6'),
('56708338cc', 'asdasdas', '2015-12-15 22:16:40', '1', 'asdasd', '5669353ae6'),
('56708347d3', 'asdasd', '2015-12-15 22:16:55', '1', 'asdasd', '5669353ae6'),
('567083553e', 'asdasdas', '2015-12-15 22:17:09', '1', 'asdasd', '5669353ae6'),
('5670837088', 'asdasdasd', '2015-12-15 22:17:36', '1', 'sadasd', '5669353ae6'),
('567169d1e6', 'asdasdasdasd', '2015-12-16 14:40:33', '1', 'dsasdasd', '5669353ae6'),
('56716a28c9', 'asdasd', '2015-12-16 14:42:00', '1', 'sadasd', '5669353ae6'),
('56716a7da0', 'sadasdas', '2015-12-16 14:43:25', '1', 'asdasd', '5669353ae6'),
('56716aee3d', 'asdasd', '2015-12-16 14:45:18', '1', 'asdasd', '5669353ae6'),
('56716b253a', 'asdasd', '2015-12-16 14:46:13', '1', 'asdasd', '5669353ae6'),
('56716b2e97', 'w', '2015-12-16 14:46:22', '1', 'p', '5669353ae6'),
('6', 'ghost', '2015-12-07 00:00:00', '2', 'think', '566a494fb9'),
('pr56707ced', 'ample', NULL, 'gi566a3a50', 'sample', '5669353ae6');

-- --------------------------------------------------------

--
-- Table structure for table `userpost_ext`
--

CREATE TABLE IF NOT EXISTS `userpost_ext` (
  `extId` varchar(10) NOT NULL,
  `extContent` varchar(255) DEFAULT NULL,
  `extType` varchar(1) DEFAULT NULL,
  `postId` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userpost_ext`
--

INSERT INTO `userpost_ext` (`extId`, `extContent`, `extType`, `postId`) VALUES
('5669a57f3b', './post_image/152585669a57f3b0d8.jpg', '1', '5669a57f3b'),
('5669a5eeb8', 'pokemon.com,pokemon.com,pokemon.com,pokemon.com', '2', '5669a5eeb8'),
('5669a5eebd', './post_image/153185669a5eeb7e6b.jpg', '1', '5669a5eeb8'),
('566a3b9960', './post_image/31947566a3b995f955.jpg', '1', '566a3b9960'),
('566a3e393d', 'sadasdasd.com,asdasdas', '2', '566a3e393d'),
('566a3e98c8', 'http://asdasda', '2', '566a3e98c8'),
('566a3ea9e0', 'http://adsasdasd', '2', '566a3ea9e0'),
('566a3ec983', 'http://google.com,facebook.com', '2', '566a3ec983'),
('566a3f3d4a', 'google.com,asdsad', '2', '566a3f3d4a'),
('566a437720', 'asdsadsa', '2', '566a437720'),
('566a43971d', 'sadasd', '2', '566a43971d'),
('566a43dca4', 'asdasd', '2', '566a43dca4'),
('566a43e69d', 'asdasd', '2', '566a43e69d'),
('566a43ef89', 'asdsadas', '2', '566a43ef89'),
('566a44543d', 'sadas', '2', '566a44543d'),
('566a445a79', 'asdasd', '2', '566a445a79'),
('5670306215', 'asdasdasd', '2', '5670306215'),
('56708338bf', './post_image/1133056708338bf173.jpg', '1', '56708338bf'),
('56708338cc', './post_image/1577056708338cc07e.jpg', '1', '56708338cc'),
('56708347d3', './post_image/2221956708347d313a.jpg', '1', '56708347d3'),
('567083553e', './post_image/9299567083553e180.jpg', '1', '567083553e'),
('5670837088', './post_image/324245670837088349.png', '1', '5670837088'),
('56716a7da0', './post_image/1009356716a7da0791.jpg', '1', '56716a7da0');

-- --------------------------------------------------------

--
-- Table structure for table `user_dtl`
--

CREATE TABLE IF NOT EXISTS `user_dtl` (
  `userId` varchar(10) DEFAULT NULL,
  `user_lName` varchar(30) DEFAULT NULL,
  `user_fName` varchar(30) DEFAULT NULL,
  `user_midInit` varchar(2) DEFAULT NULL,
  `user_age` varchar(3) DEFAULT NULL,
  `user_gender` varchar(1) DEFAULT NULL,
  `user_shortSelfDescription` varchar(100) DEFAULT NULL,
  `user_nameOfBusiness` varchar(45) DEFAULT NULL,
  `user_businessType` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_dtl`
--

INSERT INTO `user_dtl` (`userId`, `user_lName`, `user_fName`, `user_midInit`, `user_age`, `user_gender`, `user_shortSelfDescription`, `user_nameOfBusiness`, `user_businessType`) VALUES
('5669353ae6', 'Pitogo', 'Jason', 'D', '21', 'M', 'asdasd', NULL, NULL),
('566a494fb9', 'Pitogo', 'Kitkat', 'D', '21', 'F', 'sadasd', NULL, NULL),
('566a72cf45', 'Pitogo', 'Kitkat', 'D', '20', 'F', 'sadas', 'asdasd', 'asdasd'),
('566c5fc1af', 'Asdasdasdasdasdasdasdasdddd', 'Asddddddddddddddddd', 'AS', '21', 'F', 'asdasd', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_md`
--

CREATE TABLE IF NOT EXISTS `user_md` (
  `userId` varchar(10) NOT NULL,
  `user_Type` varchar(8) DEFAULT NULL,
  `user_dateRegistered` datetime DEFAULT NULL,
  `user_emailAdd` varchar(50) DEFAULT NULL,
  `user_password` varchar(40) DEFAULT NULL,
  `user_profilePicId` varchar(10) DEFAULT NULL,
  `user_status` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_md`
--

INSERT INTO `user_md` (`userId`, `user_Type`, `user_dateRegistered`, `user_emailAdd`, `user_password`, `user_profilePicId`, `user_status`) VALUES
('5669353ae6', 'Ideator', '2015-12-10 09:18:02', 'jason.pitogo1@gmail.com', '00bfc8c729f5d4d529a412b12c58ddd2', 'pi5669353a', '0'),
('566a494fb9', 'Ideator', '2015-12-11 04:55:59', 'kitkat@gmail.com', '00bfc8c729f5d4d529a412b12c58ddd2', 'pi566a494f', '0'),
('566a72cf45', 'Investor', '2015-12-11 07:53:03', 'pokemon@gmail.com', '00bfc8c729f5d4d529a412b12c58ddd2', 'pi566a72cf', '0'),
('566c5fc1af', 'Ideator', '2015-12-12 18:56:17', 'kitkat2@gmail.com', '00bfc8c729f5d4d529a412b12c58ddd2', 'pi566c5fc1', '0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `avatar_dtl`
--
ALTER TABLE `avatar_dtl`
  ADD PRIMARY KEY (`avatar_id`),
  ADD KEY `fk_picture_dtl_User_MD1_idx` (`userId`);

--
-- Indexes for table `badge_dtl`
--
ALTER TABLE `badge_dtl`
  ADD KEY `fk_badge_dtl_User_MD1_idx` (`userId`),
  ADD KEY `fk_badge_dtl_User_MD2_idx` (`fromUserId`);

--
-- Indexes for table `comment_dtl`
--
ALTER TABLE `comment_dtl`
  ADD KEY `fk_comment_dtl_User_MD1_idx` (`userId`),
  ADD KEY `fk_comment_dtl_userpost1` (`postId`);

--
-- Indexes for table `company_dtl`
--
ALTER TABLE `company_dtl`
  ADD KEY `fk_Company_dtl_User_MD_idx` (`userId`);

--
-- Indexes for table `conference_dtl`
--
ALTER TABLE `conference_dtl`
  ADD PRIMARY KEY (`msgId`),
  ADD KEY `fk_conference_dtl_group_md1_idx` (`groupId`),
  ADD KEY `fk_conference_dtl_User_MD1_idx` (`userId`);

--
-- Indexes for table `conference_ext`
--
ALTER TABLE `conference_ext`
  ADD PRIMARY KEY (`conExtId`),
  ADD KEY `fk_conference_ext_group_md1_idx` (`conId`);

--
-- Indexes for table `group_ext`
--
ALTER TABLE `group_ext`
  ADD KEY `fk_group_ext_group_md1_idx` (`groupId`),
  ADD KEY `fk_group_ext_User_MD1_idx` (`userId`);

--
-- Indexes for table `group_md`
--
ALTER TABLE `group_md`
  ADD PRIMARY KEY (`groupId`),
  ADD KEY `fk_group_md_User_MD1_idx` (`userId`);

--
-- Indexes for table `investor_dtl`
--
ALTER TABLE `investor_dtl`
  ADD PRIMARY KEY (`investorId`),
  ADD KEY `fk_investor_dtl_userpost1_idx` (`postId`),
  ADD KEY `fk_investor_dtl_User_MD1_idx` (`userId`);

--
-- Indexes for table `location_dtl`
--
ALTER TABLE `location_dtl`
  ADD PRIMARY KEY (`locationId`),
  ADD KEY `fk_location_dtl_User_MD1_idx` (`userId`);

--
-- Indexes for table `msg_dtl`
--
ALTER TABLE `msg_dtl`
  ADD PRIMARY KEY (`msgId`),
  ADD KEY `fk_msg_dtl_User_MD1_idx` (`userId`),
  ADD KEY `fk_msg_dtl_User_MD2_idx` (`msg_fromUserId`);

--
-- Indexes for table `report_dtl`
--
ALTER TABLE `report_dtl`
  ADD PRIMARY KEY (`reportId`),
  ADD KEY `fk_report_dtl_User_MD1_idx` (`userId`),
  ADD KEY `fk_report_dtl_User_MD2_idx` (`fromUserId`);

--
-- Indexes for table `upvote_dtl`
--
ALTER TABLE `upvote_dtl`
  ADD PRIMARY KEY (`voteId`),
  ADD KEY `fk_upvote_dtl_userpost1_idx` (`postId`),
  ADD KEY `fk_upvote_dtl_User_MD1_idx` (`userId`);

--
-- Indexes for table `userpost`
--
ALTER TABLE `userpost`
  ADD PRIMARY KEY (`postId`),
  ADD KEY `fk_userpost_User_MD1_idx` (`userId`);

--
-- Indexes for table `userpost_ext`
--
ALTER TABLE `userpost_ext`
  ADD PRIMARY KEY (`extId`);

--
-- Indexes for table `user_dtl`
--
ALTER TABLE `user_dtl`
  ADD KEY `fk_User_dtl_User_MD1` (`userId`);

--
-- Indexes for table `user_md`
--
ALTER TABLE `user_md`
  ADD PRIMARY KEY (`userId`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `avatar_dtl`
--
ALTER TABLE `avatar_dtl`
  ADD CONSTRAINT `fk_picture_dtl_User_MD1` FOREIGN KEY (`userId`) REFERENCES `user_md` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `badge_dtl`
--
ALTER TABLE `badge_dtl`
  ADD CONSTRAINT `fk_badge_dtl_User_MD1` FOREIGN KEY (`userId`) REFERENCES `user_md` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_badge_dtl_User_MD2` FOREIGN KEY (`fromUserId`) REFERENCES `user_md` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `comment_dtl`
--
ALTER TABLE `comment_dtl`
  ADD CONSTRAINT `fk_comment_dtl_User_MD1` FOREIGN KEY (`userId`) REFERENCES `user_md` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_comment_dtl_userpost1` FOREIGN KEY (`postId`) REFERENCES `userpost` (`postId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `company_dtl`
--
ALTER TABLE `company_dtl`
  ADD CONSTRAINT `fk_Company_dtl_User_MD` FOREIGN KEY (`userId`) REFERENCES `user_md` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `conference_dtl`
--
ALTER TABLE `conference_dtl`
  ADD CONSTRAINT `fk_conference_dtl_User_MD1` FOREIGN KEY (`userId`) REFERENCES `user_md` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_conference_dtl_group_md1` FOREIGN KEY (`groupId`) REFERENCES `group_md` (`groupId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `conference_ext`
--
ALTER TABLE `conference_ext`
  ADD CONSTRAINT `fk_conference_ext_group_md1` FOREIGN KEY (`conId`) REFERENCES `group_md` (`groupId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `group_ext`
--
ALTER TABLE `group_ext`
  ADD CONSTRAINT `fk_group_ext_User_MD1` FOREIGN KEY (`userId`) REFERENCES `user_md` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_group_ext_group_md1` FOREIGN KEY (`groupId`) REFERENCES `group_md` (`groupId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `group_md`
--
ALTER TABLE `group_md`
  ADD CONSTRAINT `fk_group_md_User_MD1` FOREIGN KEY (`userId`) REFERENCES `user_md` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `investor_dtl`
--
ALTER TABLE `investor_dtl`
  ADD CONSTRAINT `fk_investor_dtl_User_MD1` FOREIGN KEY (`userId`) REFERENCES `user_md` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_investor_dtl_userpost1` FOREIGN KEY (`postId`) REFERENCES `userpost` (`postId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `location_dtl`
--
ALTER TABLE `location_dtl`
  ADD CONSTRAINT `fk_location_dtl_User_MD1` FOREIGN KEY (`userId`) REFERENCES `user_md` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `msg_dtl`
--
ALTER TABLE `msg_dtl`
  ADD CONSTRAINT `fk_msg_dtl_User_MD1` FOREIGN KEY (`userId`) REFERENCES `user_md` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_msg_dtl_User_MD2` FOREIGN KEY (`msg_fromUserId`) REFERENCES `user_md` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `report_dtl`
--
ALTER TABLE `report_dtl`
  ADD CONSTRAINT `fk_report_dtl_User_MD1` FOREIGN KEY (`userId`) REFERENCES `user_md` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_report_dtl_User_MD2` FOREIGN KEY (`fromUserId`) REFERENCES `user_md` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `upvote_dtl`
--
ALTER TABLE `upvote_dtl`
  ADD CONSTRAINT `fk_upvote_dtl_User_MD1` FOREIGN KEY (`userId`) REFERENCES `user_md` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_upvote_dtl_userpost1` FOREIGN KEY (`postId`) REFERENCES `userpost` (`postId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `userpost`
--
ALTER TABLE `userpost`
  ADD CONSTRAINT `fk_userpost_User_MD1` FOREIGN KEY (`userId`) REFERENCES `user_md` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user_dtl`
--
ALTER TABLE `user_dtl`
  ADD CONSTRAINT `fk_User_dtl_User_MD1` FOREIGN KEY (`userId`) REFERENCES `user_md` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
