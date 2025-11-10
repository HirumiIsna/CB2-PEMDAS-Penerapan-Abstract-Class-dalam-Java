import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Kendaraan[] inventaris = new Kendaraan[5];
    private static int jumlahKendaraan = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        isiInventarisAwal();
        jalankanMenu();
        scanner.close();    
        System.out.println("Terima kasih telah menggunakan sistem rental.");
    }

    private static void tambahKendaraan(Kendaraan k) {
        if (jumlahKendaraan < inventaris.length) {
            inventaris[jumlahKendaraan] = k;
            jumlahKendaraan++;
        } else {
            System.out.println("Inventaris penuh, tidak bisa menambah kendaraan baru.");
        }
    }

    private static void isiInventarisAwal() {
        tambahKendaraan(new Mobil("M01", "Toyota Avanza", 300000, true, true));
        tambahKendaraan(new Mobil("M02", "Hyundai Ioniq 5", 500000, true, false));
        tambahKendaraan(new Motor("B01", "Honda Vario 125", 100000, true));
        tambahKendaraan(new Motor("B02", "Yamaha NMAX", 150000, false));
        tambahKendaraan(new truk("T01", "Mitsubishi Fuso", 450000, true, 2.5));
    }

    private static void jalankanMenu() {
        int pilihan = -1;
        while (pilihan != 0) {
            System.out.println("\n--- Sistem Rental Kendaraan ---");
            System.out.println("1. Tampilkan Semua Kendaraan");
            System.out.println("2. Hitung Biaya Sewa Total");
            System.out.println("3. Cari Kendaraan Tersedia (Berdasarkan Jenis)");
            System.out.println("0. Keluar");
            System.out.print("Pilih opsi: ");

            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1:
                        tampilkanSemuaKendaraan();
                        break;
                    case 2:
                        hitungBiayaSewa();
                        break;
                    case 3:
                        cariKendaraanTersedia();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Masukkan angka.");
                scanner.nextLine();
            }
        }
    }

    private static void tampilkanSemuaKendaraan() {
        System.out.println("\n--- Daftar Semua Kendaraan ---");
        if (jumlahKendaraan == 0) {
            System.out.println("Inventaris kosong.");
            return;
        }

        for (int i = 0; i < jumlahKendaraan; i++) {
            inventaris[i].tampilkanDetail();
            System.out.println("--------------------");
        }
    }

    private static void hitungBiayaSewa() {
        System.out.println("\n--- Hitung Biaya Sewa ---");
        System.out.print("Masukkan ID Kendaraan: ");
        String id = scanner.nextLine();

        Kendaraan ditemukan = null;
        for (int i = 0; i < jumlahKendaraan; i++) {
            if (inventaris[i].getId().equalsIgnoreCase(id)) {
                ditemukan = inventaris[i];
                break;
            }
        }

        if (ditemukan == null) {
            System.out.println("Kendaraan dengan ID " + id + " tidak ditemukan.");
            return;
        }

        try {
            System.out.print("Masukkan jumlah hari sewa (1-7): ");
            int hari = scanner.nextInt();
            scanner.nextLine();

            double totalBiaya = ditemukan.hitungBiayaTotal(hari);

            System.out.printf("Biaya total untuk %s ID: %s selama %d hari: Rp %.0f\n",
                    ditemukan.getClass().getSimpleName(),
                    ditemukan.getId(),
                    hari,
                    totalBiaya);

        } catch (InputMismatchException e) {
            System.out.println("Input hari tidak valid. Masukkan angka.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void cariKendaraanTersedia() {
        System.out.println("\n--- Cari Kendaraan Tersedia ---");
        System.out.print("Masukkan jenis kendaraan (Mobil, Motor, Truk): ");
        String jenis = scanner.nextLine();

        boolean ditemukan = false;
        System.out.println("Hasil pencarian untuk " + jenis + " yang tersedia:");

        for (int i = 0; i < jumlahKendaraan; i++) {
            Kendaraan k = inventaris[i];
            if (k.isTersedia()) {
                if (jenis.equalsIgnoreCase("Mobil") && k instanceof Mobil) {
                    k.tampilkanDetail();
                    ditemukan = true;
                } else if (jenis.equalsIgnoreCase("Motor") && k instanceof Motor) {
                    k.tampilkanDetail();
                    ditemukan = true;
                } else if (jenis.equalsIgnoreCase("Truk") && k instanceof truk) {
                    k.tampilkanDetail();
                    ditemukan = true;
                }
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada " + jenis + " yang tersedia saat ini.");
        }
    }
}