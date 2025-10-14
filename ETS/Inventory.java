import java.util.ArrayList;

public class Inventory {
    private ArrayList<Coffee> coffeeList = new ArrayList<>();

    public Inventory() {
        // Tambahkan beberapa jenis kopi default
        coffeeList.add(new Coffee("Espresso", "S", 10000, 5));
        coffeeList.add(new Coffee("Latte", "M", 15000, 5));
        coffeeList.add(new Coffee("Cappuccino", "L", 20000, 5));
    }

    public ArrayList<Coffee> getCoffeeList() {
        return coffeeList;
    }

    public Coffee getCoffee(int index) {
        if (index >= 0 && index < coffeeList.size()) {
            return coffeeList.get(index);
        }
        return null;
    }

    public void showMenu() {
        System.out.println("\n=== MENU KOPI ===");
        for (int i = 0; i < coffeeList.size(); i++) {
            System.out.println((i + 1) + ". " + coffeeList.get(i));
        }
    }

    public void refillAll() {
        for (Coffee c : coffeeList) {
            if (!c.isAvailable()) {
                c.refillStock(5);
                System.out.println(c.getName() + " telah diisi ulang.");
            }
        }
    }
}
