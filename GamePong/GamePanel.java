import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    static final int GAME_WIDTH = 800;
    static final int GAME_HEIGHT = 600;

    Thread gameThread;

    Paddle player1, player2;
    Ball ball;

    int score1 = 0;
    int score2 = 0;

    public GamePanel() {
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        player1 = new Paddle(10, (GAME_HEIGHT / 2) - 50);
        player2 = new Paddle(GAME_WIDTH - 30, (GAME_HEIGHT / 2) - 50);
        ball = new Ball((GAME_WIDTH / 2) - 15, (GAME_HEIGHT / 2) - 15);

        this.addKeyListener(new AL());

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i < GAME_HEIGHT; i += 25) {
            g.fillRect(GAME_WIDTH / 2 - 5, i, 10, 15);
        }

        // scores
        g.setFont(new Font("Consolas", Font.BOLD, 36));
        g.drawString(String.valueOf(score1), GAME_WIDTH / 2 - 100, 50);
        g.drawString(String.valueOf(score2), GAME_WIDTH / 2 + 70, 50);

        // draw paddles and ball
        player1.draw(g);
        player2.draw(g);
        ball.draw(g);
    }

    public void move() {
        player1.move();
        player2.move();
        ball.move();

        player1.clampY(0, GAME_HEIGHT);
        player2.clampY(0, GAME_HEIGHT);

        // ball - paddle collisions
        if (ball.getBounds().intersects(player1.getBounds())) {
            ball.x = player1.x + player1.width; 
            ball.xVelocity = Math.abs(ball.xVelocity) + 1; 
        } else if (ball.getBounds().intersects(player2.getBounds())) {
            ball.x = player2.x - ball.diameter; // push out
            ball.xVelocity = -Math.abs(ball.xVelocity) - 1; 
        }

        // scoring
        if (ball.x <= 0) {
            score2++;
            resetBall(true);
        } else if (ball.x >= GAME_WIDTH - ball.diameter) {
            score1++;
            resetBall(false);
        }
    }

    private void resetBall(boolean towardRight) {
        ball.x = (GAME_WIDTH / 2) - (ball.diameter / 2);
        ball.y = (GAME_HEIGHT / 2) - (ball.diameter / 2);
        ball.xVelocity = towardRight ? 4 : -4;
        ball.yVelocity = 3 * (Math.random() < 0.5 ? 1 : -1);
    }

    @Override
    public void run() {
        double nsPerFrame = 1000000000.0 / 60.0;
        long lastTime = System.nanoTime();
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerFrame;
            lastTime = now;
            while (delta >= 1) {
                move();
                repaint();
                delta--;
            }
        }
    }

    // Key Handler: direct left/right mapping so only the intended paddle reacts
    public class AL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if (code == KeyEvent.VK_W) player1.setYVelocity(-player1.speed);
            if (code == KeyEvent.VK_S) player1.setYVelocity(player1.speed);
            if (code == KeyEvent.VK_UP) player2.setYVelocity(-player2.speed);
            if (code == KeyEvent.VK_DOWN) player2.setYVelocity(player2.speed);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int code = e.getKeyCode();
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_S) player1.setYVelocity(0);
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN) player2.setYVelocity(0);
        }
    }
}
