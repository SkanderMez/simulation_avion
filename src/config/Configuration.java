package config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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

    //A mettre dans la classe Configuration (utilisé aussi dans simulation)
    public NodeList getAllNodeFromSpecificNodeName(String balise, String node) throws IOException, SAXException, ParserConfigurationException {

        Document doc = readXmlFile();

        //lire la balise racine ( simulation ou metadonnees)
        Node metadonnees = doc.getElementsByTagName(balise).item(0);

        //verifier que la balise metadonnes/simulation a des fils ( qui seront les usines)
        if (metadonnees.getNodeType() == Node.ELEMENT_NODE) {

            //caster la balise metadonnees/simulation en Element
            Element eElement = (Element) metadonnees;

            //Lire les nodes
            NodeList nodeList = getSpecificNodeListFromElement(eElement, node);

            return nodeList;
        }
        return null;
    }



}
