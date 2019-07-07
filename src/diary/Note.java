/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import java.time.LocalDate;

/**
 *
 * @author yukatan
 */
public class Note {
    private final LocalDate date;
    private final String title;
    private final String text;
    
    public Note(LocalDate date, String title, String text) {
        this.date = date;
        this.title = title;
        this.text = text;
    }
    
    public LocalDate getDate() {
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
}
