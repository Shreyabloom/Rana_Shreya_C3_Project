import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        // Create a new instance of Restaurant and add data
        Restaurant restaurant = new Restaurant("Theobroma", "Mumbai", LocalTime.of(10, 0), LocalTime.of(22, 0));
        restaurant.addToMenu("Chocolate Brownie", 120);
        restaurant.addToMenu("Dutch Truffle Cake", 625);

        // Display restaurant details
        restaurant.displayDetails();

        // Calculate order value for specific items
        int totalOrderValue = restaurant.calculateOrderValue("Chocolate Brownie", "Dutch Truffle Cake");
        System.out.println("Total order value: " + totalOrderValue);
    }
}