import processing.core.PImage;

public class ContrastedPanel extends Panel {
    // Class 5/11/2021 - currently not displayed, as it is very similar to my threshold panel

    public ContrastedPanel(int _x, int _y, int _w, int _h, int _identifier, String _imageName) {
        super(_x, _y, _w, _h, _identifier, _imageName);

        if (_x == 0) {
            setPanelName("Contrasted (pixels)");
        }
    }

    public void display() {
        PImage newImg = getImage().copy();
        newImg.loadPixels();

        int w = newImg.width;
        int h = newImg.height;

        int pix[] = newImg.pixels;

        // Iterate over every pixel (row and col) in local panel space
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {

                int loc = x + (y * w);

                if (Main.app.brightness(pix[loc]) > 150) {
                    pix[loc] = Main.app.color(255);
                } else {
                    pix[loc] = Main.app.color(0, 0);
                }
            }
        }

        newImg.updatePixels();

        if (getX() == 0) {
            displayPanelName();
        }

        Main.app.image(newImg, getX(), getY(), getWidth(), getHeight());
    }
}