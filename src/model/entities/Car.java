package model.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String model;
	private Double hourlyValue;
	
	public Car() {
	}

	public Car(Integer id, String model, Double hourlyValue) {
		super();
		this.id=id;
		this.model = model;
		this.hourlyValue = hourlyValue;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getHourlyValue() {
		return hourlyValue;
	}

	public void setHourlyValue(Double hourlyValue) {
		this.hourlyValue = hourlyValue;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n---------------------------------------\n");
		sb.append("Model: " + model);
		sb.append(" - HourLyValue: " + String.format("%.2f", hourlyValue));
		return sb.toString();
	}
	
}
