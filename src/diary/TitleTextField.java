/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 *
 * @author yukatan
 */
public class TitleTextField extends JTextField implements FocusListener {
    private final String hint;
    private boolean showingHint = true;
    
    public TitleTextField() {
        hint = "";
    }
    
    public TitleTextField(final String hint) {
        this.hint = hint;
        super.addFocusListener(this);
    }

    private void hideHint() {
         if (this.getText().isEmpty()) {
            super.setText("");
            showingHint = false;
            super.setForeground(Color.BLACK);
        }
    }
    
    private void showHint() {
        if (this.getText().isEmpty()) {
            super.setText(hint);
            showingHint = true;
            super.setForeground(Color.LIGHT_GRAY);
        }
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        hideHint();
    }
    
    @Override
    public void focusLost(FocusEvent e) {
        showHint();
    }
    
    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }
    
    /*@Override
    public void setText(String text) {
        super.setText(text);
        if (text.isEmpty()) {
            showHint();
        } else {
            hideHint();
        }
    }*/
}
