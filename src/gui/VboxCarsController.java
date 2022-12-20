package gui;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utilitary;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Car;
import model.exceptions.DbException;
import model.exceptions.DbIntegrityException;
import model.services.CarService;
import model.util.Utils;

public class VboxCarsController implements Initializable, DataChangeListener {

	private CarService service;

	@FXML
	private MenuItem menuFind;
	@FXML
	private MenuItem menuClose;
	@FXML
	private MenuItem menuDelete;
	@FXML
	private MenuItem menuNew;
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
	@FXML
	private TableColumn<Car, Car> tableColumnEdit;
	@FXML
	private TableColumn<Car,Car> tableColumnRemove;

	private ObservableList<Car> obsList;

	@FXML
	public void onMenuItemCloseAction() {
	Main.getVbox().getChildren().clear();
	}
	
	@FXML
	public void onMenuItemNewAction(ActionEvent event) {
		loadView(new Car(), "/gui/CarFormulary.fxml", Main.getParentStage());
	}
	@FXML
	public void onMenuItemDeleteAction() {}
	@FXML
	public void onMenuItemFindAction() {}
	@FXML
	public void onMenuItemAboutAction() {}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
		initEditButtons();
		initRemoveButtons();

	}

	public void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
		tableColumnHourlyValue.setCellValueFactory(new PropertyValueFactory<>("hourlyValue"));
		Utilitary.formatTableColumnDouble(tableColumnHourlyValue, 2);
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

	private void initEditButtons() {
		tableColumnEdit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEdit.setCellFactory(param -> new TableCell<Car, Car>() {
			private final Button button = new Button("edit");

			@Override
			protected void updateItem(Car obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> loadView(obj, "/gui/CarFormulary.fxml", Utils.currentStage(event)));
			}
		});
	}
	
	private void initRemoveButtons() {
		tableColumnRemove.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnRemove.setCellFactory(param -> new TableCell<Car, Car>() {
			private final Button button = new Button("remove");

			@Override
			protected void updateItem(Car obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> removeEntity(obj));
			}
		});
	}
	
	private void removeEntity(Car obj) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Are you sure to delete?");
		if(result.get() == ButtonType.OK) {
			if(service == null) {
				throw new IllegalStateException("service was null");
			}
			try {
				service.removeCar(obj.getId());
				updateTableView();
			}catch(DbIntegrityException e) {
				Alerts.showAlert("Error", null, e.getMessage(), AlertType.ERROR);
			}
		}
	}
	
	

	public <T> void loadView(Car car, String absoluteName, Stage Parentstage) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			CarFormularyController controller = loader.getController();
			controller.setEntity(car);
			controller.setService(service);
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();

			Stage stage = new Stage();
			stage.setTitle("Car formulary");
			stage.setScene(new Scene(pane));
			stage.setResizable(false);
			stage.initOwner(Parentstage);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.showAndWait();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onDataChanged() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/VboxCars.fxml"));
			Node node = loader.load();	
			VboxCarsController controller = loader.getController();	
			VBox vBox = (VBox) Main.getAnchorPane().getChildren().get(1);
			vBox.getChildren().clear();
			vBox.getChildren().addAll(node);
			controller.setCarService(service);
			controller.updateTableView();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
