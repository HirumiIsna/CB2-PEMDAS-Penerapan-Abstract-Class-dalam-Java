public class truk extends Kendaraan {

    private double muatanMaks;

    public truk(String id, String nama, double biayaHarian, boolean tersedia, double muatanMaks) {
        super(id, nama, biayaHarian, tersedia);
        this.muatanMaks = muatanMaks;
    }

    @Override
    protected double hitungBiayaTambahan() {
        return this.muatanMaks * 50000;
    }

    @Override
    public void tampilkanDetail() {
        super.tampilkanDetail();
        System.out.println("Jenis: Truk, Muatan Maks: " + muatanMaks + " ton");
    }
}