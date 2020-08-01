package decoratorpattern;

public class TomatoKetchupDecorator extends ToppingDecorator {

	public TomatoKetchupDecorator(Pizza tempPizza) {
		super(tempPizza);
		System.out.println("Adding Tomato Ketchup");
	}
	
	public String getDescription() {
		return pizza.getDescription() + ", Tomato Ketchup Added";
	}

	public double getPrice() {
		return pizza.getPrice() + 5.0;
	}

}
