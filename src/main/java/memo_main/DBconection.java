package memo_main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconection {
    private static final String url = "jdbc:mysql://localhost:3307/mysql?serverTimezone=Asia/Tokyo";
    private static final String user = "root";
    private static final String password = "ktcpass23";

    // データベース接続を返すメソッド
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // ドライバのロード
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBCドライバが見つかりません");
        }
        return DriverManager.getConnection(url, user, password);
    }
}