package dev.thongnm.components.datatable;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.util.List;

public class DataTable<T> extends VBox {
    private TableView<T> table;

    private ObservableList<T> items;

    private boolean showPagination = false;

    private List<Integer> pageSizeOptions = null;

    private Integer defaultPageSize = 25;

    private boolean showFilter = false;

    private String selectionMode = "";

    public DataTable() {

    }
}
