-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 27, 2025 at 03:19 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpustakaan_sungaipenuh`
--

-- --------------------------------------------------------

--
-- Table structure for table `anggota`
--

CREATE TABLE `anggota` (
  `id_anggota` varchar(10) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` text,
  `tipe_anggota` varchar(20) DEFAULT NULL,
  `status_aktif` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `anggota`
--

INSERT INTO `anggota` (`id_anggota`, `nama`, `alamat`, `tipe_anggota`, `status_aktif`) VALUES
('A001', 'Larisa', 'PADANG', 'Pelajar', 1),
('A002', 'Nadila Lailany Numai', 'PADANG', 'Umum', 1),
('A003', 'Salwa Marani Ronza', 'Sungai Penuh', 'Umum', 1),
('A004', 'Zaskia Munawaroh Asnadi', 'Jakarta', 'Pelajar', 1),
('A005', 'Baskara Mahendra', 'Jakarta', 'Umum', 1);

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `kode_buku` varchar(10) NOT NULL,
  `judul` varchar(200) NOT NULL,
  `pengarang` varchar(100) DEFAULT NULL,
  `stok` int DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`kode_buku`, `judul`, `pengarang`, `stok`) VALUES
('BK01', 'Hujan', 'Anonim', 10),
('BK02', 'BUMI', 'Tere Liye', 6),
('BK03', 'Logika Pemrograman Menggunakan Java', 'Adbdul Kadir', 3),
('BK04', 'Atomic Habits: Perubahan Kecil, Hasil Luar Biasa', 'James Clear', 5),
('BK05', 'Laskar Pelangi', 'Andrea Hirata', 2),
('BK06', 'Laut Bercerita', 'Leila S. Chudori', 4),
('BK07', 'Negeri 5 Menara', 'Ahmad Fuadi', 2);

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id_peminjaman` varchar(50) NOT NULL,
  `id_anggota` varchar(10) DEFAULT NULL,
  `kode_buku` varchar(10) DEFAULT NULL,
  `tanggal_pinjam` date DEFAULT NULL,
  `jatuh_tempo` date DEFAULT NULL,
  `tanggal_kembali` date DEFAULT NULL,
  `denda` decimal(10,2) DEFAULT '0.00',
  `status` varchar(20) DEFAULT 'Dipinjam'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`id_peminjaman`, `id_anggota`, `kode_buku`, `tanggal_pinjam`, `jatuh_tempo`, `tanggal_kembali`, `denda`, `status`) VALUES
('PJM-1766834226494', 'A001', 'BK01', '2025-12-27', '2026-01-03', NULL, 0.00, 'Kembali'),
('PJM-1766834464708', 'A001', 'BK02', '2025-12-27', '2026-01-03', NULL, 0.00, 'Dipinjam'),
('PJM-1766843063768', 'A004', 'BK02', '2025-12-27', '2026-01-03', '2025-12-27', 0.00, 'Kembali'),
('PJM-1766843103588', 'A002', 'BK05', '2025-12-27', '2026-01-03', NULL, 0.00, 'Dipinjam'),
('PJM-1766843422115', 'A003', 'BK02', '2025-12-27', '2026-01-03', '2025-12-27', 0.00, 'Kembali'),
('PJM-1766848395052', 'A005', 'BK06', '2025-12-27', '2026-01-03', '2025-12-27', 0.00, 'Kembali');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`id_anggota`);

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`kode_buku`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`id_peminjaman`),
  ADD KEY `id_anggota` (`id_anggota`),
  ADD KEY `kode_buku` (`kode_buku`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD CONSTRAINT `peminjaman_ibfk_1` FOREIGN KEY (`id_anggota`) REFERENCES `anggota` (`id_anggota`),
  ADD CONSTRAINT `peminjaman_ibfk_2` FOREIGN KEY (`kode_buku`) REFERENCES `buku` (`kode_buku`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
