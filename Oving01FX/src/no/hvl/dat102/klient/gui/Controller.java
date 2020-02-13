package no.hvl.dat102.klient.gui;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import no.hvl.dat102.Fil;
import no.hvl.dat102.Film;
import no.hvl.dat102.Sjanger;
import no.hvl.dat102.adt.FilmarkivADT;
import no.hvl.dat102.klient.Tekstgrensesnitt;

import java.io.File;
import java.io.IOException;

public class Controller {

    private FilmarkivADT arkiv;
    private String filnavn;
    private FileChooser chooser;

    @FXML
    private ListView<Film> listView;

    @FXML
    private Label filmTittel;

    @FXML
    private Label filmInfo;

    @FXML
    private Label filmerTotalt;

    @FXML
    private ComboBox<String> sokType;

    @FXML
    private TextField sokField;

    @FXML
    private Button redigerButton;

    @FXML
    private Button slettButton;

    @FXML
    private VBox vBox;


    public void initialize() {
        filnavn = "filmer.txt";
        arkiv = Fil.lesFraFil(arkiv, filnavn);
        assert arkiv != null;

        Tekstgrensesnitt grense = new Tekstgrensesnitt();
        grense.skrivUtStatistikk(arkiv);


        loadListView(arkiv.hentFilmer());

        // combobox
        sokType.getItems().addAll("Tittel", "Produsent");
        sokType.setValue("Tittel");

        setSokField();
        initListView();

        chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Tekstfil (*.txt)", "*.txt")
        );
    }

    /**
     * array må være trimmet, ellers bruk overlastet versjon og definer lengde
     * @param filmer
     */
    private void loadListView(Film[] filmer) {
        loadListView(filmer, filmer.length);
        if (filmer.length == 0) {
            updateSelected(null);
        } else {
            listView.getSelectionModel().selectFirst();
        }
    }

    private void loadListView(Film[] filmer, int antall) {
        listView.getItems().clear();
        for (int i = 0; i < antall; i++) {
            Film film = filmer[i];
            listView.getItems().add(film);
        }
        updateTotal();
    }

    private void updateTotal() {
        filmerTotalt.setText("Totalt " + listView.getItems().size() + " filmer");
    }

    @FXML
    private void handleExit(ActionEvent e) {
        exit();
    }

    @FXML
    private void handleSok(ActionEvent e) {
        System.out.println("sok: " + sokField.getText());
        if (sokType.getValue().equals("Tittel")) {
            loadListView(arkiv.sokTittel(sokField.getText()));
        } else {
            loadListView(arkiv.sokProdusent(sokField.getText()));
        }
    }

    @FXML
    private void handleSokType(ActionEvent e) {
        setSokField();
    }

    @FXML
    private void handleSlett(ActionEvent e) {
        Film film = listView.getSelectionModel().getSelectedItem();

        if (film == null) return;

        if (arkiv.slett(film.getFilmNummer())) {
            listView.getItems().remove(film);
            updateTotal();
        }
    }

    @FXML
    private void handleSave(ActionEvent e) {
        if (!Fil.skrivTilFil(arkiv, filnavn)) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lagre");
        alert.setHeaderText("Arkiv lagret til " + filnavn + ".");
        alert.showAndWait();
    }

    @FXML
    private void handleSaveAs(ActionEvent e) {
        File file = chooser.showSaveDialog(vBox.getScene().getWindow());
        if (file == null) return;
        filnavn = file.toString();
        Fil.skrivTilFil(arkiv, filnavn);
    }

    @FXML
    private void handleOpen(ActionEvent e) {
        File file = chooser.showOpenDialog(vBox.getScene().getWindow());
        if (file == null) return;
        arkiv = Fil.lesFraFil(arkiv, file.toString());
        if (arkiv != null) {
            loadListView(arkiv.hentFilmer());
            filnavn = file.toString();
        }
    }

    @FXML
    public void exit() {
        assert arkiv != null;
//        Fil.skrivTilFil(arkiv, "filmer.txt");
        System.out.println("handleExit called.");
        Platform.exit();
        System.exit(0);
    }

    private void initListView() {
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.getSelectionModel().selectFirst();
        addListViewListener();
        setListViewCellFactory();
        updateSelected(listView.getSelectionModel().getSelectedItem());
    }

    private void updateSelected(Film film) {
        if (film == null) {
            filmTittel.setText(" ");
            filmInfo.setText(" \n \n ");
            redigerButton.setDisable(true);
            slettButton.setDisable(true);
            return;
        }
        filmTittel.setText(film.getTittel());
        filmInfo.setText(Sjanger.formatertSjanger(film.getSjanger()) +
                "\nProdusent: " + film.getProdusent() +
                "\nLansering: " + film.getLansering());
        redigerButton.setDisable(false);
        slettButton.setDisable(false);
    }

    private void setSokField() {
        sokField.clear();
        if (sokType.getValue().equals("Tittel")) {
            sokField.setPromptText("Søk etter tittel...");
        } else {
            sokField.setPromptText("Søk etter produsent...");
        }
    }

    private void addListViewListener() {
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Film>() {
            @Override
            public void changed(ObservableValue<? extends Film> observable, Film previous, Film film) {
                updateSelected(film);
            }
        });
    }

    private void setListViewCellFactory() {
        listView.setCellFactory(new Callback<ListView<Film>, ListCell<Film>>() {
            @Override
            public ListCell<Film> call(ListView<Film> param) {
                return new ListCell<Film>() {
                    @Override
                    protected void updateItem(Film film, boolean empty) {
                        super.updateItem(film, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(film.getTittel());
                        }
                    }
                };
            }
        });
    }

    @FXML
    private void showRedigerDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(vBox.getScene().getWindow());
        dialog.setTitle("Rediger film");
        dialog.setHeaderText("Bruk denne dialogen til å endre film");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("filmDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Kunne ikke laste dialog");
            e.printStackTrace();
            return;
        }
    }
}
