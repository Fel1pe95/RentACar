package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainViewController {

	@FXML
	private Button btCars;
	@FXML
	private Button btRents;
	
	@FXML
	public void onBtCarsAction() {
		System.out.println("Cars button");
	}
	
	@FXML
	public void onBtRentsAction() {
		System.out.println("Rents button");
	}

	
}
