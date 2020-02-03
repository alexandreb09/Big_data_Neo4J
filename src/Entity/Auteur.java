package Entity;

import org.neo4j.graphdb.Node;

public class Auteur {
    private Node noeud_de_reference;

    public Auteur(Node personneNode){
        this.noeud_de_reference = personneNode;
    }

    public Auteur fill(String nom, String prenom, String ville, String telephone){
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setVille(ville);
        this.setTelephone(telephone);
        return this;
    }

    public Auteur fillInt(int i){
        this.fill("Nom_" + i, "Prenom_" + i, "Ville_" + i, "" + i);
        return this;
    }

    public Auteur afficher(){
        String txt = "Personne: "
                + "\n\t- NOM: " + getNom()
                + "\n\t- PRENOM: " + getPrenom()
                + "\n\t- VILLE: " + getVille()
                + "\n\t- TEL: " + getTelephone();
        System.out.println(txt);
        return this;
    }

    public String getNom() {
        if (noeud_de_reference.hasProperty("Nom"))
            return (String) noeud_de_reference.getProperty("Nom");
        return "no lastname";
    }

    public void setNom(String nom) {
        this.noeud_de_reference.setProperty("Nom", nom);
    }

    public String getPrenom() {
        if (noeud_de_reference.hasProperty("Prenom"))
            return (String) noeud_de_reference.getProperty("Prenom");
        return "no firstname";
    }

    public void setPrenom(String prenom) {
        this.noeud_de_reference.setProperty("Prenom", prenom);
    }

    public String getVille() {
        if (noeud_de_reference.hasProperty("Ville"))
            return (String) noeud_de_reference.getProperty("Ville");
        return "no city";
    }

    public void setVille(String ville) {
        this.noeud_de_reference.setProperty("Ville", ville);
    }

    public String getTelephone() {
        if (noeud_de_reference.hasProperty("Telephone"))
            return (String) noeud_de_reference.getProperty("Telephone");
        return "no phone number";
    }

    public void setTelephone(String telephone) {
        this.noeud_de_reference.setProperty("Telephone", telephone);
    }
}
