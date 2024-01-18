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
import domain.Magacin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractSO;

/**
 *
 * @author milos
 */
public class SOAddFizickoLice extends AbstractSO{
    private boolean uspesno;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof FizickoLice)) {
            uspesno = false;
            throw new Exception("Nevalidan objekat!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado){
        try {
            FizickoLice fl = (FizickoLice) ado;
            ResultSet rs = DBBroker.getInstance().insert((AbstractDomainObject) new Klijent());
            int id = 0;
            while(rs.next()) id = rs.getInt(1);
            fl.setKlijent(new Klijent(id));
            DBBroker.getInstance().insert(fl);
            uspesno = true;
        } catch (SQLException ex) {
            uspesno = false;
        }

    }

    public boolean isUspesno() {
        return uspesno;
    }
}
