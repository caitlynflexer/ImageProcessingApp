public class NegativePanel extends Panel {

    public NegativePanel(int _x, int _y, int _w, int _h, int _identifier, String _imageName) {
        super(_x, _y, _w, _h, _identifier, _imageName);

        setPanelName("Negative (pixels)");
        setPanelNameColor(0);
        applyNegative();
    }

    public void applyNegative() {

        int w = getWidth();
        int h = getHeight();

        int pix[] = getImage().pixels;

        // Iterate over every pixel (row and col) in local panel space
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {

                int loc = x + (y * w);

                // get original pixel value of r, g, b and calculate new r, g, b values
                float r = 255 - Main.app.red(pix[loc]);
                float g = 255 - Main.app.green(pix[loc]);
                float b = 255 - Main.app.blue(pix[loc]);

                pix[loc] = Main.app.color(r, g, b);
            }

            getImage().updatePixels();
        }
    }
} 