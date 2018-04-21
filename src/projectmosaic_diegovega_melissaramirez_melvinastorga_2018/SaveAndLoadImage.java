package projectmosaic_diegovega_melissaramirez_melvinastorga_2018;


import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveAndLoadImage {

    BufferedImage bf_img;
    BufferedImage[][] lostOfpieces;
    
    public BufferedImage[][] mosaicFrame(BufferedImage imgFile, int rows, int columns) throws IOException {
       
        int smallWidth = (int)imgFile.getWidth()/rows ;
        int smallHeight = (int)imgFile.getHeight()/columns;

       BufferedImage[][]  smallImages = new BufferedImage[rows][columns];

        int count = 0;
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                smallImages[x][y] = imgFile.getSubimage(x *smallWidth, y
                        * smallHeight, smallWidth, smallHeight);
//                try {
//                    ImageIO.write(smallImages[x][y], "png", new File("tile-"
//                            + (count++) + ".png"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            }

        }
        return smallImages;
    }

    public BufferedImage ImageChooser() throws IOException {

         BufferedImage bfimg =null;
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif","PNG", "png","jpeg","JPEG","pgn");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            
            File selected = file.getSelectedFile();
            String path = selected.getAbsolutePath();

            bfimg = ImageIO.read(selected);
                return bfimg;
        } else if (result == JFileChooser.CANCEL_OPTION) {

            System.out.println("No file selected");
        }

        return bfimg;
    }

    public void saveImage(JLabel im) throws IOException, AWTException {

        JFileChooser fl_chooser = new JFileChooser();
        if (fl_chooser.showSaveDialog(null) == fl_chooser.APPROVE_OPTION) {

            File fl = fl_chooser.getSelectedFile();

            bf_img = new Robot().createScreenCapture(im.bounds());

            try {
                ImageIO.write(bf_img, "jpg", fl);

            } catch (IOException ioe) {

                ioe.printStackTrace();

            }

        }

    }
    
    
    public  BufferedImage resize(int newW, int newH) throws IOException {
        BufferedImage imageSlected= ImageChooser();
        
        int w = imageSlected.getWidth();
        int h = imageSlected.getHeight();
        BufferedImage bufim = new BufferedImage(newW, newH, imageSlected.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(imageSlected, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return bufim;
    }

    public static void  pieceTOtranfer( BufferedImage piece, BufferedImage[][] tempint, int positionX,int positionY){    
      tempint[positionX][positionY] = piece;
    }
    
}
