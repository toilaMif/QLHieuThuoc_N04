package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.ConnectDB;
import entity.ent_DanhMucThuoc;

public class dao_DanhMuc {
	public ArrayList<ent_DanhMucThuoc> getAllDanhMucThuoc() {
        ArrayList<ent_DanhMucThuoc> list = new ArrayList<>();
        String sql = "SELECT maDM, tenDM FROM DanhMucThuoc ORDER BY maDM";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ent_DanhMucThuoc dm = new ent_DanhMucThuoc(
                    rs.getString("maDM"),
                    rs.getString("tenDM")
                );
                list.add(dm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
