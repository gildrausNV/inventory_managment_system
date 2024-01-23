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
import domain.StavkeOtpremnice;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractSO;

/**
 *
 * @author milos
 */
public class SOGetAllOtpremnica extends AbstractSO{
    private ArrayList<Otpremnica> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Otpremnica)) {
            throw new Exception("Nevalidan objekat");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {

        ArrayList<AbstractDomainObject> otpremnice = DBBroker.getInstance().select(ado);
        for (AbstractDomainObject o : otpremnice) {
            try {
                Otpremnica otpremnica = (Otpremnica) o;

                ArrayList<AbstractDomainObject> stavkeAdo = DBBroker.getInstance().selectWhere(new StavkeOtpremnice(), String.valueOf((Object) otpremnica.getOtpremnicaID()));
                ArrayList<StavkeOtpremnice> stavkeBaza = new ArrayList<>();
                for (AbstractDomainObject abstractDomainObject : stavkeAdo) {
                    if (abstractDomainObject instanceof StavkeOtpremnice) {
                        stavkeBaza.add((StavkeOtpremnice) abstractDomainObject);
                    }
                }
                
                otpremnica.setStavke(stavkeBaza);
                o = otpremnica;
            } catch (Exception ex) {
                Logger.getLogger(SOGetAllOtpremnica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        lista = (ArrayList<Otpremnica>) (ArrayList<?>) otpremnice;  
    }

    public ArrayList<Otpremnica> getLista() {
        return lista;
    }
}
