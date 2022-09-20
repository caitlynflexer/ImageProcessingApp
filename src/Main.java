import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Random;

public class Main extends PApplet {

    final int NUM_PANELS_HORIZONTAL = 4; // the horizontal quantity of panels; number of columns
    final int NUM_PANELS_VERTICAL = 11; // the vertical quantity of panels; number of rows
    private ArrayList<Drawable> panels; // declaration of array of type Drawable

    public static PApplet app;

    public Main() {
        app = this;
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings() {
        size(600, 700);
    }

    public void setup() {
        // allocating space for panels, but not adding them yet
        panels = new ArrayList<Drawable>();

        // position in the array for the next panel
        int index = 0;

        // calculating the width and height of each panel object
        int w = width / NUM_PANELS_HORIZONTAL;
        int h = height / NUM_PANELS_VERTICAL;

        String imageName = "data/galaxytransparent.png";

        for (int row = 0; row < NUM_PANELS_VERTICAL; row++) {    // iterate by row
            for (int col = 0; col < NUM_PANELS_HORIZONTAL; col++) {   // iterate by column

                // calculating upper left corner of panel
                int x = col * width / NUM_PANELS_HORIZONTAL;
                int y = row * height / NUM_PANELS_VERTICAL;

                // instantiate and setup a Panel object
                Panel s;  // declaring a new variable of type Drawable

                if (row == 0) {
                    if (col == 0) {
                        s = new Panel(x, y, w, h, index, imageName);
                        s.setPanelName("Original");
                    } else {
                        s = new ReflectionPanel(x, y, w, h, index, imageName);
                    }
                } else if (row == 1) {
                    if (col % 2 == 0) {
                        s = new GrayscalePanel(x, y, w, h, index, imageName);
                    } else {
                        s = new NegativePanel(x, y, w, h, index, imageName);
                    }
                } else if (row == 2) {
                    s = new AlphaModification(x, y, w, h, index, imageName);
                } else if (row == 3) {
                    s = new TintedPanel(x, y, w, h, index, imageName);
                } else if (row == 4) {
                    s = new ThresholdPanel(x, y, w, h, index, imageName);
                } else if (row == 5) {
                    s = new ScaleAndRotatePanel(x, y, w, h, index, imageName);
                } else if (row == 6) {
                    s = new FlashlightPanel(x, y, w, h, index, imageName);
                } else if (row == 7) {
                    s = new VectorPanel(x, y, w, h, index, imageName, (float) 0.3);
                } else if (row == 8) {
                    s = new FilterPanel(x, y, w, h, index, imageName);
                } else if (row == 9) {
                    s = new BlendedPanel(x, y, w, h, index, imageName, "data/spaceimage.jpg");
                } else {
                    s = new ContrastedPanel(x, y, w, h, index, imageName);
                }

                // adding the Panel object into the array
                panels.add(index, s);
                // increment index to the next position in the array
                index++;
            }
        }
    }

    public void draw() {
        fancyBackground();
        for (int i = 0; i < panels.size(); i++) {
            panels.get(i).display();
        }
    }

    public void mouseClicked() {
        for (int i = 0; i < panels.size(); i++) {
            Drawable s = panels.get(i);
            if (s instanceof Clickable) { // check if s is an instance of Clickable
                ((Clickable)s).handleMouseClicked(mouseX, mouseY); // downcasting
            }
        }
    }

    public void keyPressed() {
        if (key == 's') {
            swapPanels(0, panels.size()-1);
        }
        if (key == 'r') {
            Random ran = new Random();

            // get two random panel indices
            int panel1Index = ran.nextInt(panels.size());
            int panel2Index = ran.nextInt(panels.size());

            // ensure panels at indices are of different subclasses/types
            while(panels.get(panel1Index).getClass() == panels.get(panel2Index).getClass()) {
                panel2Index = ran.nextInt(panels.size());
            }

            swapPanels(panel1Index, panel2Index);
        }
    }

    private void swapPanels(int index1, int index2) {
        Panel panel1 = (Panel)panels.get(index1);
        int x1 = panel1.getX();
        int y1 = panel1.getY();

        Panel panel2 = (Panel)panels.get(index2);
        int x2 = panel2.getX();
        int y2 = panel2.getY();

        panel1.setX(x2);
        panel1.setY(y2);

        panel2.setX(x1);
        panel2.setY(y1);

        panels.set(index1, panel2);
        panels.set(index2, panel1);
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

