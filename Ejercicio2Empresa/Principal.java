
public class Principal extends javax.swing.JFrame {

  public Principal() {
    initComponents();
  }

  private void initComponents() {

    title = new java.awt.Panel();
    label1 = new java.awt.Label();
    panel1 = new java.awt.Panel();
    dptBtn = new javax.swing.JButton();
    asigBtn = new javax.swing.JButton();
    proyBtn = new javax.swing.JButton();
    ingBtn = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    title.setBackground(new java.awt.Color(0, 0, 0));

    label1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
    label1.setForeground(new java.awt.Color(255, 255, 255));
    label1.setName(""); // NOI18N
    label1.setText("CLIENTE/SERVIDOR");
    title.add(label1);

    panel1.setLayout(new java.awt.GridLayout(2, 2, 15, 15));

    dptBtn.setText("Departamentos");
    dptBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        dptBtnActionPerformed(evt);
      }
    });
    panel1.add(dptBtn);

    asigBtn.setText("Asignaciones");
    asigBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        asigBtnActionPerformed(evt);
      }
    });
    panel1.add(asigBtn);

    proyBtn.setText("Proyectos");
    proyBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        proyBtnActionPerformed(evt);
      }
    });
    panel1.add(proyBtn);

    ingBtn.setText("Ingenieros");
    ingBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        ingBtnActionPerformed(evt);
      }
    });
    panel1.add(ingBtn);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 278,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 57, Short.MAX_VALUE)));

    pack();
  }// </editor-fold>

  private void dptBtnActionPerformed(java.awt.event.ActionEvent evt) {
    Ingeniero ventana = new Ingeniero();
    ventana.setVisible(true);
  }

  private void asigBtnActionPerformed(java.awt.event.ActionEvent evt) {
    // OPEN ASIGNACIONES
    AsignacionesGUI ventana = new AsignacionesGUI();
    ventana.setVisible(true);
  }

  private void proyBtnActionPerformed(java.awt.event.ActionEvent evt) {
    // OPEN PROYECTOS
    ProyectosGUI ventana = new ProyectosGUI();
    ventana.setVisible(true);
  }

  private void ingBtnActionPerformed(java.awt.event.ActionEvent evt) {
    // OPEN INGENEIREOS:
    Ingeniero ventana = new Ingeniero();
    ventana.setVisible(true);
  }

  public static void main(String args[]) {

    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
   
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Principal().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify
  private javax.swing.JButton asigBtn;
  private javax.swing.JButton dptBtn;
  private javax.swing.JButton ingBtn;
  private java.awt.Label label1;
  private java.awt.Panel panel1;
  private javax.swing.JButton proyBtn;
  private java.awt.Panel title;
  // End of variables declaration
}
