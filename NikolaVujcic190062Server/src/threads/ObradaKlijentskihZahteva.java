/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import communication.Request;
import communication.Response;
import controller.ControllerServer;
import domain.FizickoLice;
import domain.MenadzerLogistike;
import domain.PravnoLice;
import domain.Roba;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import operations.Operations;

/**
 *
 * @author milos
 */
public class ObradaKlijentskihZahteva extends Thread{
    private Socket socket;

    ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request r = (Request) in.readObject();
                Response res = new Response();
                
                switch (r.getOperation()) {
                    case Operations.KREIRAJ_PRAVNO_LICE:
                        res.setResult(ControllerServer.getInstance().savePravnoLice((PravnoLice) r.getArgument()));
                        break;
                    case Operations.KREIRAJ_FIZICKO_LICE:
                        res.setResult(ControllerServer.getInstance().saveFizickoLice((FizickoLice) r.getArgument()));
                        break;
                    case Operations.VRATI_MAGACINE:
                        res.setResult(ControllerServer.getInstance().getMagacin());
                        break;
                    case Operations.KREIRAJ_ROBU:
                        res.setResult(ControllerServer.getInstance().createRoba((Roba) r.getArgument()));
                        break;
                    case Operations.VRATI_ROBU:
                        res.setResult(ControllerServer.getInstance().getRoba());
                        break;
                    case Operations.VRATI_OTPREMNICE:
                        res.setResult(ControllerServer.getInstance().getOtpremnica());
                        break;
                    case Operations.VRATI_FIZICKA_LICA:
                        res.setResult(ControllerServer.getInstance().getFizickaLica());
                        break;
                    case Operations.VRATI_PRAVNA_LICA:
                        res.setResult(ControllerServer.getInstance().getPravnaLica());
                        break;
                    case Operations.KREIRAJ_OTPREMNICU:
                        res.setResult(ControllerServer.getInstance().createOtpremnica(r.getArgument()));
                        break;
                    case Operations.PRETRAZI_ROBU:
                        res.setResult(ControllerServer.getInstance().searchRoba(r.getArgument()));
                        break;
                    case Operations.PRETRAZI_OTPREMNICE:
                        res.setResult(ControllerServer.getInstance().searchOtpremnica(r.getArgument()));
                        break;
                    case Operations.PRETRAZI_FIZICKA_LICA:
                        res.setResult(ControllerServer.getInstance().searchFizickaLica(r.getArgument()));
                        break;
                    case Operations.PRETRAZI_PRAVNA_LICA:
                        res.setResult(ControllerServer.getInstance().searchPravnaLica(r.getArgument()));
                        break;
                    case Operations.IZMENI_PRAVNO_LICE:
                        res.setResult(ControllerServer.getInstance().updatePravnoLice(r.getArgument()));
                        break;
                    case Operations.IZMENI_FIZICKO_LICE:
                        res.setResult(ControllerServer.getInstance().updateFizickoLice(r.getArgument()));
                        break;
                    case Operations.OBRISI_KLIJENTA:
                        res.setResult(ControllerServer.getInstance().deleteKlijent(r.getArgument()));
                        break;
                    case Operations.LOGIN:
                        ArrayList<MenadzerLogistike> menadzeri = ControllerServer.getInstance().getMenadzer();
                        MenadzerLogistike m = (MenadzerLogistike) r.getArgument();
                        for (MenadzerLogistike ml : menadzeri) {
                            if(ml.getKorisnickoIme().equals(m.getKorisnickoIme()) &&
                                    ml.getSifra().equals(m.getSifra())){
                                res.setResult(ml);
                                ControllerServer.getInstance().addUser(ml);
                            }
                        }
                        break;
                    case Operations.LOGOUT:
                        res.setResult(ControllerServer.getInstance().odjava(r.getArgument()));
                        break;
                    case Operations.IZMENI_OTPREMNICU:
                        res.setResult(ControllerServer.getInstance().updateOtpremnica(r.getArgument()));
                        break;
                    default:
                        
                        break;
                }
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
