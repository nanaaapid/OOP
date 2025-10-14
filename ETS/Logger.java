import java.util.ArrayList;

public class Logger {
    private ArrayList<String> logs = new ArrayList<>();

    public void addLog(String text) {
        logs.add(text);
    }

    public void showAll() {
        System.out.println("\n=== TRANSACTION LOG ===");
        if (logs.isEmpty()) {
            System.out.println("Belum ada transaksi.");
        } else {
            for (String log : logs) {
                System.out.println(log);
            }
        }
        System.out.println("=======================\n");
    }
}
