package imageviewer.model;

public interface Image {

    Object bitmap();

    Image prev();

    Image next();

}
