/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author yukatan
 */
public class NotePanelRenderer extends NotePanel implements ListCellRenderer<Note> {
    
    public NotePanelRenderer () {
        setOpaque(true);
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Note> list,
        Note note, int index, boolean isSelected, boolean cellHasFocus) {
        this.addNoteToPanel(note);
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}
