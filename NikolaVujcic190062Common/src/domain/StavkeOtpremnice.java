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
public class StavkeOtpremnice extends AbstractDomainObject{
    private int RB;
    private String jedinicaMere;
    private int kolicina;
    private Roba roba;
    private Otpremnica otpremnica;

    public StavkeOtpremnice() {
    }

    public StavkeOtpremnice(int RB, String jedinicaMere, int kolicina, Roba roba, Otpremnica otpremnica) {
        this.RB = RB;
        this.jedinicaMere = jedinicaMere;
        this.kolicina = kolicina;
        this.roba = roba;
        this.otpremnica = otpremnica;
    }

    public Otpremnica getOtpremnica() {
        return otpremnica;
    }

    public void setOtpremnica(Otpremnica otpremnica) {
        this.otpremnica = otpremnica;
    }

    public int getRB() {
        return RB;
    }

    public void setRB(int RB) {
        this.RB = RB;
    }

    public String getJedinicaMere() {
        return jedinicaMere;
    }

    public void setJedinicaMere(String jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Roba getRoba() {
        return roba;
    }

    public void setRoba(Roba roba) {
        this.roba = roba;
    }

    @Override
    public String nazivTabele() {
        return "stavkaotpremnice";
    }

    @Override
    public String alijas() {
        return "so";
    }

    @Override
    public String spajanje() {
        return "JOIN otpremnica o on(o.otpremnicaID=so.otpremnicaID) "
                + "JOIN roba r ON(r.robaiD=so.robaID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> selectLista(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Otpremnica o = new Otpremnica(rs.getInt("otpremnicaID"), rs.getString("odrediste"), null, null);
            Roba r = new Roba(rs.getInt("robaID"), rs.getString("naziv"), null);
            StavkeOtpremnice so = new StavkeOtpremnice(rs.getInt("RB"), rs.getString("jedinicaMere"), rs.getInt("kolicina"), r, o);
            lista.add(so);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(otpremnicaID, jedinicaMere, kolicina, robaID)";
    }

    @Override
    public String primarniKljuc() {
        return "RB= "+RB+", otpremnicaID= "+otpremnica.getOtpremnicaID();
    }

    @Override
    public String vrednostiZaInsert() {
        return otpremnica.getOtpremnicaID() + ", '"+jedinicaMere+"' , "+kolicina+ ", "+roba.getRobaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "jedinicaMere= '" + jedinicaMere + "', kolicina="+kolicina+", robaID= "+roba.getRobaID();
    }
    @Override
    public String kolonaZaPretragu(){
        return "otpremnicaID";
    }

    @Override
    public String id() {
        return "RB= "+RB;
    }

    @Override
    public int getID() {
        return otpremnica.getID();
    }

    @Override
    public int setID(ResultSet rs) {
        try {
            otpremnica = new Otpremnica(rs.getInt("otpremnicaID"), rs.getString("jedinicaMere"), null, new Klijent(rs.getInt("klijentID")));
        } catch (SQLException ex) {
            Logger.getLogger(StavkeOtpremnice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return otpremnica.getID();
    }

    
    
    
    
    
}
