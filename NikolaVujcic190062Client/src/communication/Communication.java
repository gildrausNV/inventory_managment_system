/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import domain.MenadzerLogistike;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author milos
 */
public class Communication{
    private static Communication instance;
    private Socket socket;
    private MenadzerLogistike trenutnoUlogovani;

    public Communication() {
        try {
            socket = new Socket("localhost", 9000);  
        } catch (IOException ex) {                    
            ex.printStackTrace();
        }
    }

    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setTrenutnoUlogovani(MenadzerLogistike trenutnoUlogovani) {
        this.trenutnoUlogovani = trenutnoUlogovani;
    }

    public MenadzerLogistike getTrenutnoUlogovani() {
        return trenutnoUlogovani;
    }

    public void stop() throws IOException {
        trenutnoUlogovani = null;
        socket.close();
    }
}
