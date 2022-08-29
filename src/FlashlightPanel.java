import processing.core.PApplet;

public class FlashlightPanel extends Panel {

    int xFlashlight;
    int dxFlashlight;

    public FlashlightPanel(PApplet _pApplet, int _x, int _y, int _w, int _h, int _identifier) {
        super(_pApplet, _x, _y, _w, _h, _identifier);

        xFlashlight = 0;
        dxFlashlight = 3;

        if (_x == 0) {
            setPanelName("Flashlight (pixels)");
        }
    }

    public void display() {

        getPApplet().loadPixels();

        int w = getWidth();
        int h = getHeight();

        int pix[] = getImage().pixels;

        // Iterate over every pixel (row and col) in local panel space
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {

                int loc = x + (y * w);

                // get original pixel value of r, g, b
                float r = getPApplet().red(pix[loc]);
                float g = getPApplet().green(pix[loc]);
                float b = getPApplet().blue(pix[loc]);

                int xGlobal = getX() + x;
                int yGlobal = getY() + y;

                int yFlashlight = getY() + h/2;

                float d = getPApplet().dist(xFlashlight, yFlashlight, xGlobal, yGlobal);

                int locGlobal = globalLoc(x, y);

                float factor = getPApplet().map(d, 0, 100, 2, 0);
                getPApplet().pixels[locGlobal] = getPApplet().color(r*factor, g*factor, b*factor);
            }
        }
        getPApplet().updatePixels();

        if (getX() == 0) {
            int percent = xFlashlight * 100 / getPApplet().width;
            setPanelName("Flashlight (pixels): " + percent + "%");
        }

        if (xFlashlight >= getPApplet().width || xFlashlight < 0) {
            dxFlashlight *= -1;
        }
        xFlashlight += dxFlashlight;

        displayPanelName();
    }
} 