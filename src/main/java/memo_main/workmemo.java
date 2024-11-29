package memo_main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class workmemo
 */
@WebServlet("/sinin_servlet")
public class workmemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
		Connection connection = null;
        PreparedStatement statement = null;

		
		try (Connection con = DBconection.getConnection()){
			String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            statement = con.prepareStatement(sql);
            
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            
            int rowsInserted = statement.executeUpdate();
            
            response.setContentType("text/html;charset=UTF-8");
            
            PrintWriter out = response.getWriter();
            if (rowsInserted > 0) {
                out.println("<h3>ユーザー登録が完了しました</h3>");
                out.println("<a href='login.html'>ログイン画面へ</a>");
            } else {
                out.println("<h3>ユーザー登録に失敗しました</h3>");
                out.println("<a href='sinin.html'>再試行</a>");
            }
            } catch (SQLException e) {
			System.out.println(e);
		}finally{
			try {
				if(statement != null) statement.close();
				if(connection != null) connection.close();
			}catch (SQLException e) {
                e.printStackTrace();
            }
		}
	}
}
		
		
		
