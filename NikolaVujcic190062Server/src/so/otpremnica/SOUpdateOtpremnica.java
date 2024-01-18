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
            ArrayList<StavkeOtpremnice> stavkeSistem = otpremnica.getStavke();//STAVKE OTPREMNICE KOJE SU TRENUTNO U SISTEMU
            
            SOFilterStavke so = new SOFilterStavke((Object)otpremnica.getOtpremnicaID());
            so.templateExecute(new StavkeOtpremnice());
            ArrayList<StavkeOtpremnice> stavkeBaza = so.getLista();//STAVKE OTPREMNICE KOJE SU U BAZI
            
            for (StavkeOtpremnice sob : stavkeBaza) {
                System.out.println(sob.getRB());
                if(!stavkeSistem.contains(sob)) DBBroker.getInstance().delete(sob);//BRISANJE STAVKI KOJE SU IZBRISANE SA FORME
            }
            for (StavkeOtpremnice sos : stavkeSistem) {
                if(!stavkeBaza.contains(sos)) DBBroker.getInstance().insert(sos);//DODAVANJE STAVKI KOJE NE POSTOJE U BAZI
            }
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
