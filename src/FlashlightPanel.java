import processing.core.PApplet;

public class FlashlightPanel extends Panel {

    int xFlashlight;
    int dxFlashlight;

    public FlashlightPanel(int _x, int _y, int _w, int _h, int _identifier, String _imageName) {
        super(_x, _y, _w, _h, _identifier, _imageName);

        xFlashlight = 0;
        dxFlashlight = 3;

        if (_x == 0) {
            setPanelName("Flashlight (pixels)");
        }
    }

    public void display() {

        Main.app.loadPixels();

        int w = getWidth();
        int h = getHeight();

        int pix[] = getImage().pixels;

        // Iterate over every pixel (row and col) in local panel space
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {

                int loc = x + (y * w);

                // get original pixel value of r, g, b
                float r = Main.app.red(pix[loc]);
                float g = Main.app.green(pix[loc]);
                float b = Main.app.blue(pix[loc]);

                int xGlobal = getX() + x;
                int yGlobal = getY() + y;

                int yFlashlight = getY() + h/2;

                float d = Main.app.dist(xFlashlight, yFlashlight, xGlobal, yGlobal);

                int locGlobal = globalLoc(x, y);

                float factor = Main.app.map(d, 0, 100, 2, 0);
                Main.app.pixels[locGlobal] = Main.app.color(r*factor, g*factor, b*factor);
            }
        }
        Main.app.updatePixels();

        if (getX() == 0) {
            int percent = xFlashlight * 100 / Main.app.width;
            setPanelName("Flashlight (pixels): " + percent + "%");
        }

        if (xFlashlight >= Main.app.width || xFlashlight < 0) {
            dxFlashlight *= -1;
        }
        xFlashlight += dxFlashlight;

        displayPanelName();
    }
} 