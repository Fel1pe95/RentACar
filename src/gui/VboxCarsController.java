package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Car;
import model.exceptions.DbException;
import model.services.CarService;

public class VboxCarsController implements Initializable {

	private CarService service;

	@FXML
	private MenuItem menuFind;
	@FXML
	private MenuItem menuClose;
	@FXML
	private MenuItem menuDelete;
	@FXML
	private MenuItem menuUpdate;
	@FXML
	private MenuItem menuAbout;
	@FXML
	private TableView<Car> tbCars;
	@FXML
	private TableColumn<Car, Integer> tableColumnId;
	@FXML
	private TableColumn<Car, String> tableColumnModel;
	@FXML
	private TableColumn<Car, Double> tableColumnHourlyValue;

	private ObservableList<Car> obsList;
	
	@FXML
	public void onMenuItemCloseAction() {
		Main.getVbox().getChildren().clear();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
	}

	public void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
		tableColumnHourlyValue.setCellValueFactory(new PropertyValueFactory<>("hourlyValue"));
	}

	public void setCarService(CarService service) {
		this.service = service;
	}

	public void updateTableView() {
		if (service == null) {
			throw new DbException("Erro ao conectar ao banco de dados");
		}
		List<Car> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tbCars.setItems(obsList);
	}

}
