package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.ConnectDB;
import entity.ent_HoaDon;

import java.time.LocalDate;

public class dao_HoaDon {

    public ArrayList<ent_HoaDon> getAllHoaDon() {
        ArrayList<ent_HoaDon> list = new ArrayList<>();
        String sql = "SELECT hd.maHoaDon, hd.ngayLap, hd.tongTien, hd.hinhThucThanhToan, " +
                     "nv.hoTen AS nguoiLap, kh.hoTen AS khachHang, th.mucThue AS thue " +
                     "FROM HoaDon hd " +
                     "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                     "JOIN KhachHang kh ON hd.maKH = kh.maKH " +
                     "LEFT JOIN Thue th ON hd.maThue = th.maThue " +
                     "ORDER BY hd.ngayLap DESC";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String maHoaDon = rs.getString("maHoaDon");
                LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
                String nguoiLap = rs.getString("nguoiLap");
                String khachHang = rs.getString("khachHang");
                double thue = rs.getDouble("thue"); // Nếu null sẽ là 0.0
                String hinhThucThanhToan = rs.getString("hinhThucThanhToan");
                double tongTien = rs.getDouble("tongTien");

                ent_HoaDon hd = new ent_HoaDon(maHoaDon, ngayLap, nguoiLap, khachHang, thue, hinhThucThanhToan, tongTien);
                list.add(hd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public ent_HoaDon getHoaDonByMaHoaDon(String maHoaDon) {
        ent_HoaDon hoaDon = null;
        String sql = "SELECT hd.maHoaDon, hd.ngayLap, hd.tongTien, hd.hinhThucThanhToan, " +
                     "hd.maNV, nv.hoTen AS nguoiLap, kh.maKH, kh.hoTen AS khachHang, kh.sDT, " +
                     "th.mucThue AS thue " +
                     "FROM HoaDon hd " +
                     "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                     "JOIN KhachHang kh ON hd.maKH = kh.maKH " +
                     "LEFT JOIN Thue th ON hd.maThue = th.maThue " +
                     "WHERE hd.maHoaDon = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, maHoaDon);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String maHoaDonResult = rs.getString("maHoaDon");
                    LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
                    String maNV = rs.getString("maNV");
                    String nguoiLap = rs.getString("nguoiLap");
                    String khachHang = rs.getString("khachHang");
                    String sdtKhachHang = rs.getString("sDT");
                    double thue = rs.getDouble("thue");
                    String hinhThucThanhToan = rs.getString("hinhThucThanhToan");
                    double tongTien = rs.getDouble("tongTien");

                    hoaDon = new ent_HoaDon(maHoaDonResult, ngayLap, maNV, nguoiLap, khachHang, sdtKhachHang, thue, hinhThucThanhToan, tongTien);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hoaDon;
    }


    public static void main(String[] args) {
        dao_HoaDon dao = new dao_HoaDon();
         

        
            System.out.println(dao.getHoaDonByMaHoaDon("HD001").toString());
        
    }
}
