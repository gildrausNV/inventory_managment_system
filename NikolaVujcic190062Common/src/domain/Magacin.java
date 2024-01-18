/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class Magacin extends AbstractDomainObject{
    private int magacinID;
    private String naziv;

    public Magacin() {
    }

    public Magacin(int magacinID, String naziv) {
        this.magacinID = magacinID;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getMagacinID() {
        return magacinID;
    }

    public void setMagacinID(int magacinID) {
        this.magacinID = magacinID;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    @Override
    public String nazivTabele() {
        return "magacin";
    }

    @Override
    public String alijas() {
        return "m";
    }

    @Override
    public String spajanje() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> selectLista(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Magacin m = new Magacin(rs.getInt("magacinID"), rs.getString("naziv"));
            lista.add(m);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(magacinID, naziv)";
    }

    @Override
    public String primarniKljuc() {
        return "magacinID= "+magacinID;
    }

    @Override
    public String vrednostiZaInsert() {
        return magacinID+",'"+naziv + "','";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "naziv= '" + naziv + "'";
    }

    @Override
    public String id() {
        return "magacinID= "+magacinID;
    }

    @Override
    public String kolonaZaPretragu() {
        return "naziv";
    }
    
     @Override
    public int getID() {
        return magacinID;
    }

    @Override
    public int setID(ResultSet rs) {
        try {
            magacinID = rs.getInt("magacinID");
        } catch (SQLException ex) {
            Logger.getLogger(Magacin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return magacinID;
    }
}
