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
import so.AbstractSO;

/**
 *
 * @author milos
 */
public class SOFilterRoba extends AbstractSO{
    private ArrayList<Roba> lista;
    private String kriterijum;

    public SOFilterRoba(Object argument) {
        kriterijum = (String) argument;
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Roba)) {
            throw new Exception("Nevalidan objekat");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {

        ArrayList<AbstractDomainObject> roba = DBBroker.getInstance().selectWhere(ado, kriterijum);
        lista = (ArrayList<Roba>) (ArrayList<?>) roba;  

    }

    public ArrayList<Roba> getLista() {
        return lista;
    }
}
