package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author Group2<br>
 * Authenticates username and password from webapp.<br>
 * Passes 30 minute cookie to the browser.<br> 
 *
 */
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String dbURL = "jdbc:mysql://localhost:3306/dt340a";
		String dbUserName = "root";
		String dbPassword = "toor";
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbURL, dbUserName,
					dbPassword);

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			String userName = request.getParameter("userName");
			String userPassword = request.getParameter("password");

			PreparedStatement statement = connection
					.prepareStatement("SELECT userPassword from UserTable WHERE userName = ?");
			statement.setString(1, userName);

			ResultSet resultset = statement.executeQuery();
			ResultSetMetaData resultmetadata = resultset.getMetaData();

			int columnCount = resultmetadata.getColumnCount();

			while (resultset.next()) {
				for (int i = 1; i <= columnCount; i++) {

					String columnValue = resultset.getString(i);
					if (columnValue.equals(userPassword)) {
						Cookie loginCookie = new Cookie("user", userName);
						// setting cookie to expiry in 30 mins
						loginCookie.setMaxAge(30 * 60);

						response.addCookie(loginCookie);
						response.sendRedirect("import.jsp");
					} else {
						response.sendRedirect("index.jsp");
					}
				}
			}

			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// close connection
		}
	}
}