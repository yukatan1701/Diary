/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.border.*;

/**
 *
 * @author yukatan
 */
public class NotePanel extends JPanel {
    private NoteLabel labelDate = null;
    private NoteLabel labelTitle = null;
    private NoteLabel labelText = null;
        
    public NotePanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(LEFT_ALIGNMENT);
        this.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
            new EmptyBorder(5, 5, 5, 5)));
        labelDate = new NoteLabel();
        labelDate.setFont(labelDate.getFont().deriveFont(Font.BOLD, super.getFont().getSize()));
        labelTitle = new NoteLabel();
        labelTitle.setFont(labelTitle.getFont().deriveFont(Font.PLAIN, super.getFont().getSize()));
        labelText = new NoteLabel();
        labelText.setFont(labelText.getFont().deriveFont(Font.ITALIC));
        this.add(labelDate);
        this.add(labelTitle);
        this.add(labelText);
    }
    
    public void addNoteToPanel(Note note) {
        String[] date = note.getDate().split("-");
        labelDate.setText(String.format("%s.%s.%s [%d]", date[2], date[1], date[0]));
        labelTitle.setText(note.getTitle());
        labelText.setText(note.getText());
    }
    
}
