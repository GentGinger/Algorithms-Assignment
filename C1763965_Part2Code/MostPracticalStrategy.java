public class MostPracticalStrategy implements ChoiceStrategy {
	@Override
	public Product chooseBetween(Product a, Product b){
		//Simple if statement to see which product is more Practial
		if(b.practicality <= a.practicality){
			return a;
		} else{
			return b;
		}
	}
}
   
