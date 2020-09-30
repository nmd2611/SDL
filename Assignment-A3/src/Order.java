import java.util.Vector;

public class Order {

    //

    private Vector<FoodItem> ordersList;

    Order() {
        ordersList = new Vector<FoodItem>();

        ordersList.add(new FoodItem(1, "A", 100));
        ordersList.add(new FoodItem(2, "B", 20));
        ordersList.add(new FoodItem(3, "E", 80));
        ordersList.add(new FoodItem(4, "D", 70));
        ordersList.add(new FoodItem(5, "C", 30));
    }

    // public void display_menu()
    // {

    // for(Vector.Entry<FoodItem> i:ordersList.entrySet())
    // {
    // String key=(String)i.getItemName();
    // int val=(int)i.getPrice();
    // System.out.println("Dish name: "+key + " Price: "+ val);
    // }
    // }

}