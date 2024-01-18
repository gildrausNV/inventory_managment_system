/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.AbstractDomainObject;
import domain.FizickoLice;
import domain.Magacin;
import domain.MenadzerLogistike;
import domain.Otpremnica;
import domain.PravnoLice;
import domain.Roba;
import java.util.ArrayList;
import so.klijent.SOAddFizickoLice;
import so.klijent.SOAddPravnoLice;
import so.klijent.SODeleteKlijent;
import so.klijent.SOFilterFizickoLice;
import so.klijent.SOFilterPravnoLice;
import so.klijent.SOGelAllPravnoLice;
import so.klijent.SOGetAllFizickoLice;
import so.klijent.SOUpdateFizickoLice;
import so.klijent.SOUpdatePravnoLice;
import so.magacin.SOGetAllMagacin;
import so.menadzer.SOGetAllMenadzer;
import so.otpremnica.SOAddOtpremnica;
import so.otpremnica.SOFilterOtpremnica;
import so.otpremnica.SOGetAllOtpremnica;
import so.otpremnica.SOUpdateOtpremnica;
import so.roba.SOAddRoba;
import so.roba.SOFilterRoba;
import so.roba.SOGetAllRoba;
import view.ServerForm;

/**
 *
 * @author milos
 */
public class ControllerServer {
    private static ControllerServer instance;
    private ArrayList<MenadzerLogistike> lista;
    private ServerForm sf;

    public ControllerServer() {
        lista = new ArrayList<>();
    }
    
    public static ControllerServer getInstance(){
        if(instance == null) instance = new ControllerServer();
        return instance;
    }

    public boolean savePravnoLice(PravnoLice p) throws Exception {
        System.out.println(p.getNaziv());
        SOAddPravnoLice so = new SOAddPravnoLice();
        so.templateExecute(p);
        return so.isUspesno();
    }

    public ArrayList<Magacin> getMagacin() throws Exception {
        SOGetAllMagacin so = new SOGetAllMagacin();
        so.templateExecute(new Magacin());
        return so.getLista();
    }

    public boolean createRoba(Roba r) throws Exception {
        SOAddRoba so = new SOAddRoba();
        so.templateExecute(r);
        return so.isUspesno();
    }

    public ArrayList<Roba> getRoba() throws Exception {
        SOGetAllRoba so = new SOGetAllRoba();
        so.templateExecute(new Roba());
        return so.getLista();
    }

    public ArrayList<Otpremnica> getOtpremnica() throws Exception {
        SOGetAllOtpremnica so = new SOGetAllOtpremnica();
        so.templateExecute(new Otpremnica());
        return so.getLista();
    }


    public ArrayList<FizickoLice> getFizickaLica() throws Exception {
        SOGetAllFizickoLice so = new SOGetAllFizickoLice();
        so.templateExecute(new FizickoLice());
        return so.getLista();
    }

    public ArrayList<PravnoLice> getPravnaLica() throws Exception {
        SOGelAllPravnoLice so = new SOGelAllPravnoLice();
        so.templateExecute(new PravnoLice());
        return so.getLista();
    }

    public boolean createOtpremnica(Object argument) throws Exception {
        SOAddOtpremnica so = new SOAddOtpremnica();
        so.templateExecute((AbstractDomainObject) argument);
        return so.isUspesno();
    }

    public Object searchRoba(Object argument) throws Exception {
        SOFilterRoba so = new SOFilterRoba(argument);
        so.templateExecute(new Roba());
        return so.getLista();
    }

    public Object searchOtpremnica(Object argument) throws Exception {
        SOFilterOtpremnica so = new SOFilterOtpremnica(argument);
        so.templateExecute(new Otpremnica());
        return so.getLista();
    }

    public boolean updatePravnoLice(Object argument) throws Exception {
        SOUpdatePravnoLice so = new SOUpdatePravnoLice();
        so.templateExecute((AbstractDomainObject) argument);
        return so.isUspesno();
    }

    public boolean updateFizickoLice(Object argument) throws Exception {
        SOUpdateFizickoLice so = new SOUpdateFizickoLice();
        so.templateExecute((AbstractDomainObject) argument);
        return so.isUspesno();
    }

    public boolean deleteKlijent(Object argument) throws Exception {
        SODeleteKlijent so = new SODeleteKlijent();
        so.templateExecute((AbstractDomainObject) argument);
        return so.isUspesno();
    }

    public Object searchPravnaLica(Object argument) throws Exception {
        SOFilterPravnoLice so = new SOFilterPravnoLice(argument);
        so.templateExecute(new PravnoLice());
        return so.getLista();
    }

    public Object searchFizickaLica(Object argument) throws Exception {
        SOFilterFizickoLice so = new SOFilterFizickoLice(argument);
        so.templateExecute(new FizickoLice());
        return so.getLista();
    }

    public boolean saveFizickoLice(FizickoLice f) throws Exception {
        System.out.println(f.getIme());
        SOAddFizickoLice so = new SOAddFizickoLice();
        so.templateExecute(f);
        return so.isUspesno();
    }

    public ArrayList<MenadzerLogistike> getMenadzer() throws Exception {
        SOGetAllMenadzer so = new SOGetAllMenadzer();
        so.templateExecute(new MenadzerLogistike());
        return so.getLista();
    }

    public boolean addUser(MenadzerLogistike ml) {
        lista.add(ml);
        sf.refresh();
        return true;
    }

    public ArrayList<MenadzerLogistike> getLista() {
        return lista;
    }

    public void setLista(ArrayList<MenadzerLogistike> lista) {
        this.lista = lista;
    }

    public boolean odjava(Object argument) {
        MenadzerLogistike ml = (MenadzerLogistike) argument;
        for (MenadzerLogistike m : lista) {
            if(m.getId()==ml.getId()) {
                lista.remove(m);
                sf.refresh();
                break;
            }
        }
        return true;
    }

    public boolean updateOtpremnica(Object argument) throws Exception {
        SOUpdateOtpremnica so = new SOUpdateOtpremnica();
        so.templateExecute((AbstractDomainObject) argument);
        return so.isUspesno();
    }

    public void setServerForm(ServerForm aThis) {
        sf = aThis;
    }
    
}
