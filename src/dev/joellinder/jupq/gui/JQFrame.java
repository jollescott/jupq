package dev.joellinder.jupq.gui;

import javax.swing.JFrame;

import dev.joellinder.jupq.gui.JQMenuPanel;
import dev.joellinder.jupq.quiz.JQManager;
import dev.joellinder.jupq.quiz.JQState;
import dev.joellinder.jupq.quiz.JQStateListener;

public class JQFrame extends JFrame {
    private JQMenuPanel menuPanel;

    public JQFrame() {
        this.setTitle("Joel's Universal Picture Quiz");
        this.setSize(800, 600);

        JQManager.getInstance().addStateListener(new JQStateListener() {
            @Override
            public void stateChanged(JQState newState) {
                changeView(newState);
            }
        });

        menuPanel = new JQMenuPanel();
        JQManager.getInstance().setState(JQState.MENU);
    }

    private void changeView(JQState state) {
        switch (state) {
            case GAME:
                this.remove(menuPanel);
                break;
            case MENU:
            default:
                this.add(menuPanel);
                break;
        }

        pack();
    }
}
