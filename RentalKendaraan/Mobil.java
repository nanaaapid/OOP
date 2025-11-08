public class Mobil extends Kendaraan {
    private int jumlahRoda;

    public Mobil(String merk, String model, int tahunProduksi, int jumlahRoda) {
        super(merk, model, tahunProduksi);
        this.jumlahRoda = jumlahRoda;
    }

    public void tampilInfo() {
        super.tampilInfo();
        System.out.println(" | Jumlah Roda: " + jumlahRoda + "\n");
    }
}
