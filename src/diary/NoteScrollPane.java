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
import database.*;

/**
 *
 * @author yukatan
 */
public class NoteScrollPane extends JScrollPane {
    private NoteList noteList = null;
    private MainForm mainform = null;
    
    private void initialize() {
        this.setViewportView(noteList);
        ListSelectionModel selModel = noteList.getSelectionModel();
        selModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                    int index = lsm.getLeadSelectionIndex();
                    Note selectedNote = (Note) noteList.getModel().getElementAt(index);
                    mainform.setDiaryFields(selectedNote);
                }
            }
        });
    }
    
    public NoteScrollPane() {
        noteList = new NoteList("examples.txt");
        initialize();
    }
    
    public NoteScrollPane(MainForm mainform) {
        this.mainform = mainform;
        noteList = new NoteList("examples.txt");
        initialize();
    }
    
    public NoteScrollPane(MainForm mainform, DefaultListModel<Note> listModel) {
        this.mainform = mainform;
        noteList = new NoteList(listModel);
        initialize();
    }
    
    public void setListModel(DefaultListModel<Note> listModel) {
        noteList.setModel(listModel);
    }
}
