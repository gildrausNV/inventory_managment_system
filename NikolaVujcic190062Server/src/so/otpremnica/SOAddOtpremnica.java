/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.otpremnica;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Otpremnica;
import domain.StavkeOtpremnice;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractSO;

/**
 *
 * @author milos
 */
public class SOAddOtpremnica extends AbstractSO{
    private boolean uspesno;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Otpremnica)) {
            uspesno = false;
            throw new Exception("Nevalidan objekat!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado){
        try {
            Otpremnica o = (Otpremnica) ado;
            ResultSet rs = DBBroker.getInstance().insert(o);
            int id = 0;
            while(rs.next()) id = rs.getInt(1);
            o.setOtpremnicaID(id);
            for (StavkeOtpremnice so : o.getStavke()) {
                so.setOtpremnica(o);
                DBBroker.getInstance().insert(so);
            }
            uspesno = true;
        } catch (SQLException ex) {
            uspesno = false;
        }

    }

    public boolean isUspesno() {
        return uspesno;
    }
}
