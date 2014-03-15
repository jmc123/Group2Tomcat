package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.LoginServlet;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.PersistenceUtil;
import entity.User;

public class TestLoginServlet {
	static User adminTest;
	static User nmeTest;
	static User seTest;
	static User csrTest;
	static User noTypeTest;
	
	@BeforeClass
	public static void setUp(){
		PersistenceUtil.useTestDatabase();
		adminTest = new User("admin", DigestUtils.sha1Hex("pass"), 1, "login", "test", "login@test.com", "01111111");
		nmeTest = new User("nme", DigestUtils.sha1Hex("pass"), 2, "login", "test", "login@test.com", "01111111");
		seTest = new User("se", DigestUtils.sha1Hex("pass"), 3, "login", "test", "login@test.com", "01111111");
		csrTest = new User("csr", DigestUtils.sha1Hex("pass"), 4, "login", "test", "login@test.com", "01111111");
		noTypeTest = new User("noType", DigestUtils.sha1Hex("pass"), 5, "login", "test", "login@test.com", "01111111");
	}
	
	@Test
	public void testLoginServletLogout() throws ServletException, IOException{
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        
        when(request.getSession()).thenReturn(session);
        
        new LoginServlet().doPost(request, response);
        
        assertEquals("Should be null", null, request.getSession().getAttribute("userName"));
	}

	@Test
	public void testLoginServletLoginAdmin() throws ServletException, IOException{
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        
        PersistenceUtil.registerUser(adminTest);
        
        when(request.getParameter("userName")).thenReturn(adminTest.getUserName());
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getSession(true)).thenReturn(session);
        when(session.getAttribute("userType")).thenReturn(adminTest.getUserType().getDesc());
        
        when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
        
        new LoginServlet().doGet(request, response);
        
        assertEquals("Should be 'System Administrator'", "System Administrator", request.getSession(true).getAttribute("userType"));
	}
	
	@Test
	public void testLoginServletLoginNME() throws ServletException, IOException{
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        
        PersistenceUtil.registerUser(nmeTest);
        
        when(request.getParameter("userName")).thenReturn(nmeTest.getUserName());
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getSession(true)).thenReturn(session);
        when(session.getAttribute("userType")).thenReturn(nmeTest.getUserType().getDesc());
        
        when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
        
        new LoginServlet().doGet(request, response);
        
        assertEquals("Should be 'Network Management Engineer'", "Network Management Engineer", request.getSession(true).getAttribute("userType"));
	}
	
	@Test
	public void testLoginServletLoginSE() throws ServletException, IOException{
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        
        PersistenceUtil.registerUser(seTest);
        
        when(request.getParameter("userName")).thenReturn(seTest.getUserName());
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getSession(true)).thenReturn(session);
        when(session.getAttribute("userType")).thenReturn(seTest.getUserType().getDesc());
        
        when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
        
        new LoginServlet().doGet(request, response);
        
        assertEquals("Should be 'Support Engineer'", "Support Engineer", request.getSession(true).getAttribute("userType"));
	}
	
	@Test
	public void testLoginServletLoginCSR() throws ServletException, IOException{
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        
        PersistenceUtil.registerUser(csrTest);
        
        when(request.getParameter("userName")).thenReturn(csrTest.getUserName());
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getSession(true)).thenReturn(session);
        when(session.getAttribute("userType")).thenReturn(csrTest.getUserType().getDesc());
        
        when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
        
        new LoginServlet().doGet(request, response);
        
        assertEquals("Should be 'Customer Service Rep'", "Customer Service Rep", request.getSession(true).getAttribute("userType"));
	}
	
	@Test
	public void testLoginServletLoginFailPassword() throws ServletException, IOException{
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        
        when(request.getParameter("userName")).thenReturn(csrTest.getUserName());
        when(request.getParameter("password")).thenReturn("invalidPass");
        when(request.getSession(true)).thenReturn(session);
        when(session.getAttribute("userType")).thenReturn(csrTest.getUserType().getDesc());
        
        when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
        when(request.getSession()).thenReturn(session);
        
        new LoginServlet().doGet(request, response);
        
        assertEquals("Should be null", null, request.getSession().getAttribute("userName"));
	}
	
	@Test
	public void testLoginServletLoginFailUsername() throws ServletException, IOException{
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        
        when(request.getParameter("userName")).thenReturn("invalidUser");
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getSession(true)).thenReturn(session);
        when(session.getAttribute("userType")).thenReturn(csrTest.getUserType().getDesc());
        
        when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
        when(request.getSession()).thenReturn(session);
        
        new LoginServlet().doGet(request, response);
   
        assertEquals("Should be null", null, request.getSession().getAttribute("userName"));
	}
}
