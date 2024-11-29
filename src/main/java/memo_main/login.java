package memo_main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login_servlet")
public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String sql = "SELECT password FROM users WHERE username = ?";

        try (Connection con = DBconection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String Password = rs.getString("password");

                    // パスワードの比較（BCryptを使用）
                    if (password.equals(Password)){
                        // ログイン成功時にセッションを開始
                        HttpSession session = request.getSession();
                        session.setAttribute("username", username);

                        // ホームページへリダイレクト
                        response.sendRedirect("main_home.html");
                    } else {
                        // パスワード不一致
                        response.sendRedirect("login.jsp?error=invalid");
                    }
                } else {
                    // ユーザー名が存在しない
                	response.sendRedirect("login.jsp?error=notfound");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "ログイン処理中にエラーが発生しました");
        }
    }
}
