package dev.joellinder.jupq;

import javax.swing.JFrame;

import dev.joellinder.jupq.gui.JQFrame;
import dev.joellinder.jupq.quiz.JQManager;

public class Jupq {
    public static void main(String[] args) throws Exception {
        if (!JQManager.getInstance().init()) {
            System.out.println("Could not initialize Jupq!");
            return;
        }

        JQFrame window = new JQFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
