import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewerPanel extends JPanel {
    private ViewerController ctr;

    public ViewerPanel(ViewerController ctr) {
        this.ctr = ctr;
        setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics gph) {
        super.paintComponent(gph);
        Graphics2D g2 = (Graphics2D) gph.create();

        if (!ctr.adaGambar()) {
            // pesan sederhana jika tidak ada gambar
            g2.setColor(Color.LIGHT_GRAY);
            g2.setFont(g2.getFont().deriveFont(14f));
            String teks = "Belum ada gambar. Gunakan File -> Open.";
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(teks)) / 2;
            int y = getHeight() / 2;
            g2.drawString(teks, x, y);
            g2.dispose();
            return;
        }

        BufferedImage img = ctr.getGambar().getImage();
        double s = ctr.getScale();

        int iw = img.getWidth();
        int ih = img.getHeight();

        int drawW = (int) Math.round(iw * s);
        int drawH = (int) Math.round(ih * s);

        // pusatkan gambar
        int x = (getWidth() - drawW) / 2;
        int y = (getHeight() - drawH) / 2;

        // kualitas rendering
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2.drawImage(img, x, y, drawW, drawH, null);
        g2.dispose();
    }
}
