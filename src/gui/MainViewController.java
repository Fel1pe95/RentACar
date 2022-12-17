package gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.listeners.DataChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.services.CarService;

public class MainViewController implements Initializable {

	@FXML
	private Button btCars;
	@FXML
	private Button btRents;

	@FXML
	public void onBtCarsAction() {
		loadView("/gui/VboxCars.fxml", (VboxCarsController controller) ->{
			controller.setCarService(new CarService());
			controller.updateTableView();
		} );

	}

	@FXML
	public void onBtRentsAction() {
		System.out.println("Rents button");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public <T> void loadView(String absoluteName, Consumer<T> initializingAction) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Node node = loader.load();		
			
			VBox vBox = (VBox) Main.getAnchorPane().getChildren().get(1);
			vBox.getChildren().addAll(node);
			
			T controller = loader.getController();
			initializingAction.accept(controller);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
}
