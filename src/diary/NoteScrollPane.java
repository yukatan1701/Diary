/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JOptionPane;
import database.*;

/**
 *
 * @author yukatan
 */
public class NoteScrollPane extends JScrollPane {
    private NoteList noteList = null;
    private MainForm mainForm = null;
    
    private void initialize() {
        this.setViewportView(noteList);
        ListSelectionModel selModel = noteList.getSelectionModel();
        selModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // main form creation moment
                    //System.out.println(lastSelectedIndex);
                    if (mainForm.getCurrentNote() == null) {
                        Note selectedNote = (Note) noteList.getModel().getElementAt(0);
                        mainForm.updateDiaryFields(selectedNote.getId());
                        return;
                    }
                    ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                    int index = lsm.getLeadSelectionIndex();
                    //Note lastNote = (Note) noteList.getModel().getElementAt(lastSelectedIndex);
                    Note lastNote = mainForm.getCurrentNote();
                    Note formNote = mainForm.createNoteByFormFields();
                    // if note has been changed
                    if (!lastNote.compareTo(formNote)) {
                        String message = "Note has been changed. Do you want to save it?";
                        String title = "Confirm";
                        Object[] options = { "Yes", "No" };
                        int answer = JOptionPane.showOptionDialog(mainForm, message, 
                            title, JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        if (answer == JOptionPane.YES_OPTION) {
                            formNote.editNoteInDatabase(mainForm.getDBName());
                            mainForm.setStatus("Note has been updated.");
                        }
                    }
                    Note selectedNote = (Note) noteList.getModel().getElementAt(index);
                    mainForm.updateDiaryFields(selectedNote.getId());
                }
            }
        });
    }
    
    public NoteScrollPane() {
        noteList = new NoteList("examples.txt");
        initialize();
    }
    
    public NoteScrollPane(MainForm mainForm) {
        this.mainForm = mainForm;
        noteList = new NoteList("examples.txt");
        initialize();
    }
    
    public NoteScrollPane(MainForm mainForm, DefaultListModel<Note> listModel) {
        this.mainForm = mainForm;
        noteList = new NoteList(listModel);
        initialize();
    }
    
    public void setListModel(DefaultListModel<Note> listModel) {
        noteList.setModel(listModel);
    }
    
    public void selectIndex(int index) {
        noteList.setSelectedIndex(index);
    }
}
