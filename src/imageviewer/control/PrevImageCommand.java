package imageviewer.control;

import imageviewer.view.ImageDisplay;

public class PrevImageCommand implements Command {

    private final ImageDisplay display;

    public PrevImageCommand(ImageDisplay display) {
        this.display = display;
    }

    @Override
    public void execute() {
        display.show(display.image().prev());
    }

}
