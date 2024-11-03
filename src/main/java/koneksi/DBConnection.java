package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "db_mahasiswa"; // Change this to your database name
    private static final String USER = "root"; // Change this to your DB username
    private static final String PASS = ""; // Change this to your DB password

    public static Connection connectDB() {
        try {
            Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USER, PASS);
            createTableIfNotExists(con); // Call the method to create the table
            return con;
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }

    private static void createTableIfNotExists(Connection con) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS tb_mahasiswa (" +
                "nim VARCHAR(20) PRIMARY KEY, " +
                "nama VARCHAR(100), " +
                "jenis_kelamin VARCHAR(10), " +
                "kelas VARCHAR(20)" +
                ");";
        try (Statement stmt = con.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Table 'tb_mahasiswa' is ready.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }
}
