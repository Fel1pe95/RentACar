package application;

import java.util.ArrayList;
import java.util.List;

import model.entities.Car;
import model.services.CarService;

public class TestConsole {

	public static void main(String[] args) {

		CarService service = new CarService();
		Car car = new Car(null, "Corola", 30.0);
		service.saveAcar(car);

		List<Object> list = new ArrayList<>();
		list = service.findAll();

		list.forEach(System.out::print);
	}
}
