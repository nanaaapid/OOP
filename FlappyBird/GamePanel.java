import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {

    private final int WIDTH = 400, HEIGHT = 600;
    private Bird bird;
    private ArrayList<Pipe> pipes = new ArrayList<>();
    private Timer timer;
    private int score = 0;

    // GAME STATE
    private enum GameState {
        MENU, PLAYING, GAME_OVER
    }

    private GameState state = GameState.MENU;

    // background & card image
    private Image bg;
    private Image cardImg;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // load background
        try {
            bg = new ImageIcon("background.jpg").getImage();
        } catch (Exception e) {
            System.out.println("Background tidak ditemukan!");
        }

        // load card popup image
        try {
            cardImg = new ImageIcon("card.png").getImage();
        } catch (Exception e) {
            System.out.println("Card popup tidak ditemukan!");
        }

        timer = new Timer(16, this);

        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
    }

    // MULAI GAME BARU
    private void startGame() {
        score = 0;
        pipes.clear();
        pipes.add(new Pipe(WIDTH, 60, 150, HEIGHT));

        bird = new Bird(80, HEIGHT / 2, 80);

        state = GameState.PLAYING;
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (state != GameState.PLAYING) {
            repaint();
            return;
        }

        bird.update();

        for (int i = 0; i < pipes.size(); i++) {

            Pipe p = pipes.get(i);
            p.update();

            // Tambah skor
            if (!p.isScored() && p.getX() + p.getWidth() < 80) {
                score++;
                p.setScored(true);
            }

            // hilangkan pipa lama + tambahkan pipa baru
            if (p.isOffScreen()) {
                pipes.remove(i);
                pipes.add(new Pipe(WIDTH, 60, 150, HEIGHT));
            }

            // deteksi tabrakan
            if (bird.getBounds().intersects(p.getTopBounds()) ||
                bird.getBounds().intersects(p.getBottomBounds())) {
                gameOver();
            }
        }

        // jatuh ke bawah
        if (bird.getY() > HEIGHT) {
            gameOver();
        }

        repaint();
    }

    private void gameOver() {
        timer.stop();
        state = GameState.GAME_OVER;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(bg, 0, 0, WIDTH, HEIGHT, null);

        switch (state) {
            case MENU:      drawMenu(g); break;
            case PLAYING:   drawGame(g); break;
            case GAME_OVER: drawGameOver(g); break;
        }
    }


    // ==== DRAW CARD IMAGE POPUP ====
    private void drawImageCard(Graphics g, Image img, int w, int h) {

        int offsetX = 0; 
        int offsetY = -5;

        int x = (WIDTH - w) / 2 + offsetX;
        int y = (HEIGHT - h) / 2 + offsetY;

        g.drawImage(img, x, y, w, h, null);
    }


    // ==========================================================
    // MENU SCREEN
    // ==========================================================
    private void drawMenu(Graphics g) {

        int cardW = 300;
        int cardH = 220;

        drawImageCard(g, cardImg, cardW, cardH);

        int cardX = (WIDTH - cardW) / 2;
        int cardY = (HEIGHT - cardH) / 2 - 5;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // === Text Centering ===
        int centerY = cardY + cardH / 2;

        // Title
        String title = "Selamat Datang!";
        g2.setFont(new Font("SansSerif", Font.BOLD, 26));
        int titleWidth = g2.getFontMetrics().stringWidth(title);
        int titleX = cardX + (cardW - titleWidth) / 2;
        int titleY = centerY - 10;

        g2.setColor(Color.BLACK);
        g2.drawString(title, titleX, titleY);

        // Subtitle
        String sub = "Tekan SPACE untuk mulai";
        g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        int subWidth = g2.getFontMetrics().stringWidth(sub);
        int subX = cardX + (cardW - subWidth) / 2;
        int subY = titleY + 35;

        g2.drawString(sub, subX, subY);
    }


    // ==========================================================
    // GAME VIEW
    // ==========================================================
    private void drawGame(Graphics g) {

        for (Pipe p : pipes) {
            p.draw(g);
        }

        if (bird != null) bird.draw(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 24));
        g.drawString("Score: " + score, 20, 40);
    }


    // ==========================================================
    // GAME OVER SCREEN
    // ==========================================================
    private void drawGameOver(Graphics g) {

        drawGame(g);

        int cardW = 300;
        int cardH = 240;

        drawImageCard(g, cardImg, cardW, cardH);

        int cardX = (WIDTH - cardW) / 2;
        int cardY = (HEIGHT - cardH) / 2 - 5;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // === Text Centering ===
        int centerY = cardY + cardH / 2;

        // Title
        String title = "GAME OVER";
        g2.setFont(new Font("SansSerif", Font.BOLD, 28));
        int titleWidth = g2.getFontMetrics().stringWidth(title);
        int titleX = cardX + (cardW - titleWidth) / 2;
        int titleY = centerY - 30;

        g2.setColor(Color.BLACK);
        g2.drawString(title, titleX, titleY);

        // Score
        String scr = "Total Skor: " + score;
        g2.setFont(new Font("Poppins", Font.PLAIN, 20));
        int scrWidth = g2.getFontMetrics().stringWidth(scr);
        int scrX = cardX + (cardW - scrWidth) / 2;
        int scrY = titleY + 35;

        g2.drawString(scr, scrX, scrY);

        // R untuk ulang
        String r = "Tekan R untuk ulang";
        g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        int rWidth = g2.getFontMetrics().stringWidth(r);
        int rX = cardX + (cardW - rWidth) / 2;
        int rY = scrY + 30;

        g2.drawString(r, rX, rY);

        // ESC untuk keluar
        String esc = "Tekan ESC untuk keluar";
        int escWidth = g2.getFontMetrics().stringWidth(esc);
        int escX = cardX + (cardW - escWidth) / 2;
        int escY = rY + 25;

        g2.drawString(esc, escX, escY);
    }


    // ==========================================================
    // INPUT HANDLER
    // ==========================================================
    @Override
    public void keyPressed(KeyEvent e) {

        if (state == GameState.MENU && e.getKeyCode() == KeyEvent.VK_SPACE)
            startGame();

        if (state == GameState.PLAYING && e.getKeyCode() == KeyEvent.VK_SPACE)
            bird.jump();

        if (state == GameState.GAME_OVER && e.getKeyCode() == KeyEvent.VK_R)
            startGame();

        if (state == GameState.GAME_OVER && e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    @Override 
    public void mousePressed(MouseEvent e) {
        if (state == GameState.PLAYING)
            bird.jump();
    }

    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
