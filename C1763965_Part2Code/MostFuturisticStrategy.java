public class MostFuturisticStrategy implements ChoiceStrategy {
 	@Override
	public Product chooseBetween(Product a, Product b){
		//Simple if statement to see which product is more futuristic
		if(b.futuristicness <= a.futuristicness){
			return a;
		} else{
			return b;
		}
	}
}


