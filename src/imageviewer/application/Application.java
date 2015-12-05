package imageviewer.application;

import imageviewer.FileImageReader;
import imageviewer.ImagePanel;
import imageviewer.control.Command;
import imageviewer.control.NextImageCommand;
import imageviewer.control.PrevImageCommand;
import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Application extends JFrame {

    private Map<String, Command> commands;
    private ImageDisplay imageDisplay;

    private final String PREV = "prev";
    private final String NEXT = "next";

    public Application() {
        this.deployUI();
        this.createCommands();
    }

    private void createCommands() {
        commands = new HashMap<>();
        commands.put(PREV, new PrevImageCommand(imageDisplay));
        commands.put(NEXT, new NextImageCommand(imageDisplay));
    }

    private void deployUI() {
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 300));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addComponents();
        this.setVisible(true);
    }

    private void addComponents() {
        this.getContentPane().add(imagePanel(), BorderLayout.CENTER);
        this.getContentPane().add(toolBar(), BorderLayout.SOUTH);
    }

    private ImagePanel imagePanel() {
        ImagePanel imagePanel = new ImagePanel(image());
        this.imageDisplay = imagePanel;
        return imagePanel;
    }

    private Image image() {
        return new FileImageReader("res/").read();
    }

    private JPanel toolBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(prevButton());
        panel.add(nextButton());
        return panel;
    }

    private JButton prevButton() {
        JButton button = new JButton("<");
        button.addActionListener(doCommand(PREV));
        return button;
    }

    private JButton nextButton() {
        JButton button = new JButton(">");
        button.addActionListener(doCommand(NEXT));
        return button;
    }

    private ActionListener doCommand(String operation) {
        return (e) -> commands.get(operation).execute();
    }

    public static void main(String[] args) {
        new Application();
    }

}
