public class ProductRecommender {

  public void doExample() {
    // Create products for comparison
    Product p1=new Product("DeLorean DMC-12", 5, 1);
    Product p2=new Product("LDV Maxus", 1, 5);

   	System.out.println("Current strategy: choose most futuristic");
    // Setup Most Futuristic 
    Context Future=new Context(new MostFuturisticStrategy());
    // Run and Print Most Futuistic 
    System.out.println("Chosen vehicle: " + Future.executeStrategy(p1, p2).name);

    System.out.println("Strategy changed: choose most practical");
    // Setup Most practical 
    Context Practical=new Context(new MostPracticalStrategy());
    // Run and Print Most practical 
    System.out.println("Chosen vehicle: " + Practical.executeStrategy(p1, p2).name);
  }
  
  public static void main(String args[]) {
    // Create productRecomender class to run doExample
    ProductRecommender recommender=new ProductRecommender();
    recommender.doExample();
    
    
  }
}
class Context {
    private ChoiceStrategy strategy;

    public Context(ChoiceStrategy strategy){
        this.strategy = strategy;
    }

    public Product executeStrategy(Product num1, Product num2){
        return strategy.chooseBetween(num1, num2);
    }
  }


