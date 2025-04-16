package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

import controller.ConnectDB;
import entity.ent_thuoc;

import java.time.LocalDate;

public class dao_thuoc {

    public static ArrayList<ent_thuoc> getAllThuoc() {
        ArrayList<ent_thuoc> list = new ArrayList<>();
        String sql = "SELECT t.maThuoc, t.tenThuoc, dv.tenDV AS tenDonVi, dm.tenDM AS tenDanhMuc, ncc.tenNCC, " +
                     "t.hanSuDung, t.giaNhap, t.giaBan, t.xuatXu " +
                     "FROM Thuoc t " +
                     "JOIN DonVi dv ON t.maDonVi = dv.maDV " +
                     "JOIN DanhMucThuoc dm ON t.maDM = dm.maDM " +
                     "JOIN NhaCungCap ncc ON t.maNCC = ncc.maNCC " +
                     "ORDER BY t.maThuoc";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ent_thuoc thuoc = new ent_thuoc(
                    rs.getString("maThuoc"),
                    rs.getString("tenThuoc"),
                    rs.getString("tenDonVi"),
                    rs.getString("tenDanhMuc"),
                    rs.getString("tenNCC"),
                    rs.getDate("hanSuDung") != null ? rs.getDate("hanSuDung").toLocalDate() : null,
                    rs.getDouble("giaNhap"),
                    rs.getDouble("giaBan"),
                    rs.getString("xuatXu")
                );
                list.add(thuoc);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all thuoc: " + e.getMessage());
        }
        return list;
    }

    public boolean addThuoc(ent_thuoc thuoc) {
        String sql = "INSERT INTO Thuoc(maThuoc, tenThuoc, maDonVi, maDM, maNCC, hanSuDung, giaNhap, giaBan, xuatXu) " +
                     "VALUES (?, ?, (SELECT maDV FROM DonVi WHERE tenDV = ?), " +
                     "(SELECT maDM FROM DanhMucThuoc WHERE tenDM = ?), " +
                     "(SELECT maNCC FROM NhaCungCap WHERE tenNCC = ?), ?, ?, ?, ?)";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Validate inputs
            if (thuoc.getTenDonVi() == null || thuoc.getTenDanhMuc() == null || thuoc.getTenNCC() == null) {
                System.err.println("Invalid input: tenDonVi, tenDanhMuc, or tenNCC is null");
                return false;
            }

            pstmt.setString(1, thuoc.getMaThuoc());
            pstmt.setString(2, thuoc.getTenThuoc());
            pstmt.setString(3, thuoc.getTenDonVi());
            pstmt.setString(4, thuoc.getTenDanhMuc());
            pstmt.setString(5, thuoc.getTenNCC());
            pstmt.setDate(6, thuoc.getHanSuDung() != null ? Date.valueOf(thuoc.getHanSuDung()) : null);
            pstmt.setDouble(7, thuoc.getGiaNhap());
            pstmt.setDouble(8, thuoc.getGiaBan());
            pstmt.setString(9, thuoc.getXuatXu());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding thuoc: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteThuoc(String maThuoc) {
        String sql = "DELETE FROM Thuoc WHERE maThuoc = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, maThuoc);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting thuoc: " + e.getMessage());
            return false;
        }
    }

    public ent_thuoc getThuocByMa(String maThuoc) {
        ent_thuoc thuoc = null;
        String sql = "SELECT t.maThuoc, t.tenThuoc, dv.tenDV AS tenDonVi, dm.tenDM AS tenDanhMuc, ncc.tenNCC, " +
                     "t.hanSuDung, t.giaNhap, t.giaBan, t.xuatXu " +
                     "FROM Thuoc t " +
                     "JOIN DonVi dv ON t.maDonVi = dv.maDV " +
                     "JOIN DanhMucThuoc dm ON t.maDM = dm.maDM " +
                     "JOIN NhaCungCap ncc ON t.maNCC = ncc.maNCC " +
                     "WHERE t.maThuoc = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, maThuoc);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    thuoc = new ent_thuoc(
                        rs.getString("maThuoc"),
                        rs.getString("tenThuoc"),
                        rs.getString("tenDonVi"),
                        rs.getString("tenDanhMuc"),
                        rs.getString("tenNCC"),
                        rs.getDate("hanSuDung") != null ? rs.getDate("hanSuDung").toLocalDate() : null,
                        rs.getDouble("giaNhap"),
                        rs.getDouble("giaBan"),
                        rs.getString("xuatXu")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching thuoc by maThuoc: " + e.getMessage());
        }
        return thuoc;
    }

    public boolean updateThuoc(ent_thuoc thuoc) {
        String sql = "UPDATE Thuoc SET tenThuoc = ?, maDonVi = (SELECT maDV FROM DonVi WHERE tenDV = ?), " +
                     "maDM = (SELECT maDM FROM DanhMucThuoc WHERE tenDM = ?), " +
                     "maNCC = (SELECT maNCC FROM NhaCungCap WHERE tenNCC = ?), " +
                     "hanSuDung = ?, giaNhap = ?, giaBan = ?, xuatXu = ? WHERE maThuoc = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Validate inputs
            if (thuoc.getTenDonVi() == null || thuoc.getTenDanhMuc() == null || thuoc.getTenNCC() == null) {
                System.err.println("Invalid input: tenDonVi, tenDanhMuc, or tenNCC is null");
                return false;
            }

            pstmt.setString(1, thuoc.getTenThuoc());
            pstmt.setString(2, thuoc.getTenDonVi());
            pstmt.setString(3, thuoc.getTenDanhMuc());
            pstmt.setString(4, thuoc.getTenNCC());
            pstmt.setDate(5, thuoc.getHanSuDung() != null ? Date.valueOf(thuoc.getHanSuDung()) : null);
            pstmt.setDouble(6, thuoc.getGiaNhap());
            pstmt.setDouble(7, thuoc.getGiaBan());
            pstmt.setString(8, thuoc.getXuatXu());
            pstmt.setString(9, thuoc.getMaThuoc());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating thuoc: " + e.getMessage());
            return false;
        }
    }
}