import processing.core.PApplet;

public class ScaleAndRotatePanel extends Panel {

    float scale;
    float dScale;

    float angle;
    double dAngle;

    boolean animate;

    //float rad;

    public ScaleAndRotatePanel(PApplet _pApplet, int _x, int _y, int _w, int _h, int _identifier) {
        super(_pApplet, _x, _y, _w, _h, _identifier);

        scale = 1;
        dScale = (float) -0.01;
        angle = 0;
        dAngle = randomDouble(0.005, 0.02);
        animate = true;
        //rad = 0;

        if (_x == 0) {
            setPanelName("Scale & Rotate (Click to stop animation!) (Xform) ");
        }
    }

    public void handleMouseClicked(int _x, int _y) {
        // toggle rotation if mouse click is inside panel
        int localMouseX = _x - getX();
        int localMouseY = _y - getY();
        boolean insidePanel = localMouseY >= 0 && localMouseY < getHeight() && localMouseX >= 0 && localMouseX < getWidth();
        if (insidePanel) {
            animate = !animate;
        }
    }

    public void display() {
        getPApplet().pushMatrix();
        getPApplet().translate(getX() + getWidth()/2, getY() + getHeight()/2); // translate to center of panel
        getPApplet().rotate(angle);
        getPApplet().scale(scale);
        getPApplet().translate(-getWidth()/2, -getHeight()/2);  // translate so that center of image matches center of rotation
        getPApplet().image(getImage(), 0, 0, getWidth(), getHeight());
        getPApplet().popMatrix();

        //Class solution:
        //imageMode(CENTER);
        //int x = getX(); // saving original coordinates
        //int y = getY();
        //pushMatrix(); // saving original coordinate system
        //translate(x + getWidth()/2, y + getHeight()/2); // moving origin to center of image
        //rotate(rad);
        //setX(0); // setting the center of the image to (0, 0)
        //setY(0);
        //scale(scale);
        //super.display();
        //popMatrix();
        //rad += PI / 8;
        //imageMode(CORNER); // restore original values
        //setX(x);
        //setY(y);

        displayPanelName();

        if (scale <= 0.1 || scale > 1) {
            dScale *= -1;
        }

        if (animate) {
            angle += dAngle;
            scale += dScale;
        }
    }
} 