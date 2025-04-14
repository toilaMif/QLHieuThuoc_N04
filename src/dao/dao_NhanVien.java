package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

import controller.ConnectDB;
import entity.ent_NhanVien;

import java.time.LocalDate;

public class dao_NhanVien {

    public ArrayList<ent_NhanVien> getAllNhanVien() {
        ArrayList<ent_NhanVien> list = new ArrayList<>();
        String sql = "SELECT nv.maNV, nv.hoTen, nv.sDT, nv.email, nv.ngaySinh, nv.ngayVaoLam, cv.tenChucVu, nv.gioiTinh " +
                "FROM NhanVien nv " +
                "JOIN ChucVu cv ON nv.maChucVu = cv.maChucVu " +
                "ORDER BY nv.maNV";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ent_NhanVien nv = new ent_NhanVien(
                    rs.getString("maNV"),
                    rs.getString("hoTen"),
                    rs.getString("sDT"),
                    rs.getString("email"),
                    rs.getDate("ngaySinh").toLocalDate(),
                    rs.getDate("ngayVaoLam").toLocalDate(),
                    rs.getString("tenChucVu"),
                    rs.getString("gioiTinh")
                );
                list.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addNhanVien(ent_NhanVien nv) {
        String sql = "INSERT INTO NhanVien(maNV, hoTen, sDT, email, ngaySinh, ngayVaoLam, maChucVu, gioiTinh) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, nv.getMaNV());
            pstmt.setString(2, nv.getHoTen());
            pstmt.setString(3, nv.getsDT());
            pstmt.setString(4, nv.getEmail());
            pstmt.setDate(5, Date.valueOf(nv.getNgaySinh())); 
            pstmt.setDate(6, Date.valueOf(nv.getNgayVaoLam()));
            pstmt.setString(7, nv.getChucVu()); 
            pstmt.setString(8, nv.getGioiTinh());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteNhanVien(String maNV) {
        String sql = "DELETE FROM NhanVien WHERE maNV = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, maNV);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ent_NhanVien getNhanVienByMa(String maNV) {
        ent_NhanVien nv = null;
        String sql = "SELECT nv.*, cv.tenChucVu FROM NhanVien nv JOIN ChucVu cv ON nv.maChucVu = cv.maChucVu WHERE maNV = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, maNV);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    nv = new ent_NhanVien(
                        rs.getString("maNV"),
                        rs.getString("hoTen"),
                        rs.getString("sDT"),
                        rs.getString("email"),
                        rs.getDate("ngaySinh").toLocalDate(),
                        rs.getDate("ngayVaoLam").toLocalDate(),
                        rs.getString("tenChucVu"),
                        rs.getString("gioiTinh")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nv;
    }

    public boolean updateNhanVien(ent_NhanVien nv) {
        String sql = "UPDATE NhanVien SET hoTen = ?, sDT = ?, email = ?, ngaySinh = ?, ngayVaoLam = ?, maChucVu = ?, gioiTinh = ? WHERE maNV = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, nv.getHoTen());
            pstmt.setString(2, nv.getsDT());
            pstmt.setString(3, nv.getEmail());
            pstmt.setDate(4, Date.valueOf(nv.getNgaySinh()));
            pstmt.setDate(5, Date.valueOf(nv.getNgayVaoLam()));
            pstmt.setString(6, nv.getChucVu());
            pstmt.setString(7, nv.getGioiTinh());
            pstmt.setString(8, nv.getMaNV());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
