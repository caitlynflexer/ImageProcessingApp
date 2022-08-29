import processing.core.PApplet;

public class VectorPanel extends Panel {

    int locX;
    int locY;
    int dx;
    int dy;
    float scaleImage;

    public VectorPanel(PApplet _pApplet, int _x, int _y, int _w, int _h, int _identifier, float _scaleImage) {
        super(_pApplet, _x, _y, _w, _h, _identifier);
        locX = _x;
        locY = _y;
        dx = randomInt(1, 7);
        dy = randomInt(1, 7);
        scaleImage = _scaleImage;

        if (_x == 0) {
            setPanelName("Vector");
        }
    }

    public void setupImage() {
        super.setupImage();
        getImage().resize((int)(scaleImage * getWidth()), (int)(scaleImage * getHeight()));
    }

    public void display() {

        getPApplet().image(getImage(), locX, locY);

        locX += dx;
        locY += dy;

        if ((locX + getImage().width > getPApplet().width) || (locX < 0)) {
            dx *= -1;
        }
        if ((locY + getImage().height > getY() + getHeight()) || (locY < getY())) {
            dy *= -1;
        }

        displayPanelName();
    }
} 