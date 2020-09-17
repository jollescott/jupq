package dev.joellinder.jupq.gui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import dev.joellinder.jupq.quiz.JQManager;

public class JQMenuPanel extends JPanel {

    public JQMenuPanel() {
        var datasets = JQManager.getInstance().getDatasetNames();

        Box box = new Box(BoxLayout.Y_AXIS);
        box.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        // Game Panel
        var gamePanel = new JPanel();
        gamePanel.setMaximumSize(new Dimension(800, 600));
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

        var comboBox = new JComboBox<String>(datasets);
        comboBox.setMaximumSize(new Dimension(200, 25));
        gamePanel.add(comboBox);

        var startBtn = new JButton();
        startBtn.setText("Start round");
        gamePanel.add(startBtn);

        box.add(Box.createVerticalGlue());
        box.add(gamePanel);
        box.add(Box.createVerticalGlue());

        this.add(box);
    }
}
