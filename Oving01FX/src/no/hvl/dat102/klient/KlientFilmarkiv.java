package no.hvl.dat102.klient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import no.hvl.dat102.Filmarkiv2;
import no.hvl.dat102.adt.FilmarkivADT;
import no.hvl.dat102.klient.gui.Controller;

public class KlientFilmarkiv extends Application {

    public static void main(String[] args) {

        System.out.println("main called");
        FilmarkivADT arkiv = new Filmarkiv2();
        Meny meny = new Meny(arkiv);
        meny.start();
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("start called");
        setUserAgentStylesheet(STYLESHEET_MODENA);

        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("gui/mainwindow.fxml"));
        Controller controller = loader.getController();

        stage.setOnCloseRequest(e -> controller.exit());
        stage.setTitle("Filmarkiv");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }
}
