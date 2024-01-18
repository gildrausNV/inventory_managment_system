/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import domain.Roba;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milos
 */
public class ModelTabeleRoba extends AbstractTableModel{
    ArrayList<Roba> lista;
    String[] kolone={"ID", "Magacin", "Naziv"};

    public ModelTabeleRoba() {
        lista = new ArrayList<>();
    }

    public ModelTabeleRoba(ArrayList<Roba> lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Roba r=lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return r.getRobaID();
            case 1:
                return r.getMagacin().getNaziv();
            case 2:
                return r.getNaziv();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void setLista(ArrayList<Roba> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }
    
    
    
}
