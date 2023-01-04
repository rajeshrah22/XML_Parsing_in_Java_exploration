package test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Servlet implementation class XmlParsingUsingSax
 */
@WebServlet("/XmlParsingUsingSax")
public class XmlParsingUsingSax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XmlParsingUsingSax() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler()
			{
				boolean sid = false;
				int count = 0;
				public void startElement( String sg, String pp, String q, Attributes a ) throws SAXException {
					if (q.equalsIgnoreCase("SID")) {
						System.out.println("Beginning Node :" + q);
						count++;
						sid = true;
					}
				}
				
				public void endElement( String sg, String pp, String q ) throws SAXException {
					if (q.equalsIgnoreCase("SID")) {
						System.out.println("LAst Node:" + q);
					}
				}
				
				public void characters(char chr[], int st, int len) throws SAXException
				{
					if (sid)
					{
						System.out.println("SID : " + new String(chr, st, len));
						sid = false;
					}
				}
				
				public void endDocument() throws SAXException {
					System.out.println("Number of SID: " + count);
				}
			};
			saxParser.parse("C:\\temp\\sample.xml", handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
