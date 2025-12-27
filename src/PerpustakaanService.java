import java.sql.*;
import java.util.*;

// Implementasi dari Interface IPerpustakaan
public class PerpustakaanService implements IPerpustakaan {
    // Collection Framework (ArrayList) untuk log sesi
    private List<String> logSesi = new ArrayList<>();

    @Override
    public void kelolaBuku(Scanner sc) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("\n  ╔════════════════════════════════════════╗");
            System.out.println("  ║       MENU PENGELOLAAN BUKU            ║");
            System.out.println("  ╠════════════════════════════════════════╣");
            System.out.println("  ║ [1] Tambah Buku Baru (CRUD Create)     ║");
            System.out.println("  ║ [2] Lihat Katalog Digital (CRUD Read)  ║");
            System.out.println("  ╚════════════════════════════════════════╝");
            System.out.print("   Pilihan > ");
            int pil = Integer.parseInt(sc.nextLine());

            if (pil == 1) { // CRUD Create
                System.out.println("\n  ┌── INPUT DATA BUKU BARU ──┐");
                System.out.print("   Kode Buku : "); String k = sc.nextLine();
                System.out.print("   Judul     : "); String j = sc.nextLine();
                System.out.print("   Pengarang : "); String p = sc.nextLine(); 
                System.out.print("   Stok      : "); int s = Integer.parseInt(sc.nextLine());
                
                PreparedStatement ps = conn.prepareStatement("INSERT INTO buku VALUES (?,?,?,?)");
                ps.setString(1, k); ps.setString(2, j); ps.setString(3, p); ps.setInt(4, s);
                ps.executeUpdate();
                System.out.println("   [✔] BERHASIL: Buku oleh " + p + " telah didigitalisasi.");
            } else { // CRUD Read
                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM buku");
                System.out.println("\n   ═══ KATALOG BUKU ═══");
                System.out.println("   ╔══════════╦═══════════════════════════════════╦═════════════════════════╦══════╗");
                System.out.println("   ║ KODE     ║ JUDUL BUKU (String)               ║ PENGARANG               ║ STOK ║");
                System.out.println("   ╠══════════╬═══════════════════════════════════╬═════════════════════════╬══════╣");
                while(rs.next()) { // Perulangan
                    String judul = rs.getString(2);
                    String pengarang = rs.getString(3);
                    
                    // Manipulasi String
                    if (judul.length() > 35) judul = judul.substring(0, 32) + "..."; 
                    if (pengarang.length() > 25) pengarang = pengarang.substring(0, 22) + "...";
                    
                    System.out.printf("   ║ %-8s ║ %-33s ║ %-23s ║ %-4d ║\n", rs.getString(1), judul, pengarang, rs.getInt(4));
                }
                System.out.println("   ╚══════════╩═══════════════════════════════════╩═════════════════════════╩══════╝");
            }
        } catch (Exception e) { System.err.println("   [!] Gagal: " + e.getMessage()); }
    }

    @Override
    public void kelolaAnggota(Scanner sc) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("\n  ╔════════════════════════════════════════╗");
            System.out.println("  ║      MENU PENGELOLAAN ANGGOTA          ║");
            System.out.println("  ╠════════════════════════════════════════╣");
            System.out.println("  ║ [1] Registrasi Anggota (CRUD Create)   ║");
            System.out.println("  ║ [2] Tracking Anggota (CRUD Read)       ║");
            System.out.println("  ╚════════════════════════════════════════╝");
            System.out.print("   Pilihan > ");
            int pil = Integer.parseInt(sc.nextLine());

            if (pil == 1) { // CRUD Create
                System.out.println("\n  ┌── FORM PENDAFTARAN ANGGOTA ──┐");
                System.out.print("   ID Anggota   : "); String id = sc.nextLine();
                System.out.print("   Nama Lengkap : "); String n = sc.nextLine();
                System.out.print("   Alamat       : "); String a = sc.nextLine();
                System.out.print("   Tipe (Pelajar/Umum): "); String t = sc.nextLine();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO anggota VALUES (?,?,?,?,TRUE)");
                ps.setString(1, id); ps.setString(2, n); ps.setString(3, a); ps.setString(4, t);
                ps.executeUpdate();
                System.out.println("   [✔] BERHASIL: Data anggota resmi terdigitalisasi.");
            } else { // CRUD Read
                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM anggota");
                System.out.println("\n   ═══ DATA TRACKING ANGGOTA ═══");
                System.out.println("   ╔══════════╦═══════════════════════════════════╦═══════════════╦════════════╗");
                System.out.println("   ║ ID       ║ NAMA LENGKAP ANGGOTA              ║ TIPE          ║ STATUS     ║");
                System.out.println("   ╠══════════╬═══════════════════════════════════╬═══════════════╬════════════╣");
                while(rs.next()) {
                    String nama = rs.getString(2);
                    if (nama.length() > 35) nama = nama.substring(0, 32) + "...";
                    String status = rs.getBoolean(5) ? "AKTIF" : "NON-AKTIF";
                    System.out.printf("   ║ %-8s ║ %-33s ║ %-13s ║ %-10s ║\n", rs.getString(1), nama, rs.getString(4), status);
                }
                System.out.println("   ╚══════════╩═══════════════════════════════════╩═══════════════╩════════════╝");
            }
        } catch (Exception e) { System.err.println("   [!] Gagal: " + e.getMessage()); }
    }

    @Override
    public void transaksiPinjam(Scanner sc) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("\n  ╔════════════════════════════════════════╗");
            System.out.println("  ║    PROSES PINJAM (Create & Date)       ║");
            System.out.println("  ╠════════════════════════════════════════╣");
            System.out.print("   ║ ID Anggota : "); String idA = sc.nextLine();
            System.out.print("   ║ Kode Buku  : "); String kB = sc.nextLine();
            System.out.println("  ╚════════════════════════════════════════╝");
            
            // Generate ID Pinjam secara otomatis
            String idP = "PJM-" + System.currentTimeMillis(); 
            
            // Manipulasi Date
            PreparedStatement ps = conn.prepareStatement("INSERT INTO peminjaman (id_peminjaman, id_anggota, kode_buku, tanggal_pinjam, jatuh_tempo) VALUES (?, ?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY))");
            ps.setString(1, idP); ps.setString(2, idA); ps.setString(3, kB);
            ps.executeUpdate();

            ResultSet rs = conn.createStatement().executeQuery("SELECT CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY)");
            if(rs.next()) {
                System.out.println("\n  ┌────────────────────────────────────────┐");
                System.out.println("   [✔] PINJAM BERHASIL!");
                System.out.println("   ID Peminjaman: " + idP); 
                System.out.println("   Tgl Pinjam   : " + rs.getString(1));
                System.out.println("   Jatuh Tempo  : " + rs.getString(2));
                System.out.println("  └────────────────────────────────────────┘");
                System.out.println("   (!) Catat ID Peminjaman di atas untuk proses pengembalian.");
            }
            logSesi.add("Pinjam: " + idP);
        } catch (Exception e) { System.err.println("   [!] Gagal: " + e.getMessage()); }
    }

    @Override
    public void transaksiKembali(Scanner sc) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("\n  ╔════════════════════════════════════════╗");
            System.out.println("  ║   PENGEMBALIAN BUKU (Math & Update)    ║");
            System.out.println("  ╠════════════════════════════════════════╣");
            System.out.print("   ║ Masukkan ID Pinjam : "); String id = sc.nextLine();
            System.out.println("  ╚════════════════════════════════════════╝");

            PreparedStatement psCek = conn.prepareStatement("SELECT jatuh_tempo FROM peminjaman WHERE id_peminjaman = ?");
            psCek.setString(1, id);
            ResultSet rs = psCek.executeQuery();

            if (rs.next()) {
                java.util.Date tglTempo = rs.getDate(1);
                java.util.Date tglSekarang = new java.util.Date(); 
                
                // Perhitungan Matematika Denda melalui class Peminjaman
                double denda = Peminjaman.hitungDenda(tglTempo, tglSekarang);
                
                PreparedStatement psUpd = conn.prepareStatement("UPDATE peminjaman SET tanggal_kembali = CURDATE(), denda = ?, status = 'Kembali' WHERE id_peminjaman = ?");
                psUpd.setDouble(1, denda); psUpd.setString(2, id);
                psUpd.executeUpdate(); // CRUD Update

                System.out.println("\n  ┌────────────────────────────────────────┐");
                System.out.println("   [✔] PENGEMBALIAN BERHASIL!");
                System.out.println("   Tgl Kembali  : " + new java.sql.Date(tglSekarang.getTime()));
                System.out.println("   Denda        : Rp " + denda);
                System.out.println("  └────────────────────────────────────────┘");
            } else { System.out.println("   [!] ID Peminjaman tidak ditemukan!"); }
        } catch (Exception e) { System.err.println("   [!] Gagal: " + e.getMessage()); }
    }

    @Override
    public void laporanStatistik() {
        System.out.println("\n  ╔════════════════════════════════════════╗");
        System.out.println("  ║    LAPORAN STATISTIK SESI (Collection) ║");
        System.out.println("  ╠════════════════════════════════════════╣");
        // Menggunakan .size() dari ArrayList
        System.out.println("  ║ Total Aktivitas Digital Sesi Ini: " + String.format("%-4s", logSesi.size()) + " ║");
        System.out.println("  ╚════════════════════════════════════════╝");
    }
}