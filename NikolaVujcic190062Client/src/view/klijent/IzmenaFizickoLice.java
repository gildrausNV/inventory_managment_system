/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.klijent;

import controller.ControllerClient;
import domain.FizickoLice;
import javax.swing.JOptionPane;

/**
 *
 * @author milos
 */
public class IzmenaFizickoLice extends javax.swing.JDialog {
    FizickoLice fizickoLice;
    /**
     * Creates new form IzmenaFizickoLice1
     */
    public IzmenaFizickoLice(java.awt.Frame parent, boolean modal, Object klijent) {
        super(parent, modal);
        initComponents();
        FizickoLice f = (FizickoLice) klijent;
        txtIme.setText(f.getIme());
        txtPrezime.setText(f.getPrezime());
        txtJMBG.setText(f.getJMBG()+"");
        fizickoLice = (FizickoLice) klijent;
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
        txtJMBG = new javax.swing.JTextField();
        txtIme = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPrezime = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("JMBG:");

        jLabel2.setText("Ime:");

        jLabel3.setText("Prezime:");

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
                        .addGap(57, 57, 57)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJMBG, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtJMBG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if(txtJMBG.getText().equals("") || txtIme.getText().equals("") || txtPrezime.getText().equals("") || !provera()) {
                JOptionPane.showMessageDialog(this, "Popunite sva polja");
            }
            else if(!provera()){
                JOptionPane.showMessageDialog(this, "Greska pri unosu podataka");
            }
            else{
                FizickoLice fl = new FizickoLice(Integer.parseInt(txtJMBG.getText()), txtIme.getText(), txtPrezime.getText(), fizickoLice.getKlijent());
                ControllerClient.getInstance().updateFizickoLice(fl);
                JOptionPane.showMessageDialog(this, "Izmene su sacuvane");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Greska pri cuvanju izmena");
            //Logger.getLogger(IzmenaFizickoLice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtJMBG;
    private javax.swing.JTextField txtPrezime;
    // End of variables declaration//GEN-END:variables

public boolean provera(){
    char[] JMBG = txtJMBG.getText().toCharArray();
    char[] ime = txtIme.getText().toCharArray();
    char[] prezime = txtPrezime.getText().toCharArray();
    for (char c : JMBG) {
        if(Character.isLetter(c)) return false;
    }
    for (char c : ime) {
        if(!Character.isLetter(c)) return false;
    }
    for (char c : prezime) {
        if(!Character.isLetter(c)) return false;
    }
    return true;
}

}
