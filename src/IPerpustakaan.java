import java.util.Scanner;

// Interface untuk mendefinisikan kontrak operasi sistem (Interface)
public interface IPerpustakaan {
    void kelolaBuku(Scanner sc);
    void kelolaAnggota(Scanner sc);
    void transaksiPinjam(Scanner sc);
    void transaksiKembali(Scanner sc);
    void laporanStatistik();
}