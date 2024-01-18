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
public class Klijent extends AbstractDomainObject{
    private int klijentID;

    public Klijent() {
    }

    public Klijent(int klijentID) {
        this.klijentID = klijentID;
    }

    public int getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(int klijentID) {
        this.klijentID = klijentID;
    }

    @Override
    public String nazivTabele() {
        return "klijent";
    }

    @Override
    public String alijas() {
        return "kl";
    }

    @Override
    public String spajanje() {
        return "JOIN otpreminca o on(o.klijentID=kl.klijentID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> selectLista(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            //Otpremnica o = new Otpremnica(1, "", null, null);
            //Klijent k = new Klijent(rs.getInt("klijentID"), o);
            //lista.add(g);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String primarniKljuc() {
        return "klijentID= "+klijentID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "klijentID= " + klijentID + "";
    }

    @Override
    public String id() {
        return "klijentID= "+klijentID;
    }

    @Override
    public String kolonaZaPretragu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     @Override
    public int getID() {
        return klijentID;
    }

    @Override
    public int setID(ResultSet rs) {
        try {
            while(rs.next()) klijentID = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return klijentID;
    }
    
    
}
