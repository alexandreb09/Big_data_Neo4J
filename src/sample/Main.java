package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.neo4j.cypher.internal.compiler.v2_3.commands.expressions.EFunction;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.schema.Schema;


import java.io.File;
import java.io.IOException;

public class Main extends Application {
    private static final File databaseDirectory = new File( "D:\\1_Cours\\ISIMA\\ZZ3\\Big_Data\\Neo4j\\neo4j-community-3.5.14_SERVER\\data\\databases\\graph.db" );


    public static void main( final String[] args ) throws IOException {
        // CREATE LABEL
        Main main = new Main();
        main.createLabels();

        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("TP Neo4J!");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }

    private static void registerShutdownHook(@NotNull final GraphDatabaseService graphDb ) {
        Runtime.getRuntime().addShutdownHook(new Thread(graphDb::shutdown));
    }

    public void createLabels(){
        System.out.println("Début création index: Auteur et Livre");

        GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( databaseDirectory );
        registerShutdownHook(graphDb);

        Schema schema;
        Label auteurLabel = Label.label("Auteur");
        Label livreLabel = Label.label("Livre");

        // INDEX Auteur by Nom
        try (Transaction tx = graphDb.beginTx()) {
            schema = graphDb.schema();

            schema.indexFor(auteurLabel)
                    .on("Nom")
                    .create();
            schema.indexFor(livreLabel)
                    .on("Titre")
                    .create();
            tx.success();
            System.out.println("Création index réussie");
        }
        graphDb.shutdown();
    }
}
