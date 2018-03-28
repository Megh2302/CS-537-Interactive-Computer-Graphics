import java.awt.*;
import java.awt.event.*;

public class clipTest implements KeyListener {

    extendedCanvas T;
    private clipper C;

    private void drawClipRegion( float llx, float lly, float urx, float ury )
    {
        float x[] = new float[4];
        float y[] = new float[4];

        x[0] = llx; y[0] = lly;
        x[1] = urx; y[1] = lly;
        x[2] = urx; y[2] = ury;
        x[3] = llx; y[3] = ury;

        T.printLoop( 4, x, y );
    }

    clipTest()
    {

        T = new extendedCanvas(300, 300);
        C = new clipper();

        T.setColor (0.0f, 0.0f, 0.0f);
        T.clear();
        
        float wx[] = new float [50];
        float wy[] = new float [50];
        int wl;

        T.setColor( 1.0f, 1.0f, 1.0f );
	drawClipRegion(  20, 100,  80, 150 );
        drawClipRegion(  110,  150,  170,  250 );
        drawClipRegion(  210,  150, 260,  250 );
       
        //entire figure inside region
        float pent1x[] = new float[5];
        float pent1y[] = new float[5];
        pent1x[0] = 30; pent1x[1] = 30; pent1x[2] = 50; pent1x[3] = 60; pent1x[4] = 50;
        pent1y[0] = 120; pent1y[1] = 140; pent1y[2] = 140; pent1y[3] = 130; pent1y[4] = 120;
        wl = 0;
        wl = C.clipPolygon( 5, pent1x, pent1y, wx, wy, 20, 100, 80, 150 );
        T.setColor ( 1.0f, 0.5f, 1.0f );        
        T.printLoop( 5, pent1x, pent1y );
        T.printPoly( wl, wx, wy );
        
        //two vertices outside w/r/t one edge
        float hex1x[] = new float[6];
        float hex1y[] = new float[6];
        hex1x[0] = 90; hex1x[1] = 90; hex1x[2] = 105; hex1x[3] = 120; hex1x[4] = 120; hex1x[5] = 105;
        hex1y[0] = 160; hex1y[1] = 180; hex1y[2] = 190; hex1y[3] = 180; hex1y[4] = 160; hex1y[5] = 151; 
        wl = 0;
        wl = C.clipPolygon( 6, hex1x, hex1y, wx, wy, 110,  150,  170,  250 );
        T.setColor ( 0.7f, 0.7f, 0.7f );        
        T.printLoop( 6, hex1x, hex1y );
        T.printPoly( wl, wx, wy );
        
        //one vertex outside with respect to one edge
        float tri1x[] = new float[3];
        float tri1y[] = new float[3];
        tri1x[0] =  195; tri1x[1] =  225; tri1x[2] =  225; 
        tri1y[0] = 195; tri1y[1] = 205; tri1y[2] =185 ; 
        wl = 0;
        wl = C.clipPolygon( 3, tri1x, tri1y, wx, wy, 210,  150, 260,  250 );
        T.setColor ( 0.7f, 0.7f, 0.7f );        
        T.printLoop( 3, tri1x, tri1y );
        T.printPoly( wl, wx, wy );
        
        //one vertex outside with respect to two edges
        float tri2x[] = new float[3];
        float tri2y[] = new float[3];
        tri2x[0] =  180; tri2x[1] =  215; tri2x[2] = 230 ; 
        tri2y[0] = 125; tri2y[1] = 170; tri2y[2] =155 ; 
        wl = 0;
        wl = C.clipPolygon( 3, tri2x, tri2y, wx, wy, 210,  150, 260,  250 );
        T.setColor ( 0.7f, 0.7f, 0.7f );        
        T.printLoop( 3, tri2x, tri2y );
        T.printPoly( wl, wx, wy );
        
        //entire figure outside region
        float quad2x[] = new float[4];
        float quad2y[] = new float[4];
        quad2x[0] =  70; quad2x[1] =  70; quad2x[2] =  30; quad2x[3] =  30;
        quad2y[0] = 160; quad2y[1] = 200; quad2y[2] = 200; quad2y[3] = 160;
        wl = 0;
        T.setColor ( 0.0f, 1.0f, 0.0f );        
        T.printLoop( 4, quad2x, quad2y );
        wl = C.clipPolygon( 4, quad2x, quad2y, wx, wy, 10, 110, 50, 150 );
        if( wl > 0 ) {
            T.printPoly( wl, wx, wy );
        }
        
        //two vertices outside w/r/t two edge
        float tri3x[] = new float[3];
        float tri3y[] = new float[3];
        tri3x[0] = 90 ; tri3x[1] =  140; tri3x[2] =  140; 
        tri3y[0] = 220; tri3y[1] = 270; tri3y[2] = 220; 
        wl = 0;
        wl = C.clipPolygon( 3, tri3x, tri3y, wx, wy, 110,  150,  170,  250 );
        T.setColor ( 0.7f, 0.7f, 0.7f );        
        T.printLoop( 3, tri3x, tri3y );
        T.printPoly( wl, wx, wy );
        
        //multiple vertices outside region
        float quad1x[] = new float[4];
        float quad1y[] = new float[4];
        quad1x[0] =  195; quad1x[1] = 195 ; quad1x[2] =  225; quad1x[3] = 225 ;
        quad1y[0] = 230; quad1y[1] = 265; quad1y[2] = 265; quad1y[3] = 230;
        wl = 0;
        wl = C.clipPolygon( 4, quad1x, quad1y, wx, wy, 210,  150, 260,  250 );
        T.setColor ( 0.7f, 0.7f, 0.7f );        
        T.printLoop( 4, quad1x,quad1y );
        T.printPoly( wl, wx, wy );
        
        //all vertices outside region
        float tri4x[] = new float[3];
        float tri4y[] = new float[3];
        tri4x[0] = 235 ; tri4x[1] =  270; tri4x[2] = 270 ; 
        tri4y[0] = 140; tri4y[1] = 175; tri4y[2] = 140; 
        wl = 0;
        wl = C.clipPolygon( 3, tri4x, tri4y, wx, wy, 210,  150, 260,  250 );
        T.setColor ( 0.7f, 0.7f, 0.7f );        
        T.printLoop( 3, tri4x, tri4y );
        T.printPoly( wl, wx, wy );       
    }

    // Because we are a KeyListener
    public void keyTyped(KeyEvent e)
    {
        // What key did we type?
        char key = e.getKeyChar();

        if( (key == 'Q') || (key == 'q') ) System.exit(0); // quit

    }
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    static public void main(String[] args)
    {
        clipTest C = new clipTest();
        C.T.addKeyListener( C );

        Frame f = new Frame( "Clipping Test" );
        f.add("Center", C.T);
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
