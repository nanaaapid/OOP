import java.util.ArrayList;

public class Mahasiswa {
    private String nama;
    private String nrp;
    private ArrayList<MataKuliah> daftarMatkul;

    public Mahasiswa(String nama, String nrp) {
        this.nama = nama;
        this.nrp = nrp;
        this.daftarMatkul = new ArrayList<>();
    }

    public String getNama() {
        return nama;
    }

    public String getNrp() {
        return nrp;
    }

    // Mahasiswa ambil mata kuliah
    public void tambahMatkul(MataKuliah matkul) {
        daftarMatkul.add(matkul);
        System.out.println(nama + " berhasil mengambil " + matkul.getNamaMatkul());
    }

    public void tampilkanFRS() {
        System.out.println("\nFRS Mahasiswa: " + nama + " | NRP: " + nrp);
        for (MataKuliah mk : daftarMatkul) {
            System.out.println("- " + mk.getNamaMatkul() + " (" + mk.getSks() + " SKS), Dosen: " + mk.getDosenPengampu().getNama());
        }
    }
}
