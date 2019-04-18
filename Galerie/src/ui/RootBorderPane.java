package ui;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import model.Galerie;
import model.GalerieException;

import java.io.File;

public class RootBorderPane extends BorderPane {
    private MenuBar menuBar;
    private Menu file, edit, help, load, save, sort, add;
    private MenuItem exit, loadSer, loadCsv, loadTxt, sortDescendingValue, sortAscendingArtist, image, sculpture, about, saveSer, saveCsv, saveTxt, delete, change;
    private TableOverview tableOverview;
    private Button sortArtist, sortValue, close;
    private ButtonBar buttonBar;

    private Galerie galerie;

    public RootBorderPane() {
        galerie = new Galerie("someName");

        initComp();
        addComp();
        addListeners();
        setVisibility(false);
    }

    private void initComp() {
        menuBar = new MenuBar();

        file = new Menu("File");
        edit = new Menu("Edit");
        help = new Menu("Help");
        load = new Menu("Load");
        save = new Menu("Save");
        sort = new Menu("Sort");
        add = new Menu("Add");

        delete = new MenuItem("Delete");
        change = new MenuItem("Change");
        exit = new MenuItem("Exit");
        loadSer = new MenuItem("load ser");
        loadCsv = new MenuItem("load csv");
        loadTxt = new MenuItem("load txt");
        sortDescendingValue = new MenuItem("sort value");
        sortAscendingArtist = new MenuItem("sort artist");
        image = new MenuItem("Image");
        sculpture = new MenuItem("Sculpture");
        about = new MenuItem("About");
        saveSer = new MenuItem("Save ser");
        saveCsv = new MenuItem("save csv");
        saveTxt = new MenuItem("save txt");

        tableOverview = new TableOverview();

        buttonBar = new ButtonBar();
        sortArtist = new Button("Sort by Artist");
        sortValue = new Button("Sort by Value");
        close = new Button("Close overview");
    }

    private void addComp() {
        load.getItems().addAll(loadSer, loadCsv, loadTxt);
        save.getItems().addAll(saveSer, saveCsv, saveTxt);
        file.getItems().addAll(load, save, exit);
        sort.getItems().addAll(sortAscendingArtist, sortDescendingValue);
        add.getItems().addAll(image, sculpture);
        help.getItems().addAll(about);
        edit.getItems().addAll(sort, delete, change, add);
        menuBar.getMenus().addAll(file, edit, help);

        buttonBar.getButtons().addAll(sortArtist, sortValue, close);

        setTop(menuBar);
        setCenter(tableOverview);
        setBottom(buttonBar);
    }

    private void addListeners() {
        exit.setOnAction(event -> Platform.exit());
        loadSer.setOnAction(e -> loadSerFile());
        loadCsv.setOnAction(e -> loadCsvFile());
        loadTxt.setOnAction(e -> loadTxtFile());
        saveSer.setOnAction(e -> saveSerFile());
        sortArtist.setOnAction(e -> sortArt());
        sortValue.setOnAction(e -> sortVal());
        close.setOnAction(e -> {
            tableOverview.clear();
            galerie.clear();
        });
    }

    public void setVisibility(boolean visible) {
        save.setDisable(!visible);
        sort.setDisable(!visible);
        delete.setDisable(!visible);
        change.setDisable(!visible);
        buttonBar.setVisible(visible);
    }

    private void loadSerFile() {
        File file = getFile("ser");
        if (file != null && file.isFile()) {
            System.out.println(file.getAbsolutePath());
            try {
                galerie.loadKunstwerke(file.getAbsolutePath());
                setVisibility(true);
                tableOverview.setArt(galerie.getKunstwerke());
            } catch (GalerieException e) {
                Main.alert(e.getCause() + ": " + e.getMessage());
            }
        } else {
            Main.alert("No file selected");
        }
    }

    private void loadTxtFile() {
        System.out.println("load txt");
    }

    private void loadCsvFile() {
        System.out.println("load csv");
    }

    private void saveSerFile() {
        File file = getFile("ser");
        if (file != null && file.isFile()) {
            System.out.println(file.getAbsolutePath());
            try {
                galerie.saveKunstwerke(file.getAbsolutePath());
                Main.alert("saved");
            } catch (GalerieException e) {
                Main.alert(e.getCause() + ": " + e.getMessage());
            }
        } else {
            Main.alert("No file selected");
        }
    }

    private File getFile(String extension) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter(extension, extension));
        fileChooser.setInitialDirectory(new File("C:\\scratch"));
        return fileChooser.showOpenDialog(null);
    }

    private void sortVal() {
        try {
            galerie.sort("Künstler");
            tableOverview.setArt(galerie.getKunstwerke());
        } catch (GalerieException e) {
            Main.alert(e.getCause() + ": " + e.getMessage());
        }
    }

    private void sortArt() {
        try {
            galerie.sort("Wert");
            tableOverview.setArt(galerie.getKunstwerke());
        } catch (GalerieException e) {
            Main.alert(e.getCause() + ": " + e.getMessage());
        }
    }
}
