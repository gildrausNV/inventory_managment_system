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
public class SOFilterPravnoLice extends AbstractSO{
    private ArrayList<PravnoLice> lista;
    private String kriterijum;

    public SOFilterPravnoLice(Object argument) {
        kriterijum = (String) argument;
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PravnoLice)) {
            throw new Exception("Nevalidan objekat");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {

        ArrayList<AbstractDomainObject> klijenti = DBBroker.getInstance().selectWhere(ado, kriterijum);
        lista = (ArrayList<PravnoLice>) (ArrayList<?>) klijenti;  

    }

    public ArrayList<PravnoLice> getLista() {
        return lista;
    }
}
