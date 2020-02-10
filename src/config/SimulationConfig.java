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


/***********************************************************Singleton Design Pattern*********************************************************** */


public class SimulationConfig extends Configuration {

    private static MetadonneeConfig metadonneeConfig = new MetadonneeConfig();

    private static List<UsineSimulation> usineSimulationList = null;
    private static List<Chemin> cheminList = null;


    private SimulationConfig() {
    }

    public static List<UsineSimulation> getUniqueUsinesSimulationInstances(){
        if (usineSimulationList == null){
            usineSimulationList = getUsinesSimulationInstances();
        }

        return usineSimulationList;
    }

    public static List<Chemin> getUniqueCheminsInstances(){
        if (cheminList== null){
            cheminList = getCheminInstances();
        }

        return cheminList;
    }

    private static Usine getUsineByType(String type, List<Usine> listUsines) {
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

    private static Icone getIconeByType(String type,List<IconeUsine>iconeUsineList){
        for (int i = 0; i<iconeUsineList.size(); i++){
            IconeUsine iconeUsine= iconeUsineList.get(i);
            if(iconeUsine.getType().equalsIgnoreCase(type))
                return iconeUsine;
        }
        return null;
    }

    private static UsineSimulation getUsineById(int id,List<UsineSimulation> usines){
        for (int i = 0; i < usines.size(); i++) {
            UsineSimulation u = usines.get(i);
            if(u.getId()==id) return u;
        }
        return null;
    }

    private static List<UsineSimulation> getUsinesSimulationInstances(){
     List<UsineSimulation> usineSimulationList= new ArrayList<>();
        List<Usine> usineList = null;
        try {
            usineList = metadonneeConfig.getUsineInstances();
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
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

     return usineSimulationList;

}

    private static List<Chemin> getCheminInstances() {
        List<Chemin> cheminObjectList = new ArrayList<>();
        NodeList chemins = null;
        try {
            chemins = getAllNodeFromSpecificNodeName("simulation", "chemins");
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return cheminObjectList;
    }

}
