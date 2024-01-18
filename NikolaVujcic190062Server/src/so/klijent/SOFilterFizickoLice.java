/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.klijent;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.FizickoLice;
import domain.Klijent;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author milos
 */
public class SOFilterFizickoLice extends AbstractSO{
    private ArrayList<FizickoLice> lista;
    private String kriterijum;

    public SOFilterFizickoLice(Object argument) {
        kriterijum = (String) argument;
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof FizickoLice)) {
            throw new Exception("Nevalidan objekat");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {

        ArrayList<AbstractDomainObject> klijenti = DBBroker.getInstance().selectWhere(ado, kriterijum);
        lista = (ArrayList<FizickoLice>) (ArrayList<?>) klijenti;  

    }

    public ArrayList<FizickoLice> getLista() {
        return lista;
    }
}
