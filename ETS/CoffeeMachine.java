import java.util.Scanner;

public class CoffeeMachine {
    private Inventory inventory;
    private Logger logger;

    public CoffeeMachine() {
        inventory = new Inventory();
        logger = new Logger();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== SELAMAT DATANG DI VENDING COFFEE MACHINE ===");

        while (running) {
            inventory.showMenu();
            System.out.print("Pilih kopi (0 untuk keluar): ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            if (choice == 0) {
                running = false;
                continue;
            }

            Coffee selected = inventory.getCoffee(choice - 1);
            if (selected == null) {
                System.out.println("Pilihan tidak valid!");
                continue;
            }

            if (!selected.isAvailable()) {
                System.out.println("Stok habis, silakan pilih kopi lain!");
                continue;
            }
            
            System.out.print("Tambah gula? (y/n): ");
            boolean sugar = sc.nextLine().equalsIgnoreCase("y");

            System.out.print("Tambah susu? (y/n): ");
            boolean milk = sc.nextLine().equalsIgnoreCase("y");

            Order order = new Order(selected, sugar, milk);
            int total = order.calculateTotal();
            
            System.out.println("Total harga: Rp" + total);
            System.out.print("Masukkan uang: Rp");
            int pay = sc.nextInt();
            sc.nextLine();

            Payment payment = new Payment(pay);
            
            if (payment.isEnough(total)) {
                int change = payment.getChange(total);
                selected.reduceStock();
                System.out.println("Kopi sedang dibuat...");
                System.out.println("Silakan ambil kopi Anda!");
                System.out.println("Kembalian: Rp" + change);

                logger.addLog(order.toString() + " | Bayar: Rp" + pay + " | Kembali: Rp" + change);
            } else {
                System.out.println("Uang tidak cukup! Transaksi dibatalkan.");
            }

            System.out.print("\nLihat log transaksi? (y/n): ");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                logger.showAll();
            }
        }

        System.out.println("Mesin dimatikan. Terima kasih!");
        sc.close();
    }
}
