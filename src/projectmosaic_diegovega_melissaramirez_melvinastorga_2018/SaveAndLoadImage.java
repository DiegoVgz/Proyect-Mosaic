package projectmosaic_diegovega_melissaramirez_melvinastorga_2018;


import java.awt.AWTException;
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

    public BufferedImage[][] mosaicFrame(BufferedImage imgFile, int rows, int columns) throws IOException {
        int smallWidth = imgFile.getWidth()/rows ;
        int smallHeight = imgFile.getHeight()/columns;

        BufferedImage[][] smallImages = new BufferedImage[rows][columns];

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

        File selected = null;
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "pgn");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {

            selected = file.getSelectedFile();
            String path = selected.getAbsolutePath();

            BufferedImage bfimg = ImageIO.read(selected);

            if (bfimg.getHeight() > 800 && bfimg.getWidth() > 800) {

                bfimg = null;
            } else {
                return bfimg;
            }
        } else if (result == JFileChooser.CANCEL_OPTION) {

            System.out.println("No file selected");
        }

        return bf_img;
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

    public ArrayList pieceTOtranfer(BufferedImage img){
        ArrayList pieceTocPaste = new ArrayList();
        BufferedImage img2 = img;
        pieceTocPaste.addAll(0, pieceTocPaste);
        return pieceTocPaste;
    }
    
}
