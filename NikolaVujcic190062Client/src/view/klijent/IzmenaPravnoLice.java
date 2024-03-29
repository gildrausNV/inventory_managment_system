/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.klijent;

import controller.ControllerClient;
import domain.Klijent;
import domain.PravnoLice;
import javax.swing.JOptionPane;

/**
 *
 * @author milos
 */
public class IzmenaPravnoLice extends javax.swing.JDialog {
    PravnoLice pravnoLice;
    /**
     * Creates new form IzmenaPravnoLice1
     */
    public IzmenaPravnoLice(java.awt.Frame parent, boolean modal, Object klijent) {
        super(parent, modal);
        initComponents();
        PravnoLice p = (PravnoLice) klijent;
        pravnoLice = p;
        txtNaziv.setText(p.getNaziv());
        txtPIB.setText(p.getPIB()+"");
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
        txtPIB = new javax.swing.JTextField();
        txtNaziv = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("PIB:");

        jLabel2.setText("Naziv:");

        jButton1.setText("Sacuvaj izmene");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNaziv, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(txtPIB)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtPIB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if(txtPIB.getText().equals("") || txtNaziv.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Popunite sva polja");
            }
            else if(!provera()){
                JOptionPane.showMessageDialog(this, "Greska pri unosu podataka");
            }
            else{
                PravnoLice pl = new PravnoLice(Integer.parseInt(txtPIB.getText()), txtNaziv.getText(), pravnoLice.getKlijent());
                ControllerClient.getInstance().updatePravnoLice(pl);
                JOptionPane.showMessageDialog(this, "Izmene su sacuvane");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Greska pri cuvanju izmena");
            //Logger.getLogger(IzmenaPravnoLice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtNaziv;
    private javax.swing.JTextField txtPIB;
    // End of variables declaration//GEN-END:variables

public boolean provera(){
    char[] PIB = txtPIB.getText().toCharArray();
    char[] naziv = txtNaziv.getText().toCharArray();
    for (char c : PIB) {
        if(Character.isLetter(c)) return false;
    }
    for (char c : naziv) {
        if(!Character.isLetter(c)) return false;
    }
    return true;
}
}
