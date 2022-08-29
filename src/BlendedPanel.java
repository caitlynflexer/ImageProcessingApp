import processing.core.PApplet;
import processing.core.PImage;

public class BlendedPanel extends Panel {

    String blendedImageName;

    public BlendedPanel(PApplet _pApplet, int _x, int _y, int _w, int _h, int _identifier, String _blendedImageName) {
        super(_pApplet, _x, _y, _w, _h, _identifier);
        createTimer(3000);
        blendedImageName = _blendedImageName;
    }

    public void setupImage() {
        super.setupImage();
        applyBlend();
    }

    public void onTimer() {
        setupImage();
    }

    public void applyBlend() {
        int[] blends = {getPApplet().ADD, getPApplet().SUBTRACT, getPApplet().DARKEST, getPApplet().LIGHTEST, getPApplet().DIFFERENCE, getPApplet().EXCLUSION, getPApplet().SCREEN, getPApplet().OVERLAY, getPApplet().HARD_LIGHT - getPApplet().SCREEN, getPApplet().SOFT_LIGHT, getPApplet().DODGE, getPApplet().BURN};
        String[] blendNames = {"Add", "Subtract", "Darkest", "Lightest", "Difference", "Exclusion", "Screen", "Overlaw", "Hard-Light - Screen", "Soft-Light", "Dodge", "Burn"};

        // generate random blend
        int blendIndex = randomInt(0, blends.length);
        int blend = blends[blendIndex];
        setPanelName("Blend: " + blendNames[blendIndex]);

        PImage blendImage = getPApplet().loadImage(blendedImageName);

        getImage().blend(blendImage, 0, 0, blendImage.width, blendImage.height, 0, 0, getWidth(), getHeight(), blend);
    }
}