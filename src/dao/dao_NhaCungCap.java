package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.ConnectDB;
import entity.ent_NhaCungCap;

public class dao_NhaCungCap {

    public ArrayList<ent_NhaCungCap> getAllNhaCungCap() {
        ArrayList<ent_NhaCungCap> list = new ArrayList<>();
        String sql = "SELECT * FROM NhaCungCap ORDER BY maNCC";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ent_NhaCungCap ncc = new ent_NhaCungCap(
                        rs.getString("maNCC"),
                        rs.getString("tenNCC"),
                        rs.getString("diaChi"),
                        rs.getString("sDT")
                );
                list.add(ncc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addNhaCungCap(ent_NhaCungCap ncc) {
        String sql = "INSERT INTO NhaCungCap(maNCC, tenNCC, diaChi, sDT) VALUES (?, ?, ?, ?)";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, ncc.getMaNCC());
            pstmt.setString(2, ncc.getTenNCC());
            pstmt.setString(3, ncc.getDiaChi());
            pstmt.setString(4, ncc.getsDT());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateNhaCungCap(ent_NhaCungCap ncc) {
        String sql = "UPDATE NhaCungCap SET tenNCC = ?, diaChi = ?, sDT = ? WHERE maNCC = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, ncc.getTenNCC());
            pstmt.setString(2, ncc.getDiaChi());
            pstmt.setString(3, ncc.getsDT());
            pstmt.setString(4, ncc.getMaNCC());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteNhaCungCap(String maNCC) {
        String sql = "DELETE FROM NhaCungCap WHERE maNCC = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, maNCC);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ent_NhaCungCap getNhaCungCapByMa(String maNCC) {
        ent_NhaCungCap ncc = null;
        String sql = "SELECT * FROM NhaCungCap WHERE maNCC = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, maNCC);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ncc = new ent_NhaCungCap(
                            rs.getString("maNCC"),
                            rs.getString("tenNCC"),
                            rs.getString("diaChi"),
                            rs.getString("sDT")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ncc;
    }
}
