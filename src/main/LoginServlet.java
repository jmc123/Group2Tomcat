package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import persistence.PersistenceUtil;
import entity.UserType;
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
		//Would be nice if we could run these two, but errors with it adding to the database too many times.
		//For now, will use an SQL script, and go ahead as if DB is persistent, but can look into it later.
		//Sorry, Tim. :(
//		UserType.createTypes();
//		User.createAdmin();
		
		String inputUserName = request.getParameter("userName");
		String inputUserPassword = DigestUtils.sha1Hex(request.getParameter("password"));
		
		List<Object[]> userDetails = PersistenceUtil.findPasswordAndUserTypeByUsername(inputUserName);
		
		if(!userDetails.isEmpty()){
			String userPassword = String.valueOf(userDetails.get(0)[0]);
			int userTypeId = ((UserType) userDetails.get(0)[1]).getId();			
			
			if(inputUserPassword.equals(userPassword)){
				Cookie loginCookie = new Cookie("user", inputUserName);
				loginCookie.setMaxAge(30*60);

				response.addCookie(loginCookie);
				if(userTypeId == SYSTEM_ADMINISTRATOR){
					response.sendRedirect("webpages/admin/sysHome.jsp");
				} else if(userTypeId == NETWORK_MANAGEMENT_ENGINEER){
					//Need page here - next sprint
				} else if(userTypeId == SUPPORT_ENGINEER){
					response.sendRedirect("webpages/supportEng/seHome.jsp");
				} else if(userTypeId == CUSTOMER_SERVICE_REP){
					response.sendRedirect("webpages/customerRep/csHome.jsp");
				} else{
					System.out.println("No UserType found for this user!");
					response.sendRedirect("webpages/login.jsp");
				}
			} else{
//				response.sendRedirect("webpages/login.jsp");
				response.getWriter().print("<script>location.replace(\"webpages/login.jsp\");</script>");
			}
		} else{
//			response.sendRedirect("webpages/login.jsp"); //Redirect, or give alert?
			response.getWriter().print("<script>location.replace(\"webpages/login.jsp\");</script>");
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
		response.sendRedirect("webpages/login.jsp");
	}
}