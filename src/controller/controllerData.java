/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.DAOData;
import DAOInterface.IDAOData;
import java.util.List;
import javax.swing.JOptionPane;
import model.TabelModelData;
import model.TambahData;
import view.formcrud;

/**
 *
 * @author ASUS
 */
public class controllerData {
    formcrud fc;
    IDAOData iData;
    List<TambahData> lstMhs;
    
    public controllerData(formcrud fc){
        this.fc = fc;
        iData = new DAOData();        
    }
    
    public void isiTable(){
        lstMhs = iData.getAll();
        TabelModelData tabelMhs = new TabelModelData(lstMhs);
        fc.getTabelData().setModel(tabelMhs); 
    }
    
    public void insert(){
        TambahData b = new TambahData();
        b.setNim(fc.gettxtNim().getText());
        b.setNama(fc.gettxtNama().getText());
        b.setJenisKelamin(fc.getjenisKelamin().getSelectedItem().toString());
        b.setKelas(fc.gettxtKelas().getText());
        iData.insert(b);
        
    }
    
    public void reset(){
        if(!fc.gettxtNim().isEnabled())
            fc.gettxtNim().setEnabled(true);
        fc.gettxtNim().setText("");
        fc.gettxtNama().setText("");
        fc.getjenisKelamin().setSelectedItem("Pilih Jenis Kelamin");
        fc.gettxtKelas().setText("");
    }
    
    public void isiField(int row){
        fc.gettxtNim().setEnabled(false);
        fc.gettxtNim().setText(lstMhs.get(row).getNim());
        fc.gettxtNama().setText(lstMhs.get(row).getNama());
        fc.getjenisKelamin().setSelectedItem(lstMhs.get(row).getJenisKelamin());
        fc.gettxtKelas().setText(lstMhs.get(row).getKelas());
    }
    
    public void update(){
        TambahData b = new TambahData();
        b.setNama(fc.gettxtNama().getText());
        b.setJenisKelamin(fc.getjenisKelamin().getSelectedItem().toString());
        b.setKelas(fc.gettxtKelas().getText());
        b.setNim(fc.gettxtNim().getText());
        iData.update(b);
        JOptionPane.showMessageDialog(null, "Berhasil Melakukan Update!");
    }
    
    public void delete(){
        TambahData b = new TambahData();
        iData.delete(fc.gettxtNim().getText());
        JOptionPane.showMessageDialog(null, "Berhasil Menghapus Data!");
    }
}
