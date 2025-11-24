public class ViewerController {
    private Gambar g;
    private double scale;
    private boolean fitMode;

    public ViewerController(Gambar g) {
        this.g = g;
        scale = 1.0;
        fitMode = false;
    }

    public boolean adaGambar() {
        return g.ada();
    }

    public Gambar getGambar() {
        return g;
    }

    public double getScale() {
        return scale;
    }

    public void zoomIn() {
        scale = scale * 1.25;
        fitMode = false;
    }

    public void zoomOut() {
        scale = scale / 1.25;
        if (scale < 0.05) scale = 0.05;
        fitMode = false;
    }

    public void resetZoom() {
        scale = 1.0;
        fitMode = false;
    }

    // set scale supaya gambar fit di area (lebar x tinggi) panel
    public void fitToArea(int areaW, int areaH) {
        if (!g.ada()) return;
        int iw = g.getImage().getWidth();
        int ih = g.getImage().getHeight();
        if (iw <= 0 || ih <= 0) return;

        double sx = (double) areaW / iw;
        double sy = (double) areaH / ih;
        double s = sx;
        if (sy < sx) s = sy;
        if (s <= 0) s = 1.0;
        scale = s;
        fitMode = true;
    }

    public boolean isFitMode() {
        return fitMode;
    }

    public void clearImage() {
        g.clear();
        scale = 1.0;
        fitMode = false;
    }
}
