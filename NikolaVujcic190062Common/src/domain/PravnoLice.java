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
public class PravnoLice extends AbstractDomainObject{
    private int PIB;
    private String naziv;
    private Klijent klijent;

    public PravnoLice() {
    }

    public PravnoLice(int PIB, String naziv, Klijent klijent) {
        this.PIB = PIB;
        this.naziv = naziv;
        this.klijent = klijent;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public int getPIB() {
        return PIB;
    }

    public void setPIB(int PIB) {
        this.PIB = PIB;
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
        return "pravnolice";
    }

    @Override
    public String alijas() {
        return "pl";
    }

    @Override
    public String spajanje() {
        return "JOIN klijent kl on(kl.klijentID=pl.klijentID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> selectLista(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Klijent k = new Klijent(rs.getInt("klijentID"));
            PravnoLice pl = new PravnoLice(rs.getInt("PIB"), rs.getString("naziv"), k);
            lista.add(pl);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(PIB, naziv, klijentID)";
    }

    @Override
    public String primarniKljuc() {
        return "klijentID= "+ klijent.getKlijentID();
    }

    @Override
    public String vrednostiZaInsert() {
        return PIB+", '"+naziv + "' , " + klijent.getKlijentID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "PIB= " + PIB + ", naziv= '" + naziv + "'";
    }

    @Override
    public String id() {
        return "klijentID= "+klijent.getKlijentID();
    }

    @Override
    public String kolonaZaPretragu() {
        return "naziv";
    }

    @Override
    public int getID() {
        return klijent.getKlijentID();
    }

    @Override
    public int setID(ResultSet rs) {
        try {
            while(rs.next()) {
                System.out.println(rs.getInt(1)+"OVDEEE");
                setKlijent(new Klijent(rs.getInt(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PravnoLice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getKlijent().getID();
    }
}
