public class Main {
    public static void main(String[] args) {
        // Buat dosen
        Dosen d1 = new Dosen("Fajar Baskoro", "12345");
        Dosen d2 = new Dosen("Dwi sunaryono", "67890");

        // Buat mata kuliah
        MataKuliah mk1 = new MataKuliah("IF101", "Pemrograman Berorientasi Objek", 3, d1);
        MataKuliah mk2 = new MataKuliah("IF102", "Struktur Data", 3, d2);

        // Buat mahasiswa
        Mahasiswa mhs1 = new Mahasiswa("Nashwa", "5025241064");
        Mahasiswa mhs2 = new Mahasiswa("Siti", "5025241065");

        // Mahasiswa ambil matkul
        mhs1.tambahMatkul(mk1);
        mhs1.tambahMatkul(mk2);

        mhs2.tambahMatkul(mk2);

        // Tampilkan hasil FRS
        mhs1.tampilkanFRS();
        mhs2.tampilkanFRS();
    }
}
