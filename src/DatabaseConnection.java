import java.sql.*;

// Kelas untuk mengatur koneksi ke database MySQL (JDBC)
public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        // Mengembalikan koneksi menggunakan driver JDBC (Exception Handling)
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/perpustakaan_sungaipenuh", "root", "");
    }
}