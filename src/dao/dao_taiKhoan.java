package dao;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.ConnectDB;
import entity.ent_taiKhoan;

public class dao_taiKhoan {
    public ArrayList<ent_taiKhoan> getAllTaiKhoan() {
        ArrayList<ent_taiKhoan> list = new ArrayList<>();
        String sql = "SELECT tk.maNV, tk.tenDN, tk.matKhau, tk.maChucVu, cv.tenChucVu " +
                "FROM TaiKhoan tk " +
                "JOIN ChucVu cv ON tk.maChucVu = cv.maChucVu " +
                "ORDER BY tk.maNV";

        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new ent_taiKhoan(
                    rs.getString("maNV"), 
                    rs.getString("tenDN"), 
                    rs.getString("matKhau"), 
                    rs.getString("tenChucVu")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ent_taiKhoan getTaiKhoanByUserName(String userName) {
        String sql = "SELECT * FROM TaiKhoan WHERE tenDN = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, userName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ent_taiKhoan(
                    		rs.getString("maNV"), 
                            rs.getString("tenDN"), 
                            rs.getString("matKhau"), 
                            rs.getString("maChucVu")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

  
    
    
    public boolean createTaiKhoan(ent_taiKhoan taiKhoan) {
        // Kiểm tra xem tài khoản đã tồn tại hay chưa
        if (getTaiKhoanByUserName(taiKhoan.getTenDangNhap()) != null) {
            System.out.println("Tài khoản đã tồn tại!");
            return false;
        }
        
        String sql = "INSERT INTO TaiKhoan (maNV, tenDN, matKhau, maChucVu) VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, taiKhoan.getMaTK());
            stmt.setString(2, taiKhoan.getTenDangNhap());
            stmt.setString(3, taiKhoan.getMatKhau());
            stmt.setString(4, taiKhoan.getChucVu());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


//    public boolean updateTaiKhoan(ent_taiKhoan taiKhoan) {
//        String sql = "UPDATE TaiKhoan SET tenDN = ?, matKhau = ?, maChucVu = ? WHERE maNV = ?";
//        try (Connection con = ConnectDB.getConnection();
//             PreparedStatement stmt = con.prepareStatement(sql)) {
//            stmt.setString(1, taiKhoan.getTenDangNhap());
//            stmt.setString(2, taiKhoan.getMatKhau());
//            stmt.setString(3, taiKhoan.getChucVu());
//            stmt.setString(4, taiKhoan.getMaTK());
//
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    
    public boolean updateTaiKhoan(ent_taiKhoan taiKhoan) {
        // Check if the maChucVu exists in the ChucVu table
        if (!isChucVuExist(taiKhoan.getChucVu())) {
            System.out.println("Mã chức vụ không tồn tại trong bảng ChucVu.");
            return false;  // Or handle the case appropriately
        }

        String updateTaiKhoanSQL = "UPDATE TaiKhoan SET tenDN = ?, matKhau = ?, maChucVu = ? WHERE maNV = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(updateTaiKhoanSQL)) {
            
            stmt.setString(1, taiKhoan.getTenDangNhap());
            stmt.setString(2, taiKhoan.getMatKhau());
            stmt.setString(3, taiKhoan.getChucVu());
            stmt.setString(4, taiKhoan.getMaTK());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Utility method to check if the maChucVu exists in ChucVu table
    private boolean isChucVuExist(String maChucVu) {
        String sql = "SELECT COUNT(*) FROM ChucVu WHERE maChucVu = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maChucVu);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean deleteTaiKhoan(String maNV) {
        String sql = "DELETE FROM TaiKhoan WHERE maNV = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maNV);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}