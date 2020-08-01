package decoratorpattern;

public class ChickenToppingDecorator extends ToppingDecorator {

	public ChickenToppingDecorator(Pizza tempPizza) {
		super(tempPizza);
		System.out.println("Adding Chicken Topping");
	}
	
	public String getDescription() {
		return pizza.getDescription() + ", Chicken Topping Added";
	}

	public double getPrice() {
		return pizza.getPrice() + 30.0;
	}

}
