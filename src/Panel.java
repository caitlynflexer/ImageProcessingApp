import processing.core.PApplet;
import processing.core.PImage;

import java.util.Timer;
import java.util.TimerTask;

public class Panel extends ImageWrapper implements Drawable {

    private PImage _img;
    private int _panelNameColor;
    private String _panelName;

    private Timer _timer;
    private int _identifier;


    Panel(int x, int y, int w, int h, int identifier, String imageName) {
        super(x, y, w, h, imageName);
        _panelNameColor = 255;
        _identifier = identifier;
        _panelName = "";
        _timer = null;
        _img = Main.app.loadImage(imageName);
        _img.resize(getWidth(), getHeight());
    }

    public void display() {
        Main.app.image(_img, getX(), getY(), getWidth(), getHeight());
        displayPanelName();
    }

    public int globalLoc(int x, int y) {
        int xGlobal = getX() + x;
        int yGlobal = getY() + y;
        return xGlobal + (yGlobal * Main.app.width);
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
        _timer = new Timer();
        TimerTask task = new PanelTimerTask(this);
        _timer.schedule(task, duration, duration);
    }

    public PImage getImage() {
        return _img;
    }

    public void reloadImage() {
        _img = Main.app.loadImage(getImageName());
        _img.resize(getWidth(), getHeight());
    }

    public void onTimer() {
        // can be overridden by panel sub-class
    }

    public void setPanelNameColor(int _clr) {
        _panelNameColor = _clr;
    }

    public String getPanelName(){
        return(_panelName);
    }

    public void setPanelName(String panelName) {
        _panelName = panelName;
    }

    public int getIdentifier() {
        return _identifier;
    }

    public void displayPanelName() {
        if (getPanelName().length() > 0) {
            Main.app.textSize(12);
            Main.app.fill(_panelNameColor);
            Main.app.text(getPanelName(), getX() + 5, getY() + 20);
        }
    }
}
