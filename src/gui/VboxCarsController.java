package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Car;
import model.exceptions.DbException;
import model.services.CarService;
import model.util.Utils;

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
	@FXML
	private TableColumn<Car, Car> tableColumnEdit;

	private ObservableList<Car> obsList;

	@FXML
	public void onMenuItemCloseAction() {
		Main.getVbox().getChildren().clear();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
		initEditButtons();
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
				button.setOnAction(event -> loadView(obj, "/gui/CarFormulary.fxml", Utils.currentStage(event),
						(CarFormularyController controller) -> {
							controller.setEntity(obj);
							controller.setService(new CarService());
							controller.updateFormData();
						}));
			}
		});
	}

	public <T> void loadView(Car car, String absoluteName, Stage Parentstage, Consumer<T> initializingAction) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			T controller = loader.getController();
			initializingAction.accept(controller);

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

}
