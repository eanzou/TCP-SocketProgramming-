/*
 * Teoh Ean Zou
 * SWE1804433
 * Tan Tian Lim
 * SWE1804420
 */

package assignment_client;

import static assignment_client.start.bar_title;
import static assignment_client.start.user;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class ParcelPage extends javax.swing.JFrame {

    static Socket socket = null;
    static ObjectOutputStream oos = null;
    static ObjectInputStream ois = null;
    int pass_choose, takeonce; 
    String page = "Parcel";
    start start = new start(); 
    
    public ParcelPage() {
        initComponents();
    }
    
    public ParcelPage(Socket socket, ObjectOutputStream oos, ObjectInputStream ois, int pass_choose, int takeonce) {
        initComponents();
        this.setTitle(bar_title + ": Client (" + user + ")");
        
        this.socket = socket;
        this.oos = oos;
        this.ois = ois; 
        this.takeonce = takeonce;
        this.pass_choose = pass_choose;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        back = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pickup = new javax.swing.JComboBox<>();
        dropoff = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        vehicle = new javax.swing.JComboBox<>();
        next = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 400));
        setResizable(false);

        back.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe Print", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Zoom Parcel Delivery");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Pickup Location: ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Dropoff Location: ");

        pickup.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pickup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Xiamen University Malaysia", "The Olive", "Sunway University", "Taylor University", " " }));
        pickup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickupActionPerformed(evt);
            }
        });

        dropoff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dropoff.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Xiamen University Malaysia", "The Olive", "Sunway University", "Taylor University" }));
        dropoff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropoffActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Vehicle Type: ");

        vehicle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        vehicle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Motor Cycle", "Car", "Pickup Truck", "Van", "1-Ton Lorry", "3-Ton Lorry" }));
        vehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehicleActionPerformed(evt);
            }
        });

        next.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        next.setText("Next");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 83, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dropoff, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pickup, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pickup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dropoff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(next)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //back button
    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        try {
            // TODO add your handling code here:
            oos.writeObject("Back");
            
            page = "Category";
            oos.writeObject(page); //pass page number
            
            CategoryPage category_page = new CategoryPage(socket, oos, ois, takeonce);
            category_page.setLocation(this.getX(), this.getY());
            category_page.setVisible(true);
            this.setVisible(false);
        } catch (IOException ex) {start.fail_to_connect();}
        
    }//GEN-LAST:event_backActionPerformed

    String[] selected_item = new String[3];
    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        // TODO add your handling code here:
        try 
        {
            oos.writeObject("next");
            //oos = new ObjectOutputStream(socket.getOutputStream());
            
            selected_item[0] = (String) pickup.getSelectedItem(); //record
            selected_item[1] = (String) dropoff.getSelectedItem();
            selected_item[2] = (String) vehicle.getSelectedItem();
            
            for (int i=0; i<3; i++)
                oos.writeObject(selected_item[i]); //pass all items selected
        
            check_duplication();
        } catch (IOException | ClassNotFoundException ex) {start.fail_to_connect();}
    }//GEN-LAST:event_nextActionPerformed
    
    
    private void check_duplication() throws IOException, ClassNotFoundException
     {
        int error;
        if (takeonce==0)
        {
            this.ois = new ObjectInputStream(socket.getInputStream());
            takeonce=1;
        }
        error = (int) ois.readObject();
        
        if (error == 1)
        {
            JOptionPane.showMessageDialog(null, "Pickup location and Dropoff location are the same, please change",
                                            "Warning",JOptionPane.WARNING_MESSAGE);
            page = "Parcel";
            oos.writeObject(page);
        }
        else
        {
            page = "Summary";
            oos.writeObject(page); //pass page number
            SummaryPage summary_page = new SummaryPage(socket, oos, ois, pass_choose, selected_item, takeonce);
            summary_page.setLocation(this.getX(), this.getY());
            summary_page.setVisible(true); 
            this.setVisible(false);
        }
     }
    private void dropoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropoffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dropoffActionPerformed

    private void vehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehicleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vehicleActionPerformed

    private void pickupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pickupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pickupActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ParcelPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParcelPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParcelPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParcelPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 new ParcelPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JComboBox<String> dropoff;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton next;
    private javax.swing.JComboBox<String> pickup;
    private javax.swing.JComboBox<String> vehicle;
    // End of variables declaration//GEN-END:variables
}