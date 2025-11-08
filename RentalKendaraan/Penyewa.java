public class Penyewa {
    private String nama;
    private Kendaraan kendaraan;

    public Penyewa(String nama, Kendaraan kendaraan) {
        this.nama = nama;
        this.kendaraan = kendaraan;
    }

    public String getNama() {
        return nama;
    }

    public void tampilInfoPenyewa() {
        System.out.println("Nama Penyewa: " + nama);
        System.out.println("Kendaraan yang disewa:");
        kendaraan.tampilInfo();
    }
}
