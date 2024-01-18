/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domain.AbstractDomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author milos
 */
public interface DBBrepository {
    public ResultSet insert(AbstractDomainObject ado) throws SQLException;
    public ArrayList<AbstractDomainObject> select(AbstractDomainObject ado) throws SQLException;
    public boolean update(AbstractDomainObject ado) throws SQLException;
    public boolean delete(AbstractDomainObject ad) throws SQLException;
    public ArrayList<AbstractDomainObject> selectWhere(AbstractDomainObject ado, String kriterijum) throws SQLException;
}
