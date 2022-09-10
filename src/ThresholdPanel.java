public class ThresholdPanel extends Panel {

    int xMover;
    int dxMover;

    public ThresholdPanel(int _x, int _y, int _w, int _h, int _identifier, String _imageName) {
        super(_x, _y, _w, _h, _identifier, _imageName);

        xMover = 0;
        dxMover = 3;

        if (_x == 0) {
            setPanelName("Threshold (pixels)");
        }
    }

    public void display() {

        Main.app.loadPixels();

        int w = getWidth();
        int h = getHeight();

        int pix[] = getImage().pixels;

        int brightnessThreshold = xMover * 255 / Main.app.width;

        // Iterate over every pixel (row and col) in local panel space
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int loc = x + (y * w);
                int locGlobal = globalLoc(x, y);
                float brightness = Main.app.brightness(pix[loc]);
                if (brightness > brightnessThreshold) {
                    Main.app.pixels[locGlobal] = Main.app.color(255);
                } else {
                    Main.app.pixels[locGlobal] = Main.app.color(0);
                }
            }
        }
        Main.app.updatePixels();

        if (getX() == 0) {
            int percent = xMover * 100 / Main.app.width;
            setPanelName("Threshold (pixels): " + percent + "%");
        }

        if (xMover >= Main.app.width || xMover < 0) {
            dxMover *= -1;
        }
        xMover += dxMover;

        displayPanelName();
    }
}