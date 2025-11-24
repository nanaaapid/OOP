import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ViewerFrame extends JFrame {
    private Gambar gb;
    private ViewerController ctr;
    private ViewerPanel pn;
    private JLabel lbStatus;

    public ViewerFrame() {
        gb = new Gambar();
        ctr = new ViewerController(gb);
        pn = new ViewerPanel(ctr);
        lbStatus = new JLabel(" Siap ");

        setTitle("Simple Image Viewer");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        add(new JScrollPane(pn), BorderLayout.CENTER);

        // status bar
        JPanel pStatus = new JPanel(new BorderLayout());
        pStatus.add(lbStatus, BorderLayout.WEST);
        add(pStatus, BorderLayout.SOUTH);

        // menu
        JMenuBar mb = new JMenuBar();
        JMenu mFile = new JMenu("File");
        JMenuItem miOpen = new JMenuItem("Open...");
        JMenuItem miClear = new JMenuItem("Clear");
        JMenuItem miExit = new JMenuItem("Exit");
        mFile.add(miOpen);
        mFile.add(miClear);
        mFile.addSeparator();
        mFile.add(miExit);
        mb.add(mFile);
        setJMenuBar(mb);

        // toolbar
        JToolBar tb = new JToolBar();
        JButton btnOpen = new JButton("Open");
        JButton btnZoomIn = new JButton("Zoom +");
        JButton btnZoomOut = new JButton("Zoom -");
        JButton btnFit = new JButton("Fit");
        JButton btnReset = new JButton("Reset");
        tb.add(btnOpen);
        tb.addSeparator();
        tb.add(btnZoomIn);
        tb.add(btnZoomOut);
        tb.add(btnFit);
        tb.add(btnReset);
        add(tb, BorderLayout.NORTH);

        // Actions
        ActionListener bukaAksi = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bukaFile();
            }
        };

        ActionListener clearAksi = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctr.clearImage();
                pn.repaint();
                lbStatus.setText(" Bersih ");
            }
        };

        ActionListener exitAksi = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };

        ActionListener zoomInAksi = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ctr.adaGambar()) {
                    ctr.zoomIn();
                    pn.repaint();
                    lbStatus.setText(" Zoom: " + String.format("%.2f", ctr.getScale()));
                }
            }
        };

        ActionListener zoomOutAksi = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ctr.adaGambar()) {
                    ctr.zoomOut();
                    pn.repaint();
                    lbStatus.setText(" Zoom: " + String.format("%.2f", ctr.getScale()));
                }
            }
        };

        ActionListener fitAksi = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ctr.adaGambar()) {
                    // fit ke ukuran panel saat ini
                    ctr.fitToArea(pn.getWidth(), pn.getHeight());
                    pn.repaint();
                    lbStatus.setText(" Fit (Zoom: " + String.format("%.2f", ctr.getScale()) + ")");
                }
            }
        };

        ActionListener resetAksi = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ctr.adaGambar()) {
                    ctr.resetZoom();
                    pn.repaint();
                    lbStatus.setText(" Zoom di-reset ");
                }
            }
        };

        miOpen.addActionListener(bukaAksi);
        btnOpen.addActionListener(bukaAksi);
        miClear.addActionListener(clearAksi);
        miExit.addActionListener(exitAksi);
        btnZoomIn.addActionListener(zoomInAksi);
        btnZoomOut.addActionListener(zoomOutAksi);
        btnFit.addActionListener(fitAksi);
        btnReset.addActionListener(resetAksi);

        // resize listener: jika sedang fit mode, atur ulang saat ukuran berubah
        pn.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                if (ctr.isFitMode()) {
                    ctr.fitToArea(pn.getWidth(), pn.getHeight());
                    pn.repaint();
                    lbStatus.setText(" Fit (Zoom: " + String.format("%.2f", ctr.getScale()) + ")");
                }
            }
        });
    }

    private void bukaFile() {
        JFileChooser fc = new JFileChooser();
        int ok = fc.showOpenDialog(this);
        if (ok == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            boolean s = gb.muat(f);
            if (s) {
                // jika sukses, set fit mode otomatis agar terlihat
                ctr.fitToArea(pn.getWidth(), pn.getHeight());
                pn.repaint();
                lbStatus.setText(" Dibuka: " + f.getName() + "  (Zoom: " + String.format("%.2f", ctr.getScale()) + ")");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal membuka gambar.", "Error", JOptionPane.ERROR_MESSAGE);
                lbStatus.setText(" Gagal membuka ");
            }
        }
    }
}
