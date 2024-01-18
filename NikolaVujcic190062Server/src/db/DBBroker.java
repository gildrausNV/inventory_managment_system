/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domain.AbstractDomainObject;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class DBBroker implements DBBrepository{
    private static DBBroker instance;
    private static Connection connection;

    public DBBroker() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src\\config\\db.properties"));
            String url = properties.getProperty("mysql_url");
            String username = properties.getProperty("mysql_user");
            String password = properties.getProperty("mysql_password");
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/logistika", "root", "");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);

        } catch (Exception ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DBBroker(Connection connection) {
        this.connection = connection;

    }

    public Connection getConnection() {
        return connection;
    }

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    @Override
    public ResultSet insert(AbstractDomainObject ado) throws SQLException {
        String upit = "INSERT INTO " + ado.nazivTabele() + " "
                + ado.koloneZaInsert() + " VALUES(" + ado.vrednostiZaInsert() + ")";
        PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        return rs;
    }
    
    @Override
    public ArrayList<AbstractDomainObject> select(AbstractDomainObject ado) throws SQLException {
        String upit = "SELECT * FROM " + ado.nazivTabele() + " " + ado.alijas() + " " + ado.spajanje();
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return ado.selectLista(rs);
    }
    
    @Override
    public boolean update(AbstractDomainObject ado){
        try {
            String upit = "UPDATE " + ado.nazivTabele() + " SET "
                    + ado.vrednostiZaUpdate() + " WHERE " + ado.id();
            Statement s = connection.createStatement();
            s.executeUpdate(upit);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    @Override
    public boolean delete(AbstractDomainObject ado){
        try {
            String upit = "DELETE FROM " + ado.nazivTabele() + " WHERE " + ado.id();
            Statement s = connection.createStatement();
            s.executeUpdate(upit);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public ArrayList<AbstractDomainObject> selectWhere(AbstractDomainObject ado, String kriterijum) throws SQLException {
        
        String upit = "SELECT * FROM "  + ado.nazivTabele() + " " + ado.alijas() + " " + ado.spajanje() + " WHERE " + ado.alijas() + "." + ado.kolonaZaPretragu() + "= '" + kriterijum + "'";
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return ado.selectLista(rs);
    }
    

    
}
