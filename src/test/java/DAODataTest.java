package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import DAO.DAOData;  // Import the DAOData class
import model.TambahData;
import java.util.List;

public class DAODataTest {
    
    private DAOData daoData;

    @BeforeEach
    public void setUp() {
        daoData = new DAOData();
    }

    @Test
    public void testInsertAndGetAll() {
        TambahData data = new TambahData();
        data.setNim("123456");
        data.setNama("John Doe");  // Pastikan nama yang diharapkan adalah "John Doe"
        data.setJenisKelamin("Laki-laki");
        data.setKelas("A");
    
        // Masukkan data
        daoData.insert(data);
    
        // Ambil semua data
        List<TambahData> result = daoData.getAll();
    
        // Verifikasi bahwa nama yang dimasukkan ada dalam hasil
        assertEquals("John Doe", result.get(0).getNama());  // Periksa apakah nama yang diambil sesuai
    }

    @Test
    public void testUpdate() {
        // Arrange
        TambahData data = new TambahData();
        data.setNim("12345");
        data.setNama("John Doe");
        data.setJenisKelamin("Laki-laki");
        data.setKelas("Kelas A");
        daoData.insert(data);

        // Update
        data.setNama("Jane Doe");
        daoData.update(data);

        // Act
        List<TambahData> allData = daoData.getAll();

        // Assert
        assertEquals("Jane Doe", allData.get(0).getNama());
    }

    @Test
    public void testDelete() {
        // Ensure there's data to delete
        // Insert the data first if necessary
        daoData.delete("12345");
        List<TambahData> allData = daoData.getAll();
        assertFalse(allData.stream().anyMatch(mhs -> mhs.getNim().equals("12345")), "Data should be deleted");
    }
}
