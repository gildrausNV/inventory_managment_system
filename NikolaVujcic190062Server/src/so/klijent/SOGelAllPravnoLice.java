/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.klijent;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.FizickoLice;
import domain.PravnoLice;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author milos
 */
public class SOGelAllPravnoLice extends AbstractSO{
    private ArrayList<PravnoLice> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PravnoLice)) {
            throw new Exception("Nevalidan objekat");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {

        ArrayList<AbstractDomainObject> pravnaLica = DBBroker.getInstance().select(ado);
        lista = (ArrayList<PravnoLice>) (ArrayList<?>) pravnaLica;  

    }

    public ArrayList<PravnoLice> getLista() {
        return lista;
    }
}
