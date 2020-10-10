public class FoodItem {

    // Defines the structure of the Dish/item we are going to store

    private int id;
    private String itemName;
    private int price, quantity;

    FoodItem(int id, String itemName, int price) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}