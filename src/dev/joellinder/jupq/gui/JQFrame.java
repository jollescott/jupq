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

        menuPanel = new JQMenuPanel();
        gamePanel = new JQGamePanel();
        resultPanel = new JQResultPanel();

        JQManager.getInstance().setState(JQState.MENU);
    }

    private void changeView(JQState state) {
        switch (state) {
        	case RESULTS:
        		resultPanel.init();

        		this.remove(menuPanel);
        		this.remove(gamePanel);
        		this.add(resultPanel);
        		break;
            case GAME:
                gamePanel.init();

                this.remove(menuPanel);
                this.add(gamePanel);
                this.remove(resultPanel);
                break;
            case MENU:
            default:
                this.add(menuPanel);
                this.remove(gamePanel);
                this.remove(resultPanel);
                break;
        }

        pack();
    }
}
