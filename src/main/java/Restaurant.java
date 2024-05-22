import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    private List<Order> orderHistory = new ArrayList<>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen(LocalTime currentTime) {
        if (currentTime == null) {
            currentTime = getCurrentTime();
        }
        return currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime);
    }

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public List<Item> getMenu() {
        return menu;
    }

    public int calculateOrderValue(String... itemNames) {
        int total = 0;
        for (String itemName : itemNames) {
            Item item = findItemByName(itemName);
            if (item != null) {
                total += item.getPrice();
            }
        }
        return total;
    }

    private Item findItemByName(String itemName) {
        for (Item item : menu) {
            if (item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name, price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public void displayDetails() {
        System.out.println("Restaurant:" + name + "\n"
                + "Location:" + location + "\n"
                + "Opening time:" + openingTime + "\n"
                + "Closing time:" + closingTime + "\n"
                + "Menu:" + "\n" + getMenu());

    }

    public String getName() {
        return name;
    }

    public void placeOrder(String customerName, List<Item> items) {
        // Implement method to place an order for the customer
        Order order = new Order(customerName, items);
        // Logic to process the order
    }

    public List<Order> getOrderHistory() {
        // Implement method to return the order history of the restaurant
        return this.orderHistory;
    }

    public String getLocation() {
        // Implement method to return the location of the restaurant
        return this.location;
    }

    public String getCategory() {
        Order order = new Order("", null); // Creating an instance of Order class
        return order.getCategory(); // Calling getCategory() method from Order class
    }

    public List<Order> getOrderHistory(String customerName) {
        // Return the order history of the specified customer
        List<Order> customerOrderHistory = new ArrayList<>();
        for (Order order : orderHistory) {
            if (order.getCustomerName().equalsIgnoreCase(customerName)) {
                customerOrderHistory.add(order);
            }
        }
        return customerOrderHistory;

    }
}

class Order {

    private String customerName;
    private String category;
    private List<Item> items;
    private List<Order> orderHistory;

    public Order(String customerName, List<Item> items) {
        this.customerName = customerName;
        this.items = items;
    }

    // Getters and setters
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    //Getter and setter methods for orderHistory
    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }
}

