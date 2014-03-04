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
		String imsiInput = request.getParameter("IMSI");
		long imsi = Long.parseLong(imsiInput);
		PrintWriter out = response.getWriter();
		
		List<EventCause> eventCauses = PersistenceUtil.findEventCauseByIMSI(imsi);
		
		if(!eventCauses.isEmpty()){
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
					+ "<p>Query executed in " + String.format("%.2f",(double) timeTakenInNanos/1000000) + " ms</p>"
					+ "</center></body></html>");
		}
		
		out.close();
	}
}