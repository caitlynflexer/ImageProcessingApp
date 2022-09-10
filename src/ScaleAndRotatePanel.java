public class ScaleAndRotatePanel extends Panel implements Clickable {

    float scale;
    float dScale;

    float angle;
    double dAngle;

    boolean animate;

    //float rad;

    public ScaleAndRotatePanel(int _x, int _y, int _w, int _h, int _identifier, String _imageName) {
        super(_x, _y, _w, _h, _identifier, _imageName);

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
        Main.app.pushMatrix();
        Main.app.translate(getX() + getWidth()/2, getY() + getHeight()/2); // translate to center of panel
        Main.app.rotate(angle);
        Main.app.scale(scale);
        Main.app.translate(-getWidth()/2, -getHeight()/2);  // translate so that center of image matches center of rotation
        Main.app.image(getImage(), 0, 0, getWidth(), getHeight());
        Main.app.popMatrix();

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