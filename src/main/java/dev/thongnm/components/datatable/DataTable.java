package dev.thongnm.components.datatable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.Data;

import java.util.List;

@Data
public class DataTable<T> {
    private TableView<T> tableBody;

    private ObservableList<T> items;

    private boolean paginator = true;

    private List<TblColumn<T>> tblColumns;

    private ObservableList<TableColumn<T,?>> columns = FXCollections.observableArrayList();

    private ObservableList<TableColumn<T,?>> sortOrder = FXCollections.observableArrayList();
}
