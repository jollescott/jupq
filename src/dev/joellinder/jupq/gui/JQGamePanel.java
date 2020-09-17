package dev.joellinder.jupq.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class JQGamePanel extends JPanel {

    class ImageView extends JPanel {

        private BufferedImage image;

        public void loadImage(String path) {
            try {
                image = ImageIO.read(new File(path));
            } catch (IOException ex) {
                // TODO: Handle
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (image != null) {
                g.drawImage(image, 0, 0, this);
            }
        }
    }

    private ImageView image;

    public JQGamePanel() {
        var resultPanel = new JPanel();
        var imagePanel = new JPanel();
        var buttonPanel = new JPanel();

        image = new ImageView();
        imagePanel.add(image);

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        this.add(resultPanel, BorderLayout.NORTH);
        this.add(imagePanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
}
