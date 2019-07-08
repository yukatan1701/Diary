/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;

/**
 *
 * @author yukatan
 */
public class NoteList extends JList {
    
    private ArrayList<Note> getNotesFromResources(String filename){
        ArrayList<Note> notes = new ArrayList<>();
        ClassLoader cldr = getClass().getClassLoader();
        try (InputStream file = cldr.getResourceAsStream(filename)) {
            try (Scanner in = new Scanner(file)) {
                int id = 0;
                while (in.hasNextLine()) {
                    Note.Fields fields = new Note.Fields();
                    fields.setId(id++);
                    fields.setDate(in.nextLine());
                    fields.setTitle(in.nextLine());
                    fields.setText(in.nextLine());
                    fields.setMood(Integer.parseInt(in.nextLine()));
                    fields.setDesire(Integer.parseInt(in.nextLine()));
                    fields.setBlood(Boolean.parseBoolean(in.nextLine()));
                    fields.setTears(Boolean.parseBoolean(in.nextLine()));
                    
                    notes.add(new Note(fields));
                }
            } catch (Exception ex) {
            }
        } catch (IOException ex) {
        }
        return notes;
    }
    
    private void initNoteList() {
        this.setCellRenderer(new NotePanelRenderer());
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public NoteList() {
        initNoteList();
    }
    
    public NoteList(DefaultListModel<Note> listModel) {
        initNoteList();
        this.setModel(listModel);
    }
    
    public NoteList(String filename) {
        initNoteList();
        DefaultListModel<Note> listModel = new DefaultListModel<>();
        ArrayList<Note> notes = getNotesFromResources(filename);
        notes.forEach((note) -> {
            listModel.addElement(note);
        });
        this.setModel(listModel);   
    }
}
