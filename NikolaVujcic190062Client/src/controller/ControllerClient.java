/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.Communication;
import communication.Request;
import communication.Response;
import domain.MenadzerLogistike;
import domain.FizickoLice;
import domain.Magacin;
import domain.Otpremnica;
import domain.PravnoLice;
import domain.Roba;
import domain.StavkeOtpremnice;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import operations.Operations;

/**
 *
 * @author milos
 */
public class ControllerClient {
    private static ControllerClient instance;

    public ControllerClient() {
    }

    public static ControllerClient getInstance() {
        if (instance == null) {
            instance = new ControllerClient();
        }
        return instance;
    }
    
     private Object sendRequest(int operation, Object data) throws Exception {
         
        Request req = new Request(operation, data);
        ObjectOutputStream out = new ObjectOutputStream(Communication.getInstance().getSocket().getOutputStream());
        out.writeObject(req);
        
        ObjectInputStream in = new ObjectInputStream(Communication.getInstance().getSocket().getInputStream());
        Response res = (Response) in.readObject();
        return res;
        
    }

    public ArrayList<PravnoLice> getPravnaLica(Request r) throws Exception {
        Response res = (Response) sendRequest(r.getOperation(), r.getArgument());
        return (ArrayList<PravnoLice>) res.getResult();
    }

    public ArrayList<FizickoLice> getFizickaLica(Request r) throws Exception {
        Response res = (Response) sendRequest(r.getOperation(), r.getArgument());
        return (ArrayList<FizickoLice>) res.getResult();
    }

    public ArrayList<Object> searchKlijenti(Request r) throws Exception {
        Response res = (Response) sendRequest(r.getOperation(), r.getArgument());
        return (ArrayList<Object>) res.getResult();
    }

    public boolean deleteKlijent(Request r) throws Exception {
        Response res = (Response) sendRequest(r.getOperation(), r.getArgument());
        return (boolean) res.getResult();
    }

    public boolean createKlijent(Request r) throws Exception {
        Response res = (Response) sendRequest(r.getOperation(), r.getArgument());
        return (boolean) res.getResult();
    }

    public boolean updatePravnoLice(PravnoLice pl) throws Exception{
        Response res = (Response) sendRequest(Operations.IZMENI_PRAVNO_LICE, pl);
        return (boolean) res.getResult();
    }

    public boolean updateFizickoLice(FizickoLice fl) throws Exception{
        Response res = (Response) sendRequest(Operations.IZMENI_FIZICKO_LICE, fl);
        return (boolean) res.getResult();
    }

    public ArrayList<Otpremnica> getOtpremnice(Request r) throws Exception{
        Response res = (Response) sendRequest(r.getOperation(), r.getArgument());
        return (ArrayList<Otpremnica>) res.getResult();
    }

    public ArrayList<StavkeOtpremnice> getStavkeOtpremnice(Request r) throws Exception{
        return (ArrayList<StavkeOtpremnice>) sendRequest(r.getOperation(), r.getArgument());
    }

    public boolean createOtpremnica(Otpremnica o) throws Exception {
        Response res = (Response) sendRequest(Operations.KREIRAJ_OTPREMNICU, o);
        return (boolean) res.getResult();
    }

    public ArrayList<Roba> getRoba(Request r) throws Exception {
        Response res = (Response) sendRequest(r.getOperation(), r.getArgument());
        return (ArrayList<Roba>) res.getResult();
    }

    public boolean createRoba(Roba r) throws Exception {
        Response res = (Response) sendRequest(Operations.KREIRAJ_ROBU, r);
        return (boolean) res.getResult();
    }

    public ArrayList<Magacin> getMagacin(Request r) throws Exception {
        Response res = (Response) sendRequest(r.getOperation(), r.getArgument());
        return (ArrayList<Magacin>) res.getResult();
    }

    public ArrayList<Roba> searchRoba(String kriterijum) throws Exception {
        Response res = (Response) sendRequest(Operations.PRETRAZI_ROBU, kriterijum);
        return (ArrayList<Roba>) res.getResult();
    }

    public MenadzerLogistike login(MenadzerLogistike m) throws Exception {
        Response res = (Response) sendRequest(Operations.LOGIN, m);
        return (MenadzerLogistike) res.getResult();
    }

    public boolean createPravnoLice(PravnoLice pl) throws Exception {
        Response res = (Response) sendRequest(Operations.KREIRAJ_PRAVNO_LICE, pl);
        return (boolean) res.getResult();
    }

    public boolean createFizickoLice(FizickoLice fl) throws Exception {
        Response res = (Response) sendRequest(Operations.KREIRAJ_FIZICKO_LICE, fl);
        return (boolean) res.getResult();
    }

    public ArrayList<Object> searchPravnaLica(String kriterijum) throws Exception {
        Response res = (Response) sendRequest(Operations.PRETRAZI_PRAVNA_LICA, kriterijum);
        return (ArrayList<Object>) res.getResult();
    }

    public ArrayList<Object> searchFizickaLica(String kriterijum) throws Exception {
        Response res = (Response) sendRequest(Operations.PRETRAZI_FIZICKA_LICA, kriterijum);
        return (ArrayList<Object>) res.getResult();
    }

    public ArrayList<FizickoLice> getFizickaLica() throws Exception {
        Response res = (Response) sendRequest(Operations.VRATI_FIZICKA_LICA, null);
        return (ArrayList<FizickoLice>) res.getResult();
    }

    public ArrayList<PravnoLice> getPravnaLica() throws Exception {
        Response res = (Response) sendRequest(Operations.VRATI_PRAVNA_LICA, null);
        return (ArrayList<PravnoLice>) res.getResult();
    }

    public ArrayList<Roba> getRoba() throws Exception {
        Response res = (Response) sendRequest(Operations.VRATI_ROBU, null);
        return (ArrayList<Roba>) res.getResult();
    }

    public ArrayList<Magacin> getMagacin() throws Exception {
        Response res = (Response) sendRequest(Operations.VRATI_MAGACINE, null);
        return (ArrayList<Magacin>) res.getResult();
    }

    public void odjaviKorisnika(MenadzerLogistike ml) throws Exception {
        Response res = (Response) sendRequest(Operations.LOGOUT, ml);
        Communication.getInstance().stop();
    }

    public boolean updateOtpremnica(Otpremnica otpremnica) throws Exception {
        Response res = (Response) sendRequest(Operations.IZMENI_OTPREMNICU, otpremnica);
        return (boolean) res.getResult();
    }
}
