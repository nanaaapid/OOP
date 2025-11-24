import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Gambar {
    private BufferedImage img;

    public Gambar() {
        img = null;
    }

    public boolean muat(File f) {
        try {
            img = ImageIO.read(f);
            if (img == null) {
                return false;
            } else {
                return true;
            }
        } catch (IOException e) {
            return false;
        }
    }

    public BufferedImage getImage() {
        return img;
    }

    public boolean ada() {
        if (img == null) return false;
        else return true;
    }

    public void clear() {
        img = null;
    }
}
