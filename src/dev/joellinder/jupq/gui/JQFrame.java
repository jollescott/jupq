package dev.joellinder.jupq.gui;

import javax.swing.JFrame;

import dev.joellinder.jupq.quiz.JQManager;
import dev.joellinder.jupq.quiz.JQState;
import dev.joellinder.jupq.quiz.JQStateListener;

public class JQFrame extends JFrame {
    private JQMenuPanel menuPanel;
    private JQGamePanel gamePanel;

    public JQFrame() {
        this.setTitle("Joel's Universal Picture Quiz");

        JQManager.getInstance().addStateListener(new JQStateListener() {
            @Override
            public void stateChanged(JQState newState) {
                changeView(newState);
            }
        });

        menuPanel = new JQMenuPanel();
        gamePanel = new JQGamePanel();

        JQManager.getInstance().setState(JQState.MENU);
    }

    private void changeView(JQState state) {
        switch (state) {
            case GAME:
                gamePanel.init();

                this.remove(menuPanel);
                this.add(gamePanel);
                break;
            case MENU:
            default:
                this.remove(gamePanel);
                this.add(menuPanel);
                break;
        }

        pack();
    }
}
