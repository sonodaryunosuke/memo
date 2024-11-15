package memo_main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
		

		String url = "jdbc:mysql://localhost:3307/mysql?serverTimezone=Asia/Tokyo";

		String user = "root";
		String passwd = "ktcpass23";
		
		try (Connection con = DriverManager.getConnection(url,user,passwd)){
		System.out.println("connect");
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
		
		
		
		request.setCharacterEncoding("UTF8");
        String email = request.getParameter("email");    
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // 取得したパラメータを使用した処理
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        out.println("Username: " + username);
        out.println("Password: " + password);
        out.println("Password: " + email);
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>memo sinin完了</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>登録が完了しました</h2>");
        out.println("<a href=\"login.html\">login画面へ</a>");
        out.println("</body>");
        out.println("</html>");
	}

}
