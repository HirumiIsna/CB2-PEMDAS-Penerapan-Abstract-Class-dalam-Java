public abstract class Kendaraan {

    private String id;
    private String nama;
    private double biayaHarian;
    private boolean tersedia;

    public Kendaraan(String id, String nama, double biayaHarian, boolean tersedia) {
        this.id = id;
        this.nama = nama;
        this.biayaHarian = biayaHarian;
        this.tersedia = tersedia;
    }

    public double hitungBiayaTotal(int hari) {
        if (hari <= 0) {
            throw new IllegalArgumentException("Hari sewa harus positif");
        }
        return (this.biayaHarian * hari) + hitungBiayaTambahan();
    }

    public void tampilkanDetail() {
        System.out.println("ID: " + id + ", Nama: " + nama +
                ", Biaya Harian: Rp " + biayaHarian +
                ", Status: " + (tersedia ? "Tersedia" : "Tidak Tersedia"));
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getBiayaHarian() {
        return biayaHarian;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    abstract double hitungBiayaTambahan();
}
