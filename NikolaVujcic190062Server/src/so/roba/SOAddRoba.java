/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.roba;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Roba;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractSO;

/**
 *
 * @author milos
 */
public class SOAddRoba extends AbstractSO{
    private boolean uspesno;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Roba)) {
            uspesno = false;
            throw new Exception("Nevalidan objekat!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado){

        try {
            DBBroker.getInstance().insert(ado);
            uspesno = true;
        } catch (SQLException ex) {
            uspesno = false;
        }

    }

    public boolean isUspesno() {
        return uspesno;
    }
}
