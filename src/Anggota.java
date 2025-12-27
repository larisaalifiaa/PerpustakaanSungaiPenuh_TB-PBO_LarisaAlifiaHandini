// Subclass yang mewarisi kelas Person (Inheritance - Subclass)
public class Anggota extends Person {
    private String tipe;
    private boolean aktif;

    public Anggota(String id, String nama, String alamat, String tipe, boolean aktif) {
        super(id, nama, alamat); // Memanggil constructor superclass
        this.tipe = tipe;
        this.aktif = aktif;
    }

    @Override
    public void tampilkanInfo() {
        String status = aktif ? "AKTIF" : "NON-AKTIF";
        System.out.printf("   ║ %-8s ║ %-15s ║ %-10s ║ %-10s ║\n", id, nama, tipe, status);
    }
}