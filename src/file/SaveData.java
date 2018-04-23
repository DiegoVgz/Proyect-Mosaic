
package file;

import java.awt.image.BufferedImage;

public class SaveData implements java.io.Serializable{
    
    public BufferedImage [][] savedMosaicPieces;

    public SaveData(BufferedImage[][] savedMosaicPieces, BufferedImage saveImage, int colums, int rows) {
        this.savedMosaicPieces = savedMosaicPieces;
        this.saveImage = saveImage;
        this.colums = colums;
        this.rows = rows;
    }
    public BufferedImage saveImage;
    int colums;
    int rows;
}
