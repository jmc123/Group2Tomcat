package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import persistence.PersistenceUtil;

/**
 * Servlet implementation class Register
 */
@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean uniqueUsername = true;
		
		
		String userName = request.getParameter("userName");
		String password = DigestUtils.sha1Hex(request.getParameter("password"));
		int usertype = Integer.parseInt(request.getParameter("role"));
		
		List<String> userNamesFromDB = PersistenceUtil.findAllUserNames();

		for(String userNameFromDB : userNamesFromDB){
			if(userNameFromDB.equalsIgnoreCase(userName)){
				uniqueUsername = false;
				break;
			}
		}

		if(uniqueUsername){
			PersistenceUtil.registerUser(userName, password,usertype);		
			response.sendRedirect("webpages/admin/sysHome.jsp");
		} else {
			response.getWriter().print("<script>alert(\"Username taken!\");"
					+ "window.location.replace(\"webpages/admin/sysAddUser.jsp\");"
					+ "document.forms[\"register\"][\"userName\"].focus();</script>");
		}
	}
}	
