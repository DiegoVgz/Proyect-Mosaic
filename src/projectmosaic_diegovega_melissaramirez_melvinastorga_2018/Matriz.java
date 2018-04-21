package projectmosaic_diegovega_melissaramirez_melvinastorga_2018;

import com.sun.glass.ui.Screen;
import com.sun.media.sound.SoftEnvelopeGenerator;
import domain.MosaicImages;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
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
import javax.swing.JPanel;

public class Matriz extends JPanel implements MouseListener {
//hola mundo
    public Matriz(int row, int colum, BufferedImage[][] imagePieces, BufferedImage image) {
        addMouseListener(this);
        this.setSize(image.getHeight(), image.getHeight());
        this.imgtotal = image;
        
        this.setVisible(true);
        this.imagePieces2 = imagePieces;
        this.img = imagePieces[0][0];
        this.imgtotal = image;
        this.rows = row;
        this.colums = colum;
        System.out.println(colums);
        matrix = new Rectangle2D[colum][colum];
        this.setSize(imgtotal.getWidth(), imgtotal.getHeight());

    }
    
    
    int rows;
    int colums;
    Rectangle2D[][] matrix = new Rectangle2D[colums][colums];
    BufferedImage[][] imagePieces2 = new BufferedImage[colums][colums];
    BufferedImage img = null;
    BufferedImage imgtotal = null;

    public void paint(Graphics g) {
//        System.out.println(colums + "ll");

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

        int size = img.getHeight();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {

                
                matrix[i][j] = new Rectangle2D.Double(startX,startY,img.getHeight(),img.getHeight());
                g2.draw(matrix[i][j]);
                BufferedImage piece = imagePieces2[i][j];
                g2.drawImage(piece, startY, startX, img.getHeight(), img.getHeight(), this);
                startX = startX + size;
                

            }
            startY = startY + size;
            startX = 0;

        }

        int calculateY = img.getWidth();
        int calculateX = img.getHeight();

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
       
        SaveAndLoadImage sv = new SaveAndLoadImage();
        int x = e.getX();
        int y = e.getY();
        
        MosaicImages m = new MosaicImages();
        
        m.setImageToPaint(img);

        for(int i = 0; i < matrix.length; i++)
        for(int j =0; j < matrix.length; j++)
            if(matrix[i][j].contains(x, y)){
                
                BufferedImage pasteImage= imagePieces2[j][i];
            try {
                ImageIO.write(pasteImage, "jpg", new File("piece.jpg"));
            } catch (IOException ex) {
                Logger.getLogger(Matriz.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        int m,n;
//        int x = e.getX();
//        int y = e.getY();
//        for(int i = 0; i < matrix.length; i++)
//        for(int j =0; j < matrix.length; j++)
//            if(matrix[i][j].contains(x, y)){
//                boja = Color.blue;
//                a = i;
//                b = j;
//                repaint();
//            }
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
