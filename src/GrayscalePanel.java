public class GrayscalePanel extends Panel {

    public GrayscalePanel(int _x, int _y, int _w, int _h, int _identifier, String _imageName) {
        super(_x, _y, _w, _h, _identifier, _imageName);

        setPanelName("Grayscale (pixels)");
        applyGrayscale();
    }

    public void applyGrayscale() {

        int w = getWidth();
        int h = getHeight();

        int pix[] = getImage().pixels;

        // Iterate over every pixel (row and col) in image (panel) space
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {

                int loc = x + (y * w);  // position of pixel in image (panel) space

                // get original pixel value of r, g, b components
                float r = Main.app.red(pix[loc]);
                float g = Main.app.green(pix[loc]);
                float b = Main.app.blue(pix[loc]);

                // convert to grayscale
                float avg = (r+g+b)/3;

                pix[loc] = Main.app.color(avg);
            }

            getImage().updatePixels();
        }
    }
} 