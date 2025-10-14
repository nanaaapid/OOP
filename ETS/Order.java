public class Order {
    private Coffee coffee;
    private boolean addSugar;
    private boolean addMilk;

    public Order(Coffee coffee, boolean addSugar, boolean addMilk) {
        this.coffee = coffee;
        this.addSugar = addSugar;
        this.addMilk = addMilk;
    }

    public int calculateTotal() {
        int total = coffee.getPrice();
        if (addSugar) total += 2000;
        if (addMilk) total += 3000;
        return total;
    }

    @Override
    public String toString() {
        return coffee.getName() + " " + coffee.getSize() +
                (addSugar ? " + Gula" : "") +
                (addMilk ? " + Susu" : "") +
                " | Total: Rp" + calculateTotal();
    }

    public Coffee getCoffee() {
        return coffee;
    }
}
