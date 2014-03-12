//package queryServlets;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import persistence.PersistenceUtil;
//import entity.EventCause;
///**
// * 
// * @author Group2<br>
// * Gets query parameter from webapp.<br>
// * Executes the query.<br>
// * Sends HTTPSerlvetResponse to webapp.<br>
// *
// */
//@SuppressWarnings("serial")
//public class CSRepServlet extends HttpServlet {
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String queryType = request.getParameter("query");
//
//		if(queryType.equals("EventCauseByIMSI")){
//			eventCauseByIMSIQuery(request, response);
//		} else if(queryType.equals("CountIMSIByTimePeriod")){
////			countIMSIByTimePeriod(request, response);
//		} else if(queryType.equals("UniqueCauseCodesByIMSI")){
//			uniqueCauseCodesByIMSI(request, response);
//		}
//	}
//
//	/**
//	 * IMSI query from Sprint 1 - working fine, so use as example if needed
//	 */
//	private void eventCauseByIMSIQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		long startTime = System.nanoTime();
//		long imsi = Long.parseLong(request.getParameter("IMSI"));
//		PrintWriter out = response.getWriter();
//
//		List<EventCause> eventCauses = PersistenceUtil.findEventCauseByIMSI(imsi);		
//
//		out.print("<!DOCTYPE html><html><head>"
//				+ "<title>IMSI Query Result</title>"
//				+ "<center><b><font face=\"verdana\" color=\"red\" size=\"5\">"
//				+ "The Event IDs and Cause Codes for <br />IMSI: " + imsi
//				+ "</font></b></center>"
//				+ "</head>");
//
//		out.print("<body><center><br />"
//				+ "<table cellpadding=\"5\" border=\"1\">"
//				+ "<tr>"
//				+ "<td><b>Event ID</b></td>"
//				+ "<td><b>Cause Code</b></td>"
//				+ "</tr>");
//
//		for(EventCause eventCause : eventCauses){
//			out.print("<tr>"
//					+ "<td>" + eventCause.getEventId() + "</td>"
//					+ "<td>" + eventCause.getCauseCode() + "</td>"
//					+ "</tr>");
//		}	
//
//		long timeTakenInNanos = System.nanoTime()-startTime;
//		out.print("</table>"
//				+ String.format("<p>Query executed in %.2f ms<p>", (double) timeTakenInNanos/1000000)
//				+ "</center></body></html>");
//
//		out.close();
//	}
//
//	/**
//	 * Tim
//	 */
////	private void countIMSIByTimePeriod(HttpServletRequest request, HttpServletResponse response) throws IOException{
////
////		long startTime = System.nanoTime();
////		PrintWriter out = response.getWriter();
////
////
////		long imsi = Long.parseLong(request.getParameter("imsi"));
////
////
////		String fromDate = request.getParameter("imsifrom");
////		String toDate = request.getParameter("imsito");
////
////		String fromdate = PersistenceUtil.returnDate(request.getParameter("imsifrom"));
////		String todate = PersistenceUtil.returnDate(request.getParameter("imsito"));
////
////		SimpleDateFormat currentParsed = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////
////		Date fdate = null, tdate = null;
////		try{
////			fdate = currentParsed.parse(fromdate);
////			tdate = currentParsed.parse(todate);
////		}catch (ParseException e){
////
////		}
////		List<Object[]> queryDetails = PersistenceUtil.findNumberOfFailures(imsi, fdate, tdate);
////			
////		
////		//Long count = ((Long) counts.get(0)[0]).longValue();
////
////
////		/**
////		 * TODO Update the info for the query (as confirmation to the user)
////		 */
////		out.print("<!DOCTYPE html><html><head>"
////				+ "<title>Number of Failures for : "+imsi+"</title>"
////				+ "<center><b><font face=\"verdana\" color=\"red\" size=\"5\">"
////				+ "Number of failures between "+fromDate+" and "+toDate+"<br>"
////				+ "</font></b></center>"
////				+ "</head>");
////
////		/**
////		 * TODO Table headers, added manually
////		 */
////		out.print("<body><center><br />"
////				+ "<table cellpadding=\"5\" border=\"1\">"
////				+ "<tr>"
////				+ "<td><b>Num of Failures</b></td>"
////				+ "</tr>");
////
////		/**
////		 * TODO Print out the results from your List<> called above
////		 */
////
////		out.print("<tr>"
////				+ "<td>" + queryDetails.get(0)[0] + "</td>"
////				+ "</tr>");
////
////
////		long timeTakenInNanos = System.nanoTime()-startTime;
////		out.print("</table>"
////				+ String.format("<p>Query executed in %.2f ms<p>", (double) timeTakenInNanos/1000000)
////				+ "</center></body></html>");
////
////		out.close();
////
////	}
//
//	/**
//	 * Gavin
//	 */
//	private void uniqueCauseCodesByIMSI(HttpServletRequest request, HttpServletResponse response) throws IOException{
//		long startTime = System.nanoTime();
//		PrintWriter out = response.getWriter();
//
//		/**
//		 * TODO Get the imsi value (commented code should work)
//		 */
//				long imsi = Long.parseLong(request.getParameter("IMSI"));
//
//		/**
//		 * TODO Call your query from PersistenceUtil here
//		 */
//				List<Integer> eventCauses = PersistenceUtil.findUniqueCauseByIMSI(imsi);		
//
//		/**
//		 * TODO Update the info for the query (as confirmation to the user)
//		 */
//				out.print("<!DOCTYPE html><html><head>"
//						+ "<title>Unique Cause Codes </title>"
//						+ "<center><b><font face=\"verdana\" color=\"red\" size=\"5\">"
//						+ "The Unique Cause Codes for <br />IMSI: " + imsi
//						+ "</font></b></center>"
//					+ "</head>");
//
//		/**
//		 * TODO Table headers, added manually
//		 */
//				out.print("<body><center><br />"
//						+ "<table cellpadding=\"5\" border=\"1\">"
//						+ "<tr>"
//							+ "<td><b>Cause Code</b></td>"
//						+ "</tr>");
//
//		/**
//		 * TODO Print out the results from your List<> called above
//		 */
//				for(Integer eventCause : eventCauses){
//					out.print("<tr>"
//								+ "<td>" + eventCause + "</td>"
//							+ "</tr>");
//				}	
//
//		long timeTakenInNanos = System.nanoTime()-startTime;
//		out.print("</table>"
//				+ String.format("<p>Query executed in %.2f ms<p>", (double) timeTakenInNanos/1000000)
//				+ "</center></body></html>");
//
//		out.close();
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//
//}