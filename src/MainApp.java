import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        // Inisialisasi Scanner untuk input user
        Scanner sc = new Scanner(System.in);
        
        // Polimorfisme: Interface sebagai tipe referensi objek
        IPerpustakaan perpus = new PerpustakaanService(); 
        
        String userValid = "admin";
        String passValid = "perpus2025";
        boolean aksesDiterima = false;

        System.out.println("\n  ╔════════════════════════════════════════════╗");
        System.out.println("  ║    LOGIN SYSTEM: PERPUS SUNGAI PENUH       ║");
        System.out.println("  ╚════════════════════════════════════════════╝");

        while (!aksesDiterima) { // Perulangan login
            System.out.print("  Username : ");
            String inputUser = sc.nextLine();
            System.out.print("  Password : ");
            String inputPass = sc.nextLine();

            if (inputUser.equals(userValid) && inputPass.equals(passValid)) {
                System.out.println("  [✔] Login Berhasil! Membuka Dashboard...");
                aksesDiterima = true;
            } else {
                System.out.println("  [!] Username atau Password Salah!\n");
            }
        }

        // Perulangan Menu Utama dengan Garis Ganda Tanpa Warna
        while (true) { 
            System.out.println("\n  ╔════════════════════════════════════════════╗");
            System.out.println("  ║      SISTEM PERPUSTAKAAN SUNGAI PENUH      ║");
            System.out.println("  ╠════════════════════════════════════════════╣");
            System.out.println("  ║ [1] Pengelolaan Buku (Digital & CRUD)      ║");
            System.out.println("  ║ [2] Pengelolaan Anggota (Tracking & CRUD)  ║");
            System.out.println("  ║ [3] Proses Peminjaman (Date & Create)      ║");
            System.out.println("  ║ [4] Proses Pengembalian (Math & Update)    ║");
            System.out.println("  ║ [5] Laporan Statistik Sesi (Collection)    ║");
            System.out.println("  ║ [0] Keluar Sistem                          ║");
            System.out.println("  ╚════════════════════════════════════════════╝");
            System.out.print("   Pilih Menu > ");
            
            // Exception Handling validasi input angka
            try { 
                int p = Integer.parseInt(sc.nextLine());
                
                // Percabangan switch-case untuk navigasi fitur
                switch (p) {
                    case 1 -> perpus.kelolaBuku(sc);
                    case 2 -> perpus.kelolaAnggota(sc);
                    case 3 -> perpus.transaksiPinjam(sc);
                    case 4 -> perpus.transaksiKembali(sc);
                    case 5 -> perpus.laporanStatistik();
                    case 0 -> {
                        System.out.println("   Shutdown Sistem... Terima kasih!");
                        System.exit(0);
                    }
                    default -> System.out.println("   [!] Menu tidak tersedia!");
                }
            } catch (Exception e) {
                // Menangani error jika user memasukkan karakter selain angka
                System.out.println("   [!] Error: Input harus berupa angka!");
            }
        }
    }
}