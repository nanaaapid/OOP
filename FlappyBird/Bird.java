import java.awt.*;
import javax.swing.*;

public class Bird {
    private int x, y;
    private int size;
    private double velocity = 0;
    private final double GRAVITY = 0.5;
    private final double JUMP_STRENGTH = -8;

    private Image img;

    public Bird(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;

        try {
            img = new ImageIcon(getClass().getResource("bird.png")).getImage();
        } catch (Exception e) {
            System.out.println("Gambar burung tidak ditemukan!");
        }
    }

    public void jump() {
        velocity = JUMP_STRENGTH;
    }

    public void update() {
        velocity += GRAVITY;
        y += velocity;
    }

    public Rectangle getBounds() {
        int hitboxSize = (int)(size * 0.6);  // 60% dari ukuran gambar
        int offset = (size - hitboxSize) / 2;
        return new Rectangle(x + offset, y + offset, hitboxSize, hitboxSize);
    }
    
    public Image getImage() {
        return img;
    }

    public void draw(Graphics g) {
        if (img != null)
            g.drawImage(img, x, y, size, size, null);
        else {
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, size, size);
        }
    }

    public int getY() { return y; }
}
