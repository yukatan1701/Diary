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
/**
 *
 * @author yukatan
 */
public class NoteScrollPane extends JScrollPane {
    
    private final int BEGIN_INDEX = 0;
    private NoteList noteList = null;
    private MainForm mainForm = null;
    private DefaultListModel<Note> listModel = null;
    private int lastIndex = BEGIN_INDEX;
    
    public class NoteListSelectionHandler implements ListSelectionListener {
        NoteScrollPane pane = null;
        
        public NoteListSelectionHandler(NoteScrollPane pane) {
            this.pane = pane;
        }   

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                Note savedNote = mainForm.getCurrentNote();
                Note editedNote = mainForm.createWideNoteByFormFields();
                // if note has been changed
                if (!savedNote.compareWithWideNote(editedNote, mainForm)) {
                    String message = "Note has been changed. Do you want to save it?";
                    if (showConfirmDialog(message) == JOptionPane.YES_OPTION) {
                        pane.saveNoteAndRefresh(savedNote, editedNote);
                    }
                }
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                int index = lsm.getLeadSelectionIndex();
                Note selectedNote = (Note) noteList.getModel().getElementAt(index);
                mainForm.updateDiaryFields(selectedNote);
                lastIndex = index;
            }
        }
    }
    
    private int showConfirmDialog(String message) {
        String title = "Confirm";
        Object[] options = { "Yes", "No" };
        return JOptionPane.showOptionDialog(mainForm, message, 
            title, JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }
    
    public void saveNoteAndRefresh(Note savedNote, Note editedNote) {
        editedNote.editNoteInDatabase(mainForm.getDBName());
        mainForm.editTextInCache(editedNote.getId(), editedNote.getText());
        savedNote.copyPreview(editedNote);
        mainForm.setStatus("Note has been updated.");
        listModel.setElementAt(savedNote, lastIndex);
    }
    
    public void createNoteAndRefresh(Note savedNote, Note editedNote) {
        int id = editedNote.addNoteToDatabase(mainForm.getDBName());
        System.out.println(id);
        editedNote.setId(id);
        mainForm.editTextInCache(id, editedNote.getText());
        savedNote.copyPreview(editedNote);
        mainForm.setStatus("Note has been created.");
        lastIndex = BEGIN_INDEX;
        listModel.setElementAt(savedNote, lastIndex);
    }
    
    public void addBlankNote() {
        lastIndex++;
        listModel.add(BEGIN_INDEX, new Note());
        noteList.setSelectedIndex(BEGIN_INDEX);
    }
    
    public boolean tryCancelAdding() {
        String message = "Do you want to cancel creating a note?";
        if (showConfirmDialog(message) == JOptionPane.YES_OPTION) {
            lastIndex--;
            listModel.removeElementAt(BEGIN_INDEX);
            noteList.setSelectedIndex(BEGIN_INDEX);
            return true;
        }
        return false;
    }
    
    // just for design preview
    public NoteScrollPane() {
        noteList = new NoteList("examples.txt");
        this.setViewportView(noteList);
    }
    
    public NoteScrollPane(MainForm mainForm) {
        this.mainForm = mainForm;
        loadListFromDatabase();
    }
    
    private void loadListFromDatabase() {
        listModel = Note.loadNotesFromDatabase(mainForm.getDBName());
        noteList = new NoteList(listModel);
        noteList.setSelectedIndex(BEGIN_INDEX);
        lastIndex = BEGIN_INDEX;
        this.setViewportView(noteList);
    }
    
    public void additionalInit() {
        mainForm.updateDiaryFields(listModel.getElementAt(BEGIN_INDEX));
        ListSelectionModel selModel = noteList.getSelectionModel();
        selModel.addListSelectionListener(new NoteListSelectionHandler(this));
    }
    
    public void refresh() {
        mainForm.clearCache();
        loadListFromDatabase();
        additionalInit();
        mainForm.setStatus("All notes are reloaded.");
    }
    
    public void deleteNote(Note note) {
        String message = "Do you want to delete this note?\nThis process is irreversible.";
        if (showConfirmDialog(message) == JOptionPane.YES_OPTION) {
            listModel.remove(lastIndex);
            if (lastIndex > listModel.size() - 1)
                lastIndex = listModel.size() - 1;
            noteList.setSelectedIndex(lastIndex);
            note.deleteNoteFromDatabase(mainForm.getDBName());
        }
    }
}
