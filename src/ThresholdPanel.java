import processing.core.PApplet;

public class ThresholdPanel extends Panel {

    int xMover;
    int dxMover;

    public ThresholdPanel(PApplet _pApplet, int _x, int _y, int _w, int _h, int _identifier) {
        super(_pApplet, _x, _y, _w, _h, _identifier);

        xMover = 0;
        dxMover = 3;

        if (_x == 0) {
            setPanelName("Threshold (pixels)");
        }
    }

    public void display() {

        getPApplet().loadPixels();

        int w = getWidth();
        int h = getHeight();

        int pix[] = getImage().pixels;

        int brightnessThreshold = xMover * 255 / getPApplet().width;

        // Iterate over every pixel (row and col) in local panel space
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int loc = x + (y * w);
                int locGlobal = globalLoc(x, y);
                float brightness = getPApplet().brightness(pix[loc]);
                if (brightness > brightnessThreshold) {
                    getPApplet().pixels[locGlobal] = getPApplet().color(255);
                } else {
                    getPApplet().pixels[locGlobal] = getPApplet().color(0);
                }
            }
        }
        getPApplet().updatePixels();

        if (getX() == 0) {
            int percent = xMover * 100 / getPApplet().width;
            setPanelName("Threshold (pixels): " + percent + "%");
        }

        if (xMover >= getPApplet().width || xMover < 0) {
            dxMover *= -1;
        }
        xMover += dxMover;

        displayPanelName();
    }
}