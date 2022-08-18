import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class XMLParser {
    /**
     * TODO: Parse the input XML file and return a dictionary as described in the assignment insturctions
     *
     * @param filename the input XML file
     * @return a dictionary as described in the assignment insturctions
     */
    public static Map<String, Malware> parse(String filename) {

        Map<String, Malware> malwareMap = new HashMap<>();//keeps hash value as key as String
        try
        {
//creating a constructor of file class and parsing an XML file
            File file = new File(filename);
//an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("row");
// nodeList is not iterable, so we are using for loop
            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;

                    String title = eElement.getElementsByTagName("title").item(0).getTextContent();
                    String hash = eElement.getElementsByTagName("hash").item(0).getTextContent();
                    int level = Integer.parseInt(eElement.getElementsByTagName("level").item(0).getTextContent());

                    Malware malware = new Malware(title,level,hash);
                    malwareMap.put(hash,malware);

                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return malwareMap;
    }
}
