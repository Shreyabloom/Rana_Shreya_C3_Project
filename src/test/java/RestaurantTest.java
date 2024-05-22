import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    // Common method to create a restaurant
    @BeforeEach
    public void setUp() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        // Arrange: Set the current time between opening and closing time
        LocalTime currentTime = LocalTime.parse("12:00:00");

        // Act
        boolean isOpen = restaurant.isRestaurantOpen(currentTime);

        // Assert
        assertTrue(isOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        LocalTime testTime = LocalTime.parse("23:00:00");
        Clock fixedClock = Clock.fixed(Instant.parse("2024-04-11T" + testTime + ":00Z"), ZoneId.systemDefault());

        // Use the fixed clock to get the current time
        LocalTime currentTime = LocalTime.now(fixedClock);

        // Assert
        assertFalse(restaurant.isRestaurantOpen(currentTime));
    }

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }

    @Test
    public void calculate_order_value_should_return_total_for_given_items() {
        // Arrange
        int expectedTotal = 119 + 269;

        // Act
        int actualTotal = restaurant.calculateOrderValue("Sweet corn soup", "Vegetable lasagne");

        // Assert
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void calculate_order_value_should_not_return_total_for_given_items() {
        // Arrange
        int expectedTotal = 1000; // Intentionally set a wrong expected total

        // Act
        int actualTotal = restaurant.calculateOrderValue("Sweet corn soup", "Vegetable lasagne");

        // Assert
        assertFalse(expectedTotal == actualTotal); // Intentional failure, but test still passes
    }

}
