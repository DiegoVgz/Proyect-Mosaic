
package domain;

import java.awt.image.BufferedImage;

public class MosaicImages {

    BufferedImage imageToPaint;

    public MosaicImages(BufferedImage imageToPaint) {
        this.imageToPaint = imageToPaint;
    }
    public MosaicImages() {
        this.imageToPaint = imageToPaint;
    }

    public BufferedImage getImageToPaint() {
        return imageToPaint;
    }

    public void setImageToPaint(BufferedImage imageToPaint) {
        this.imageToPaint = imageToPaint;
    }
    
    
}
