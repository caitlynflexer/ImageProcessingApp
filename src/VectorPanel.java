public class VectorPanel extends Panel {

    int locX;
    int locY;
    int dx;
    int dy;
    float scaleImage;

    public VectorPanel(int _x, int _y, int _w, int _h, int _identifier, String _imageName, float _scaleImage) {
        super(_x, _y, _w, _h, _identifier, _imageName);
        locX = _x;
        locY = _y;
        dx = randomInt(1, 7);
        dy = randomInt(1, 7);
        scaleImage = _scaleImage;

        if (_x == 0) {
            setPanelName("Vector");
        }

        resize();
    }

    public void resize() {
        getImage().resize((int)(scaleImage * getWidth()), (int)(scaleImage * getHeight()));
    }

    public void display() {

        Main.app.image(getImage(), locX, locY);

        locX += dx;
        locY += dy;

        if ((locX + getImage().width > Main.app.width) || (locX < 0)) {
            dx *= -1;
        }
        if ((locY + getImage().height > getY() + getHeight()) || (locY < getY())) {
            dy *= -1;
        }

        displayPanelName();
    }
} 