/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.klijent;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.FizickoLice;
import domain.Magacin;
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author milos
 */
public class SOUpdateFizickoLice extends AbstractSO{
    boolean uspesno;
    
     @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof FizickoLice)){
            throw new Exception("Nevalidan objekat!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        boolean odgovor = DBBroker.getInstance().update(ado);
        uspesno = odgovor;
    }

    public boolean isUspesno() {
        return uspesno;
    }
}
