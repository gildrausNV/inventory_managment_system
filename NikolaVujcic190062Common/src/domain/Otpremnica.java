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
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class Otpremnica extends AbstractDomainObject{
    private int otpremnicaID;
    private String odrediste;
    private ArrayList<StavkeOtpremnice> stavke;
    private Klijent klijent;

    public Otpremnica() {
    }

    public Otpremnica(int otpremnicaID, String odrediste, ArrayList<StavkeOtpremnice> stavke, Klijent klijent) {
        this.otpremnicaID = otpremnicaID;
        this.odrediste = odrediste;
        this.stavke = stavke;
        this.klijent = klijent;
    }

    public ArrayList<StavkeOtpremnice> getStavke() {
        return stavke;
    }

    public void setStavke(ArrayList<StavkeOtpremnice> stavke) {
        this.stavke = stavke;
    }

    public int getOtpremnicaID() {
        return otpremnicaID;
    }

    public void setOtpremnicaID(int otpremnicaID) {
        this.otpremnicaID = otpremnicaID;
    }

    public String getOdrediste() {
        return odrediste;
    }

    public void setOdrediste(String odrediste) {
        this.odrediste = odrediste;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    @Override
    public String toString() {
        return odrediste;
    }
    
    @Override
    public String nazivTabele() {
        return "otpremnica";
    }

    @Override
    public String alijas() {
        return "o";
    }

    @Override
    public String spajanje() {
        return "JOIN klijent kl on(o.klijentID=kl.klijentID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> selectLista(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            //Otpremnica o = new Otpremnica(1, "", null, null);
            Klijent k = new Klijent(rs.getInt("klijentID"));
            //lista.add(g);
            Otpremnica o = new Otpremnica(rs.getInt("otpremnicaID"), rs.getString("odrediste"), null, k);
            lista.add(o);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(odrediste, klijentID)";
    }

    @Override
    public String primarniKljuc() {
        return "otpremnicaID= "+otpremnicaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'"+odrediste+"' , "+klijent.getKlijentID() + "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "odrediste= '" + odrediste + "', klijentID="+klijent.getKlijentID();
    }

    @Override
    public String id() {
        return "otpremnicaID= "+otpremnicaID;
    }

    @Override
    public String kolonaZaPretragu() {
        return "odrediste";
    }

    @Override
    public int getID() {
        return otpremnicaID;
    }

    @Override
    public int setID(ResultSet rs) {
        try {
            otpremnicaID = rs.getInt("otpremnicaID");
        } catch (SQLException ex) {
            Logger.getLogger(Otpremnica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return otpremnicaID;
    }

    
}
