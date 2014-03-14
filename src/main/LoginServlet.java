package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import persistence.PersistenceUtil;
import entity.User;
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
	private HttpSession session;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String inputUserName = request.getParameter("userName");
		String inputUserPassword = DigestUtils.sha1Hex(request.getParameter("password"));

		User user = PersistenceUtil.findUserByUsername(inputUserName);

		if(user != null){
			String userPassword = user.getUserPassword();
			int userTypeId = user.getUserType().getId();		
			String userType = user.getUserType().getDesc();	

			if(inputUserPassword.equals(userPassword)){
				

				session = request.getSession(true);
				session.setAttribute("id", userTypeId);
				session.setAttribute("userName", inputUserName);
				session.setAttribute("userType", userType);


				if(userTypeId == SYSTEM_ADMINISTRATOR){
					response.getWriter().print("<script>location.replace(\"webpages/admin/sysHome.jsp\");</script>");
					
				} else if(userTypeId == NETWORK_MANAGEMENT_ENGINEER){
					
					response.getWriter().print("<script>location.replace(\"webpages/networkManEng/nmeHome.jsp\");</script>");

				} else if(userTypeId == SUPPORT_ENGINEER){
					response.getWriter().print("<script>location.replace(\"webpages/supportEng/seHome.jsp\");</script>");

				} else if(userTypeId == CUSTOMER_SERVICE_REP){
					response.getWriter().print("<script>location.replace(\"webpages/customerRep/csHome.jsp\");</script>");


				} else{
					System.out.println("No UserType found for this user!");
					response.getWriter().print("<script>location.replace(\"/JPASprint1\");</script>");

				}
			} else{
			
				response.getWriter().print("<script>location.replace(\"/JPASprint1\");</script>");
			}
		} else{
			
			response.getWriter().print("<script>location.replace(\"/JPASprint1\");</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)<br>	 
	 * Requests cookies from browser, gets the G2user cookie and sets MaxAge to 0 forcing cookie to be destroyed.<br>
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");

		HttpSession sess =  request.getSession();
		session.removeAttribute("id");
		session.removeAttribute("username");
		session.removeAttribute("userType");
		sess.invalidate();
 
		response.sendRedirect("/JPASprint1");
	}

}