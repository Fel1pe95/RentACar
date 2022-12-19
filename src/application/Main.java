package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

	public static Scene mainScene;
	public static AnchorPane pane;
	public static VBox vBox;
	public static Stage parentStage;
	
	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
	
		this.parentStage = stage;
		
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
		pane = loader.load();
		vBox = (VBox) pane.getChildren().get(1);
		mainScene =  new Scene(pane);
		parentStage.setScene(mainScene);
		parentStage.setResizable(false);
		parentStage.setTitle("Rent a Car");
		parentStage.show();
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
	
	public static Stage getParentStage() {
		return parentStage;
	}
}
