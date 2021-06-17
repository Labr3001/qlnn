/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */

public class DatabaseConnect {
    private Connection con;

    public DatabaseConnect() throws SQLException {
        var port = 1433;
        var db = "QuanLyNhaNghi6";
        var user = "sa";
        var password = "123456";
        var server = "ADMIN";
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(user);
        ds.setPassword(password);
        ds.setDatabaseName(db);
        ds.setServerName(server);
        ds.setPortNumber(port);
        con = (Connection) ds.getConnection();
    }
    //Object
    public List<Object[]> layThongTinPhong() throws SQLException{
        List<Object[]> list = new ArrayList<>(); //Khai báo một list thông tin phòng
        Statement statement = con.createStatement();
        ResultSet rs= statement.executeQuery("SELECT Phong.tenPhong, Phong.loaiPhong, BangGiaPhong.gia "
                    + "FROM Phong, BangGiaPhong "
                    + "WHERE Phong.loaiPhong = BangGiaPhong.loaiPhong"); //Thực hiện câu truy vấn, trả kết quả về rs
        while(rs.next()){ //Lấy dữ liệu từ rs
            String tenPhong = rs.getString(1);//tên phòng là cột 1 của kết quả truy vấn
            String loaiPhong = rs.getString(2);// loại phòng là cột 2
            int gia = rs.getInt(3); //cột 3
            Object a[] = new Object[]{
            tenPhong,loaiPhong,gia
            };
            list.add(a);
        }
        return list;
    }
    public List<Object[]> layThongTinPhong(String e) throws SQLException{
        List<Object[]> list = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs= statement.executeQuery("SELECT Phong.tenPhong, Phong.loaiPhong, BangGiaPhong.gia "
                    + "FROM Phong, BangGiaPhong "
                    + "WHERE Phong.loaiPhong = BangGiaPhong.loaiPhong"+ " AND Phong.loaiphong = N'"+e+"'"  );
        while(rs.next()){
            String tenPhong = rs.getString(1);
            String loaiPhong = rs.getString(2);
            int gia = rs.getInt(3);
            Object o[] = new Object[]{
            tenPhong,loaiPhong,gia
            };
            list.add(o);
        }
        return list;
    }
    public List<Object[]> layThongTinPhong(int e) throws SQLException{
        List<Object[]> list = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs= statement.executeQuery("SELECT Phong.tenPhong, Phong.loaiPhong, BangGiaPhong.gia "
                    + "FROM Phong, BangGiaPhong "
                    + "WHERE Phong.loaiPhong = BangGiaPhong.loaiPhong"+ " AND Phong.tenphong = "+e  );
        while(rs.next()){
            String tenPhong = rs.getString(1);
            String loaiPhong = rs.getString(2);
            int gia = rs.getInt(3);
            Object o[] = new Object[]{
            tenPhong,loaiPhong,gia
            };
            list.add(o);
        }
        return list;
    }
    public void themPhong(String tenPhong, String loaiPhong) throws SQLException{
        Statement statement = con.createStatement();
        statement.executeUpdate("Insert into Phong\n" +
            "Values("+tenPhong+",N'"+loaiPhong+"')");
    }
    public void suaPhong(String tenPhong, String loaiPhong) throws SQLException{
        Statement statement = con.createStatement();
        statement.executeUpdate("Update Phong\n" +
            "set Phong.loaiPhong = N'"+loaiPhong+"'\n" +
            "where Phong.tenPhong = "+ tenPhong);
    }
    public void xoaPhong(String tenPhong) throws SQLException{
        Statement statement = con.createStatement();
        statement.executeUpdate("DELETE FROM Phong\n" +
                            "WHERE Phong.tenPhong = "+ tenPhong);
    }
    public List<Object[]> layThongTinPhongTrong() throws SQLException{
        List<Object[]> list = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs= statement.executeQuery("SELECT Phong.tenPhong, Phong.loaiPhong, BangGiaPhong.gia\n" +
"FROM Phong,BangGiaPhong\n" +
"WHERE Phong.loaiPhong = BangGiaPhong.loaiPhong\n" +
"EXCEPT\n" +
"SELECT Phong.tenPhong, Phong.loaiPhong, BangGiaPhong.gia\n" +
"FROM Phong,BangGiaPhong\n" +
"WHERE Phong.loaiPhong = BangGiaPhong.loaiPhong\n" +
"AND tenPhong IN (\n" +
"SELECT PhongDat.tenPhong\n" +
"FROM PhongDat\n" +
"Where PhongDat.gioTra is null\n" +
")");
        while(rs.next()){
            String tenPhong = rs.getString(1);
            String loaiPhong = rs.getString(2);
            int gia = rs.getInt(3);
            Object o[] = new Object[]{
            tenPhong,loaiPhong,gia
            };
            list.add(o);
        }
        return list;
    }
    public List<Object[]> layThongTinPhongTrong(String e) throws SQLException{
        List<Object[]> list = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs= statement.executeQuery("SELECT Phong.tenPhong, Phong.loaiPhong, BangGiaPhong.gia\n" +
"FROM Phong,BangGiaPhong\n" +
"WHERE Phong.loaiPhong = BangGiaPhong.loaiPhong\n" +
"AND Phong.loaiPhong=N'"+e+"'\n" +
"EXCEPT\n" +
"SELECT Phong.tenPhong, Phong.loaiPhong, BangGiaPhong.gia\n" +
"FROM Phong,BangGiaPhong\n" +
"WHERE Phong.loaiPhong = BangGiaPhong.loaiPhong\n" +
"AND tenPhong IN (\n" +
"SELECT PhongDat.tenPhong\n" +
"FROM PhongDat\n" +
"Where PhongDat.gioTra is null\n" +
")");
        while(rs.next()){
            String tenPhong = rs.getString(1);
            String loaiPhong = rs.getString(2);
            int gia = rs.getInt(3);
            Object o[] = new Object[]{
            tenPhong,loaiPhong,gia
            };
            list.add(o);
        }
        return list;
    }
    public List<String> timKiemKhachHang(String CCCD){
        List<String> list = new ArrayList<>();
        try {
            
            Statement statement = con.createStatement();
            ResultSet rs= statement.executeQuery("SELECT KhachHang.tenKH, KhachHang.namSinhKH\n" +
                    "FROM KhachHang\n" +
                    "WHERE KhachHang.CCCD = '"+CCCD+"'");
            while (rs.next()) {
                String tenKH = rs.getString(1);
                String namSinh = rs.getString(2);
                list.add(tenKH); 
                list.add(namSinh);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;//thông tin trống => null
    }
    public void themKhachHang(String CCCD, String TenKH, String namSinh) throws SQLException{
        Statement statement = con.createStatement();
        statement.executeUpdate("INSERT INTO KhachHang\n" +
"VALUES ('"+CCCD+"', N'"+TenKH+"',"+ namSinh+")");
    }
    public void themPhongDat(String tenPhong,String CCCD) throws SQLException{
        Statement statement = con.createStatement();
        statement.executeUpdate("INSERT INTO PhongDat (tenPhong,CCCD)\n" + 
"VALUES ('"+tenPhong+"', '"+CCCD+"')");
    }
    public List<Object[]> layThongTinPhongDat() throws SQLException{
        List<Object[]> list = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs= statement.executeQuery("SELECT PhongDat.idPhongDat, Phong.tenPhong, KhachHang.tenKH, BangGiaPhong.gia, PhongDat.gioDat\n" +
"FROM Phong, KhachHang, BangGiaPhong, PhongDat\n" +
"WHERE Phong.loaiPhong = BangGiaPhong.loaiPhong\n" +
"AND Phong.tenPhong = PhongDat.tenPhong\n" +
"AND KhachHang.CCCD = PhongDat.CCCD\n" +
"AND PhongDat.gioTra is null");
        while(rs.next()){
            int idPhongDat = rs.getInt(1);
            String tenPhong = rs.getString(2);
            String tenKH = rs.getString(3);
            int gia = rs.getInt(4);
            LocalTime gioDat = rs.getTime(5).toLocalTime();
            LocalDate ngaydat = rs.getDate(5).toLocalDate();
            LocalDateTime ngayGioDat = LocalDateTime.of(ngaydat, gioDat);
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM HH:mm");
            String gioDatString = myFormatObj.format(ngayGioDat);
            Object o[] = new Object[]{
            idPhongDat,tenPhong,tenKH,gia,gioDatString
            };
            list.add(o);
        }
        return list;
    }
    public boolean kiemTraPhong(String tenPhong){
        try {
            
            Statement statement = con.createStatement();
            ResultSet rs= statement.executeQuery("Select *\n" +
"FROM Phong\n" +
"Where Phong.tenPhong="+tenPhong);
            if(rs.next())
                return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public List<Object[]> layThongTinPhongDat(String tenKhach) throws SQLException{
        List<Object[]> list = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs= statement.executeQuery("SELECT PhongDat.idPhongDat, Phong.tenPhong, KhachHang.tenKH, BangGiaPhong.gia, PhongDat.gioDat\n" +
"FROM Phong, KhachHang, BangGiaPhong, PhongDat\n" +
"WHERE Phong.loaiPhong = BangGiaPhong.loaiPhong\n" +
"AND Phong.tenPhong = PhongDat.tenPhong\n" +
"AND KhachHang.CCCD = PhongDat.CCCD\n" +
"AND PhongDat.gioTra is null\n" +
"AND KhachHang.tenKH like N'%"+tenKhach+"%'");
        while(rs.next()){
            int IDPhongDat = rs.getInt(1);
            String tenPhong = rs.getString(2);
            String tenKH = rs.getString(3);
            int gia = rs.getInt(4);
            LocalTime gioDat = rs.getTime(5).toLocalTime();
            LocalDate ngaydat = rs.getDate(5).toLocalDate();
            LocalDateTime ngayGioDat = LocalDateTime.of(ngaydat, gioDat);
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM HH:mm");
            String gioDatString = myFormatObj.format(ngayGioDat);
            Object o[] = new Object[]{
            IDPhongDat,tenPhong,tenKH,gia,gioDatString
            };
            list.add(o);
        }
        return list;
    }
    public List<Object[]> layThongTinSanPham() throws SQLException{
        List<Object[]> list = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs= statement.executeQuery("SELECT SanPham.idSanPham, SanPham.tenSanPham, SanPham.gia\n" +
"FROM SanPham");
        while(rs.next()){
            int idSanPham = rs.getInt(1);
            String tenSanPham = rs.getString(2);
            int giaSanPham = rs.getInt(3);
            Object o[] = new Object[]{
            idSanPham, tenSanPham,giaSanPham
            };
            list.add(o);
        }
        return list;
    }
    public List<Object[]> layThongTinSanPham(String tenSP) throws SQLException{
        List<Object[]> list = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs= statement.executeQuery("SELECT Sanpham.idSanPham, SanPham.tenSanPham, SanPham.gia\n" +
"FROM SanPham\n" +
"WHERE SanPham.tenSanPham like N'%"+tenSP+"%'");
        while(rs.next()){
            int idSanPham = rs.getInt(1);
            String tenSanPham = rs.getString(2);
            int giaSanPham = rs.getInt(3);
            Object o[] = new Object[]{
            idSanPham, tenSanPham,giaSanPham
            };
            list.add(o);
        }
        return list;
    }
    public void themPhuThu(String idPhongDat, String idSanPham, String soLuong) throws SQLException{
        Statement statement = con.createStatement();
        statement.executeUpdate("INSERT INTO PhuThu (idPhongDat,idSanPham,soLuong)\n" +
"VALUES ("+idPhongDat+","+idSanPham+","+soLuong+")");
    }
    public List<Object[]> layThongTinPhuThu(String idPhongDat) throws SQLException{
        List<Object[]> list = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs= statement.executeQuery("SELECT PhongDat.idPhongDat, KhachHang.tenKH, Phong.tenPhong, SanPham.tenSanPham, SanPham.gia, PhuThu.soLuong\n" +
"FROM Phong, KhachHang, SanPham, PhuThu, PhongDat\n" +
"WHERE PhongDat.tenPhong = Phong.tenPhong\n" +
"AND PhongDat.CCCD = KhachHang.CCCD\n" +
"AND PhongDat.idPhongDat = PhuThu.idPhongDat\n" +
"AND SanPham.idSanPham = PhuThu.idSanPham\n" +
"AND PhongDat.idPhongDat = "+idPhongDat);
        while(rs.next()){
            int idPD = rs.getInt(1);
            String tenKhachHang = rs.getString(2);
            String tenPhong = rs.getString(3);
            String tenSanPham = rs.getString(4);
            int gia = rs.getInt(5);
            int soLuong = rs.getInt(6);
            long thanhtien = gia*soLuong;
            Object o[] = new Object[]{
            idPD, tenKhachHang, tenPhong, tenSanPham, gia, soLuong,thanhtien
            };
            list.add(o);
        }
        return list;
    }
    public long layPhuThu(String idPhongDat) throws SQLException{
        long kq = 0;
        Statement statement = con.createStatement();
        ResultSet rs= statement.executeQuery("SELECT SUM(gia*soLuong)\n" +
"FROM Phong, KhachHang, SanPham, PhuThu, PhongDat\n" +
"WHERE PhongDat.tenPhong = Phong.tenPhong\n" +
"AND PhongDat.CCCD = KhachHang.CCCD\n" +
"AND PhongDat.idPhongDat = PhuThu.idPhongDat\n" +
"AND SanPham.idSanPham = PhuThu.idSanPham\n" +
"AND PhongDat.idPhongDat = "+idPhongDat+"\n" +
"Group by PhongDat.idPhongDat");
        if(rs.next()){
            kq=rs.getInt(1);
        }
        return kq;
    }
    public void capNhatGioTra(String idPhongTra) throws SQLException{
        Statement statement = con.createStatement();
        statement.executeUpdate("UPDATE PhongDat\n" +
"SET gioTra = GETDATE()\n" +
"WHERE PhongDat.idPhongDat = "+idPhongTra);
    }
    public List<Object> layThongTinCheckOut(String idPhongDat) throws SQLException{
        List<Object> kq = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs= statement.executeQuery("SELECT KhachHang.tenKH, BangGiaPhong.gia, DATEDIFF(minute, PhongDat.gioDat, PhongDat.gioTra)\n" +
"FROM PhongDat, KhachHang, Phong, BangGiaPhong\n" +
"WHERE PhongDat.CCCD = KhachHang.CCCD\n" +
"AND PhongDat.tenPhong = Phong.tenPhong\n" +
"AND BangGiaPhong.loaiPhong = Phong.loaiPhong\n" +
"AND PhongDat.idPhongDat="+idPhongDat);
        if(rs.next()){
            String tenKhach = rs.getString(1);
            int giaPhong = rs.getInt(2);
            int soPhut = rs.getInt(3);
            kq.add(tenKhach);
            kq.add(giaPhong);
            kq.add(soPhut);
        }
        return kq;
    }
    public boolean kiemTraDangNhap(String name, String password) throws SQLException{
        List<Object> kq = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet rs= statement.executeQuery("SELECT *\n" +
"FROM DangNhap\n" +
"WHERE DangNhap.taiKhoan = '"+name+"'\n" +
"AND DangNhap.matKhau = '"+password+"'");
        if(rs.next()){
            return true;
        }
        return false;
    }
}
