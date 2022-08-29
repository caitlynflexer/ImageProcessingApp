import processing.core.PApplet;

public class GrayscalePanel extends Panel {

    public GrayscalePanel(PApplet _pApplet, int _x, int _y, int _w, int _h, int _identifier) {
        super(_pApplet, _x, _y, _w, _h, _identifier);

        setPanelName("Grayscale (pixels)");
    }

    public void setupImage() {
        super.setupImage();

        int w = getWidth();
        int h = getHeight();

        int pix[] = getImage().pixels;

        // Iterate over every pixel (row and col) in image (panel) space
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {

                int loc = x + (y * w);  // position of pixel in image (panel) space

                // get original pixel value of r, g, b components
                float r = getPApplet().red(pix[loc]);
                float g = getPApplet().green(pix[loc]);
                float b = getPApplet().blue(pix[loc]);

                // convert to grayscale
                float avg = (r+g+b)/3;

                pix[loc] = getPApplet().color(avg);
            }

            getImage().updatePixels();
        }
    }
} 