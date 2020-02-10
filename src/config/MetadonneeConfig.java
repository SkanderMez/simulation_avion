package config;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import reseau.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MetadonneeConfig extends Configuration {

    //Cette methode retourne les instances icones usine pour une usine spécifiée en paramètre
    public static List<IconeUsine> getIconesUsineInstances(Node usine) throws ParserConfigurationException, SAXException, IOException {

        List<IconeUsine> iconeObjectsList = new ArrayList<>();

        if (usine.getNodeType() == Node.ELEMENT_NODE) {
            Element usineElement = (Element) usine;

            //Lire la balise icones contenant les icones
            Node icones = getSpecificNodeListFromElement(usineElement, "icones").item(0);

            if (icones.getNodeType() == Node.ELEMENT_NODE) {
                Element iconesElement = (Element) icones;

                //Lire les icones
                NodeList iconesList = getSpecificNodeListFromElement(iconesElement, "icone");
                for (int compteurIcones = 0; compteurIcones < iconesList.getLength(); compteurIcones++) {
                    Node icone = iconesList.item(compteurIcones);
                    iconeObjectsList.add(new IconeUsine(icone.getAttributes().getNamedItem("path").getNodeValue(), icone.getAttributes().getNamedItem("type").getNodeValue()));
                }
            }
        }

        return iconeObjectsList;

    }

    //Cette methode retourne les instances entree usine pour une usine spécifiée en paramètre
    public static List<QuantiteDTO> getComposantsEntreeUsineInstances(Node usine) {

        List<QuantiteDTO>  composantsEntreeObjectsList = new ArrayList<>();

        if (usine.getNodeType() == Node.ELEMENT_NODE) {
            Element usineElement = (Element) usine;

            //verifier si l'usine a au moins une entree
            if (nodeExistsInElement(usineElement, "entree")) {
                //Lire les entrees (s'ils existent)
                NodeList entreeList = getSpecificNodeListFromElement(usineElement, "entree");
                for (int compteurEntrees = 0; compteurEntrees < entreeList.getLength(); compteurEntrees++) {
                    Node entree = entreeList.item(compteurEntrees);
                    if (usine.getAttributes().getNamedItem("type").getNodeValue().equals("entrepot")) {
                        TypeComposant typeComposant = new TypeComposant(new Icone("src/ressources/" + entree.getAttributes().getNamedItem("type").getNodeValue() + ".png"), entree.getAttributes().getNamedItem("type").getNodeValue());
                        //Creer une liste d'entrees dans laquelle on va stocker le nombre de composant entrés dans l'entrepot
                        QuantiteDTO capacite= new QuantiteDTO(typeComposant,Integer.parseInt(entree.getAttributes().getNamedItem("capacite").getNodeValue()));
                        composantsEntreeObjectsList.add(capacite);
                        //ajouter la liste d'entrees dans une liste qui stockera les types de composant que possède un entrepot(cas exceptionnel 1)
                    } else {
                        //Creer une liste d'entrees dans laquelle on va stocker le nombre de composant entrés dans l'usine
                       // List<ComposantUsine> composantsEntreeObjectsList = new ArrayList<>();
                        TypeComposant c = new TypeComposant(new Icone("src/ressources/" + entree.getAttributes().getNamedItem("type").getNodeValue() + ".png"), entree.getAttributes().getNamedItem("type").getNodeValue());
                        QuantiteDTO quantite = new QuantiteDTO(c,Integer.parseInt(entree.getAttributes().getNamedItem("quantite").getNodeValue()));
                        composantsEntreeObjectsList.add(quantite);
                        //ajouter la liste d'entrees dans une liste qui stockera les types de composant que possède une unsine
                        //listcomposantsEntreeObjectsList.add(composantsEntreeObjectsList);
                    }
                }
            }
        }

        return composantsEntreeObjectsList;
    }

    //Cette methode retourne l' instances sortie usine pour une usine spécifiée en paramètre
    public static TypeComposant getComposantSortieUsineInstances(Node usine) {
        TypeComposant typeComposantSortieObject = new TypeComposant();

        if (usine.getNodeType() == Node.ELEMENT_NODE) {
            Element usineElement = (Element) usine;

            if (nodeExistsInElement(usineElement, "sortie")) {
                //Lire la sortie ( une seule sortie par usine )
                Node sortie = getSpecificNodeListFromElement(usineElement, "sortie").item(0);
                typeComposantSortieObject = new TypeComposant(new Icone("src/ressources/" + sortie.getAttributes().getNamedItem("type").getNodeValue() + ".png"), sortie.getAttributes().getNamedItem("type").getNodeValue());
            }
        }

        return typeComposantSortieObject;
    }

    //Cette methode retourne la valeur de l'intervalde production d'une usine donnée
    public static int getUsinesIntervalProductionValue(Node usine) {

        int intervalProductionValue = 0;

        if (usine.getNodeType() == Node.ELEMENT_NODE) {
            Element usineElement = (Element) usine;

            if (nodeExistsInElement(usineElement, "interval-production")) {
                Node intervalProduction = getSpecificNodeListFromElement(usineElement, "interval-production").item(0);
                intervalProductionValue = Integer.parseInt(intervalProduction.getTextContent());
            }
        }


        return intervalProductionValue;
    }


    public static List<Usine> getUsineInstances() throws ParserConfigurationException, SAXException, IOException {

        List<Usine> usineObjectsList = new ArrayList<>();

        NodeList usineList = getAllNodeFromSpecificNodeName("metadonnees", "usine");
        for (int compteurUsines = 0; compteurUsines < usineList.getLength(); compteurUsines++) {
            Node usine = usineList.item(compteurUsines);
            Element usineElement = (Element) usine;
            List<IconeUsine> iconesUsineList = getIconesUsineInstances(usine);
            List<QuantiteDTO> composantEntreeList = getComposantsEntreeUsineInstances(usine);
            List<TypeComposant> composantsEntree = new ArrayList<>();
            for(QuantiteDTO qté:composantEntreeList){composantsEntree.add(qté.getTypeComposant());}
            TypeComposant typeComposantSortie = getComposantSortieUsineInstances(usine);
            int intervalProductionValue = getUsinesIntervalProductionValue(usine);

            //Usine avec entre sortie
            if (nodeExistsInElement(usineElement, "entree") && nodeExistsInElement(usineElement, "sortie")) {
                UsineAvecEntree usineE = new UsineAvecEntree(iconesUsineList, typeComposantSortie, intervalProductionValue, usineElement.getAttribute("type"), composantsEntree);
               // System.out.println(usineE);
                usineObjectsList.add(usineE);
                for(QuantiteDTO qté: composantEntreeList) {
                    ComposantUsine composantUsine = new ComposantUsine(usineE,qté.getTypeComposant(),qté.getQuantiteOuCapacite());
                    usineE.addComposantUsine(composantUsine);

                }
                    //Usine avec sortie sans entree
            } else if (!nodeExistsInElement(usineElement, "entree") && nodeExistsInElement(usineElement, "sortie")) {
                usineObjectsList.add(new UsineProduction( iconesUsineList, typeComposantSortie, intervalProductionValue, usineElement.getAttribute("type")));
                }
            // Entrepot
            else if (nodeExistsInElement(usineElement, "entree") && !nodeExistsInElement(usineElement, "sortie")) {
                usineObjectsList.add(new Entrepot( iconesUsineList, null, composantEntreeList.get(0).getTypeComposant(),composantEntreeList.get(0).getQuantiteOuCapacite()));
                }

        }


        return usineObjectsList;
    }

}
