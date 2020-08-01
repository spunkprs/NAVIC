package decoratorpattern;

public class BasicPizza implements Pizza {

	@Override
	public String getDescription() {
		return "Basic Pizza Ready";
	}

	@Override
	public double getPrice() {
		return 50.0;
	}

}
