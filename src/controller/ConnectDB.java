package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static Connection con = null;
    private static final String URL = "jdbc:sqlserver://localhost:1433;databasename=QuanLyHieuThuoc;encrypt=false";
    private static final String USER = "sa";
    private static final String PASSWORD = "123!@#";

    private ConnectDB() {}

    public static Connection getConnection() {
        try {
            // Nếu kết nối null hoặc đã bị đóng, tạo lại kết nối mới
            if (con == null || con.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("🔗 Kết nối CSDL thành công!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Không tìm thấy driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("❌ Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
        return con;
    }

    public static void disconnect() {
        if (con != null) {
            try {
                con.close();
                con = null;
                System.out.println("🔌 Ngắt kết nối thành công.");
            } catch (SQLException e) {
                System.err.println("⚠️ Lỗi khi đóng kết nối: " + e.getMessage());
            }
        }
    }
}
