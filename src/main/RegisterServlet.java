package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import persistence.PersistenceUtil;
import entity.User;

/**
 * Servlet implementation class Register
 */
@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean uniqueUsername = true;
		
		String userName = request.getParameter("userName");
		String password = DigestUtils.sha1Hex(request.getParameter("password"));
		int usertype = Integer.parseInt(request.getParameter("role"));
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		List<String> userNamesFromDB = PersistenceUtil.findAllUserNames();

		for(String userNameFromDB : userNamesFromDB){
			if(userNameFromDB.equalsIgnoreCase(userName)){
				uniqueUsername = false;
				break;
			}
		}

		if(uniqueUsername){
			PersistenceUtil.registerUser(new User(userName, password, usertype, firstName, lastName, email, phone));		
			response.sendRedirect("webpages/admin/sysListUsers.jsp");
		} else {
			response.getWriter().print("<script>alert(\"Username taken!\");"
					+ "window.location.replace(\"webpages/admin/sysAddUser.jsp\");"
					+ "document.forms[\"register\"][\"userName\"].focus();</script>");
		}
	}
}	
