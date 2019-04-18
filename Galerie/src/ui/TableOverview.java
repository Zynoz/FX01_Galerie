package ui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Kunstwerk;
import model.Skulptur;

import java.util.List;

@SuppressWarnings("unchecked")
public class TableOverview extends TableView {
    private TableColumn<Kunstwerk, String> artist, title;
    private TableColumn<Kunstwerk, Double> length, width;
    private TableColumn<Skulptur, Double> height;
    private TableColumn<Skulptur, String> material;


    public TableOverview() {
        initComp();
        addComp();
        setFactories();
    }

    private void initComp() {
        artist = new TableColumn<>("Artist");
        title = new TableColumn<>("Title");
        material = new TableColumn<>("Material");
        length = new TableColumn<>("Length");
        width = new TableColumn<>("Width");
        height = new TableColumn<>("Height");
    }

    private void addComp() {
        getColumns().addAll(artist, title, length, width, height, material);
    }

    private void setFactories() {
        artist.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getKuenstler()));
        title.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getTitel()));
        length.setCellValueFactory(c -> new ReadOnlyObjectWrapper(c.getValue().getLaenge()));
        width.setCellValueFactory(c -> new ReadOnlyObjectWrapper(c.getValue().getBreite()));
        height.setCellValueFactory(new PropertyValueFactory<>("hoehe"));
        material.setCellValueFactory(new PropertyValueFactory<>("material"));
    }

    public void setArt(List<Kunstwerk> kunstwerkList) {
        getItems().setAll(kunstwerkList);
    }

    public void clear() {
        getItems().clear();
    }
}