/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import database.DBConnection;
import database.Query;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;


/**
 *
 * @author yukatan
 */
public class Note {
    private final int id;
    private final String date;
    private final String title;
    private final String text;
    
    public Note(int id, String date, String title, String text) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.text = text;
    }
    
    public int getId() {
        return id;
    }
    
    public String getDate() {
        return date;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getText() {
        return text;
    }
    
    @Override
    public String toString() {
        return title;
    }
    
    public static DefaultListModel<Note> loadNotesFromDatabase(String dbname) {
        DefaultListModel<Note> listModel = new DefaultListModel<>();
        DBConnection dbconn = new DBConnection(dbname);
        try {
            ResultSet rs = dbconn.getQueryResult(Query.selectPreview(dbname));
            //ResultSet rs = dbconn.getQueryResult(Query.selectAll(dbname));
            while (rs.next()) {
                int id = rs.getInt("ID");
                String date = rs.getString("DATE");
                String title = rs.getString("TITLE");
                String text = rs.getString("TEXT");
                listModel.addElement(new Note(id, date, title, text));
            }
        } catch (IOException|SQLException ex) {
            ex.printStackTrace();
        }
        dbconn.closeConnection();
        return listModel;
    }
    
    public static Note loadNoteFromDatabase(String dbname, int id) {
        String date = null;
        String title = null;
        String text = null;
        DBConnection dbconn = new DBConnection(dbname);
        try {
            ResultSet rs = dbconn.getQueryResult(Query.selectById(dbname, id));
            if (rs.next()) {
                date = rs.getString("DATE");
                title = rs.getString("TITLE");
                text = rs.getString("TEXT");
            }
        } catch (IOException|SQLException ex) {
            ex.printStackTrace();
        }
        dbconn.closeConnection();
        return new Note(id, date, title, text);
    }
}
