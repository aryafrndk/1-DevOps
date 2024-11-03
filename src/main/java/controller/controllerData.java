package controller;

import DAO.DAOData;
import DAOInterface.IDAOData;
import java.util.List;
import javax.swing.JOptionPane;
import model.TabelModelData;
import model.TambahData;
import view.formcrud;

/**
 * Controller untuk mengelola data mahasiswa
 */
public class controllerData {
    private formcrud fc;
    private IDAOData iData;
    private List<TambahData> lstMhs;

    public controllerData(formcrud fc) {
        this.fc = fc;
        iData = new DAOData();        
    }
    
    // Method untuk mengisi tabel dengan data mahasiswa
    public void isiTable() {
        lstMhs = iData.getAll();
        TabelModelData tabelMhs = new TabelModelData(lstMhs);
        fc.getTabelData().setModel(tabelMhs); 
    }
    
    // Method untuk menambahkan data mahasiswa
    public void insert() {
        TambahData b = new TambahData();
        b.setNim(fc.gettxtNim().getText());
        b.setNama(fc.gettxtNama().getText());
        b.setJenisKelamin(fc.getjenisKelamin().getSelectedItem().toString());
        b.setKelas(fc.gettxtKelas().getText());
        b.setProdi(fc.gettxtProdi().getText());
        b.setFakultas(fc.gettxtFakultas().getText());
        b.setAngkatan(fc.gettxtAngkatan().getText());
        iData.insert(b);
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
    }
    
    // Method untuk mereset form input
    public void reset() {
        if (!fc.gettxtNim().isEnabled())
            fc.gettxtNim().setEnabled(true);
        fc.gettxtNim().setText("");
        fc.gettxtNama().setText("");
        fc.getjenisKelamin().setSelectedItem("Pilih Jenis Kelamin");
        fc.gettxtKelas().setText("");
        fc.gettxtProdi().setText("");
        fc.gettxtFakultas().setText("");
        fc.gettxtAngkatan().setText("");
    }
    
    // Method untuk mengisi field dengan data yang dipilih dari tabel
    public void isiField(int row) {
        fc.gettxtNim().setEnabled(false);
        fc.gettxtNim().setText(lstMhs.get(row).getNim());
        fc.gettxtNama().setText(lstMhs.get(row).getNama());
        fc.getjenisKelamin().setSelectedItem(lstMhs.get(row).getJenisKelamin());
        fc.gettxtKelas().setText(lstMhs.get(row).getKelas());
        fc.gettxtProdi().setText(lstMhs.get(row).getProdi());
        fc.gettxtFakultas().setText(lstMhs.get(row).getFakultas());
        fc.gettxtAngkatan().setText(lstMhs.get(row).getAngkatan());
    }
    
    // Method untuk memperbarui data mahasiswa
    public void update() {
        TambahData b = new TambahData();
        b.setNim(fc.gettxtNim().getText());
        b.setNama(fc.gettxtNama().getText());
        b.setJenisKelamin(fc.getjenisKelamin().getSelectedItem().toString());
        b.setKelas(fc.gettxtKelas().getText());
        b.setProdi(fc.gettxtProdi().getText());
        b.setFakultas(fc.gettxtFakultas().getText());
        b.setAngkatan(fc.gettxtAngkatan().getText());
        iData.update(b);
        JOptionPane.showMessageDialog(null, "Berhasil Melakukan Update!");
    }
    
    // Method untuk menghapus data mahasiswa
    public void delete() {
        iData.delete(fc.gettxtNim().getText());
        JOptionPane.showMessageDialog(null, "Berhasil Menghapus Data!");
    }
    
    // Method untuk mencari data mahasiswa berdasarkan NIM atau nama
    public void cari(String keyword) {
        List<TambahData> searchResults = iData.search(keyword);
        TabelModelData tabelMhs = new TabelModelData(searchResults);
        fc.getTabelData().setModel(tabelMhs); 
    }
}
