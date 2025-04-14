package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.ConnectDB;
import entity.ent_KeThuoc;

public class dao_KeThuoc {

    // Lấy toàn bộ danh sách kệ thuốc
    public ArrayList<ent_KeThuoc> getAllKeThuoc() {
        ArrayList<ent_KeThuoc> list = new ArrayList<>();
        String sql = "SELECT * FROM KeThuoc ORDER BY maKe";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ent_KeThuoc ke = new ent_KeThuoc(
                    rs.getString("maKe"),
                    rs.getString("tenKe"),
                    rs.getInt("sucChuaToDa"),
                    rs.getInt("soLuong")
                );
                list.add(ke);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm một kệ thuốc mới
    public boolean addKeThuoc(ent_KeThuoc ke) {
        String sql = "INSERT INTO KeThuoc(maKe, tenKe, sucChuaToiDa, soLuong) VALUES (?, ?, ?, ?)";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, ke.getMaKe());
            pstmt.setString(2, ke.getTenKe());
            pstmt.setInt(3, ke.getSucChuaToiDa());
            pstmt.setInt(4, ke.getSoLuong());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin một kệ thuốc
    public boolean updateKeThuoc(ent_KeThuoc ke) {
        String sql = "UPDATE KeThuoc SET tenKe = ?, sucChuaToiDa = ?, soLuong = ? WHERE maKe = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, ke.getTenKe());
            pstmt.setInt(2, ke.getSucChuaToiDa());
            pstmt.setInt(3, ke.getSoLuong());
            pstmt.setString(4, ke.getMaKe());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa một kệ thuốc theo mã
    public boolean deleteKeThuoc(String maKe) {
        String sql = "DELETE FROM KeThuoc WHERE maKe = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, maKe);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy kệ thuốc theo mã
    public ent_KeThuoc getKeThuocByMa(String maKe) {
        ent_KeThuoc ke = null;
        String sql = "SELECT * FROM KeThuoc WHERE maKe = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, maKe);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                ke = new ent_KeThuoc(
                    rs.getString("maKe"),
                    rs.getString("tenKe"),
                    rs.getInt("sucChuaToiDa"),
                    rs.getInt("soLuong")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ke;
    }
}
