/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import domain.FizickoLice;
import domain.Klijent;
import domain.PravnoLice;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milos
 */
public class ModelTabeleKlijenti extends AbstractTableModel{
    ArrayList<Object> lista;
    String[] kolone={"ID", "Tip klijenta", "JMBG/PIB", "Ime i prezime/Naziv"};

    public ModelTabeleKlijenti() {
        lista = new ArrayList<>();
    }

    public ModelTabeleKlijenti(ArrayList<Object> lista) {
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
        Object o=lista.get(rowIndex);
        if(o instanceof FizickoLice){
            FizickoLice f = (FizickoLice) o;
            switch(columnIndex){
            case 0:
                return f.getKlijent().getKlijentID();
            case 1:
                return "Fizicko lice";
            case 2:
                return f.getJMBG();
            case 3:
                return f.getIme()+ " " + f.getPrezime();
            default:
                return null;
            }
        }
        else{
            PravnoLice p = (PravnoLice) o;
            switch(columnIndex){
            case 0:
                return p.getKlijent().getKlijentID();
            case 1:
                return "Pravno lice";
            case 2:
                return p.getPIB();
            case 3:
                return p.getNaziv();
            default:
                return null;
            }
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void izbrisi(int selectedRow) {
        lista.remove(selectedRow);
       fireTableDataChanged();
    }

    public Object getKlijent(int selectedRow) {
        return lista.get(selectedRow);
    }

    public void setLista(ArrayList<Object> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }
    
    
}
