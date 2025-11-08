import java.util.ArrayList;
import java.util.Scanner;

public class MainRental {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // daftar kendaraan
        ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();
        daftarKendaraan.add(new Mobil("Toyota", "Avanza", 2020, 4));
        daftarKendaraan.add(new Motor("Yamaha", "NMAX", 2022, 2));
        daftarKendaraan.add(new Sepeda("Polygon", "Helios", 2019, "Balap"));

        // daftar penyewa
        ArrayList<Penyewa> daftarPenyewa = new ArrayList<>();

        int pilih;
        do {
            System.out.println("\n===== MENU RENTAL KENDARAAN =====\n");
            System.out.println("1. Tampilkan daftar kendaraan");
            System.out.println("2. Tampilkan daftar penyewa");
            System.out.println("3. Tambah penyewa baru");
            System.out.println("4. Hapus penyewa");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilih = input.nextInt();
            input.nextLine(); // buang newline

            switch (pilih) {
                case 1:
                    System.out.println("\n=== DAFTAR KENDARAAN TERSEDIA ===");
                    for (int i = 0; i < daftarKendaraan.size(); i++) {
                        daftarKendaraan.get(i).tampilInfo();
                    }
                    break;

                case 2:
                    System.out.println("\n=== DAFTAR PENYEWA ===");
                    if (daftarPenyewa.isEmpty()) {
                        System.out.println("Belum ada penyewa.");
                    } else {
                        for (int i = 0; i < daftarPenyewa.size(); i++) {
                            System.out.println("Penyewa ke-" + (i + 1));
                            daftarPenyewa.get(i).tampilInfoPenyewa();
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n=== TAMBAH PENYEWA BARU ===");
                    System.out.print("Masukkan nama penyewa: ");
                    String nama = input.nextLine();

                    System.out.println("\nPilih kendaraan yang ingin disewa:");
                    for (int i = 0; i < daftarKendaraan.size(); i++) {
                        System.out.println((i + 1) + ". " + daftarKendaraan.get(i).merk + " " + daftarKendaraan.get(i).model);
                    }

                    System.out.print("Masukkan nomor kendaraan: ");
                    int pilihKendaraan = input.nextInt();
                    input.nextLine();

                    if (pilihKendaraan >= 1 && pilihKendaraan <= daftarKendaraan.size()) {
                        Penyewa penyewaBaru = new Penyewa(nama, daftarKendaraan.get(pilihKendaraan - 1));
                        daftarPenyewa.add(penyewaBaru);
                        System.out.println("\n✅ Penyewa berhasil ditambahkan! ✅");
                    } else {
                        System.out.println("\n❌ Nomor kendaraan tidak valid! ❌");
                    }
                    break;

                case 4:
                    System.out.println("\n=== HAPUS PENYEWA ===");
                    if (daftarPenyewa.isEmpty()) {
                        System.out.println("Belum ada penyewa.");
                    } else {
                        for (int i = 0; i < daftarPenyewa.size(); i++) {
                            System.out.println((i + 1) + ". " + daftarPenyewa.get(i).getNama());
                        }
                        System.out.print("\nMasukkan nomor penyewa yang ingin dihapus: ");
                        int hapus = input.nextInt();
                        input.nextLine();

                        if (hapus >= 1 && hapus <= daftarPenyewa.size()) {
                            daftarPenyewa.remove(hapus - 1);
                            System.out.println("\n✅ Penyewa berhasil dihapus!");
                        } else {
                            System.out.println("\n❌ Nomor tidak valid!");
                        }
                    }
                    break;

                case 5:
                    System.out.println("\nTerima kasih! Program selesai.\n");
                    break;

                default:
                    System.out.println("\n❌ Pilihan tidak valid!\n");
            }

        } while (pilih != 5);

        input.close();
    }
}
