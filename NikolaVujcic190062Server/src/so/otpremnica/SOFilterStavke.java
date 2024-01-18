/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.otpremnica;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Otpremnica;
import domain.StavkeOtpremnice;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author milos
 */
public class SOFilterStavke extends AbstractSO{
    private ArrayList<StavkeOtpremnice> lista;
    private String kriterijum;

    public SOFilterStavke(Object argument) {
        kriterijum = String.valueOf(argument);
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkeOtpremnice)) {
            throw new Exception("Nevalidan objekat");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {

        ArrayList<AbstractDomainObject> stavke = DBBroker.getInstance().selectWhere(ado, kriterijum);
        lista = (ArrayList<StavkeOtpremnice>) (ArrayList<?>) stavke;  

    }

    public ArrayList<StavkeOtpremnice> getLista() {
        return lista;
    }
}
