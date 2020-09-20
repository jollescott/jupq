package dev.joellinder.jupq.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dev.joellinder.jupq.quiz.JQManager;
import dev.joellinder.jupq.quiz.JQState;

public class JQResultPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4395189894756502589L;

	JPanel resultList;

	public JQResultPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		resultList = new JPanel();

		this.add(Box.createVerticalGlue());
		this.add(resultList);
		this.add(Box.createVerticalGlue());

		var proceedButton = new JButton("Proceed");
		proceedButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JQManager.getInstance().setState(JQState.MENU);
			}
		});

		this.add(proceedButton);
		this.setPreferredSize(new Dimension(400, 300));
	}

	public Boolean init() {
		var result = JQManager.getInstance().getResult();

		resultList.setLayout(new GridLayout(result.getLength(), 2));
		var answers = result.getAnswers();

		for (int i = 0; i < answers.length; i++) {
			var answer = answers[i];

			var correctLabel = new JLabel(answer.getCorrect());
			resultList.add(correctLabel, i, 0);

			var answerLabel = new JLabel(answer.getAnswer());
			answerLabel.setForeground(answer.getAnswer() == answer.getCorrect() ? Color.GREEN : Color.RED);
			resultList.add(answerLabel, i, 1);
		}

		return true;
	}
}
