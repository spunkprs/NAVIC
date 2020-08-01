package decoratorpattern;

public class PizzaMaker {
	
	public static void main(String ar[]) {
		
		System.out.println("Going to create Pizza !!");
		
		Pizza createdPizza = new MozarellaChesseTopping(new ChickenToppingDecorator(new TomatoKetchupDecorator(new BasicPizza())));
		
		System.out.println("Description of created Pizza : " + createdPizza.getDescription());
		System.out.println("Total Cost of created Pizza :" + createdPizza.getPrice()); 
		
	}
}
