/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import javax.swing.border.*;
import java.util.HashMap;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.JButton;

/**
 *
 * @author yukatan
 */
public class MainForm extends javax.swing.JFrame {
    
    private final String dbname = "diary";
    private Note currentNote = null;
    private final HashMap<Integer, String> textCache = new HashMap<>();
    private boolean addingNoteMode = false;
    /**
     * Creates new form MainForm
     */
    public MainForm() {
        
        initComponents();
        initButtons();
        noteScrollPane.additionalInit();
        labelStatus.setText("Diary is opened.");
    }
    
    public void setAddingMode(boolean mode) {
        addingNoteMode = mode;
        buttonCancel.setEnabled(mode);
        buttonAdd.setEnabled(!mode);
    }

    private void addBlankNote() {
        setAddingMode(true);
        noteScrollPane.addBlankNote();
    }
    
    private void setButtonsActions() {
        buttonSave.setAction(new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(addingNoteMode);
                if (addingNoteMode) {
                    noteScrollPane.createNoteAndRefresh(currentNote,
                        createWideNoteByFormFields());
                    setAddingMode(false);
                } else {
                    noteScrollPane.saveNoteAndRefresh(currentNote,
                        createWideNoteByFormFields());
                }
            }
        });
        buttonAdd.setAction(new AbstractAction("Add") {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBlankNote();
            }
        });
        buttonRefresh.setAction(new AbstractAction("Refresh") {
            @Override
            public void actionPerformed(ActionEvent e) {
                noteScrollPane.refresh();
            }
        });
        buttonCancel.setAction(new AbstractAction("Cancel") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonCancel.isEnabled()) {
                    boolean success = noteScrollPane.tryCancelAdding();
                    setAddingMode(!success);
                }
            }
        });
        buttonDelete.setAction(new AbstractAction("Delete") {
            @Override
            public void actionPerformed(ActionEvent e) {
                noteScrollPane.deleteNote(currentNote);
            }
        });
    }
    
    private void initButtonKeystroke(JButton button, int key, int mask) {
        int focus = JComponent.WHEN_IN_FOCUSED_WINDOW;
        KeyStroke ks = KeyStroke.getKeyStroke(key, mask);
        String command = button.getActionCommand();
        toolBar.getInputMap(focus).put(ks, command);
        toolBar.getActionMap().put(command, button.getAction());
    }
    
    private void initButtons() {
        setButtonsActions();
        initButtonKeystroke(buttonAdd, KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);
        initButtonKeystroke(buttonSave, KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        initButtonKeystroke(buttonRefresh, KeyEvent.VK_F5, 0);
        initButtonKeystroke(buttonCancel, KeyEvent.VK_ESCAPE, 0);
        initButtonKeystroke(buttonDelete, KeyEvent.VK_DELETE, 0);
        buttonCancel.setEnabled(false);
    }
    
    public void setCurrentNote(Note note) {
        currentNote = note;
    }
    
    public String getDBName() {
        return dbname;
    }
    
    public Note createWideNoteByFormFields() {
        Note.Fields fields = new Note.Fields();
        fields.setId(currentNote.getId());
        fields.setDate(textFieldDate.getText());
        fields.setTitle(titleTextField.getText());
        fields.setText(textArea.getText());
        fields.setMood(sliderMood.getValue());
        fields.setDesire(sliderDesire.getValue());
        fields.setBlood(checkBoxBlood.isSelected());
        fields.setTears(checkBoxTears.isSelected());
        return new Note(fields);
    }
    
    public void setStatus(String text) {
        labelStatus.setText(text);
    }
    
    public Note getCurrentNote() {
        return currentNote;
    }
    
    public String getCachedTextById(int id) {
        return textCache.get(id);
    }
    
    public void clearCache() {
        textCache.clear();
    }
    
    public void editTextInCache(int id, String text) {
        textCache.put(id, text);
    }
    
    public void updateDiaryFields(Note selectedNote) {
        labelStatus.setText("Loading note...");
        // TODO: use threads
        
        int id = selectedNote.getId();
        if (textCache.containsKey(id)) {
            currentNote = selectedNote;
        } else {
            currentNote = Note.loadNoteFromDatabase(dbname, id);
            // shorten note for preview and add to cache
            textCache.put(id, currentNote.getText());
            currentNote.shorten();
        }
        
        textArea.setText(textCache.get(id));
        
        textFieldDate.setText(currentNote.getDate());
        titleTextField.setText(currentNote.getTitle());
        sliderMood.setValue(currentNote.getMood());
        sliderDesire.setValue(currentNote.getDesire());
        checkBoxBlood.setSelected(currentNote.getBlood());
        checkBoxTears.setSelected(currentNote.getTears());
        labelStatus.setText("Note loaded.");
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
        buttonCancel = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();
        buttonRefresh = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        splitPane = new javax.swing.JSplitPane();
        panelLeft = new javax.swing.JPanel();
        noteScrollPane = new diary.NoteScrollPane(this);
        panelRight = new javax.swing.JPanel();
        panelFiels = new javax.swing.JPanel();
        textFieldDate = new javax.swing.JFormattedTextField();
        checkBoxBlood = new javax.swing.JCheckBox();
        titleTextField = new diary.TitleTextField();
        checkBoxTears = new javax.swing.JCheckBox();
        sliderMood = new diary.SliderPanel("Mood");
        sliderDesire = new diary.SliderPanel("Desire");
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
        toolBar.add(buttonAdd);

        buttonCancel.setText("Cancel");
        buttonCancel.setEnabled(false);
        buttonCancel.setFocusable(false);
        buttonCancel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonCancel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(buttonCancel);

        buttonSave.setText("Save");
        buttonSave.setFocusable(false);
        buttonSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(buttonSave);

        buttonRefresh.setText("Refresh");
        buttonRefresh.setFocusable(false);
        buttonRefresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonRefresh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(buttonRefresh);

        buttonDelete.setText("Delete");
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

        textFieldDate.setColumns(8);
        textFieldDate.setText("July 01, 1999");
        textFieldDate.setToolTipText("Date");

        checkBoxBlood.setText("Blood");

        titleTextField.setToolTipText("Title");

        checkBoxTears.setText("Tears");

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
                .addComponent(checkBoxTears)
                .addGap(0, 66, Short.MAX_VALUE))
        );
        panelFielsLayout.setVerticalGroup(
            panelFielsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFielsLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelFielsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelFielsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFielsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelFielsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(checkBoxBlood)
                            .addComponent(checkBoxTears))
                        .addGap(33, 33, 33))
                    .addGroup(panelFielsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFielsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sliderDesire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderMood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addComponent(panelText, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
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
        menuDiary.setDisplayedMnemonicIndex(0);

        menuItemAdd.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuItemAdd.setText("Add note...");
        menuItemAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAddActionPerformed(evt);
            }
        });
        menuDiary.add(menuItemAdd);

        menuItemRefresh.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        menuItemRefresh.setText("Refresh");
        menuDiary.add(menuItemRefresh);
        menuDiary.add(jSeparator1);

        menuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        menuItemExit.setText("Exit");
        menuDiary.add(menuItemExit);

        menuBar.add(menuDiary);

        menuTools.setText("Tools");
        menuTools.setDisplayedMnemonicIndex(0);

        menuItemPreferences.setText("Preferences...");
        menuTools.add(menuItemPreferences);

        menuBar.add(menuTools);

        menuHelp.setText("Help");
        menuHelp.setDisplayedMnemonicIndex(0);
        menuBar.add(menuHelp);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAddActionPerformed
        addBlankNote();
    }//GEN-LAST:event_menuItemAddActionPerformed

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
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JButton buttonSave;
    private javax.swing.JCheckBox checkBoxBlood;
    private javax.swing.JCheckBox checkBoxTears;
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
    private diary.SliderPanel sliderDesire;
    private diary.SliderPanel sliderMood;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JTextArea textArea;
    private javax.swing.JFormattedTextField textFieldDate;
    private diary.TitleTextField titleTextField;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
