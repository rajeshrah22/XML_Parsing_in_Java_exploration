package test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * Servlet implementation class XmlFetchingParsing
 */
@WebServlet("/XmlFetchingParsing")
public class XmlFetchingParsing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XmlFetchingParsing() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//gets inputStream from URL
			URL url = new URL("https://retro.umoiq.com/service/publicXMLFeed?command=agencyList");
			InputStream stream = url.openStream();
			//System.out.println(stream); //debug print
			
			//StaxParser Setup
			XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
			XMLEventReader reader = xmlInputFactory.createXMLEventReader(stream);
			//System.out.println("yesss");
			while (reader.hasNext()) {
				
				XMLEvent nextEvent = reader.nextEvent();
				if (nextEvent.isStartElement()) {
					System.out.println("\n----------");
					StartElement startElm = nextEvent.asStartElement();
					//if element is not an agency, then skip
					if (!startElm.getName().getLocalPart().equals("agency")) {
						continue;
					}
					
					String agencyTag = startElm.getAttributeByName(new QName("tag")).getValue();
					String title = startElm.getAttributeByName(new QName("title")).getValue();
					String regionTitle = startElm.getAttributeByName(new QName("regionTitle")).getValue();
					String shortTitleText = "";
					
					
					System.out.print("Agency Tag: " + agencyTag + "\ntitle: " + title + "\nregionTitle: " + regionTitle);
					Attribute shortTitle = startElm.getAttributeByName(new QName("shortTitle"));
					if (shortTitle != null) {
						shortTitleText = shortTitle.getValue();
						System.out.print("\nshortTitle: " + shortTitleText);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
