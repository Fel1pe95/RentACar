package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.Car;
import model.services.CarService;

public class CarFormularyController implements Initializable{

	private Car car;
	private CarService service;
	
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
	public void onBtSaveAction() {}
	@FXML
	public void onBtCancelAction() {}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
	}
	
	public void setEntity(Car car) {
		this.car = car;
	}
	public void setService(CarService service) {
		this.service = service;
	}
	
	public void updateFormData() {
		if(car == null) {
			throw new IllegalStateException("car is null");
		}
		
		txtId.setText(String.valueOf(car.getId()));
		txtModel.setText(car.getModel());
		txtHourlyValue.setText(String.format("%.2f", car.getHourlyValue()));
	}
	
}
