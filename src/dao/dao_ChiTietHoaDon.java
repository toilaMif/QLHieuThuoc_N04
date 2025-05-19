package dao;

import entity.ent_ChiTietHoaDon;
import java.sql.*;
import java.util.ArrayList;

import controller.ConnectDB;

public class dao_ChiTietHoaDon {

  

    public ArrayList<ent_ChiTietHoaDon> getAllByMaHoaDon(String maHoaDon) {
        ArrayList<ent_ChiTietHoaDon> list = new ArrayList<>();
        String sql = "SELECT ct.maHoaDon, t.tenThuoc, ct.soLuong, dv.tenDV, ct.giaBan " +
                     "FROM CTHoaDon ct " +
                     "JOIN Thuoc t ON ct.maThuoc = t.maThuoc " +
                     "JOIN DonVi dv ON t.maDonVi = dv.maDV " +
                     "WHERE ct.maHoaDon = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, maHoaDon);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String maHD = rs.getString("maHoaDon");
                    String tenThuoc = rs.getString("tenThuoc");
                    int soLuong = rs.getInt("soLuong");
                    String donVi = rs.getString("tenDV");
                    double giaBan = rs.getDouble("giaBan");

                    ent_ChiTietHoaDon chiTiet = new ent_ChiTietHoaDon(maHD, tenThuoc, soLuong, donVi, giaBan);
                    list.add(chiTiet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) {
        dao_ChiTietHoaDon dao = new dao_ChiTietHoaDon();

        
        String maHoaDonTest = "HD001";
        System.out.println("\nChi tiết hóa đơn với mã: " + maHoaDonTest);
        ArrayList<ent_ChiTietHoaDon> chiTietByMa = dao.getAllByMaHoaDon(maHoaDonTest);
        for (ent_ChiTietHoaDon ct : chiTietByMa) {
            System.out.println(ct);
        }
    }
}
