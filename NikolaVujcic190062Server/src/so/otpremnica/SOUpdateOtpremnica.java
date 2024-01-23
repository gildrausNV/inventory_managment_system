/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.otpremnica;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Magacin;
import domain.Otpremnica;
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
public class SOUpdateOtpremnica extends AbstractSO{
    boolean uspesno;
    
     @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Otpremnica)){
            throw new Exception("Nevalidan objekat!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        try {
            DBBroker.getInstance().update(ado);
            Otpremnica otpremnica = (Otpremnica) ado;
            ArrayList<StavkeOtpremnice> stavkeSistem = otpremnica.getStavke();

            ArrayList<AbstractDomainObject> stavkeAdo = DBBroker.getInstance().selectWhere(new StavkeOtpremnice(), String.valueOf((Object) otpremnica.getOtpremnicaID()));
            ArrayList<StavkeOtpremnice> stavkeBaza = new ArrayList<>();
            for (AbstractDomainObject abstractDomainObject : stavkeAdo) {
                if (abstractDomainObject instanceof StavkeOtpremnice) {
                    stavkeBaza.add((StavkeOtpremnice) abstractDomainObject);
                }
            }
            
            stavkeBaza.stream()
                .filter(sob -> !stavkeSistem.contains(sob))
                .forEach(sob -> DBBroker.getInstance().delete(sob));
            
            stavkeSistem.stream()
                .filter(sos -> !stavkeBaza.contains(sos))
                .forEach(sos -> {
                try {
                    DBBroker.getInstance().insert(sos);
                } catch (SQLException ex) {
                    Logger.getLogger(SOUpdateOtpremnica.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            uspesno = true;
        }
        catch (Exception ex) {
            uspesno = false;
        }
}

    public boolean isUspesno() {
        return uspesno;
    }
}
