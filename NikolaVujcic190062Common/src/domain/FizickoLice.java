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
public class FizickoLice extends AbstractDomainObject{
    private int JMBG;
    private String ime;
    private String prezime;
    private Klijent klijent;

    public FizickoLice() {
    }

    public FizickoLice(int JMBG, String ime, String prezime, Klijent klijent) {
        this.JMBG = JMBG;
        this.ime = ime;
        this.prezime = prezime;
        this.klijent = klijent;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public int getJMBG() {
        return JMBG;
    }

    public void setJMBG(int JMBG) {
        this.JMBG = JMBG;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }

    @Override
    public String nazivTabele() {
        return "fizickolice";
    }

    @Override
    public String alijas() {
        return "fl";
    }

    @Override
    public String spajanje() {
        return "JOIN klijent kl on(kl.klijentID=fl.klijentID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> selectLista(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Klijent k = new Klijent(rs.getInt("klijentID"));
            FizickoLice fl = new FizickoLice(rs.getInt("JMBG"), rs.getString("ime"), rs.getString("prezime"), k);
            lista.add(fl);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(JMBG, ime, prezime, klijentID)";
    }

    @Override
    public String primarniKljuc() {
        return "JMBG= "+JMBG;
    }

    @Override
    public String vrednostiZaInsert() {
        return JMBG + " , '" + ime + "' , '" + prezime + "' , " + klijent.getKlijentID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "JMBG= " + JMBG + ", ime= '" + ime + "' , prezime= '" + prezime + "'";
    }

    @Override
    public String id() {
        return "klijentID= "+klijent.getKlijentID();
    }

    @Override
    public String kolonaZaPretragu() {
        return "ime";
    }

    @Override
    public int getID() {
        return klijent.getKlijentID();
    }

    @Override
    public int setID(ResultSet rs) {
        try {
            while(rs.next()) setKlijent(new Klijent(rs.getInt(1)));
        } catch (SQLException ex) {
            Logger.getLogger(FizickoLice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return klijent.getKlijentID();
    }


    

    
    
    
}
