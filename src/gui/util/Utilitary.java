package gui.util;

import java.util.Locale;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

public class Utilitary {

	public static <T> void formatTableColumnDouble(TableColumn<T, Double> tableColumn, int decimalPlaces) {
		tableColumn.setCellFactory(column -> {
			TableCell<T, Double> cell = new TableCell<T, Double>() {
				@Override
				protected void updateItem(Double item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setText(null);
					} else {
						Locale.setDefault(Locale.US);
						setText(String.format("R$ %." + decimalPlaces + "f", item));
					}
				}
			};
			return cell;
		});
	}
	
}
