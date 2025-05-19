package dao;

import java.sql.*;
import java.util.ArrayList;

import controller.ConnectDB;
import entity.ent_PhieuDoi;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class dao_PhieuDoi {

    public ArrayList<ent_PhieuDoi> getAll() {
        ArrayList<ent_PhieuDoi> ds = new ArrayList<>();
        String sql = "SELECT * FROM PhieuDoiHang";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String maPhieu = rs.getString("maPhieuDoi");
                LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
                String maNV = rs.getString("maNV");
                String maKH = rs.getString("maKH");
                Double tongTien = rs.getDouble("tongTien");

                ds.add(new ent_PhieuDoi(maPhieu, ngayLap, maNV, maKH, tongTien));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public boolean insert(ent_PhieuDoi pd) {
        String sql = "INSERT INTO PhieuDoiHang(maPhieuDoi, ngayLap, maNV, maKH, tongTien) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, pd.getMaPhieuDoi());
            stmt.setDate(2, Date.valueOf(pd.getNgayLap()));
            stmt.setString(3, pd.getMaNV());
            stmt.setString(4, pd.getMaKH());
            stmt.setDouble(5, pd.getTongTien());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(ent_PhieuDoi pd) {
        String sql = "UPDATE PhieuDoiHang SET ngayLap = ?, maNV = ?, maKH = ?, tongTien = ? WHERE maPhieuDoi = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(pd.getNgayLap()));
            stmt.setString(2, pd.getMaNV());
            stmt.setString(3, pd.getMaKH());
            stmt.setDouble(4, pd.getTongTien());
            stmt.setString(5, pd.getMaPhieuDoi());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String maPhieuDoi) {
        String sql = "DELETE FROM PhieuDoiHang WHERE maPhieuDoi = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, maPhieuDoi);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(String[] args) {
        dao_PhieuDoi dao = new dao_PhieuDoi();

        // 1. Tạo 1 phiếu đổi mới
        ent_PhieuDoi phieu = new ent_PhieuDoi(
                "PD001", // Mã phiếu đổi (cần đảm bảo không bị trùng)
                LocalDate.now(), // Ngày lập
                "NV001", // Mã nhân viên (đảm bảo tồn tại trong DB)
                "KH001", // Mã khách hàng (đảm bảo tồn tại trong DB)
                new Double("150000") // Tổng tiền
        );

        // 2. Gọi hàm lưu vào database
        boolean success = dao.insert(phieu);
        if (success) {
            System.out.println("Thêm phiếu đổi thành công!");
        } else {
            System.out.println("Thêm phiếu đổi thất bại!");
        }

        // 3. Lấy và hiển thị danh sách tất cả phiếu đổi
        System.out.println("\nDanh sách phiếu đổi:");
        ArrayList<ent_PhieuDoi> danhSach = dao.getAll();
        for (ent_PhieuDoi pd : danhSach) {
            System.out.println(pd);
        }
    }
}
