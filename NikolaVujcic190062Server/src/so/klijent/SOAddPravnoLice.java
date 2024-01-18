/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.klijent;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.FizickoLice;
import domain.Klijent;
import domain.PravnoLice;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractSO;

/**
 *
 * @author milos
 */
public class SOAddPravnoLice extends AbstractSO{
    private boolean uspesno;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PravnoLice)) {
            uspesno = false;
            throw new Exception("Nevalidan objekat!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado){
        try {
            PravnoLice pl = (PravnoLice) ado;
            
            ResultSet rs = DBBroker.getInstance().insert((AbstractDomainObject) new Klijent());
            int id = 0;
            while(rs.next()) id = rs.getInt(1);
            pl.setKlijent(new Klijent(id));
            DBBroker.getInstance().insert(pl);
            uspesno = true;
        } catch (SQLException ex) {
            uspesno = false;
        }

    }

    public boolean isUspesno() {
        return uspesno;
    }
}
