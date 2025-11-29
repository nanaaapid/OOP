import java.awt.*;

public class Paddle {
    int x, y;
    int yVelocity = 0;
    int speed = 6;

    int width = 20;
    int height = 100;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public void move() {
        y += yVelocity;
    }

    public void setYVelocity(int v) {
        this.yVelocity = v;
    }

    public void clampY(int minY, int maxY) {
        if (y < minY) y = minY;
        if (y + height > maxY) y = maxY - height;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
