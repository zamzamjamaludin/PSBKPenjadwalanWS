-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2016 at 02:09 AM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penjadwalan`
--

-- --------------------------------------------------------

--
-- Table structure for table `dosen`
--

CREATE TABLE `dosen` (
  `id` varchar(12) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dosen`
--

INSERT INTO `dosen` (`id`, `nama`, `alamat`) VALUES
('1097566738', 'Budi', 'Soekarno Hatta'),
('1097566734', 'Ani', 'Setiabudhi');

-- --------------------------------------------------------

--
-- Table structure for table `jadwal`
--

CREATE TABLE `jadwal` (
  `id` int(5) NOT NULL,
  `hari` varchar(6) NOT NULL,
  `jam` varchar(13) NOT NULL,
  `id_matpel` varchar(5) NOT NULL,
  `id_dosen` varchar(12) NOT NULL,
  `id_ruangan` int(3) NOT NULL,
  `nrp` longtext NOT NULL,
  `kelas` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jadwal`
--

INSERT INTO `jadwal` (`id`, `hari`, `jam`, `id_matpel`, `id_dosen`, `id_ruangan`, `nrp`, `kelas`) VALUES
(1, 'Senin', '07.00 - 09.30', 'IF201', '1097566738', 1, '', 'A'),
(2, 'Selasa', '07.00 - 09.30', 'IF201', '1097566734', 2, '', 'C'),
(3, 'Sabtu', '08.00 - 10.30', 'IF301', '1097566734', 2, '', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `jadwal_mahasiswa`
--

CREATE TABLE `jadwal_mahasiswa` (
  `id_jadwal` int(5) DEFAULT NULL,
  `nrp` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jadwal_mahasiswa`
--

INSERT INTO `jadwal_mahasiswa` (`id_jadwal`, `nrp`) VALUES
(1, '133040053'),
(2, '133040053'),
(3, '133040046'),
(2, '133040046'),
(3, '133040053');

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `nrp` varchar(9) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`nrp`, `nama`, `alamat`) VALUES
('133040053', 'Zamzam Jamaludin', 'Jl. Rancamanyar'),
('133040046', 'Rayi Sundara', 'Antapani');

-- --------------------------------------------------------

--
-- Table structure for table `matpel`
--

CREATE TABLE `matpel` (
  `id` varchar(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `sks` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `matpel`
--

INSERT INTO `matpel` (`id`, `nama`, `sks`) VALUES
('IF101', 'ALPRO 1', 4),
('IF201', 'ALPRO 2', 3),
('IF301', 'ALPRO 3', 3);

-- --------------------------------------------------------

--
-- Table structure for table `ruangan`
--

CREATE TABLE `ruangan` (
  `id` int(3) NOT NULL,
  `nama` varchar(5) NOT NULL,
  `max` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ruangan`
--

INSERT INTO `ruangan` (`id`, `nama`, `max`) VALUES
(1, 'SB404', 50),
(2, 'SB403', 45);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
