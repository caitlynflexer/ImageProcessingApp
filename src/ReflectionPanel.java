import processing.core.PApplet;

public class ReflectionPanel extends Panel {

    int w;
    int h;


    public ReflectionPanel(PApplet _pApplet, int _x, int _y, int _w, int _h, int _identifier) {
        super(_pApplet, _x, _y, _w, _h, _identifier);
        w = getWidth();
        h = getHeight();
    }

    public void setupImage() {

        super.setupImage();

        int panelCol = getX() / getWidth();

        if (panelCol % 3 == 1) {
            verticalReflection();
        } else if (panelCol % 3 == 2) {
            horizontalReflection();
        } else {
            horizontalAndVerticalReflection();
        }
    }

    public void horizontalReflection() {
        int pix[] = getImage().pixels;

        // Iterate over pixels in local panel space
        for (int x = 0; x < w / 2; x++) {
            for (int y = 0; y < h; y++) {

                int x2 = w - x - 1;

                int locX1 = x + (y * w);  // position of pixel in image (panel) space
                int locX2 = x2 + (y * w);

                int rgb1 = pix[locX1];

                pix[locX1] = pix[locX2];
                pix[locX2] = rgb1;
            }
        }
        getImage().updatePixels();
        setPanelName("Horiz. reflection (pixels)");
    }

    public void verticalReflection() {
        int pix[] = getImage().pixels;

        // Iterate over pixels in local panel space
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h / 2; y++) {

                int y2 = h - y - 1;

                int locY1 = x + (y * w);
                int locY2 = x + (y2 * w);

                int rgb1 = pix[locY1];

                pix[locY1] = pix[locY2];
                pix[locY2] = rgb1;
            }
        }
        getImage().updatePixels();
        setPanelName("Vert. reflection (pixels)");
    }


    public void horizontalAndVerticalReflection() {

        horizontalReflection();
        verticalReflection();

        setPanelName("Horiz. & vert. reflection");
    }
} 