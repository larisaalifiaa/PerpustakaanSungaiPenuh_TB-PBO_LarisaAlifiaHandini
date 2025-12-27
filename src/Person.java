// Kelas abstrak sebagai induk untuk konsep Pewarisan (Inheritance)
public abstract class Person {
    protected String id, nama, alamat;

    // Constructor untuk inisialisasi data (Constructor)
    public Person(String id, String nama, String alamat) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
    }

    // Metode abstrak yang akan diimplementasikan oleh subclass
    public abstract void tampilkanInfo();
}