package imageviewer;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel implements ImageDisplay {

    private Image image;

    public ImagePanel(Image image) {
        this.image = image;
    }

    @Override
    public Image image() {
        return image;
    }

    @Override
    public void show(Image image) {
        this.image = image;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage((BufferedImage) image.bitmap(), 0, 0, this);
    }
}
