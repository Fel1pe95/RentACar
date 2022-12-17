package application;

import gui.CarFormularyController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

	public static Scene mainScene;
	public static AnchorPane pane;
	public static VBox vBox;
	
	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
	
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
		pane = loader.load();
		vBox = (VBox) pane.getChildren().get(1);
		mainScene =  new Scene(pane);
		stage.setScene(mainScene);
		stage.setResizable(false);
		stage.setTitle("Rent a Car");
		stage.show();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static AnchorPane getAnchorPane() {
		return pane;
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}
	
	public static VBox getVbox() {
		return vBox;
	}
}
