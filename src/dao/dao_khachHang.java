// dao_khachHang.java
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.ConnectDB;
import entity.ent_khachHang;

public class dao_khachHang {
	public ArrayList<ent_khachHang> getAllKhachHang() {
		ArrayList<ent_khachHang> list = new ArrayList<>();
		String sql = "SELECT maKH, hoTen, sDT, gioiTinh FROM KhachHang";

		try (Connection con = ConnectDB.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				ent_khachHang kh = new ent_khachHang(rs.getString("maKH"), rs.getString("hoTen"), rs.getString("sDT"),
						rs.getString("gioiTinh"));
				list.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean addKhachHang(ent_khachHang kh) {
		String sql = "INSERT INTO KhachHang(maKH, hoTen, sDT, gioiTinh) VALUES (?, ?, ?, ?)";
		try (Connection con = ConnectDB.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, kh.getMaKh());
			pstmt.setString(2, kh.getHoTen());
			pstmt.setString(3, kh.getsDT());
			pstmt.setString(4, kh.getGioiTinh());

			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteKhachHang(String maKH) {
		String sql = "DELETE FROM KhachHang WHERE maKH = ?";
		try (Connection con = ConnectDB.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, maKH);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ent_khachHang getKhachHangByMa(String maKh) {
		ent_khachHang kh = null;
		String sql = "SELECT maKH, hoTen, sDT, gioiTinh FROM KhachHang WHERE maKH = ?";

		try (Connection con = ConnectDB.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, maKh);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					kh = new ent_khachHang(rs.getString("maKH"), rs.getString("hoTen"), rs.getString("sDT"),
							rs.getString("gioiTinh"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kh;
	}

	public boolean updateKhachHang(ent_khachHang kh) {
		String sql = "UPDATE KhachHang SET hoTen = ?, sDT = ?, gioiTinh = ? WHERE maKH = ?";

		try (Connection con = ConnectDB.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, kh.getHoTen());
			pstmt.setString(2, kh.getsDT());
			pstmt.setString(3, kh.getGioiTinh());
			pstmt.setString(4, kh.getMaKh());

			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}