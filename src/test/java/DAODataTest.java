package DAO;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        // Arrange
        TambahData data = new TambahData();
        data.setNim("12345");
        data.setNama("John Doe");
        data.setJenisKelamin("Laki-laki");
        data.setKelas("Kelas A");
        
        // Act
        daoData.insert(data);
        List<TambahData> allData = daoData.getAll();
        
        // Assert
        assertNotNull(allData);
        assertFalse(allData.isEmpty());
        assertEquals(1, allData.size());
        assertEquals("John Doe", allData.get(0).getNama());
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
        // Arrange
        TambahData data = new TambahData();
        data.setNim("12345");
        data.setNama("John Doe");
        data.setJenisKelamin("Laki-laki");
        data.setKelas("Kelas A");
        daoData.insert(data);

        // Act
        daoData.delete("12345");
        List<TambahData> allData = daoData.getAll();

        // Assert
        assertTrue(allData.isEmpty());
    }
}
