package main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import persistence.PersistenceUtil;
import entity.User;


@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	private static final int SYSTEM_ADMINISTRATOR = 1;
	private static final int NETWORK_MANAGEMENT_ENGINEER = 2;
	private static final int SUPPORT_ENGINEER = 3;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputUserName = request.getParameter("userName");
		String inputUserPassword = DigestUtils.sha1Hex(request.getParameter("password"));
		
		User user = PersistenceUtil.findUserByUsername(inputUserName);
		
		if(user != null){
			String userPassword = user.getUserPassword();
			int userTypeId = user.getUserType().getId();		
			String userType = user.getUserType().getDesc();

			if(inputUserPassword.equals(userPassword)){
				HttpSession session = request.getSession(true);
				session.setAttribute("id", userTypeId);
				session.setAttribute("userName", user.getUserName());
				session.setAttribute("userType", userType);
				
				if(userTypeId == SYSTEM_ADMINISTRATOR){
					response.getWriter().print("<script>location.replace(\"webpages/admin/sysHome.jsp\");</script>");
				} else if(userTypeId == NETWORK_MANAGEMENT_ENGINEER){
					response.getWriter().print("<script>location.replace(\"webpages/networkManEng/nmeHome.jsp\");</script>");
				} else if(userTypeId == SUPPORT_ENGINEER){
					response.getWriter().print("<script>location.replace(\"webpages/supportEng/seHome.jsp\");</script>");
				} else {
					response.getWriter().print("<script>location.replace(\"webpages/customerRep/csHome.jsp\");</script>");
				}
			} else{
				response.getWriter().print("<script>location.replace(\"/JPASprint1\");</script>");
			}
		} else{
			response.getWriter().print("<script>location.replace(\"/JPASprint1\");</script>");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		HttpSession sess =  request.getSession();
		sess.removeAttribute("id");
		sess.removeAttribute("username");
		sess.removeAttribute("userType");
		sess.invalidate();
 
		response.sendRedirect("/JPASprint1");
	}
}