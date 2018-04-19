package projectmosaic_diegovega_melissaramirez_melvinastorga_2018;

import domain.MosaicImages;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import sun.java2d.pipe.DrawImage;

public class CanvasMosaic extends JPanel implements MouseListener {
    
    
    public CanvasMosaic(int row, int colum, BufferedImage[][] imagePieces, BufferedImage image, BufferedImage [][] screen) throws IOException {
        addMouseListener(this);
        this.setSize(image.getHeight(), image.getHeight());
        this.setVisible(true);
        this.repaint();
        this.imagePieces2 = imagePieces;
        BufferedImage imagePieceSize =imagePieces[0][0];
        this.img = imagePieceSize;
        this.imgtotal = image;
        this.rows = row;
        this.colums = colum;
        System.out.println(colums);
        matrix = new Rectangle2D[colum][colum];
        this.setSize(imgtotal.getWidth(), imgtotal.getHeight());
        this.temp=screen;
    }
    
    BufferedImage img = null;
    BufferedImage imgtemp = null;
    BufferedImage imgtotal = null;
    int rows;
    int colums;
    int cordenatesY=0;
    int cordenatesX=0;
    Rectangle2D[][] matrix = new Rectangle2D[colums][colums];

    BufferedImage[][] imagePieces2 = new BufferedImage[colums][colums];
    BufferedImage[][] temp = new BufferedImage[colums][colums];
    
    

    public void paint(Graphics g) {
       int counter = 0;
       counter++;
        System.out.println(counter);
        System.out.println(colums + "ll");

        g.toString();
        System.out.println(g.toString());
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.clearRect(0, 0, this.getWidth(), this.getHeight());
        drawMatrix(g2);

    }

//    public void drawPiece(Graphics2D g2){
//        
//        
//        
//    }
//    
    private void drawMatrix(Graphics2D g2) {
        
        g2.setColor(Color.black);
        int startX = 0;
        int startY = 0;
        int calculateY = img.getWidth();
        int calculateX = img.getHeight();
        int size = img.getHeight();
        
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {

            //caca   
                matrix[i][j] = new Rectangle2D.Double(startX,startY,img.getHeight(),img.getHeight());
                g2.draw(matrix[i][j]);
              
                 startX = startX + size;
                
            }
            startY = startY + size;
            startX = 0;

        }
      
        
        
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp.length; j++) {
            BufferedImage piece = this.temp[i][j];
            g2.drawImage(piece,cordenatesX,cordenatesY, img.getHeight(), img.getHeight(), this);
                }
            
            
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
        
 
           
        
        

        
        try {
           BufferedImage img2= ImageIO.read(new File("piece.jpg"));
            ImageIO.write(img2,"jpg", new File("2.jpg"));
            
            
        for(int i = 0; i < matrix.length; i++)
        for(int j =0; j < matrix.length; j++)
            if(matrix[i][j].contains(x, y)){
                
                this.temp[i][j]=img2;
                cordenatesX=(int)matrix[i][j].getMinX();
                cordenatesY=(int)matrix[i][j].getMinY(); 
                
                repaint();
            }
        
            
        } catch (IOException ex) {
            Logger.getLogger(CanvasMosaic.class.getName()).log(Level.SEVERE, null, ex);
        }
          
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        int m,n;
//       int x = e.getX();
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
