//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class TestConnection {
//    public static void main(String[] args) {
//        String url = "jdbc:mysql://localhost:3306/spring_learning";
//        String user = "root";
//        String password = "0000";
//
//        System.out.println("Trying to connect to database...");
//
//        try {
//            Connection conn = DriverManager.getConnection(url, user, password);
//
//            if (conn != null) {
//                System.out.println("========================================");
//                System.out.println("Chúc mừng! Kết nối MySQL thành công.");
//                System.out.println("========================================");
//
//                conn.close();
//            }
//        } catch (SQLException e) {
//            System.err.println("CONNECTION FAILED: " + e.getMessage());
//            System.err.println("Kiểm tra xem bạn đã bật MySQL trong XAMPP chưa?");
//            e.printStackTrace();
//        }
//    }
//}