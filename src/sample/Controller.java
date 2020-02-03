package sample;

import Entity.Auteur;
import Entity.Livre;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.io.fs.FileUtils;

import java.io.File;
import java.io.IOException;

public class Controller {
    private static final File databaseDirectory = new File( "D:\\1_Cours\\ISIMA\\ZZ3\\Big_Data\\Neo4j\\neo4j-community-3.5.14_SERVER\\data\\databases\\graph.db" );

    // Relation Types
    private enum RelTypes implements RelationshipType {
        A_ECRIT,
        A_ETE_ECRIT,
    }

    @FXML
    TextField auteur_prenom;
    @FXML
    TextField auteur_nom;
    @FXML
    TextField auteur_domicile;
    @FXML
    TextField auteur_numero;

    @FXML
    TextField livre_numero;
    @FXML
    TextField livre_titre;
    @FXML
    TextField livre_prix;

    @FXML
    TextArea logs;

    @FXML
    private void ajout_auteur() {
        GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( databaseDirectory );
        registerShutdownHook( graphDb );

        Label auteurLabel = Label.label("Auteur");

        try ( Transaction tx = graphDb.beginTx() ) {
            Node node = graphDb.createNode(auteurLabel);
            Auteur auteur = new Auteur(node);
            auteur.fill(auteur_prenom.getText(), auteur_nom.getText(), auteur_domicile.getText(), auteur_numero.getText());

//            IndexManager gestionnaire_index  = graphDb.index();
//            Index<Node> mon_index = gestionnaire_index.forNodes("Auteur");
//            mon_index.add(node, "Node", node);

            tx.success();
            System.out.println("Ajout réussi!");
        }

        graphDb.shutdown();

        String txt = "Ajout auteur:\n\t- " + auteur_prenom.getText() + " " + auteur_nom.getText()
                + "\n\t- " + auteur_domicile.getText() + "\n\t- " + auteur_numero.getText();
        System.out.println(txt);
        logs.setText(txt);

        clearAuteur();
    }

    @FXML
    private void ajout_livre() {
        try {
            FileUtils.deleteRecursively( databaseDirectory );
            System.out.println("SUCCESS: suppression ancienne bases de données");
        } catch (IOException e) {
            System.out.println("FAILURE suppression ancienne bases de données");
        }
        GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( databaseDirectory );
        registerShutdownHook( graphDb );

        Label livreLabel = Label.label("Livre");

        try ( Transaction tx = graphDb.beginTx() ) {
            Node node = graphDb.createNode(livreLabel);
            Livre livre = new Livre(node);
            livre.fill(livre_numero.getText(), livre_titre.getText(), livre_prix.getText());

            // Create index for old version
//            IndexManager gestionnaire_index  = graphDb.index();
//            Index<Node> mon_index = gestionnaire_index.forNodes("Livre");
//            mon_index.add(node, "Node", node);


            tx.success();
            System.out.println("Ajout livre réussi!");
        }

        graphDb.shutdown();

        String txt = "Ajout livre:\n\t- " + livre_titre.getText() + "\n\t-" + livre_numero.getText()
                + "\n\t- " + livre_prix.getText();
        System.out.println(txt);
        logs.setText(txt);

        clearLivre();
    }

    @FXML
    public void ajoutRelation(){
    }

    private void clearAuteur(){
        auteur_nom.clear();
        auteur_prenom.clear();
        auteur_domicile.clear();
        auteur_numero.clear();
    }

    private void clearLivre(){
        livre_numero.clear();
        livre_prix.clear();
        livre_titre.clear();
    }

    private static void registerShutdownHook(@NotNull final GraphDatabaseService graphDb ) {
        Runtime.getRuntime().addShutdownHook(new Thread(graphDb::shutdown));
    }
}
