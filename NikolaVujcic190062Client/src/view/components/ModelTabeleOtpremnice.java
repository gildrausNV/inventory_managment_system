/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import domain.Otpremnica;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milos
 */
public class ModelTabeleOtpremnice extends AbstractTableModel{
    ArrayList<Otpremnica> lista;
    String[] kolone={"ID", "Odrediste", "Klijent"};

    public ModelTabeleOtpremnice() {
        lista = new ArrayList<>();
    }

    public ModelTabeleOtpremnice(ArrayList<Otpremnica> lista) {
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
        Otpremnica o=lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return o.getOtpremnicaID();
            case 1:
                return o.getOdrediste();
            case 2:
                return o.getKlijent().getKlijentID();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public Otpremnica getOtpremnica(int selectedRow) {
        return lista.get(selectedRow);
    }

    public void setLista(ArrayList<Otpremnica> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public ArrayList<Otpremnica> getLista() {
        return lista;
    }

    
    
    
    
}
