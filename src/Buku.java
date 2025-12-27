public class Buku {
    private String kode, judul, pengarang;
    private int stok;

    // constructor
    public Buku(String kode, String judul, String pengarang, int stok) {
        this.kode = kode;
        this.judul = judul;
        this.pengarang = pengarang;
        this.stok = stok;
    }

    // Getter
    public String getKode() { return kode; }
    public String getJudul() { return judul; }
    public String getPengarang() { return pengarang; }
    public int getStok() { return stok; }
}