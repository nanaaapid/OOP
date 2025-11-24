public class MainApp {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginFrame f = new LoginFrame();
                f.setVisible(true);
            }
        });
    }
}
