package model.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	public static Stage currentStage(ActionEvent event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	public static Integer tryToInteger(String txt) {

		try {
			return Integer.parseInt(txt);

		} catch (NumberFormatException e) {
			return null;
		}

	}

	public static Double tryToDouble(String txt) {
		try {
			return Double.parseDouble(txt);
		} catch (NumberFormatException e) {

			return null;
		}
	}
}
