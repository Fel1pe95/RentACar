package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Car;
import model.exceptions.DbException;
import model.exceptions.ValidationException;
import model.services.CarService;
import model.util.Utils;

public class CarFormularyController implements Initializable {

	private Car car;
	private CarService service;
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField txtId;
	@FXML
	private TextField txtModel;
	@FXML
	private TextField txtHourlyValue;
	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;
	@FXML
	private Label lbErrorId;
	@FXML
	private Label lbErrorModel;
	@FXML
	private Label lbErrorHourly;

	@FXML
	public void onBtSaveAction(ActionEvent event) {

		try {
			Car car = getFormData();
			if (car.getId() == null) {
				service.saveAcar(car);
				Alerts.showAlert("Sucess", null, "Sucessfully registration", AlertType.INFORMATION);

				Utils.currentStage(event).close();
				notifyDataChangeListeners();
			} else {
				service.updateAcar(car);
				Alerts.showAlert("Sucess", null, "Sucessfully update", AlertType.INFORMATION);

				Utils.currentStage(event).close();
				notifyDataChangeListeners();
			}
		} catch (ValidationException e) {
			setErrors(e.getErrors());
		} catch (DbException e) {
			Alerts.showAlert("Erro", null, e.getMessage(), AlertType.ERROR);
		}

	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Stage stage = Utils.currentStage(event);
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	initializeNodes();
	
	}

	public void setEntity(Car car) {
		this.car = car;
	}

	public void setService(CarService service) {
		this.service = service;
	}

	public void updateFormData() {
		if (car == null) {
			throw new IllegalStateException("car is null");
		}
		if (car.getId() == null) {
			txtId.setPromptText("Auto Incremented");
			txtId.editableProperty().set(false);;
		} else{
			txtId.editableProperty().set(false);
			txtId.setText(String.valueOf(car.getId()));
		}
		
		if (car.getModel() == null) {
			txtModel.setPromptText("Model");
		}else {
			txtModel.setText(car.getModel());
		}
		if (car.getHourlyValue() == null) {
			txtHourlyValue.setPromptText("00,0");
		} else {
			txtHourlyValue.setText(String.format("%.2f", car.getHourlyValue()));
		}
	}

	public Car getFormData() {

		Car car = new Car();
		ValidationException exception = new ValidationException("Error menssages");
		if (txtId == null || txtId.getText().trim().equals("")) {
			txtId.setPromptText("Auto Incremented!");
		}
		car.setId(Utils.tryToInteger(txtId.getText()));
		if (txtModel == null || txtModel.getText().trim().equals("")) {
			exception.addErrors("model", "field cant be empty");
		}
		car.setModel(txtModel.getText());
		if (txtHourlyValue == null || txtHourlyValue.getText().trim().equals("")) {
			exception.addErrors("hourlyValue", "field cant be empty");
		}
		car.setHourlyValue(Utils.tryToDouble(txtHourlyValue.getText()));

		if (exception.getErrors().size() > 0) {
			throw exception;
		}

		return car;
	}

	public void setErrors(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		lbErrorModel.setText(fields.contains("model") ? errors.get("model") : "");
		lbErrorHourly.setText(fields.contains("hourlyValue") ? errors.get("hourlyValue") : "");
	}

	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	public void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}
	
	public void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtModel, 20);
	
	}
}
