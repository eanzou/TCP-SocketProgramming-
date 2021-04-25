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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ShopItemPage extends javax.swing.JFrame {

     static Socket socket = null;
    static ObjectOutputStream oos = null;
    static ObjectInputStream ois = null;
    String[] selected_item;
    int pass_choose, takeonce;
    String page = "ShopItem";
    start start = new start(); 
    
    public ShopItemPage() {
        initComponents();
    }
    
    public ShopItemPage(Socket socket, ObjectOutputStream oos, ObjectInputStream ois, int pass_choose, 
                            String[] selected_item, int takeonce) throws IOException, ClassNotFoundException {
        initComponents();
        this.setTitle(bar_title + ": Client (" + user + ")");
        
        this.socket = socket;
        this.oos=oos;
        this.ois = ois;
        this.pass_choose = pass_choose;
        this.takeonce=takeonce;
        
        this.selected_item = new String[2];
        for (int i=0; i<2; i++) //got pickup and shop
            this.selected_item[i] = selected_item[i];
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        add = new javax.swing.JButton();
        back = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        next = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Panel = new javax.swing.JPanel();
        item_panel = new javax.swing.JPanel();
        item0 = new javax.swing.JTextField();
        qty_panel = new javax.swing.JPanel();
        qty0 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 400));
        setPreferredSize(new java.awt.Dimension(400, 400));
        setResizable(false);

        add.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        add.setText("Add (+)");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        back.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        title.setFont(new java.awt.Font("Segoe Print", 1, 24)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Shopping List");

        next.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        next.setText("Next");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        item_panel.setLayout(new java.awt.GridLayout(20, 0, 0, 10));

        item0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item0ActionPerformed(evt);
            }
        });
        item_panel.add(item0);

        qty_panel.setLayout(new java.awt.GridLayout(20, 0, 0, 10));

        qty0.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        qty0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qty0ActionPerformed(evt);
            }
        });
        qty_panel.add(qty0);

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(item_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(qty_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(qty_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(item_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(Panel);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Item name");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Quantity");

        cart.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/trolley.png"))); // NOI18N
        cart.setText("Cart");
        cart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cart)
                                .addGap(132, 132, 132)
                                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back)
                .addGap(18, 18, 18)
                .addComponent(title)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(next)
                    .addComponent(add)
                    .addComponent(cart))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    int cart_num = 0; //originally empty
    String[][] items_in_cart;
    String[] price_in_cart = new String[20];
    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        // TODO add your handling code here:
       cart_num = 0; //everytime click in will reset and recount
       items_in_cart = new String[20][2]; //reset array
       //price_in_cart = new String[20];

       try 
        {
            oos.writeObject("next");
 
            get_items();
            oos.writeObject(cart_num); //pass the total num of items
            
            for (int i=0; i<cart_num; i++)
            {
                for (int j=0; j<2; j++)
                {
                    oos.writeObject(items_in_cart[i][j]);
                }
            }
        
            check_num();

        } catch (IOException | ClassNotFoundException ex) {start.fail_to_connect();}
        
    }//GEN-LAST:event_nextActionPerformed

    public void get_items() //put all items in array and increase cart num
    {
        int row =0 , col = 0;
        //put all items in array form
        if (item0.getText().length() > 0)
            {
                items_in_cart[row][col] = item0.getText(); 
                col = 1;
                items_in_cart[row][col] = qty0.getSelectedItem().toString();
                //price_in_cart[row] = "-";
                row++;
                cart_num++;
            }
                
        for (int i=1; i<max; i++) //newly added box
        {
            if (item[i].getText().length() > 0) //if user enter something
            {
                col = 0;
                items_in_cart[row][col] = item[i].getText(); 
                col = 1;
                items_in_cart[row][col] = qty[i].getSelectedItem().toString();
                //price_in_cart[row] = "-";
                row++;
                cart_num++;
            }
        }
    }
    
    public void check_num() throws IOException, ClassNotFoundException
    {     
        if (cart_num > 0)
            {
            page = "SummaryShop";
            try {
                oos.writeObject(page); //pass page number
            } catch (IOException ex) {}

            SummaryShopPage summaryshop = new SummaryShopPage(socket, oos, ois, pass_choose, selected_item, 
                                                                cart_num, items_in_cart, price_in_cart, takeonce);
            summaryshop.setLocation(this.getX(), this.getY());
            summaryshop.setVisible(true);
            this.setVisible(false);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "You did not enter anything, please add some items to proceed",
                                            "Warning", JOptionPane.WARNING_MESSAGE);
            page = "ShopItem";
            oos.writeObject(page); //pass page number
        }
    }
    
    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
         try {
            
            oos.writeObject("Back");
            page = "ShopMall";
            oos.writeObject(page); //pass page number
            
            ChooseShopMallPage shopmall_page = new ChooseShopMallPage(socket, oos, ois, pass_choose, takeonce);
            shopmall_page.setLocation(this.getX(), this.getY());
            shopmall_page.setVisible(true);
            this.setVisible(false);
        
        } catch (IOException ex) {start.fail_to_connect();}
    }//GEN-LAST:event_backActionPerformed

    int max = 1; 
    JTextField[] item = new JTextField[20]; //create array of 20 text field and combo box
    JComboBox[] qty = new JComboBox[20];
    String[] num = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:

        if (max < 20) //add more if less than 20
        {
            //JTextField item = new JTextField();
            item[max] = new JTextField();
            item_panel.add(item[max]);
            item_panel.validate();

            //JComboBox qty = new JComboBox(num);
            qty[max] = new JComboBox(num);
            qty_panel.add(qty[max]);
            qty_panel.validate();

            max++; //increase current limit
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Max number of items allowed to add!",
                "Warning",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_addActionPerformed

    private void qty0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qty0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qty0ActionPerformed

    private void item0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_item0ActionPerformed

    
    private void cartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartActionPerformed
        // TODO add your handling code here:
        //first item field box not an array
        cart_num = 0; //everytime click in will reset and recount
        items_in_cart = new String[20][2];
        
            get_items();
            
            String cart_dialog = ""; 
            
            for (int i=0; i<cart_num; i++)
            {
                cart_dialog = cart_dialog + (i+1) + ". ";
                for (int j=0; j<2; j++)
                {
                    cart_dialog = cart_dialog + items_in_cart[i][j] + "        ";
                }
                cart_dialog = cart_dialog + "\n";
            }
            
            if (cart_num == 0)
                JOptionPane.showMessageDialog(null, "Your cart is empty, please add something",
                    "Your Cart (" + cart_num + " item(s))", JOptionPane.PLAIN_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, cart_dialog,
                    "Your Cart (" + cart_num + " item(s))", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_cartActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(ShopItemPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShopItemPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShopItemPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShopItemPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShopItemPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JButton add;
    private javax.swing.JButton back;
    private javax.swing.JButton cart;
    private javax.swing.JTextField item0;
    private javax.swing.JPanel item_panel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton next;
    private javax.swing.JComboBox<String> qty0;
    private javax.swing.JPanel qty_panel;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
