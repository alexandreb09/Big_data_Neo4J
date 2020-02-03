package Entity;

import org.neo4j.graphdb.Node;

public class Livre {
    private Node noeud_de_reference;

    public Livre(Node auteurNode){
        this.noeud_de_reference = auteurNode;
    }

    public Livre fill(String numero, String titre, String prix){
        this.setNumero(numero);
        this.setTitre(titre);
        this.setPrix(prix);
        return this;
    }

    public Livre afficher(){
        String txt = "Livre: "
                + "\n\t- TITRE: " + getTitre()
                + "\n\t- NUMERO: " + getNumero()
                + "\n\t- PRIX: " + getPrix();
        System.out.println(txt);
        return this;
    }

    public String getNumero() {
        if (noeud_de_reference.hasProperty("Numero"))
            return (String) noeud_de_reference.getProperty("Numero");
        return "no Numero";
    }

    public void setNumero(String numero) {
        this.noeud_de_reference.setProperty("Numero", numero);
    }

    public String getTitre() {
        if (noeud_de_reference.hasProperty("Titre"))
            return (String) noeud_de_reference.getProperty("Titre");
        return "no Titre";
    }

    public void setTitre(String titre) {
        this.noeud_de_reference.setProperty("Titre", titre);
    }

    public String getPrix() {
        if (noeud_de_reference.hasProperty("Prix"))
            return (String) noeud_de_reference.getProperty("Prix");
        return "no price";
    }

    public void setPrix(String prix) {
        this.noeud_de_reference.setProperty("Prix", prix);
    }
}
