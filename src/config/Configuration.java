package config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public abstract class Configuration {

    protected static final String filePath = "src/ressources/configuration.xml";

    public Document readXmlFile() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File(filePath));
        doc.getDocumentElement().normalize();

        return doc;
    }

    public NodeList getSpecificNodeListFromElement(Element eElement, String nodeName){
        return  eElement.getElementsByTagName(nodeName);
    }

    public  boolean nodeExistsInElement(Element element, String nodeName){
        NodeList nodeList = getSpecificNodeListFromElement(element,nodeName);
        return nodeList.getLength()!=0;
    }


}
