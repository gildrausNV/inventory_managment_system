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
public class Roba extends AbstractDomainObject{
    private int robaID;
    private String naziv;
    private Magacin magacin;

    public Roba() {
    }

    public Roba(int robaID, String naziv, Magacin magacin) {
        this.robaID = robaID;
        this.naziv = naziv;
        this.magacin = magacin;
    }

    public Magacin getMagacin() {
        return magacin;
    }

    public void setMagacin(Magacin magacin) {
        this.magacin = magacin;
    }

    public int getRobaID() {
        return robaID;
    }

    public void setRobaID(int robaID) {
        this.robaID = robaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    @Override
    public String nazivTabele() {
        return "roba";
    }

    @Override
    public String alijas() {
        return "r";
    }

    @Override
    public String spajanje() {
        return "JOIN magacin m on(m.magacinID=r.magacinID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> selectLista(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Magacin m = new Magacin(rs.getInt("m.magacinID"), rs.getString("m.naziv"));
            Roba r = new Roba(rs.getInt("r.robaID"), rs.getString("r.naziv"), m);
            lista.add(r);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(naziv, magacinID)";
    }

    @Override
    public String primarniKljuc() {
        return "robaID= "+robaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'"+naziv+"' , "+magacin.getMagacinID()+ "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "naziv= '" + naziv + "', magacinID="+magacin.getMagacinID();
    }
    @Override
    public String kolonaZaPretragu(){
        return "naziv";
    }

    @Override
    public String id() {
        return "robaID= "+robaID;
    }

    @Override
    public int getID() {
        return robaID;
    }

    @Override
    public int setID(ResultSet rs) {
        try {
            robaID = rs.getInt("robaID");
        } catch (SQLException ex) {
            Logger.getLogger(Roba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return robaID;
    }
    

    
    
}
