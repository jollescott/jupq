package dev.joellinder.jupq.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dev.joellinder.jupq.quiz.JQManager;
import dev.joellinder.jupq.quiz.JQRecord;
import dev.joellinder.jupq.quiz.JQResult;
import dev.joellinder.jupq.quiz.JQState;

public class JQGamePanel extends JPanel implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5539667735359900093L;
	
	private static final int ALTERNATIVE_COUNT = 3;
    private static final int QUIZ_LENGTH = 10;

    class ImageView extends JPanel {

        /**
		 * 
		 */
		private static final long serialVersionUID = 8551800646657047198L;
		private BufferedImage image;

        public void loadImage(String path) {
            try {
                image = ImageIO.read(new File(path));
                repaint();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (image != null) {
                g.drawImage(image, (getWidth() - image.getWidth()) / 2, (getHeight() - image.getHeight()) / 2, this);
            }
        }
    }

    private ImageView image;
    private ArrayList<JButton> buttons;
    private ArrayList<JLabel> results;
    private ArrayList<JQRecord> questions;
    private ArrayList<String> answerPool;
    private JQResult result;
    private String currentAnswer;
    private int index;

    public JQGamePanel() {
        setLayout(new BorderLayout());

        image = new ImageView();
        var buttonPanel = new JPanel();
        var resultPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(0, ALTERNATIVE_COUNT));
        buttons = new ArrayList<JButton>();

        for (int i = 0; i < ALTERNATIVE_COUNT; i++) {
            var button = new JButton(String.format("Button %d", i));
            button.addActionListener(this);

            buttonPanel.add(button, 0, i);
            buttons.add(button);
        }

        resultPanel.setLayout(new GridLayout(0, QUIZ_LENGTH));
        results = new ArrayList<JLabel>();

        for (int i = 0; i < QUIZ_LENGTH; i++) {
            var label = new JLabel(String.format("Q%s", i + 1));

            resultPanel.add(label);
            results.add(label);
        }

        this.add(resultPanel, BorderLayout.NORTH);
        this.add(image, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(400, 300));
    }

    public void init() {
        questions = JQManager.getInstance().getDataset().getRecords();
        answerPool = new ArrayList<String>();
        result = new JQResult(QUIZ_LENGTH);
        index = 0;

        for (var q : questions) {
            for (var a : q.getAnswers()) {
                answerPool.add(a);
            }
        }

        nextQuestion(questions.get(0));
    }

    private void nextQuestion(JQRecord question) {
        var rand = new Random();
        var alternatives = new ArrayList<String>();

        var possibleAnswers = question.getAnswers();
        var correctAnswer = possibleAnswers[rand.nextInt(possibleAnswers.length)];

        currentAnswer = correctAnswer;
        alternatives.add(correctAnswer);

        while (alternatives.size() < ALTERNATIVE_COUNT) {
            var randomAnswer = answerPool.get(rand.nextInt(answerPool.size()));

            if (!alternatives.contains(randomAnswer)) {
                alternatives.add(randomAnswer);
            }
        }

        Collections.shuffle(alternatives);

        for (int i = 0; i < ALTERNATIVE_COUNT; i++) {
            var button = buttons.get(i);
            button.setText(alternatives.get(i));
            button.setName(alternatives.get(i));
        }

        var datasetName = JQManager.getInstance().getDataset().getName();
        image.loadImage(String.format("data/%s/images/%s", datasetName, question.getImage()));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        var button = (JButton) event.getSource();
        var guess = button.getName();
        var correct = guess == currentAnswer;

        var label = results.get(index);
        label.setText(guess);
        label.setForeground(correct ? Color.GREEN : Color.RED);

        result.setAnswer(index, guess, currentAnswer);
        index++;

        if (index >= QUIZ_LENGTH) {
            JQManager.getInstance().setResult(result);
            JQManager.getInstance().setState(JQState.RESULTS);
        } else {
            nextQuestion(questions.get(index));
        }
    }
}
