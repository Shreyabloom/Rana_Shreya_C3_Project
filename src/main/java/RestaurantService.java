import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String name) throws restaurantNotFoundException {
        // Implement this method to search for and return the restaurant matching the input string
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equalsIgnoreCase(name)) {
                return restaurant;
            }
        }
        throw new restaurantNotFoundException("Restaurant not found: " + name);
    }

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        if (restaurantToBeRemoved == null) {
            throw new restaurantNotFoundException("Restaurant not found: " + restaurantName);
        }
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<Restaurant> findRestaurantsByLocation(String location) {
        // Implement this method to search for and return all restaurants matching the input location
        List<Restaurant> matchingRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getLocation().equalsIgnoreCase(location)) {
                matchingRestaurants.add(restaurant);
            }
        }
        return matchingRestaurants;
    }

    public List<Restaurant> findRestaurantsByCategory(String category) {
        // Implement this method to search for and return all restaurants matching the input category
        List<Restaurant> matchingRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getCategory().equalsIgnoreCase(category)) {
                matchingRestaurants.add(restaurant);
            }
        }
        return matchingRestaurants;
    }

    public List<Item> getMenuItems(String restaurantName) {
        // Implement this method to return the menu items of the restaurant matching the input name
        try {
            // Implement this method to return the menu items of the restaurant matching the input name
            Restaurant restaurant = findRestaurantByName(restaurantName);
            if (restaurant != null) {
                return restaurant.getMenu();
            }
        } catch (restaurantNotFoundException e) {
            // Handle the exception if it occurs
            System.out.println("Restaurant not found: " + restaurantName);
        }
        return null;
    }

    public void placeOrder(String customerName, String restaurantName, List<Item> items) {
        // Implement this method to place an order for the customer at the specified restaurant
        try {
            // Implement this method to place an order for the customer at the specified restaurant
            Restaurant restaurant = findRestaurantByName(restaurantName);
            if (restaurant != null) {
                restaurant.placeOrder(customerName, items);
            }
        } catch (restaurantNotFoundException e) {
            // Handle the exception if it occurs
            System.out.println("Restaurant not found: " + restaurantName);
        }
    }

    public List<Order> getOrderHistory(String customerName) {
        // Implement this method to return the order history of the specified customer
        List<Order> orderHistory = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            List<Order> customerOrders = restaurant.getOrderHistory(customerName);
            if (customerOrders != null) {
                orderHistory.addAll(customerOrders);
            }
        }
        return orderHistory;
    }
}
