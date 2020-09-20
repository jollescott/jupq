package dev.joellinder.jupq.gui;

import javax.swing.JFrame;

import dev.joellinder.jupq.quiz.JQManager;
import dev.joellinder.jupq.quiz.JQState;
import dev.joellinder.jupq.quiz.JQStateListener;

public class JQFrame extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -5722590769137547014L;

    private JQMenuPanel menuPanel;
    private JQGamePanel gamePanel;
    private JQResultPanel resultPanel;

    public JQFrame() {
        this.setTitle("Joel's Universal Picture Quiz");

        JQManager.getInstance().addStateListener(new JQStateListener() {
            @Override
            public void stateChanged(JQState newState) {
                changeView(newState);
            }
        });

        JQManager.getInstance().setState(JQState.MENU);
    }

    private void changeView(JQState state) {
        switch (state) {
            case RESULTS:
                resultPanel = new JQResultPanel();
                resultPanel.init();

                this.add(resultPanel);

                if (gamePanel != null)
                    this.remove(gamePanel);

                if (menuPanel != null)
                    this.remove(menuPanel);

                break;
            case GAME:
                gamePanel = new JQGamePanel();
                gamePanel.init();

                this.add(gamePanel);

                if (menuPanel != null)
                    this.remove(menuPanel);

                if (resultPanel != null)
                    this.remove(resultPanel);

                break;
            case MENU:
            default:
                menuPanel = new JQMenuPanel();

                this.add(menuPanel);

                if (gamePanel != null)
                    this.remove(gamePanel);

                if (resultPanel != null)
                    this.remove(resultPanel);

                break;
        }

        pack();
        repaint();
    }
}
