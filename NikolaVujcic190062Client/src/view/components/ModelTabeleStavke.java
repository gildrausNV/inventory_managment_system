/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import domain.StavkeOtpremnice;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milos
 */
public class ModelTabeleStavke extends AbstractTableModel{
    ArrayList<StavkeOtpremnice> lista;
    String[] kolone={"Jedinica mere", "Kolicina", "Roba"};

    public ModelTabeleStavke() {
        lista = new ArrayList<>();
    }

    public ModelTabeleStavke(ArrayList<StavkeOtpremnice> lista) {
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
                return so.getJedinicaMere();
            case 1:
                return so.getKolicina();
            case 2:
                return so.getRoba().getNaziv();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void dodaj(StavkeOtpremnice so) {
        lista.add(so);
        fireTableDataChanged();
    }

    public void izbrisi(int selectedRow){
        lista.remove(selectedRow);
        fireTableDataChanged();
    }

    public ArrayList<StavkeOtpremnice> getLista() {
        return lista;
    }
    
    
}
