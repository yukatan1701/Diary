/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import javax.swing.JScrollPane;

/**
 *
 * @author yukatan
 */
public class NoteScrollPane extends JScrollPane {
    NoteList noteList = null;
    
    public NoteScrollPane() {
        noteList = new NoteList(Note.loadNotesFromDatabase("diary"));
        this.setViewportView(noteList);
    }
}
