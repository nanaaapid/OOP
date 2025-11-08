public class Sepeda extends Kendaraan {
    private String jenis;

    public Sepeda(String merk, String model, int tahunProduksi, String jenis) {
        super(merk, model, tahunProduksi);
        this.jenis = jenis;
    }

    public void tampilInfo() {
        super.tampilInfo();
        System.out.println(" | Jenis Sepeda: " + jenis + "\n");
    }
}
