/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.roba;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Magacin;
import domain.Roba;
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author milos
 */
public class SOUpdateRoba extends AbstractSO{
    boolean uspesno;
    
     @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Roba)){
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
