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
    public static class Fields {
        private int id;
        private String date;
        private String title;
        private String text;
        private int mood = 5;
        private int desire = 5;
        private boolean blood = false;
        private boolean tears = false;
        
        public Fields() {
            
        }
        
        public Fields(Fields newFields) {
            id = newFields.id;
            date = newFields.date;
            title = newFields.title;
            text = newFields.text;
            mood = newFields.mood;
            desire = newFields.desire;
            blood = newFields.blood;
            tears = newFields.tears;
        }
        
        public Fields(ResultSet rs) {
            
        }
        
        public void setId(int id) {
            this.id = id;
        }
        
        public void setDate(String date) {
            this.date = date;
        }
        
        public void setTitle(String title) {
            this.title = title;
        }
        
        public void setText(String text) {
            this.text = text;
        }
        
        public void setMood(int mood) {
            this.mood = mood;
        }
        
        public void setDesire(int desire) {
            this.desire = desire;
        }
        
        public void setBlood(boolean blood) {
            this.blood = blood;
        }
        
        public void setTears(boolean tears) {
            this.tears = tears;
        }
    }
    
    private final Fields fields;
    
    public Note(Fields newFields) {
        fields = new Fields(newFields);
    }
    
    public int getId() {
        return fields.id;
    }
    
    public String getDate() {
        return fields.date;
    }
    
    public String getTitle() {
        return fields.title;
    }
    
    public String getText() {
        return fields.text;
    }
    
    public int getMood() {
        return fields.mood;
    }
    
    public int getDesire() {
        return fields.desire;
    }
    
    public boolean getBlood() {
        return fields.blood;
    }
    
    public boolean getTears() {
        return fields.tears;
    }
    
    @Override
    public String toString() {
        return fields.title;
    }
    
    public static DefaultListModel<Note> loadNotesFromDatabase(String dbname) {
        DefaultListModel<Note> listModel = new DefaultListModel<>();
        DBConnection dbconn = new DBConnection(dbname);
        try {
            ResultSet rs = dbconn.getQueryResult(Query.selectPreview(dbname));
            //ResultSet rs = dbconn.getQueryResult(Query.selectAll(dbname));
            while (rs.next()) {
                Fields fields = new Fields();
                fields.setId(rs.getInt("ID"));
                fields.setDate(rs.getString("DATE"));
                fields.setTitle(rs.getString("TITLE"));
                fields.setText(rs.getString("TEXT"));
                fields.setMood(rs.getInt("MOOD"));
                fields.setDesire(rs.getInt("DESIRE"));
                fields.setBlood(rs.getBoolean("BLOOD"));
                fields.setTears(rs.getBoolean("TEARS"));
                listModel.addElement(new Note(fields));
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
        Fields fields = new Fields();
        try {
            ResultSet rs = dbconn.getQueryResult(Query.selectById(dbname, id));
            if (rs.next()) {
                fields.setId(rs.getInt("ID"));
                fields.setDate(rs.getString("DATE"));
                fields.setTitle(rs.getString("TITLE"));
                fields.setText(rs.getString("TEXT"));
                fields.setMood(rs.getInt("MOOD"));
                fields.setDesire(rs.getInt("DESIRE"));
                fields.setBlood(rs.getBoolean("BLOOD"));
                fields.setTears(rs.getBoolean("TEARS"));
            }
        } catch (IOException|SQLException ex) {
            ex.printStackTrace();
        }
        dbconn.closeConnection();
        return new Note(fields);
    }
}
