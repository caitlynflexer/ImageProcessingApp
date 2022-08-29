import processing.core.PApplet;

public class NegativePanel extends Panel {

    public NegativePanel(PApplet _pApplet, int _x, int _y, int _w, int _h, int _identifier) {
        super(_pApplet, _x, _y, _w, _h, _identifier);

        setPanelName("Negative (pixels)");
        setPanelNameColor(0);
    }

    public void setupImage() {
        super.setupImage();

        int w = getWidth();
        int h = getHeight();

        int pix[] = getImage().pixels;

        // Iterate over every pixel (row and col) in local panel space
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {

                int loc = x + (y * w);

                // get original pixel value of r, g, b and calculate new r, g, b values
                float r = 255 - getPApplet().red(pix[loc]);
                float g = 255 - getPApplet().green(pix[loc]);
                float b = 255 - getPApplet().blue(pix[loc]);

                pix[loc] = getPApplet().color(r, g, b);
            }

            getImage().updatePixels();
        }
    }
} 