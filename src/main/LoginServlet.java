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
/**
 * 
 * @author Group2<br>
 * Authenticates username and password from webapp.<br>
 * Passes 30 minute cookie to the browser.<br> 
 *
 */
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Would be nice if we could run these two, but errors with it adding to the database too many times.
		//For now, will use an SQL script, and go ahead as if DB is persistent, but can look into it later.
		//Sorry, Tim. :(
//		UserType.createTypes();
//		User.createAdmin();
		
		String inputUserName = request.getParameter("userName");
		String inputUserPassword = DigestUtils.sha1Hex(request.getParameter("password"));
		
		List<String> userPasswords = PersistenceUtil.findPasswordByUsername(inputUserName);
		
		if(!userPasswords.isEmpty()){
			if(inputUserPassword.equals(userPasswords.get(0))){
				Cookie loginCookie = new Cookie("user", inputUserName);
				loginCookie.setMaxAge(30*60);

				response.addCookie(loginCookie);
				response.sendRedirect("import.jsp");
			} else{
				response.sendRedirect("index.jsp");
			}
		} else{
			response.sendRedirect("index.jsp"); //Redirect, or give alert?
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
		response.sendRedirect("index.jsp");
	}
}