package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{

	public static Scene mainScene;
	
	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
	
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
		AnchorPane anchorPane = loader.load();
		mainScene =  new Scene(anchorPane);
		stage.setScene(mainScene);
		stage.setResizable(false);
		stage.setTitle("Rent a Car");
		stage.show();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
