/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.otpremnica;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Otpremnica;
import domain.Roba;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author milos
 */
public class SOFilterOtpremnica extends AbstractSO{
    private ArrayList<Otpremnica> lista;
    private String kriterijum;

    public SOFilterOtpremnica(Object argument) {
        kriterijum = (String) argument;
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Otpremnica)) {
            throw new Exception("Nevalidan objekat");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {

        ArrayList<AbstractDomainObject> otpremnice = DBBroker.getInstance().selectWhere(ado, kriterijum);
        lista = (ArrayList<Otpremnica>) (ArrayList<?>) otpremnice;  

    }

    public ArrayList<Otpremnica> getLista() {
        return lista;
    }
}
