import java.awt.*;

public class Ball {
    int x, y;
    int xVelocity = 4;
    int yVelocity = 3;
    int diameter = 30;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, diameter, diameter);
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;

        // bounce top/bottom
        if (y <= 0) {
            y = 0;
            yVelocity = -yVelocity;
        } else if (y >= GamePanel.GAME_HEIGHT - diameter) {
            y = GamePanel.GAME_HEIGHT - diameter;
            yVelocity = -yVelocity;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
    }
}
