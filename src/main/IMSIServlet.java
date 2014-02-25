package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author Group2<br>
 * Gets query parameter from webapp.<br>
 * Executes the query.<br>
 * Sends HTTPSerlvetResponse to webapp.<br>
 *
 */
@SuppressWarnings("serial")
public class IMSIServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		long startTime = System.nanoTime();
		String imsi = request.getParameter("IMSI");
		imsi = imsi.trim();
		long code = Long.parseLong(imsi);

		String dbURL = "jdbc:mysql://localhost:3306/dt340a";
		String username = "root";
		String password = "toor";
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbURL, username, password);
			Statement statement = connection.createStatement();

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			

			ResultSet resultset = statement
					.executeQuery("SELECT Event_ID, Cause_Code"
							+ " FROM ErrorEvent WHERE IMSI = " + code
							+ " GROUP BY Event_ID, Cause_Code");
			StringBuffer htmlRows = new StringBuffer();
			ResultSetMetaData metaData = resultset.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			htmlRows.append("<!DOCTYPE html><HTML><TITLE>IMSI Query Result</TITLE>" //<!DOCTYPE html> needed for IE
					+ "<CENTER><p><B><FONT face=\"verdana\" color=\"red\" size=\"5\">"
					+ "The Event ID and Cause Code for the<br />IMSI: "
					+ code
					+ "</FONT></B></p>"
					+ "<TABLE cellpadding=\"5\" border=\"1\"" + "<TR>");

			for (int i = 1; i <= columnCount; i++) {
				htmlRows.append("<TD><B>" + metaData.getColumnName(i)
						+ "</B></TD>");
			}
			htmlRows.append("</TR>");

			while (resultset.next()) {
				htmlRows.append("<TR>");
				for (int i = 1; i <= columnCount; i++) {
					htmlRows.append("<TD>" + resultset.getString(i) + "</TD>");
				}
				htmlRows.append("</TR>");
			}
			long timeTakenInNanos = System.nanoTime()-startTime;
			htmlRows.append("</TR></TABLE></CENTER>"
				+ "<DIV style=\"position: relative\""
				+ "<p style=\"position: fixed; bottom: 0; width=100%; text-align: center\"></p>"
				+ "<p><CENTER>Query executed in " + String.format("%.2f",(double) timeTakenInNanos/1000000) + " ms.</CENTER></p>"
				+ "</DIV></HTML>");

			String output = htmlRows.toString();
			out.print(output);

			out.close();
			resultset.close();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}