import processing.core.PApplet;

public class Main extends PApplet {

    final int NUM_PANELS_HORIZONTAL = 4; // the horizontal quantity of panels; number of columns
    final int NUM_PANELS_VERTICAL = 11; // the vertical quantity of panels; number of rows
    private Panel[] panels; // declaration of array of type Panels

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings() {
        size(600, 700);
    }

    public void setup() {

        // allocating space for panels, but not adding them yet
        panels = new Panel[NUM_PANELS_HORIZONTAL * NUM_PANELS_VERTICAL];

        // position in the array for the next panel
        int index = 0;

        // calculating the width and height of each panel object
        int w = width / NUM_PANELS_HORIZONTAL;
        int h = height / NUM_PANELS_VERTICAL;

        for (int col = 0; col < NUM_PANELS_HORIZONTAL; col++) {    // iterate by column
            for (int row = 0; row < NUM_PANELS_VERTICAL; row++) {   // iterate by row

                // calculating upper left corner of panel
                int x = col * width / NUM_PANELS_HORIZONTAL;
                int y = row * height / NUM_PANELS_VERTICAL;

                // instantiate and setup a Panel object
                Panel s;  // declaring a new variable of type panel

                if (row == 0) {
                    if (col == 0) {
                        s = new Panel(this, x, y, w, h, index);
                        s.setPanelName("Original");
                    } else {
                        s = new ReflectionPanel(this, x, y, w, h, index);
                    }
                } else if (row == 1) {
                    if (col % 2 == 0) {
                        s = new GrayscalePanel(this, x, y, w, h, index);
                    } else {
                        s = new NegativePanel(this, x, y, w, h, index);
                    }
                } else if (row == 2) {
                    s = new AlphaModification(this, x, y, w, h, index);
                } else if (row == 3) {
                    s = new TintedPanel(this, x, y, w, h, index);
                } else if (row == 4) {
                    s = new ThresholdPanel(this, x, y, w, h, index);
                } else if (row == 5) {
                    s = new FlashlightPanel(this, x, y, w, h, index);
                } else if (row == 6) {
                    s = new ScaleAndRotatePanel(this, x, y, w, h, index);
                } else if (row == 7) {
                    s = new FilterPanel(this, x, y, w, h, index);
                } else if (row == 8) {
                    s = new BlendedPanel(this, x, y, w, h, index, "data/spaceimage.jpg");
                } else if (row == 9) {
                    s = new VectorPanel(this, x, y, w, h, index, (float) 0.3);
                } else {
                    s = new ContrastedPanel(this, x, y, w, h, index);
                }

                // call method on Panel to load image
                s.setupImage("data/galaxytransparent.png");

                // adding the Panel object into the array
                panels[index] = s;
                // increment index to the next position in the array
                index++;
            }
        }
    }

    public void draw() {
        fancyBackground();
        for (int i = 0; i < panels.length; i++) {
            panels[i].display();
        }
    }

    public void mouseClicked() {
        for (int i = 0; i < panels.length; i++) {
            Panel s = panels[i];
            s.handleMouseClicked(mouseX, mouseY);
        }
    }

    private void fancyBackground() {
        loadPixels();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int loc = x + y * width;

                if (y % 10 == 0 || x % 10 == 0) {
                    pixels[loc] = color(11, 27, 71);
                } else {
                    pixels[loc] = color(12, 14, 46);
                }
            }
        }

        updatePixels();
    }
}