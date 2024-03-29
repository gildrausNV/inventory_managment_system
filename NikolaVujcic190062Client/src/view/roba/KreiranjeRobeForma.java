/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.roba;

import communication.Request;
import controller.ControllerClient;
import domain.Magacin;
import domain.Roba;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import operations.Operations;

/**
 *
 * @author milos
 */
public class KreiranjeRobeForma extends javax.swing.JDialog {

    /**
     * Creates new form KreiranjeRobeForma
     */
    public KreiranjeRobeForma(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        srediFormu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbMagacin = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtNaziv = new javax.swing.JTextField();
        btnKreirajRobu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Magacin:");

        cmbMagacin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Naziv:");

        btnKreirajRobu.setText("Kreiraj robu");
        btnKreirajRobu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKreirajRobuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbMagacin, 0, 220, Short.MAX_VALUE)
                            .addComponent(txtNaziv)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(btnKreirajRobu, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbMagacin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(btnKreirajRobu)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKreirajRobuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKreirajRobuActionPerformed
        try {
            if(txtNaziv.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Unesite naziv");
            }
            else{
                Roba roba = new Roba();
                roba.setNaziv(txtNaziv.getText());
                roba.setMagacin((Magacin) cmbMagacin.getSelectedItem());
                ControllerClient.getInstance().createRoba(roba);
                JOptionPane.showMessageDialog(this, "Roba je sacuvana");
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Greska");
            //Logger.getLogger(KreiranjeRobeForma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnKreirajRobuActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKreirajRobu;
    private javax.swing.JComboBox cmbMagacin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtNaziv;
    // End of variables declaration//GEN-END:variables

    private void srediFormu() {
        try {
            cmbMagacin.removeAllItems();
            ArrayList<Magacin> listaMagacina = ControllerClient.getInstance().getMagacin();
            System.out.println(listaMagacina.size());
            for (Magacin magacin : listaMagacina) {
                cmbMagacin.addItem(magacin);
            }
            JOptionPane.showMessageDialog(this, "Ucitana je lista magacina");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Greska pri ucitavanju magacina");
            //Logger.getLogger(KreiranjeRobeForma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
