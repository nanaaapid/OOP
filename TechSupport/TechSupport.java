import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class TechSupport {
    private Map<String, String[]> responses;
    private String[] genericResponses;
    private Random random;

    public TechSupport() {
        responses = new HashMap<>();
        random = new Random();

        // Untuk tiap kata kunci, bisa punya beberapa respons
        responses.put("error", new String[] {
            "Apakah Anda menerima pesan kesalahan? Coba restart sistem.",
            "Pastikan Anda mencatat pesan error-nya dan cek dokumentasi.",
            "Pesan error itu bisa membantu kita. Bisa kirim detailnya?"
        });
        responses.put("help", new String[] {
            "Apa yang bisa saya bantu?",
            "Silakan ceritakan masalah Anda secara rinci.",
            "Saya di sini untuk membantu. Apa yang terjadi?"
        });
        responses.put("crash", new String[] {
            "Kapan crash terjadi? Apakah ada pesan spesifik?",
            "Tolong periksa log aplikasi setelah crash.",
            "Crash bisa terjadi karena konflik memori atau bug. Ada detailnya?"
        });
        responses.put("slow", new String[] {
            "Sudahkah Anda memeriksa penggunaan CPU dan RAM?",
            "Coba tutup aplikasi berat, atau restart sistem dulu.",
            "Apakah ada aplikasi baru yang berjalan bersama?"
        });
        // Tambah keyword lain sesuai kebutuhan...

        genericResponses = new String[] {
            "Coba jelaskan lebih rinci.",
            "Saya belum mengerti, bisa ulangi?",
            "Bisakah Anda memberi detail lebih lanjut?"
        };
    }

    public String getResponse(String userInput) {
        String[] words = userInput.toLowerCase().split("\\s+");
        for (String w : words) {
            if (responses.containsKey(w)) {
                String[] options = responses.get(w);
                // pilih respons acak dari opsi
                return options[random.nextInt(options.length)];
            }
        }
        // jika tidak ketemu kata kunci
        return genericResponses[random.nextInt(genericResponses.length)];
    }

    public void startChat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Tech Support Chat ===");
        System.out.println("(Ketik 'exit' untuk keluar)");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            if (userInput == null) {
                continue;
            }
            userInput = userInput.trim();
            if (userInput.equalsIgnoreCase("exit") || userInput.equalsIgnoreCase("quit")) {
                System.out.println("TechSupport: Terima kasih, sampai jumpa!");
                break;
            }
            String resp = getResponse(userInput);
            System.out.println("TechSupport: " + resp);
        }

        scanner.close();
    }
    
    public static void main(String[] args) {
        TechSupport ts = new TechSupport();
        ts.startChat();
    }
}
