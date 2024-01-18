/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.klijent;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.FizickoLice;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author milos
 */
public class SOGetAllFizickoLice extends AbstractSO{
    private ArrayList<FizickoLice> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof FizickoLice)) {
            throw new Exception("Nevalidan objekat");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {

        ArrayList<AbstractDomainObject> fizickaLica = DBBroker.getInstance().select(ado);
        lista = (ArrayList<FizickoLice>) (ArrayList<?>) fizickaLica;  

    }

    public ArrayList<FizickoLice> getLista() {
        return lista;
    }
}
