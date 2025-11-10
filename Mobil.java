public class Mobil extends Kendaraan {

    private boolean bensin;

    public Mobil(String id, String nama, double biayaHarian, boolean tersedia, boolean bensin) {
        super(id, nama, biayaHarian, tersedia);
        this.bensin = bensin;
    }

    @Override
    public double hitungBiayaTambahan() {
        if(bensin){
            return getBiayaHarian() * 0.1;
        } else
        return 0;
    }

    @Override
    public void tampilkanDetail() {
        super.tampilkanDetail();
        System.out.println("Jenis: Mobil, Bahan Bakar: " + (bensin ? "Bensin" : "Non-Bensin"));
    }
}