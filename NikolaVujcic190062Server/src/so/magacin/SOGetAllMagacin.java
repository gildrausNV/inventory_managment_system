/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.magacin;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Magacin;
import domain.Otpremnica;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author milos
 */
public class SOGetAllMagacin extends AbstractSO{
    private ArrayList<Magacin> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Magacin)) {
            throw new Exception("Nevalidan objekat");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {

        ArrayList<AbstractDomainObject> magacini = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Magacin>) (ArrayList<?>) magacini;  
        for (Magacin m : lista) {
            System.out.println(m.getNaziv());
        }
    }

    public ArrayList<Magacin> getLista() {
        return lista;
    }
}
