/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import static java.awt.Component.CENTER_ALIGNMENT;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.BoxLayout;

/**
 *
 * @author yukatan
 */
public class SliderPanel extends javax.swing.JPanel {
    private JLabel label = null;
    private JSlider slider = null; 
    
    private void initialize() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        label = new JLabel();
        label.setAlignmentX(CENTER_ALIGNMENT);
        slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        slider.setAlignmentX(CENTER_ALIGNMENT);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        this.add(slider);
        this.add(label); 
    }
    
    public SliderPanel() {
        initialize();
        label.setText("Label");
    }
    
    public SliderPanel(String text) {
        initialize();
        label.setText(text);
    }
    
    public void setValue(int val) {
        slider.setValue(val);
    }
    
    public int getValue() {
        return slider.getValue();
    }
}
