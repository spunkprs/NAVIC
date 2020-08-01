package decoratorpattern;

public class MozarellaChesseTopping extends ToppingDecorator {

	public MozarellaChesseTopping(Pizza tempPizza) {
		super(tempPizza);
		System.out.println("Adding Mozarella Cheese");
	}
	
	public String getDescription() {
		return pizza.getDescription() + ", Mozarella Chesse Added";
	}

	public double getPrice() {
		return pizza.getPrice() + 20.0;
	}

}
