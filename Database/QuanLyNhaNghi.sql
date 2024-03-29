USE [master]
GO
/****** Object:  Database [QuanLyNhaNghi]    Script Date: 12/4/2021 9:09:43 PM ******/
CREATE DATABASE [QuanLyNhaNghi]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuanLyNhaNghi', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\QuanLyNhaNghi.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QuanLyNhaNghi_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\QuanLyNhaNghi_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [QuanLyNhaNghi] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLyNhaNghi].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLyNhaNghi] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyNhaNghi] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLyNhaNghi] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QuanLyNhaNghi] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLyNhaNghi] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET RECOVERY FULL 
GO
ALTER DATABASE [QuanLyNhaNghi] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLyNhaNghi] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLyNhaNghi] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLyNhaNghi] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLyNhaNghi] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuanLyNhaNghi] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuanLyNhaNghi] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'QuanLyNhaNghi', N'ON'
GO
ALTER DATABASE [QuanLyNhaNghi] SET QUERY_STORE = OFF
GO
USE [QuanLyNhaNghi]
GO
/****** Object:  Table [dbo].[BangGiaPhong]    Script Date: 12/4/2021 9:09:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangGiaPhong](
	[loaiPhong] [nvarchar](30) NOT NULL,
	[gia] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[loaiPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 12/4/2021 9:09:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[CCCD] [varchar](13) NOT NULL,
	[tenKH] [nvarchar](100) NOT NULL,
	[namSinhKH] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[CCCD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Phong]    Script Date: 12/4/2021 9:09:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phong](
	[tenPhong] [int] NOT NULL,
	[loaiPhong] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[tenPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhongDat]    Script Date: 12/4/2021 9:09:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhongDat](
	[idPhongDat] [int] IDENTITY(0,1) NOT NULL,
	[tenPhong] [int] NULL,
	[CCCD] [varchar](13) NULL,
	[gioDat] [datetime] NULL,
	[gioTra] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[idPhongDat] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhuThu]    Script Date: 12/4/2021 9:09:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhuThu](
	[idPhongDat] [int] NOT NULL,
	[idSanPham] [int] NOT NULL,
	[soLuong] [int] NULL,
 CONSTRAINT [phuthu_pk] PRIMARY KEY CLUSTERED 
(
	[idPhongDat] ASC,
	[idSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 12/4/2021 9:09:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[idSanPham] [int] IDENTITY(100,1) NOT NULL,
	[tenSanPham] [nvarchar](100) NULL,
	[gia] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[PhongDat] ADD  DEFAULT (getdate()) FOR [gioDat]
GO
ALTER TABLE [dbo].[Phong]  WITH CHECK ADD FOREIGN KEY([loaiPhong])
REFERENCES [dbo].[BangGiaPhong] ([loaiPhong])
GO
ALTER TABLE [dbo].[PhongDat]  WITH CHECK ADD FOREIGN KEY([CCCD])
REFERENCES [dbo].[KhachHang] ([CCCD])
GO
ALTER TABLE [dbo].[PhongDat]  WITH CHECK ADD FOREIGN KEY([tenPhong])
REFERENCES [dbo].[Phong] ([tenPhong])
GO
ALTER TABLE [dbo].[PhuThu]  WITH CHECK ADD FOREIGN KEY([idPhongDat])
REFERENCES [dbo].[PhongDat] ([idPhongDat])
GO
ALTER TABLE [dbo].[PhuThu]  WITH CHECK ADD FOREIGN KEY([idSanPham])
REFERENCES [dbo].[SanPham] ([idSanPham])
GO
USE [master]
GO
ALTER DATABASE [QuanLyNhaNghi] SET  READ_WRITE 
GO
