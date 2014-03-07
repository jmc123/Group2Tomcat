package queryServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author Group2<br>
 * Gets query parameter from webapp.<br>
 * Executes the query.<br>
 * Sends HTTPSerlvetResponse to webapp.<br>
 *
 */
@SuppressWarnings("serial")
public class SEngServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String queryType = request.getParameter("query");
		
		if(queryType.equals("CallFailuresByModel")){
			callFailuresByModel(request, response);
		} else if(queryType.equals("AllIMSITimePeriod")){
			allIMSITimePeriod(request, response);
		} 
	}
	
	/**
	 * Danny
	 */
	private void callFailuresByModel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.nanoTime();
		PrintWriter out = response.getWriter();
		
		/**
		 * TODO Get the phone model here
		 */
//		long imsi = Long.parseLong(request.getParameter("IMSI"));
		
		/**
		 * TODO Call your query from PersistenceUtil here
		 */
//		List<EventCause> eventCauses = PersistenceUtil.findEventCauseByIMSI(imsi);		
		
		/**
		 * TODO Update the info for the query (as confirmation to the user)
		 */
//		out.print("<!DOCTYPE html><html><head>"
//				+ "<title>IMSI Query Result</title>"
//				+ "<center><b><font face=\"verdana\" color=\"red\" size=\"5\">"
//				+ "The Event IDs and Cause Codes for <br />IMSI: " + imsi
//				+ "</font></b></center>"
//			+ "</head>");
	
		/**
		 * TODO Table headers, added manually
		 */
//		out.print("<body><center><br />"
//				+ "<table cellpadding=\"5\" border=\"1\">"
//				+ "<tr>"
//					+ "<td><b>Event ID</b></td>"
//					+ "<td><b>Cause Code</b></td>"
//				+ "</tr>");
		
		/**
		 * TODO Print out the results from your List<> called above
		 */
//		for(EventCause eventCause : eventCauses){
//			out.print("<tr>"
//						+ "<td>" + eventCause.getEventId() + "</td>"
//						+ "<td>" + eventCause.getCauseCode() + "</td>"
//					+ "</tr>");
//		}	
		
		long timeTakenInNanos = System.nanoTime()-startTime;
		out.print("</table>"
				+ String.format("<p>Query executed in %.2f ms<p>", (double) timeTakenInNanos/1000000)
				+ "</center></body></html>");
		
		out.close();
	}
	
	/**
	 * Jason
	 */
	private void allIMSITimePeriod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.nanoTime();
		PrintWriter out = response.getWriter();
		/**
		 * TODO Get the imsifrom and imsito parameters for time period.
		 */
//		long imsi = Long.parseLong(request.getParameter("IMSI"));
		
		
		/**
		 * TODO Call your query from PersistenceUtil here
		 */
//		List<EventCause> eventCauses = PersistenceUtil.findEventCauseByIMSI(imsi);		
		
		/**
		 * TODO Update the info for the query (as confirmation to the user)
		 */
//		out.print("<!DOCTYPE html><html><head>"
//				+ "<title>IMSI Query Result</title>"
//				+ "<center><b><font face=\"verdana\" color=\"red\" size=\"5\">"
//				+ "The Event IDs and Cause Codes for the time period<br />" + imsi
//				+ "</font></b></center>"
//			+ "</head>");
	
		/**
		 * TODO Table headers, added manually
		 */
//		out.print("<body><center><br />"
//				+ "<table cellpadding=\"5\" border=\"1\">"
//				+ "<tr>"
//					+ "<td><b>Event ID</b></td>"
//					+ "<td><b>Cause Code</b></td>"
//				+ "</tr>");
		
		/**
		 * TODO Print out the results from your List<> called above
		 */
//		for(EventCause eventCause : eventCauses){
//			out.print("<tr>"
//						+ "<td>" + eventCause.getEventId() + "</td>"
//						+ "<td>" + eventCause.getCauseCode() + "</td>"
//					+ "</tr>");
//		}	
		
		long timeTakenInNanos = System.nanoTime()-startTime;
		out.print("</table>"
				+ String.format("<p>Query executed in %.2f ms<p>", (double) timeTakenInNanos/1000000)
				+ "</center></body></html>");
		
		out.close();
	}
}