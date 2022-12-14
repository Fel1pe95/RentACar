package model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	Map <String, String> errors = new HashMap<>();
	
	public ValidationException(String msg) {
		super(msg);
	}
	
	public Map<String,String> getErrors(){
		return errors;
	}
	
	public void addErrors(String field, String errorMenssage) {
		errors.put(field, errorMenssage);
	}
	
	
}
