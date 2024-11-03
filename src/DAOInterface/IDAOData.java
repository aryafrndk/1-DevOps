/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOInterface;

import java.util.List;
import model.TambahData;

/**
 *
 * @author ASUS
 */
public interface IDAOData {
    public List<TambahData> getAll();
    public void insert(TambahData b);
    public void update(TambahData b);
    public void delete(String nim);
}
