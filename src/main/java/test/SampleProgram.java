package test;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SampleProgram
 */
@WebServlet("/SampleProgram")
public class SampleProgram extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SampleProgram() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			File file = new File("C:\\temp\\sample.xml");
			DocumentBuilderFactory docb = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbu = docb.newDocumentBuilder();
			Document dt = dbu.parse(file);
			dt.getDocumentElement().normalize();
			System.out.println("Root: " + dt.getDocumentElement().getNodeName());
			
			NodeList nd = dt.getElementsByTagName("supermarket");
			
			for (int i = 0; i < nd.getLength(); i++) {
				Node node = nd.item(i);
				System.out.println("\nNodeName :" + node.getNodeName());
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element el = (Element) node;
					System.out.println("supermarket sid: "+ el.getElementsByTagName("sid").item(0).getTextContent());
					System.out.println("sname: "+  el.getElementsByTagName("sname").item(0).getTextContent());
					System.out.println("product: "+  el.getElementsByTagName("product").item(0).getTextContent());
					System.out.println("branch: "+  el.getElementsByTagName("branch").item(0).getTextContent());
					System.out.println("location: "+ el.getElementsByTagName("location").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
