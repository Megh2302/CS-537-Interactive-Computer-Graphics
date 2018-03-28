import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.Timer;
public class midterm extends JPanel implements KeyListener, MouseListener, ActionListener
{
    Timer tm = new Timer(5,this);
    int x,velX=2;
    cgfunctions C;
    int displaycount = 1;
    int triangle,square,star,triangle1,triangle2,triangle3,square1,square2,square3,M,E,G,H1;
    static int W = 700;
    static int H = 700;  
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        x = x+ velX;
        repaint();
    }
    public static void sleep(int time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch(Exception e){}
    }
    midterm (int w, int h)
    {
        C = new cgfunctions (w, h); 
        //adding of all Polygon
        float x[] = new float [15];
        float y[] = new float [15];
        
        /*All Tringle*/
        x[0] = 10.0f; y[0] = 75.0f;
        x[1] = 75.0f; y[1] = 10.0f;
        x[2] = 120.0f; y[2] = 120.0f;
        triangle = C.addPoly (x, y, 3);
        
        x[0] = 10.0f; y[0] = 625.0f;
        x[1] = 75.0f; y[1] = 690.0f;
        x[2] = 120.0f; y[2] = 580.0f;
        triangle1 = C.addPoly (x, y, 3);

        x[0] = 690.0f; y[0] = 75.0f;
        x[1] = 625.0f; y[1] = 10.0f;
        x[2] = 580.0f; y[2] = 120.0f;
        triangle2 = C.addPoly (x, y, 3);
        
        x[0] = 690.0f; y[0] = 625.0f;
        x[1] = 625.0f; y[1] = 690.0f;
        x[2] = 580.0f; y[2] = 580.0f;
        triangle3 = C.addPoly (x, y, 3);
        
        /*All Square*/
        x[0] = 10.0f; y[0] = 400.0f;
        x[1] = 110.0f; y[1] = 400.0f;
        x[2] = 110.0f; y[2] = 300.0f;
        x[3] = 10.0f; y[3] = 300.0f;
        square = C.addPoly (x, y, 4);
        
        x[0] = 300.0f; y[0] = 10.0f;
        x[1] = 300.0f; y[1] = 110.0f;
        x[2] = 400.0f; y[2] = 110.0f;
        x[3] = 400.0f; y[3] = 10.0f;
        square1 = C.addPoly (x, y, 4);
        
        x[0] = 300.0f; y[0] = 690.0f;
        x[1] = 300.0f; y[1] = 590.0f;
        x[2] = 400.0f; y[2] = 590.0f;
        x[3] = 400.0f; y[3] = 690.0f;
        square2 = C.addPoly (x, y, 4);
        
        x[0] = 690.0f; y[0] = 300.0f;
        x[1] = 690.0f; y[1] = 400.0f;
        x[2] = 590.0f; y[2] = 400.0f;
        x[3] = 590.0f; y[3] = 300.0f;
        square3 = C.addPoly (x, y, 4);
        
        /*MEGH*/
        x[0] = 200.0f; y[0] = 350.0f;
        x[1] = 200.0f; y[1] = 450.0f;
        x[2] = 275.0f; y[2] = 410.0f;
        x[3] = 350.0f; y[3] = 450.0f;
        x[4] = 350.0f; y[4] = 350.0f;
        x[5] = 330.0f; y[5] = 350.0f;
        x[6] = 330.0f; y[6] = 420.0f;
        x[7] = 275.0f; y[7] = 390.0f;
        x[8] = 220.0f; y[8] = 420.0f;
        x[9] = 220.0f; y[9] = 350.0f;
        M = C.addPoly (x, y, 10); 
        
        x[0] = 360.0f; y[0] = 450.0f;
        x[1] = 510.0f; y[1] = 450.0f;
        x[2] = 510.0f; y[2] = 430.0f;
        x[3] = 380.0f; y[3] = 430.0f;
        x[4] = 380.0f; y[4] = 410.0f;
        x[5] = 510.0f; y[5] = 410.0f;
        x[6] = 510.0f; y[6] = 390.0f;
        x[7] = 380.0f; y[7] = 390.0f;
        x[8] = 380.0f; y[8] = 370.0f;
        x[9] = 510.0f; y[9] = 370.0f;
        x[10] = 510.0f; y[10] = 350.0f;
        x[11] = 360.0f; y[11] = 350.0f;
        E = C.addPoly (x, y, 12);
        
        x[0] = 200.0f; y[0] = 340.0f;
        x[1] = 350.0f; y[1] = 340.0f;
        x[2] = 350.0f; y[2] = 320.0f;
        x[3] = 220.0f; y[3] = 320.0f;
        x[4] = 220.0f; y[4] = 260.0f;
        x[5] = 330.0f; y[5] = 260.0f;
        x[6] = 330.0f; y[6] = 280.0f;
        x[7] = 290.0f; y[7] = 280.0f;
        x[8] = 290.0f; y[8] = 300.0f;
        x[9] = 350.0f; y[9] = 300.0f;
        x[10] = 350.0f; y[10] = 240.0f;
        x[11] = 200.0f; y[11] = 240.0f;
        G = C.addPoly (x, y, 12); 
        
        x[0] = 360.0f; y[0] = 340.0f;
        x[1] = 380.0f; y[1] = 340.0f;
        x[2] = 380.0f; y[2] = 300.0f;
        x[3] = 490.0f; y[3] = 300.0f;
        x[4] = 490.0f; y[4] = 340.0f;
        x[5] = 510.0f; y[5] = 340.0f;
        x[6] = 510.0f; y[6] = 240.0f;
        x[7] = 490.0f; y[7] = 240.0f;
        x[8] = 490.0f; y[8] = 280.0f;
        x[9] = 380.0f; y[9] = 280.0f;
        x[10] = 380.0f; y[10] = 240.0f;
        x[11] = 360.0f; y[11] = 240.0f;
        H1 = C.addPoly (x, y, 12);    
    }   
    public void keyTyped(KeyEvent e)
    {  
        char key = e.getKeyChar();

        if ((key == 'C') || (key == 'c')) displaycount = 2; 
        if ((key == 'P') || (key == 'p')) displaycount = 1; 
        if ((key == 'T') || (key == 't')) displaycount = 3; 
        if ((key == 'V') || (key == 'v')) displaycount = 0; 
        if ((key == 'Q') || (key == 'q')) System.exit(0); 
        Draw();
    }
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void mouseClicked(MouseEvent e)
    {
        displaycount++;
        Draw();
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
    //adding the components 
    private void Normalpoly( ) 
    {   
        C.Transformation_clear();
        C.setColor(0.0f, 1.0f, 0.0f);
        C.drawPoly (triangle);
        
        C.Transformation_clear();
        C.setColor( 0.0f, 0.0f, 1.0f );
        C.drawPoly (triangle1);
        
        C.Transformation_clear();
        C.setColor( 0.0f, 0.0f, 1.0f );
        C.drawPoly (triangle2);
        
        C.Transformation_clear();
        C.setColor( 0.0f, 1.0f, 0.0f );
        C.drawPoly (triangle3);
       
        C.Transformation_clear();
        C.setColor( 1.0f, 0.0f, 0.0f );
        C.drawPoly (square);
        
        C.Transformation_clear();
        C.setColor( 1.0f, 1.0f, 1.0f );
        C.drawPoly (square1);

        C.Transformation_clear();
        C.setColor( 1.0f, 1.0f, 1.0f );
        C.drawPoly (square2);

        C.Transformation_clear();
        C.setColor( 1.0f, 0.0f, 0.0f );
        C.drawPoly (square3);
         
        C.Transformation_clear();
        C.setColor( 1.0f, 0.0f, 1.0f );
        C.drawPoly (M);
        
        C.Transformation_clear();
        C.setColor( 1.0f, 0.0f, 1.0f );
        C.drawPoly (E);
        
        C.Transformation_clear();
        C.setColor( 1.0f, 0.0f, 1.0f );
        C.drawPoly (G);
        
        C.Transformation_clear();
        C.setColor( 1.0f, 0.0f, 1.0f );
        C.drawPoly (H1);
    } 
    
    //Function that perform transformation
    private void Transform( ) 
    {
        //Translation of Tringle at the center of the window
        C.Transformation_clear();
        for(int i=0;i<10;i++)
        {
        C.translate (23.0f, 23.0f); 
        }
        C.setColor( 0.0f, 1.0f, 0.0f );
        C.drawPoly (triangle);
                  
        C.Transformation_clear();
        C.translate (230.0f, -230.0f);
        C.setColor( 0.0f, 0.0f, 1.0f );
        C.drawPoly (triangle1);
             
        C.Transformation_clear();
        C.translate (-230.0f, 230.0f);
        C.setColor( 0.0f, 0.0f, 1.0f );
        C.drawPoly (triangle2);
        
        C.Transformation_clear();
        C.translate (-230.0f, -230.0f);
        C.setColor( 0.0f, 1.0f, 0.0f );
        C.drawPoly (triangle3);
        
       // Roatation and translation of Squares
        C.Transformation_clear();
        C.rotate (-43f);
        C.translate (68.0f, -5.0f);    
        C.setColor( 1.0f, 0.0f, 0.0f );
        C.drawPoly (square);
       
        C.Transformation_clear();
        C.rotate (43f);
        C.translate (122.0f, -200.0f);    
        C.setColor( 1.0f, 0.0f, 0.0f );
        C.drawPoly (square3);
       
        C.Transformation_clear();
        C.rotate (43.0f);
        C.translate (-5.0f, 68.0f);    
        C.setColor( 1.0f, 1.0f, 1.0f );
        C.drawPoly (square1);
       
        C.Transformation_clear();
        C.rotate (-43.0f);
        C.translate (-200.0f, 122.0f);    
        C.setColor( 1.0f, 1.0f, 1.0f );
        C.drawPoly (square2);
        
        /*Scaling and translation of letters in MEGH*/
        C.Transformation_clear();
        C.scale(1.2f, 1.6f);
        C.translate (-235.0f, -28.0f); 
        C.setColor( 0.6f, 0.0f, 0.6f );
        C.drawPoly (M);
        
        C.Transformation_clear();
        C.scale(1.2f, 1.4f);
        C.translate (82.0f, 61.0f); 
        C.setColor( 0.6f, 0.0f, 0.6f );
        C.drawPoly (E);
        
        C.Transformation_clear();
        C.scale(1.2f, 1.5f);
        C.translate (-235.0f, -350.0f); 
        C.setColor( 0.6f, 0.0f, 0.6f );
        C.drawPoly (G);
        
        C.Transformation_clear();
        C.scale(1.2f, 1.5f);
        C.translate (82.0f, -350.0f); 
        C.setColor( 0.6f, 0.0f, 0.6f );
        C.drawPoly (H1);    
    }
    
    //Function that shows the output after every Mouse click
    public void Draw()
    {
        C.setColor ( 0.0f, 0.0f, 0.2f );
        C.clear();
        switch (displaycount % 6)
        {
            case 1:
                C.Clipwindow( 0.0f, 700.0f,0.0f, 700.0f ); //clipping
                C.Viewport( 0, 0, W, H );                  // Viewport
                Normalpoly();                              //Draw Polygon
                break;         
            case 2:
                C.Clipwindow( 0.0f, 700.0f, 0.0f, 700.0f );
                C.Viewport( 0, 0, W, H );
                Transform();
                break;
            case 3:
                C.Clipwindow( 0.0f, 700.0f, 0.0f, 700.0f );
                C.Viewport( 0, 0, W, H );
                Normalpoly();
                break;
            case 4:
                C.Clipwindow( 0.0f, 700.0f, 0.0f, 700.0f );
                C.Viewport( 0, 0, W, H);
                Transform();
                break;   
            case 5:
                C.Clipwindow( 0.0f, 700.0f, 0.0f, 700.0f );
                int w = W /4;
                int h = H / 4;
                int x = 0,y=0;
                int i,j;
                for (i=4; i > 0; i--)
                {
                    C.Viewport (x, y, w, h);
                    Transform();
                    x+= w;
                    y+= h;
                }   break;
            default:
                C.Clipwindow( 250.0f, 500.0f, 250.0f, 500.0f );
                C.Viewport( 0, 0, W, H );
                Normalpoly();
                break;
        }
        C.repaint();
    }
    static public void main(String[] args)
    {
        midterm M = new midterm (W, H);
        M.C.addKeyListener (M);
        M.C.addMouseListener (M);
        M.Draw();
        Frame f = new Frame("2D-Pipeline Output" );
        f.add("Center", M.C);
        f.pack();
        f.setResizable (false);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }   
}