import processing.core.PApplet;
import processing.core.PImage;

import java.util.Timer;
import java.util.TimerTask;

public class Panel {
    private PImage img;
    private int x;
    private int y;
    private int w;
    private int h;
    private int identifier;
    private String imageName;
    private String panelName;
    private int panelNameColor;
    private PApplet pApplet;  // pApplet is the superclass of ImageProcessingStarter
    private Timer timer;


    Panel(PApplet _pApplet, int _x, int _y, int _w, int _h, int _identifier) {
        pApplet = _pApplet;
        x = _x;
        y = _y;
        w = _w;
        h = _h;
        identifier = _identifier;
        panelName = "";
        panelNameColor = 255;
        timer = null;
    }

    public void setupImage(String _imageName) {
        imageName = _imageName;
        setupImage();
    }

    public void setupImage() {  // method overloading
        img = getPApplet().loadImage(imageName);
        img.resize(w, h);
    }

    public void display() {
        getPApplet().image(img, x, y, w, h);
        displayPanelName();
    }

    public void handleMouseClicked(int _x, int _y) {
        System.out.println("Mouse clicked " + Integer.toString(identifier));
    }

    public PImage getImage() {
        return img;
    }

    public PApplet getPApplet() {
        return pApplet;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int _x) {
        x = _x;
    }

    public void setY(int _y) {
        y = _y;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public int getIdentifier() {
        return identifier;
    }

    public int globalLoc(int _x, int _y) {
        int xGlobal = x + _x;
        int yGlobal = y + _y;
        return xGlobal + (yGlobal * getPApplet().width);
    }

    public int randomInt(int min, int max) {
        int rand = min + (int)(Math.random() * (max - min));
        return rand;
    }

    public double randomDouble(double min, double max) {
        double rand = min + (Math.random() * (max - min));
        return rand;
    }

    public void createTimer(int duration) {
        timer = new Timer();
        TimerTask task = new PanelTimerTask(this);
        timer.schedule(task, duration, duration);
    }

    public void onTimer() {
        // can be overridden by panel sub-class
    }

    public void setPanelName(String name) {
        panelName = name;
    }

    public void setPanelNameColor(int _clr) {
        panelNameColor = _clr;
    }

    public void displayPanelName() {
        if (panelName.length() > 0) {
            getPApplet().textSize(12);
            getPApplet().fill(panelNameColor);
            getPApplet().text(panelName, getX() + 5, getY() + 20);
        }
    }
}
