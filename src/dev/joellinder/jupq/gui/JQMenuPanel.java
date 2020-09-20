package dev.joellinder.jupq.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import dev.joellinder.jupq.quiz.JQManager;
import dev.joellinder.jupq.quiz.JQState;

public class JQMenuPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8605987874384654006L;

	public JQMenuPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        var datasets = JQManager.getInstance().getDatasetNames();

        var menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));

        var comboBox = new JComboBox<String>(datasets);
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboBox.setMaximumSize(new Dimension(200, 25));
        menuPanel.add(comboBox);

        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        var startBtn = new JButton();
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        menuPanel.add(startBtn);

        this.add(Box.createVerticalGlue());
        this.add(menuPanel);
        this.add(Box.createVerticalGlue());

        this.setPreferredSize(new Dimension(400, 300));
    }
}
