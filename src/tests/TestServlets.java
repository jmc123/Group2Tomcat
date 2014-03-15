package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.ImportServlet;
import main.LoginServlet;
import main.RegisterServlet;

import org.junit.BeforeClass;
import org.junit.Test;

import persistence.PersistenceUtil;

public class TestServlets {
	
	@BeforeClass
	public static void setUp(){
		PersistenceUtil.useTestDatabase();
		new ImportServlet();
		new LoginServlet();
	}

	@Test
	public void testRegisterServlet() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("userName")).thenReturn("userMock");
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getParameter("role")).thenReturn("1");
        when(request.getParameter("fname")).thenReturn("fname");
        when(request.getParameter("lname")).thenReturn("lname");
        when(request.getParameter("email")).thenReturn("mock@mock.com");
        when(request.getParameter("phone")).thenReturn("01111111");
		
		when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));

        new RegisterServlet().doPost(request, response);

        assertEquals("Should be 'userMock'", "userMock", PersistenceUtil.findUserByUsername("userMock").getUserName());
	}
	
	@Test
	public void testRegisterServletFail() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("userName")).thenReturn("user");
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getParameter("role")).thenReturn("1");
        when(request.getParameter("fname")).thenReturn("fname");
        when(request.getParameter("lname")).thenReturn("lname");
        when(request.getParameter("email")).thenReturn("mock@mock.com");
        when(request.getParameter("phone")).thenReturn("01111111");
		
		when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));

        new RegisterServlet().doPost(request, response);

        assertFalse("mock@mock.com".equals(PersistenceUtil.findUserByUsername("user").getEmailAddress()));
	}
}
