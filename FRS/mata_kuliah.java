public class MataKuliah {
    private String kode;
    private String namaMatkul;
    private int sks;
    private Dosen dosenPengampu;

    public MataKuliah(String kode, String namaMatkul, int sks, Dosen dosenPengampu) {
        this.kode = kode;
        this.namaMatkul = namaMatkul;
        this.sks = sks;
        this.dosenPengampu = dosenPengampu;
    }

    public String getKode() {
        return kode;
    }

    public String getNamaMatkul() {
        return namaMatkul;
    }

    public int getSks() {
        return sks;
    }

    public Dosen getDosenPengampu() {
        return dosenPengampu;
    }

    public void tampilkanInfo() {
        System.out.println("Mata Kuliah: " + namaMatkul + " (" + kode + "), " + sks + " SKS");
        System.out.println("Dosen Pengampu: " + dosenPengampu.getNama());
    }
}
