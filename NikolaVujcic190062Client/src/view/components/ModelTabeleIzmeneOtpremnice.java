/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import domain.Otpremnica;
import domain.StavkeOtpremnice;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milos
 */
public class ModelTabeleIzmeneOtpremnice extends AbstractTableModel{
    ArrayList<StavkeOtpremnice> lista;
    String[] kolone={"RB", "Jedinica mere", "Kolicina", "Roba"};

    public ModelTabeleIzmeneOtpremnice() {
        lista = new ArrayList<>();
    }

    public ModelTabeleIzmeneOtpremnice(ArrayList<StavkeOtpremnice> lista) {
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
        StavkeOtpremnice so=lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return so.getRB();
            case 1:
                return so.getJedinicaMere();
            case 2:
                return so.getKolicina();
            case 3:
                so.getRoba().getNaziv();
            default:
                return null;
        }
    }

   
    
    

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    
    
    
}
