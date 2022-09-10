import processing.core.PImage;

public class BlendedPanel extends Panel {

    String blendedImageName;

    public BlendedPanel(int _x, int _y, int _w, int _h, int _identifier, String _imageName, String _blendedImageName) {
        super(_x, _y, _w, _h, _identifier, _imageName);
        createTimer(3000);
        blendedImageName = _blendedImageName;
        applyBlend();
    }

    public void onTimer() {
        reloadImage();
        applyBlend();
    }

    public void applyBlend() {
        int[] blends = {Main.app.ADD, Main.app.SUBTRACT, Main.app.DARKEST, Main.app.LIGHTEST, Main.app.DIFFERENCE, Main.app.EXCLUSION, Main.app.SCREEN, Main.app.OVERLAY, Main.app.HARD_LIGHT - Main.app.SCREEN, Main.app.SOFT_LIGHT, Main.app.DODGE, Main.app.BURN};
        String[] blendNames = {"Add", "Subtract", "Darkest", "Lightest", "Difference", "Exclusion", "Screen", "Overlaw", "Hard-Light - Screen", "Soft-Light", "Dodge", "Burn"};

        // generate random blend
        int blendIndex = randomInt(0, blends.length);
        int blend = blends[blendIndex];
        setPanelName("Blend: " + blendNames[blendIndex]);

        PImage blendImage = Main.app.loadImage(blendedImageName);

        getImage().blend(blendImage, 0, 0, blendImage.width, blendImage.height, 0, 0, getWidth(), getHeight(), blend);
    }
}