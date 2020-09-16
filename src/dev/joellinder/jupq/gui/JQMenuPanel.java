package dev.joellinder.jupq.gui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import dev.joellinder.jupq.quiz.JQManager;

public class JQMenuPanel extends JPanel {

    private JComboBox<String> _comboBox;
    private JButton _startBtn;

    public JQMenuPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        var datasets = JQManager.getInstance().getDatasetNames();

        _comboBox = new JComboBox<String>(datasets);
        this.add(_comboBox);

        this.add(Box.createRigidArea(new Dimension(0, 5)));

        _startBtn = new JButton();
        _startBtn.setText("Start round");
        this.add(_startBtn);
    }
}
