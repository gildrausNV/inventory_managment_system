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
import domain.PravnoLice;
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author milos
 */
public class SODeleteKlijent extends AbstractSO{
    boolean uspesno;
    
     @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof FizickoLice) && (ado instanceof FizickoLice)){
            throw new Exception("Nevalidan objekat!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        Klijent k;
        if(ado instanceof FizickoLice){
            FizickoLice fl = (FizickoLice) ado;
            k = fl.getKlijent();
        }
        else {
            PravnoLice pl = (PravnoLice) ado;
            k = pl.getKlijent();
        }
        boolean odgovorAdo = DBBroker.getInstance().delete(ado);
        boolean odgovor = DBBroker.getInstance().delete((AbstractDomainObject) k);
        
        uspesno = odgovor && odgovorAdo;
    }

    public boolean isUspesno() {
        return uspesno;
    }
}
