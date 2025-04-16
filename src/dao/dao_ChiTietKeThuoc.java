package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.ConnectDB;
import entity.ent_ChiTietKeThuoc;

public class dao_ChiTietKeThuoc {

    public ArrayList<ent_ChiTietKeThuoc> getAllChiTietKeThuoc() {
        ArrayList<ent_ChiTietKeThuoc> list = new ArrayList<>();
        String sql = "SELECT ct.maKe, ct.maThuoc, t.tenThuoc, dv.tenDV, dm.tenDM, ct.hanSuDung, ct.soLuong " +
                     "FROM CTKeThuoc ct " +
                     "JOIN Thuoc t ON ct.maThuoc = t.maThuoc " +
                     "JOIN DonVi dv ON ct.maDonVi = dv.maDV " +
                     "JOIN DanhMucThuoc dm ON ct.maDM = dm.maDM " +
                     "ORDER BY ct.maKe";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ent_ChiTietKeThuoc ct = new ent_ChiTietKeThuoc(
                    rs.getString("maKe"),
                    rs.getString("maThuoc"),
                    rs.getString("tenThuoc"),
                    rs.getString("tenDV"),
                    rs.getString("tenDM"),
                    rs.getDate("hanSuDung").toLocalDate(),
                    rs.getInt("soLuong")
                );
                list.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public ArrayList<ent_ChiTietKeThuoc> getChiTietKeThuocByMaKe(String maKe) {
        ArrayList<ent_ChiTietKeThuoc> list = new ArrayList<>();
        String sql = "SELECT ct.maKe, ct.maThuoc, t.tenThuoc, dv.tenDV, dm.tenDM, ct.hanSuDung, ct.soLuong " +
                     "FROM CTKeThuoc ct " +
                     "JOIN Thuoc t ON ct.maThuoc = t.maThuoc " +
                     "JOIN DonVi dv ON ct.maDonVi = dv.maDV " +
                     "JOIN DanhMucThuoc dm ON ct.maDM = dm.maDM " +
                     "WHERE ct.maKe = ? " +
                     "ORDER BY ct.maThuoc";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, maKe);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ent_ChiTietKeThuoc ct = new ent_ChiTietKeThuoc(
                        rs.getString("maKe"),
                        rs.getString("maThuoc"),
                        rs.getString("tenThuoc"),
                        rs.getString("tenDV"),
                        rs.getString("tenDM"),
                        rs.getDate("hanSuDung").toLocalDate(),
                        rs.getInt("soLuong")
                    );
                    list.add(ct);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) {
        dao_ChiTietKeThuoc dao = new dao_ChiTietKeThuoc();
        ArrayList<ent_ChiTietKeThuoc> list = dao.getAllChiTietKeThuoc();

        if (list.isEmpty()) {
            System.out.println("Không có dữ liệu chi tiết kệ thuốc.");
        } else {
            System.out.println("Danh sách chi tiết thuốc trong kệ:");
            for (ent_ChiTietKeThuoc ct : list) {
                System.out.println(ct);
            }
        }
    }
}
