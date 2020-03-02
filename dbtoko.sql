-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 16, 2020 at 02:00 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbtoko`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kd_barang` varchar(6) NOT NULL,
  `nm_barang` varchar(20) NOT NULL,
  `harga` varchar(30) NOT NULL,
  `stok` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kd_barang`, `nm_barang`, `harga`, `stok`) VALUES
('B001', 'tempe penyet', '10000', '20'),
('B002', 'ayam geprek', '12500', '12'),
('B003', 'nasi pecel', '8000', '50'),
('B004', 'lele goreng', '10000', '12'),
('Est001', 'es teh anget', '5000', '12'),
('Esj002', 'es jeruk anget', '6000', '10');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `kd_pelanggan` varchar(6) NOT NULL,
  `nm_pelanggan` varchar(30) NOT NULL,
  `alamat` varchar(30) NOT NULL,
  `notlp` varchar(13) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`kd_pelanggan`, `nm_pelanggan`, `alamat`, `notlp`) VALUES
('P001', 'Fahruddin', 'Bangil', '083837288399'),
('P002', 'Iklima N', 'Bangil', '0827636377'),
('P003', 'Novi Santoso', 'Pasuruan', '08374747748'),
('P004', 'Mahros', 'Pasuruan', '083833117951');

-- --------------------------------------------------------

--
-- Table structure for table `pembelian`
--

CREATE TABLE `pembelian` (
  `no_transaksi` varchar(30) NOT NULL,
  `tgl_transaksi` datetime NOT NULL,
  `kd_supplier` varchar(30) NOT NULL,
  `kd_barang` varchar(30) NOT NULL,
  `harga` varchar(20) NOT NULL,
  `jumlah` varchar(20) NOT NULL,
  `totalharga` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pembelian`
--

INSERT INTO `pembelian` (`no_transaksi`, `tgl_transaksi`, `kd_supplier`, `kd_barang`, `harga`, `jumlah`, `totalharga`) VALUES
('001', '2013-06-11 00:00:00', 'S002', 'B003', '1500', '23', '34500'),
('078', '2020-02-17 00:00:00', 'S003', 'B003', '1500', '12', '18000'),
('009', '2020-02-17 00:00:00', 'S002', 'B001', '1000', '20', '20000');

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `no_transaksi` varchar(30) NOT NULL,
  `tgl_transaksi` datetime NOT NULL,
  `kd_barang` varchar(30) NOT NULL,
  `harga` varchar(20) NOT NULL,
  `jumlah` varchar(20) NOT NULL,
  `totalharga` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`no_transaksi`, `tgl_transaksi`, `kd_barang`, `harga`, `jumlah`, `totalharga`) VALUES
('001', '2020-02-17 00:00:00', 'B003', '1500', '2', '3000'),
('008', '2020-02-17 00:00:00', 'B003', '1500', '3', '4500'),
('012', '2020-02-18 00:00:00', 'B002', '500', '4', '2000'),
('003', '2013-06-06 00:00:00', 'B002', '500', '2', '1000');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `kd_supplier` varchar(6) NOT NULL,
  `nm_supplier` varchar(30) NOT NULL,
  `alamat` varchar(30) NOT NULL,
  `notlp` varchar(13) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`kd_supplier`, `nm_supplier`, `alamat`, `notlp`) VALUES
('S001', 'Restoran Ayam Geprk', 'Pasuruan', '0847373447343'),
('S002', 'Restoran Lalapan', 'Surabaya', '031-789453'),
('S003', 'Restoran Serba Ada', 'Bangil', '08323245556');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kd_barang`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`kd_pelanggan`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`kd_supplier`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
