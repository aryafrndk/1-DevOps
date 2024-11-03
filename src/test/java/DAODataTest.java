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
        daoData.deleteAll();
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

    @Test
    public void testSearch() {
        // Arrange
        TambahData data1 = new TambahData();
        data1.setNim("123456");
        data1.setNama("John Doe");
        data1.setJenisKelamin("Laki-laki");
        data1.setKelas("A");
        daoData.insert(data1);
    
        TambahData data2 = new TambahData();
        data2.setNim("654321");
        data2.setNama("Jane Smith");
        data2.setJenisKelamin("Perempuan");
        data2.setKelas("B");
        daoData.insert(data2);
    
        // Act: Search by NIM
        List<TambahData> resultsByNIM = daoData.search("123456");
        
        // Assert: Verify the search results contain John Doe
        assertEquals(1, resultsByNIM.size());
        assertEquals("John Doe", resultsByNIM.get(0).getNama());
    
        // Act: Search by name
        List<TambahData> resultsByName = daoData.search("Jane");
        
        // Assert: Verify the search results contain Jane Smith
        assertEquals(1, resultsByName.size());
        assertEquals("Jane Smith", resultsByName.get(0).getNama());
    
        // Act: Search with a keyword that doesn't match any data
        List<TambahData> resultsEmpty = daoData.search("Unknown");
        
        // Assert: Verify the search results are empty
        assertTrue(resultsEmpty.isEmpty(), "Search should return no results for 'Unknown'");
    }

}
