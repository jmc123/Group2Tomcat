package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.PersistenceUtil;
import entity.EventCause;
/**
 * 
 * @author Group2<br>
 * Gets query parameter from webapp.<br>
 * Executes the query.<br>
 * Sends HTTPSerlvetResponse to webapp.<br>
 *
 */
@SuppressWarnings("serial")
public class IMSIServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		long startTime = System.nanoTime();
		long imsi = Long.parseLong(request.getParameter("IMSI"));
		PrintWriter out = response.getWriter();
		
		List<EventCause> eventCauses = PersistenceUtil.findEventCauseByIMSI(imsi);
		
		out.print("<!DOCTYPE html><html><head>"
				+ "<title>IMSI Query Result</title>"
				+ "<center><b><font face=\"verdana\" color=\"red\" size=\"5\">"
				+ "The Event IDs and Cause Codes for <br />IMSI: " + imsi
				+ "</font></b></center>"
			+ "</head>");
	
		out.print("<body><center><br />"
				+ "<table cellpadding=\"5\" border=\"1\">"
				+ "<tr>"
					+ "<td><b>Event ID</b></td>"
					+ "<td><b>Cause Code</b></td>"
				+ "</tr>");
		
		for(EventCause eventCause : eventCauses){
			out.print("<tr>"
						+ "<td>" + eventCause.getEventId() + "</td>"
						+ "<td>" + eventCause.getCauseCode() + "</td>"
					+ "</tr>");
		}	
		
		long timeTakenInNanos = System.nanoTime()-startTime;
		out.print("</table>"
				+ String.format("<p>Query executed in %.2f ms<p>", (double) timeTakenInNanos/1000000)
				+ "</center></body></html>");
		
		out.close();
	}
}