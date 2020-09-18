package dev.joellinder.jupq.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import dev.joellinder.jupq.quiz.JQManager;
import dev.joellinder.jupq.quiz.JQState;

public class JQMenuPanel extends JPanel {

    public JQMenuPanel() {
        this.setLayout(new BorderLayout());

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
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (JQManager.getInstance().loadDataset((String) comboBox.getSelectedItem())) {
                    JQManager.getInstance().setState(JQState.GAME);
                } else {
                    System.out.println(String.format("Could not load dataset: ", comboBox.getSelectedItem()));
                }
            }
        });
        gamePanel.add(startBtn);

        box.add(Box.createVerticalGlue());
        box.add(gamePanel);
        box.add(Box.createVerticalGlue());

        this.add(box, BorderLayout.CENTER);
    }
}
