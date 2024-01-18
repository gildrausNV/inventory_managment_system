/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.menadzer;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Magacin;
import domain.MenadzerLogistike;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author milos
 */
public class SOGetAllMenadzer extends AbstractSO{
    private ArrayList<MenadzerLogistike> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof MenadzerLogistike)) {
            throw new Exception("Nevalidan objekat");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {

        ArrayList<AbstractDomainObject> roba = DBBroker.getInstance().select(ado);
        lista = (ArrayList<MenadzerLogistike>) (ArrayList<?>) roba;  

    }

    public ArrayList<MenadzerLogistike> getLista() {
        return lista;
    }
}
