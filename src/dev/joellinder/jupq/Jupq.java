package dev.joellinder.jupq;

import javax.swing.JFrame;

import dev.joellinder.jupq.gui.JQMenuFrame;
import dev.joellinder.jupq.quiz.JQManager;

public class Jupq {
    public static void main(String[] args) throws Exception {

        if (!JQManager.getInstance().init()) {
            System.out.println("Could not initialize Jupq!");
            return;
        }

        JQMenuFrame window = new JQMenuFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
