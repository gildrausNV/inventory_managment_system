/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import domain.MenadzerLogistike;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milos
 */
public class ModelTabeleKorisnici extends AbstractTableModel{
    ArrayList<MenadzerLogistike> lista;
    String[] kolone = {"ID", "Korisnicko ime"};

    public ModelTabeleKorisnici() {
        lista = new ArrayList<>();
    }

    public ModelTabeleKorisnici(ArrayList<MenadzerLogistike> lista) {
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
        MenadzerLogistike ml = lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return ml.getId();
            case 1:
                return ml.getKorisnickoIme();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public ArrayList<MenadzerLogistike> getLista() {
        return lista;
    }

    public void setLista(ArrayList<MenadzerLogistike> lista) {
        this.lista = lista;
    }
    
    public void dodajKorisnika(MenadzerLogistike ml){
        lista.add(ml);
        fireTableDataChanged();
    }
    
    public void izbaciKorisnika(MenadzerLogistike ml){
        lista.remove(ml);
        fireTableDataChanged();
    }
    
}
