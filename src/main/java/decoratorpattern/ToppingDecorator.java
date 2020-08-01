package decoratorpattern;

public abstract class ToppingDecorator implements Pizza {
	
	protected Pizza pizza;
	
	public ToppingDecorator(Pizza tempPizza) {
		this.pizza = tempPizza;
	}
	
	public String getDescription() {
		return this.pizza.getDescription();
	}

	public double getPrice() {
		return this.pizza.getPrice();
	}

}
