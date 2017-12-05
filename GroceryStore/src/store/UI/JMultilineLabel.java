package store.UI;

import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class JMultilineLabel extends JTextArea{
    private static final long serialVersionUID = 1L;
    public JMultilineLabel(String text){
        super(text);
        setEditable(false);  
        setCursor(null);  
        setOpaque(false);  
        setFocusable(false);  
        setFont(UIManager.getFont("Label.font"));      
        setWrapStyleWord(true);  
        setLineWrap(true);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setColumns(300);
    }
} 
