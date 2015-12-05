package imageviewer.view;

import imageviewer.model.Image;

public interface ImageDisplay {

    Image image();

    void show(Image image);

}
