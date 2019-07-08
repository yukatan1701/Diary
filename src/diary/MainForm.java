/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import javax.swing.border.*;

/**
 *
 * @author yukatan
 */
public class MainForm extends javax.swing.JFrame {
    
    public final String dbname = "diary";
    
    /**
     * Creates new form MainForm
     */
    public MainForm() {
        
        initComponents();
        noteScrollPane.setListModel(Note.loadNotesFromDatabase(dbname));
        noteScrollPane.selectIndex(0);
        labelStatus.setText("Diary is opened.");
    }
    
    public void updateDiaryFields(int id) {
        labelStatus.setText("Loading note...");
        // TODO: use threads
        Note fullNote = Note.loadNoteFromDatabase(dbname, id);
        labelStatus.setText("Note loaded.");
        
        textFieldDate.setText(fullNote.getDate());
        titleTextField.setText(fullNote.getTitle());
        textArea.setText(fullNote.getText());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        buttonAdd = new javax.swing.JButton();
        buttonRefresh = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        splitPane = new javax.swing.JSplitPane();
        panelLeft = new javax.swing.JPanel();
        noteScrollPane = new diary.NoteScrollPane(this);
        panelRight = new javax.swing.JPanel();
        panelFiels = new javax.swing.JPanel();
        textFieldDate = new javax.swing.JFormattedTextField();
        sliderMood = new javax.swing.JSlider();
        sliderDesire = new javax.swing.JSlider();
        checkBoxBlood = new javax.swing.JCheckBox();
        titleTextField = new diary.TitleTextField();
        checkBoxBlood1 = new javax.swing.JCheckBox();
        panelText = new javax.swing.JPanel();
        scrollPaneText = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        panelStatus = new javax.swing.JPanel();
        labelStatus = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuDiary = new javax.swing.JMenu();
        menuItemAdd = new javax.swing.JMenuItem();
        menuItemRefresh = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuItemExit = new javax.swing.JMenuItem();
        menuTools = new javax.swing.JMenu();
        menuItemPreferences = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Diary");
        setMinimumSize(new java.awt.Dimension(640, 480));
        setPreferredSize(new java.awt.Dimension(740, 480));

        toolBar.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 5, 0), new MatteBorder(0, 0, 1, 0, java.awt.Color.LIGHT_GRAY)));
        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        buttonAdd.setText("Add");
        buttonAdd.setFocusable(false);
        buttonAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });
        toolBar.add(buttonAdd);

        buttonRefresh.setText("Refresh");
        buttonRefresh.setFocusable(false);
        buttonRefresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonRefresh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(buttonRefresh);

        buttonDelete.setText("Delete...");
        buttonDelete.setFocusable(false);
        buttonDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(buttonDelete);

        getContentPane().add(toolBar, java.awt.BorderLayout.PAGE_START);

        splitPane.setBorder(new EmptyBorder(0, 5, 5, 5));
        splitPane.setDividerLocation(270);

        panelLeft.setPreferredSize(new java.awt.Dimension(300, 586));
        panelLeft.setLayout(new java.awt.CardLayout());
        panelLeft.add(noteScrollPane, "card2");

        splitPane.setLeftComponent(panelLeft);

        textFieldDate.setText("July 01, 1999");
        textFieldDate.setToolTipText("Date");

        sliderMood.setMaximum(10);
        sliderMood.setToolTipText("Mood");
        sliderMood.setValue(5);

        sliderDesire.setMaximum(10);
        sliderDesire.setToolTipText("Desire");
        sliderDesire.setValue(5);

        checkBoxBlood.setText("Blood");

        titleTextField.setToolTipText("Title");

        checkBoxBlood1.setText("Water");

        javax.swing.GroupLayout panelFielsLayout = new javax.swing.GroupLayout(panelFiels);
        panelFiels.setLayout(panelFielsLayout);
        panelFielsLayout.setHorizontalGroup(
            panelFielsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFielsLayout.createSequentialGroup()
                .addComponent(textFieldDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelFielsLayout.createSequentialGroup()
                .addComponent(sliderMood, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sliderDesire, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkBoxBlood)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkBoxBlood1)
                .addGap(0, 120, Short.MAX_VALUE))
        );
        panelFielsLayout.setVerticalGroup(
            panelFielsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFielsLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelFielsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelFielsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFielsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFielsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sliderMood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderDesire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelFielsLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(panelFielsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(checkBoxBlood)
                            .addComponent(checkBoxBlood1))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelText.setLayout(new java.awt.CardLayout());

        scrollPaneText.setToolTipText("Text");

        textArea.setColumns(20);
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(new EmptyBorder(5, 5, 5, 5));
        scrollPaneText.setViewportView(textArea);

        panelText.add(scrollPaneText, "card2");

        javax.swing.GroupLayout panelRightLayout = new javax.swing.GroupLayout(panelRight);
        panelRight.setLayout(panelRightLayout);
        panelRightLayout.setHorizontalGroup(
            panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFiels, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelRightLayout.setVerticalGroup(
            panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRightLayout.createSequentialGroup()
                .addComponent(panelFiels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelText, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
        );

        splitPane.setRightComponent(panelRight);

        getContentPane().add(splitPane, java.awt.BorderLayout.CENTER);

        panelStatus.setBorder(new MatteBorder(1, 0, 0, 0, java.awt.Color.LIGHT_GRAY));
        panelStatus.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        panelStatus.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        labelStatus.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        labelStatus.setText("Status");
        panelStatus.add(labelStatus);

        getContentPane().add(panelStatus, java.awt.BorderLayout.PAGE_END);

        menuDiary.setText("Menu");

        menuItemAdd.setText("Add note...");
        menuDiary.add(menuItemAdd);

        menuItemRefresh.setText("Refresh");
        menuDiary.add(menuItemRefresh);
        menuDiary.add(jSeparator1);

        menuItemExit.setText("Exit");
        menuDiary.add(menuItemExit);

        menuBar.add(menuDiary);

        menuTools.setText("Tools");

        menuItemPreferences.setText("Preferences...");
        menuTools.add(menuItemPreferences);

        menuBar.add(menuTools);

        menuHelp.setText("Help");
        menuBar.add(menuHelp);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAddActionPerformed

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
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            /*for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }*/
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JCheckBox checkBoxBlood;
    private javax.swing.JCheckBox checkBoxBlood1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuDiary;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenuItem menuItemAdd;
    private javax.swing.JMenuItem menuItemExit;
    private javax.swing.JMenuItem menuItemPreferences;
    private javax.swing.JMenuItem menuItemRefresh;
    private javax.swing.JMenu menuTools;
    private diary.NoteScrollPane noteScrollPane;
    private javax.swing.JPanel panelFiels;
    private javax.swing.JPanel panelLeft;
    private javax.swing.JPanel panelRight;
    private javax.swing.JPanel panelStatus;
    private javax.swing.JPanel panelText;
    private javax.swing.JScrollPane scrollPaneText;
    private javax.swing.JSlider sliderDesire;
    private javax.swing.JSlider sliderMood;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JTextArea textArea;
    private javax.swing.JFormattedTextField textFieldDate;
    private diary.TitleTextField titleTextField;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
