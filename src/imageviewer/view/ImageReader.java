package imageviewer.view;

import imageviewer.model.Image;

@FunctionalInterface
public interface ImageReader {
    Image read();
}
