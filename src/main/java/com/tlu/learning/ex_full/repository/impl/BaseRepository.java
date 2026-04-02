package com.tlu.learning.ex_full.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/ex_full";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "0000";
    private static Connection connection;

    /** Phương thức getConnection để lấy kết nối tới cơ sở dữ liệu. Phương thức này kiểm tra
     * nếu connection chưa được khởi tạo (connection == null) thì sẽ tạo một kết nối mới. */
    public static Connection getConnection() {
        try {
            // Kiểm tra: Nếu null HOẶC đã bị đóng thì mới tạo mới
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                System.out.println(">>> ĐÃ TẠO KẾT NỐI MỚI TỚI: " + jdbcURL);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     *  Phương thức closeConnection để đóng kết nối tới cơ sở dữ liệu khi không cần sử dụng nữa.
     */
    public static void closeConnection() throws SQLException {
        connection.close();
        connection = null;
    }
}
