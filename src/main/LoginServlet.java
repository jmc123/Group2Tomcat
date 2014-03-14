package main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import persistence.PersistenceUtil;
import entity.User;
/**
 * 
 * @author Group2<br>
 * Authenticates username and password from webapp.<br>
 * Passes 30 minute cookie to the browser.<br> 
 *
 */
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	private static final int SYSTEM_ADMINISTRATOR = 1;
	private static final int NETWORK_MANAGEMENT_ENGINEER = 2;
	private static final int SUPPORT_ENGINEER = 3;
	private static final int CUSTOMER_SERVICE_REP = 4;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String inputUserName = request.getParameter("userName");
		String inputUserPassword = DigestUtils.sha1Hex(request.getParameter("password"));
		
		User userDetails = PersistenceUtil.findUserByUsername(inputUserName);
		
		if(userDetails != null){
			String userPassword = userDetails.getUserPassword();
			int userTypeId = userDetails.getUserType().getId();			
			
			if(inputUserPassword.equals(userPassword)){
				Cookie loginCookie = new Cookie("user", inputUserName);
				loginCookie.setPath("/");
				loginCookie.setMaxAge(30*60);

				response.addCookie(loginCookie);
				if(userTypeId == SYSTEM_ADMINISTRATOR){
					response.sendRedirect("webpages/admin/sysHome.jsp");
				} else if(userTypeId == NETWORK_MANAGEMENT_ENGINEER){
					response.sendRedirect("webpages/networkManEng/nmeHome.jsp");
				} else if(userTypeId == SUPPORT_ENGINEER){
					response.sendRedirect("webpages/supportEng/seHome.jsp");
				} else if(userTypeId == CUSTOMER_SERVICE_REP){
					response.sendRedirect("webpages/customerRep/csHome.jsp");
				} else{
					System.out.println("No UserType found for this user!");
					response.sendRedirect("/JPASprint1");
				}
			} else{
				response.getWriter().print("<script>alert(\"Incorrect username and/or password!\");location.replace(\"/JPASprint1\");</script>");
			}
		} else{
			response.getWriter().print("<script>alert(\"Incorrect username and/or password!\");location.replace(\"/JPASprint1\");</script>");
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)<br>	 
	 * Requests cookies from browser, gets the G2user cookie and sets MaxAge to 0 forcing cookie to be destroyed.<br>
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		Cookie loginCookie = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("user")){
					loginCookie = cookie;
					break;
				}
			}
		} 
		if(loginCookie != null){
			loginCookie.setMaxAge(0);
			response.addCookie(loginCookie);
		}
		response.sendRedirect("/JPASprint1");
	}
}