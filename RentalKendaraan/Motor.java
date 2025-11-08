public class Motor extends Kendaraan {
    private int jumlahRoda;

    public Motor(String merk, String model, int tahunProduksi, int jumlahRoda) {
        super(merk, model, tahunProduksi);
        this.jumlahRoda = jumlahRoda;
    }

    public void tampilInfo() {
        super.tampilInfo();
        System.out.println(" | Jumlah Roda: " + jumlahRoda + "\n");
    }
}
