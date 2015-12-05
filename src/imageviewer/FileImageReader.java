package imageviewer;

import imageviewer.model.Image;
import imageviewer.view.ImageReader;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import static java.util.Arrays.stream;

public class FileImageReader implements ImageReader {

    private final File[] files;
    private final String[] IMAGE_EXTENSIONS = {".png", ".jpg", ".gif"};

    public FileImageReader(String path) {
        this(new File(path));
    }

    public FileImageReader(File folder) {
        this.files = folder.listFiles(withImageExtension());
    }

    private FilenameFilter withImageExtension() {
        return (dir, name) -> stream(IMAGE_EXTENSIONS).anyMatch(name::endsWith);
    }

    @Override
    public Image read() {
        return imageAt(0);
    }

    private Image imageAt(int index) {
        return new Image() {
            @Override
            public Object bitmap() {
                try {
                    return ImageIO.read(files[index]);
                } catch (IOException e) {
                    return null;
                }
            }

            @Override
            public Image prev() {
                return imageAt(index > 0 ? index - 1 : files.length - 1);
            }

            @Override
            public Image next() {
                return imageAt(index < files.length - 1 ? index + 1 : 0);
            }
        };
    }

}
