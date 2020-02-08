package config;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import reseau.*;
import simulation.Chemin;
import simulation.UsineSimulation;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimulationConfig extends Configuration {

    MetadonneeConfig metadonneeConfig;

    public SimulationConfig(MetadonneeConfig metadonneeConfig) {
        this.metadonneeConfig = metadonneeConfig;
    }

    public Usine getUsineByType(String type, List<Usine> listUsines) {
        for (int i = 0; i < listUsines.size(); i++) {
            Usine u = listUsines.get(i);
            if (u instanceof UsineProduction) {
                UsineProduction usine = (UsineProduction) u;
                if (usine.getType().equalsIgnoreCase(type))
                    return usine;
            } else if (u instanceof Entrepot && type.equalsIgnoreCase("entrepot")) {
                Entrepot entrepot = (Entrepot) u;
                return entrepot;
            }

        }
        return null;
    }

    public Icone getIconeByType(String type,List<IconeUsine>iconeUsineList){
        for (int i = 0; i<iconeUsineList.size(); i++){
            IconeUsine iconeUsine= iconeUsineList.get(i);
            if(iconeUsine.getType().equalsIgnoreCase(type))
                return iconeUsine;
        }
        return null;
    }

    public UsineSimulation getUsineById(int id,List<UsineSimulation> usines){
        for (int i = 0; i < usines.size(); i++) {
            UsineSimulation u = usines.get(i);
            if(u.getId()==id) return u;
        }
        return null;
    }

    public List<UsineSimulation> getUsinesSimulationInstances()throws ParserConfigurationException, SAXException, IOException {
     List<UsineSimulation> usineSimulationList= new ArrayList<>();
     List<Usine> usineList = metadonneeConfig.getUsineInstances();
     NodeList usines=metadonneeConfig.getAllNodeFromSpecificNodeName("simulation","usine");
     for (int compteurUsines = 0; compteurUsines < usines.getLength(); compteurUsines++) {
        Node usineNode = usines.item(compteurUsines);
        Element usineElement = (Element) usineNode;
        String type= usineElement.getAttribute("type");
        //System.out.println("Type Usine "+type);
        int id= Integer.parseInt(usineElement.getAttribute("id"));
        //System.out.println("Id="+id);
        int x= Integer.parseInt(usineElement.getAttribute("x"));
        //System.out.println("X="+x);
        int y= Integer.parseInt(usineElement.getAttribute("y"));
        //System.out.println("Y="+y);
        Usine usine = getUsineByType(type,usineList);
        UsineSimulation usineSimulation= new UsineSimulation(id,usine,new Point(x,y),getIconeByType("vide",usine.getIconesUsine()));
        // System.out.println("Usine : "+usineSimulation);
        usineSimulationList.add(usineSimulation);


    }
     return usineSimulationList;

}

    public List<Chemin> getCheminInstances()throws ParserConfigurationException, SAXException, IOException {
        List<Chemin> cheminObjectList = new ArrayList<>();
        NodeList chemins = getAllNodeFromSpecificNodeName("simulation", "chemins");
        for (int compteurChemin = 0; compteurChemin < chemins.getLength(); compteurChemin++) {
            Node chemin = chemins.item(compteurChemin);
            if (chemin.getNodeType() == Node.ELEMENT_NODE) {
                Element CheminElement = (Element) chemin;
                NodeList cheminList = getSpecificNodeListFromElement(CheminElement, "chemin");
                for (int i = 0; i < cheminList.getLength(); i++) {
                    Node cheminNode = cheminList.item(i);
                    int idDepart=Integer.parseInt(cheminNode.getAttributes().getNamedItem("de").getNodeValue());
                  // System.out.println("DE "+idDepart);
                  // System.out.println("Usine Depart "+getUsineById(idDepart,getUsinesSimulationInstances()));
                    int idArrivee=Integer.parseInt(cheminNode.getAttributes().getNamedItem("vers").getNodeValue());
                  // System.out.println("VERS "+idArrivee);
                  //  System.out.println("Usine Arrivée "+getUsineById(idArrivee,getUsinesSimulationInstances()));
                    cheminObjectList.add(new Chemin(getUsineById(idDepart,getUsinesSimulationInstances()),getUsineById(idArrivee,getUsinesSimulationInstances())));
                }
            }
        }
        return cheminObjectList;
    }

}
