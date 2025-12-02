import java.awt.*;
import java.util.Random;

public class Pipe {
    private int x, width, gap;
    private int topHeight, bottomHeight;
    private int speed = 3;
    private boolean scored = false;

    public Pipe(int startX, int width, int gap, int panelHeight) {
        this.x = startX;
        this.width = width;
        this.gap = gap;

        Random rand = new Random();
        topHeight = rand.nextInt(panelHeight - gap - 50);
        bottomHeight = panelHeight - topHeight - gap;
    }

    public void update() {
        x -= speed;
    }

    public boolean isOffScreen() {
        return x + width < 0;
    }

    public Rectangle getTopBounds() {
        return new Rectangle(x, 0, width, topHeight);
    }

    public Rectangle getBottomBounds() {
        return new Rectangle(x, topHeight + gap, width, bottomHeight);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0X32CD32));
        g.fillRect(x, 0, width, topHeight);
        g.fillRect(x, topHeight + gap, width, bottomHeight);
    }

    public int getX() { return x; }
    public int getWidth() { return width; }

    public boolean isScored() { return scored; }
    public void setScored(boolean s) { scored = s; }
}
