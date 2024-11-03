/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import javax.swing.JOptionPane;
import DAOInterface.IDAOData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.TambahData;
import koneksi.DBConnection;

/**
 *
 * @author ASUS
 */
public class DAOData implements IDAOData {
    
    private Connection con;

    public DAOData() {
        con = DBConnection.connectDB();
        if (con == null) {
            System.out.println("Failed to establish database connection.");
        }
    }

    @Override
    public List<TambahData> getAll() {
        List<TambahData> lstMhs = new ArrayList<>(); // Initialize to an empty list
        try (Statement st = con.createStatement(); ResultSet res = st.executeQuery(read)) {
            while (res.next()) {
                TambahData mhs = new TambahData();
                mhs.setNim(res.getString("nim"));
                mhs.setNama(res.getString("nama"));
                mhs.setJenisKelamin(res.getString("jenis_kelamin"));
                mhs.setKelas(res.getString("kelas"));
                lstMhs.add(mhs);
            }
        } catch (SQLException e) {  
            System.out.println("Error retrieving data: " + e.getMessage());
        }
        return lstMhs;
    }

    @Override
    public void insert(TambahData b) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = con.prepareStatement(checkQuery);
            statement.setString(1, b.getNim());
            resultSet = statement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.out.println("Data sudah ada di dalam database!");
                return;
            }
            statement = con.prepareStatement(insert);
            statement.setString(1, b.getNim());
            statement.setString(2, b.getNama());
            statement.setString(3, b.getJenisKelamin());
            statement.setString(4, b.getKelas());
            statement.execute();
            System.out.println("Data berhasil diinput!");
        } catch (SQLException e) {
            System.out.println("Gagal Input Data!");
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                System.out.println("Gagal Input Data!");
            }
        }
    }

    @Override
    public void update(TambahData b) {
        String checkExists = "SELECT COUNT(*) FROM tb_mahasiswa WHERE nim = ?";
        String update = "UPDATE tb_mahasiswa set nama=?,jenis_kelamin=?,kelas=? WHERE nim=?";
        
        try (PreparedStatement checkStmt = con.prepareStatement(checkExists)) {
            checkStmt.setString(1, b.getNim());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Data with NIM " + b.getNim() + " does not exist!");
                return;
            }

            try (PreparedStatement updateStmt = con.prepareStatement(update)) {
                updateStmt.setString(1, b.getNama());
                updateStmt.setString(2, b.getJenisKelamin());
                updateStmt.setString(3, b.getKelas());
                updateStmt.setString(4, b.getNim());
                updateStmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Gagal Update Data! " + e.getMessage());
        }
    }

    @Override
    public void delete(String nim) {
        try (PreparedStatement statement = con.prepareStatement(delete)) {
            statement.setString(1, nim);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal Hapus Data! " + e.getMessage());
        }
    }

    public List<TambahData> search(String keyword) {
        List<TambahData> lstMhs = new ArrayList<>();
        String sql = "SELECT * FROM tb_mahasiswa WHERE nim LIKE ? OR nama LIKE ?";
        
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                TambahData mhs = new TambahData();
                mhs.setNim(res.getString("nim"));
                mhs.setNama(res.getString("nama"));
                mhs.setJenisKelamin(res.getString("jenis_kelamin"));
                mhs.setKelas(res.getString("kelas"));
                lstMhs.add(mhs);
            }
        } catch (SQLException e) {
            System.out.println("Error while searching data: " + e.getMessage());
        }
        
        return lstMhs;
    }

    // SQL Query
    String read = "SELECT * FROM tb_mahasiswa";
    String delete = "DELETE FROM tb_mahasiswa WHERE nim=?";
}
