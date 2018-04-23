package projectmosaic_diegovega_melissaramirez_melvinastorga_2018;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class CanvasMosaic extends JPanel implements MouseListener {

    public CanvasMosaic(int row, int colum, BufferedImage[][] imagePieces, BufferedImage image, BufferedImage[][] screen) throws IOException {
        addMouseListener(this);
        this.setSize(image.getHeight(), image.getHeight());
        this.setVisible(true);
        this.repaint();
        this.imagePieces2 = imagePieces;
        BufferedImage imagePieceSize = imagePieces[0][0];
        this.img = imagePieceSize;
        this.imgtotal = image;
        this.rows = row;
        this.colums = colum;
        matrix = new Rectangle2D[colum][colum];
        this.setSize(imgtotal.getWidth(), imgtotal.getHeight());
        this.temp = screen;

     
    }

    BufferedImage img = null;
    BufferedImage imgtemp = null;
    BufferedImage imgtotal = null;
    static int rows;
    static int colums;
    int cordenatesY = 0;
    int cordenatesX = 0;
    int x_position=0;
    int y_position=0;
    Rectangle2D[][] matrix = new Rectangle2D[colums][colums];

    static BufferedImage[][] imagePieces2 = new BufferedImage[colums][colums];
    static BufferedImage[][] temp = new BufferedImage[colums][colums];
    static BufferedImage[][] temp2 = new BufferedImage[colums][colums];
   
   
    
    public void paintComponent(Graphics g) {
        
        int counter = 0;
        counter++;
        g.toString();
        System.out.println(g.toString());
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.clearRect(0, 0, this.getWidth(), this.getHeight());
        drawMatrix(g2);

    }

    
    private void drawMatrix(Graphics2D g2) {
        
        g2.setColor(Color.black);
        int startX = 0;
        int startY = 0;
        int calculateY = img.getWidth();
        int calculateX = img.getHeight();
        int size = img.getHeight();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {

                matrix[i][j] = new Rectangle2D.Double(startX, startY, img.getHeight(), img.getHeight());
                g2.draw(matrix[i][j]);
                BufferedImage imt =temp[i][j];
                g2.drawImage(imt, startX, startY, img.getHeight(), img.getHeight(), this);
                startX = startX + size;

            }
            startY = startY + size;
            startX = 0;

        }

        
                if (temp[x_position][y_position] != null) {
                    BufferedImage piece = this.temp[x_position][y_position];
                    g2.drawImage(piece, cordenatesX, cordenatesY, img.getHeight(), img.getHeight(), this);
                    
                }
            

        for (int i = 0; i < imgtotal.getWidth(); i++) {
            for (int j = 0; j < imgtotal.getHeight(); j++) {

                if (calculateX <= j) {

                    g2.drawLine(j, 0, j, getHeight());
                    calculateX = calculateX + img.getHeight();
                }
            }

            if (calculateY <= i) {

                g2.drawLine(0, i, getWidth(), i);
                calculateY = calculateY + img.getWidth();
            }
        }

    }
 
 
 
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println(x+"x"+y+"y");
        SaveAndLoadImage sv = new SaveAndLoadImage();

        try {
            BufferedImage img2 = ImageIO.read(new File("piece.jpg"));
            ImageIO.write(img2, "jpg", new File("2.jpg"));

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][j].contains(x, y)) {

                        sv.pieceTOtranfer(img2, temp, i, j);
                        x_position=i;
                        y_position=j;
                        cordenatesX = (int) matrix[i][j].getMinX();
                        cordenatesY = (int) matrix[i][j].getMinY();
                      x=0;
                      y=0; 
                      img2=null;
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(CanvasMosaic.class.getName()).log(Level.SEVERE, null, ex);
        }
        repaint();
        
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    
    
}
