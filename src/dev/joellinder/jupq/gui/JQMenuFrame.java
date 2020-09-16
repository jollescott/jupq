package dev.joellinder.jupq.gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;

public class JQMenuFrame extends JFrame {
    private JQMenuPanel _menuPanel;

    public JQMenuFrame() {
        this.setTitle("Joel's Universal Picture Quiz");
        this.setSize(800, 600);

        _menuPanel = new JQMenuPanel();
        _menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        this.add(_menuPanel, BorderLayout.CENTER);
        pack();
    }
}
