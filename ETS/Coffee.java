public class Coffee {
    private String name;
    private String size;
    private int price;
    private int stock;

    public Coffee(String name, String size, int price, int stock) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public boolean isAvailable() {
        return stock > 0;
    }

    public void reduceStock() {
        if (stock > 0) stock--;
    }

    public void refillStock(int amount) {
        stock += amount;
    }

    @Override
    public String toString() {
        return name + " (" + size + ") - Rp" + price + " | Stok: " + stock;
    }
}
