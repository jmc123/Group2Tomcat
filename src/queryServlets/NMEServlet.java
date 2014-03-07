package queryServlets;

import java.io.IOException;

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
public class NMEServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String queryType = request.getParameter("query");
		
		//No queries for NME this sprint
	}
}