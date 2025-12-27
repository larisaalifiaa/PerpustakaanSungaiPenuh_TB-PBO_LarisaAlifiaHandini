import java.util.Date;
import java.util.concurrent.TimeUnit;

// Kelas untuk menangani logika perhitungan peminjaman
public class Peminjaman {
    // Metode untuk menghitung denda keterlambatan (Perhitungan Matematika)
    public static double hitungDenda(Date tglJatuhTempo, Date tglKembali) {
        if (tglKembali.after(tglJatuhTempo)) {
            long selisihMillis = tglKembali.getTime() - tglJatuhTempo.getTime();
            long selisihHari = TimeUnit.DAYS.convert(selisihMillis, TimeUnit.MILLISECONDS);
            return selisihHari * 2000.0; // Denda Rp 2.000 per hari (Math)
        }
        return 0.0;
    }
}