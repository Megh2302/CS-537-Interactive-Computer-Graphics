//
//  Rasterizer.java
//
// Created by Srinivas Sridharan on 2/10/17.
// Copyright 2017 Stevens Institute of Technology. All rights reserved.
//
//  Contributor:  MEGH VANKAWALA
//

///
// 
// This is a class that performas rasterization algorithms
//
///

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

public class Rasterizer implements KeyListener
{
    
   ///
    // number of scanlines
    ///

    int n_scanlines;
    
    ///
    // Constructor
    //
    // @param n - number of scanlines
    //
    ///

    Rasterizer (int n)
    {
        n_scanlines = n;
    }
    
    ///
    // Draw a filled polygon in the simpleCanvas C.
    //
    // The polygon has n distinct vertices. The 
    // coordinates of the vertices making up the polygon are stored in the 
    // x and y arrays.  The ith vertex will have coordinate  (x[i], y[i])
    //
    // You are to add the implementation here using only calls
    // to C.setPixel()
    ///

    public void drawPolygon(int n, int x[], int y[], simpleCanvas C)
    {
        int ymax, ymin;
        ymax = y[0];
        ymin = y[0];
        int i;
        for(i= 0; i < n; i++)
        {
            if(ymin > y[i])
            {    
                ymin = y[i];
            }
            if(ymax < y[i])
            {
                ymax = y[i];
            }       
        }
        ArrayList<edge> AEL = new ArrayList<edge>();       
        for(i = ymin; i < ymax ; i++)
        {   
           ArrayList<edge> edge = new ArrayList<edge>();
            for(edge e: AEL)
            {
                if(e.ymax() != i)
                {
                    edge.add(e);
                }
            AEL = edge;
            }
        HashMap<Integer,ArrayList<edge>> ET = edge_table(n,x,y);            
        if(ET.containsKey(i))
        {
            AEL.addAll(ET.get(i));
            Collections.sort(AEL);
        }
        for(int j = 0; j < edge.size();j +=2)
        {
            int x0 = edge.get(j).x();
            int x1 = edge.get(j+1).x();
            int m;
            for( m = x0; m < x1; m++)
            {
                C.setPixel(m, i);
            }
        }

        // Adjust the x and the sum 
        for(edge e: edge)
        {
            e.adjust();
        }
        }
    } 
      // making of edge table
    
    
    public HashMap<Integer, ArrayList<edge>> edge_table(int n,int x[],int y[])
    {
        
        HashMap<Integer, ArrayList<edge>> ET = new HashMap<Integer, ArrayList<edge>>();
        int i;        
        for(i = 0; i < n-1; i++)
        {
            adedge(ET,x[i], y[i], x[i+1], y[i+1]);
        }
        adedge(ET, x[0], y[0], x[n-1], y[n-1]);
   
        return ET;
    }
     //Adding an edge to the edge table
     
    public void adedge(HashMap<Integer,ArrayList<edge>> ET,int x0, int y0, int x1, int y1)
    {
        edge e = new edge();        
        int sl = e.createedge(x0, y0, x1, y1);
        if(e.dy() != 0)
        { 
            if(ET.containsKey(sl))
            {
                ET.get(sl).add(e);
            }
            else 
            {
                ET.put(sl, new ArrayList<edge>());
                ET.get(sl).add(e);
            }
        }
    }    

    private simpleCanvas T;
    private Rasterizer R;

    private Rasterizer()
    {
        
        T = new simpleCanvas( 901, 601 );
        R = new Rasterizer( 601 );

        T.setColor( 0.0f, 0.0f, 0.0f );
        T.clear();
        T.setColor( 1.0f, 0.0f, 0.0f );

        int x[] = new int[10];
        int y[] = new int[10];

        // ########## BORDERS ###############
        // SQUARE BOTTOM LEFT
        x[0] = 0;    y[0] = 0;
        x[1] = 0;    y[1] = 20;
        x[2] = 20;   y[2] = 20;
        x[3] = 20;   y[3] = 0;
        T.setColor( 0.0f, 0.0f, 1.0f );
        R.drawPolygon( 4, x, y, T );
    
        x[0] = 0;    y[0] = 10;
        x[1] = 10;   y[1] = 10;
        x[2] = 10;   y[2] = 580;
        x[3] = 0;    y[3] = 580;
        T.setColor( 0.0f, 0.1f, 0.9f );
        R.drawPolygon( 4, x, y, T );
    
        x[0] = 0;    y[0] = 580;
        x[1] = 0;    y[1] = 600;
        x[2] = 20;   y[2] = 600;
        x[3] = 20;   y[3] = 580;
        T.setColor( 0.0f, 0.2f, 0.8f );
        R.drawPolygon( 4, x, y, T );
    
        //  TRIANGLE TOP:TOP
        x[0] = 10;   y[0] = 590;
        x[1] = 10;   y[1] = 600;
        x[2] = 880;  y[2] = 600;
        T.setColor( 0.0f, 0.3f, 0.7f );
        R.drawPolygon( 3, x, y, T );
    
        //  TRIANGLE TOP:BOTTOM
        x[0] = 10;   y[0] = 590;
        x[1] = 880;  y[1] = 590;
        x[2] = 880;  y[2] = 600;
        T.setColor( 0.0f, 0.4f, 0.6f );
        R.drawPolygon( 3, x, y, T );
    
        // SQUARE TOP RIGHT
        x[0] = 900;  y[0] = 600;
        x[1] = 900;  y[1] = 580;
        x[2] = 880;  y[2] = 580;
        x[3] = 880;  y[3] = 600;
        T.setColor( 0.1f, 0.4f, 0.5f );
        R.drawPolygon( 4, x, y, T );
    
        //  TRIANGLE RIGHT:RIGHT
        x[0] = 890;  y[0] = 580;
        x[1] = 900;  y[1] = 580;
        x[2] = 890;  y[2] = 20;
        T.setColor( 0.2f, 0.4f, 0.4f );
        R.drawPolygon( 3, x, y, T );
    
        //  TRIANGLE RIGHT:LEFT
        x[0] = 900;  y[0] = 580;
        x[1] = 900;  y[1] = 20;
        x[2] = 890;  y[2] = 20;
        T.setColor( 0.3f, 0.4f, 0.3f );
        R.drawPolygon( 3, x, y, T );
    
        // SQUARE BOTTOM RIGHT
        x[0] = 900;  y[0] = 0;
        x[1] = 900;  y[1] = 20;
        x[2] = 880;  y[2] = 20;
        x[3] = 880;  y[3] = 0;
        T.setColor( 0.4f, 0.4f, 0.2f );
        R.drawPolygon( 4, x, y, T );
    
        // QUAD BOTTOM
        x[0] = 20;   y[0] = 0;
        x[1] = 20;   y[1] = 10;
        x[2] = 880;  y[2] = 10;
        x[3] = 880;  y[3] = 0;
        T.setColor( 0.4f, 0.5f, 0.1f );
        R.drawPolygon( 4, x, y, T );
       
        // convex polygon- rectangle
        x[0] = 100;y[0] = 80;
        x[1] = 25;y[1] = 80;
        x[2] = 25;y[2] = 25;
        x[3] = 100;y[3] = 25;
        T.setColor( 0.0f, 0.0f, 0.5f );
        R.drawPolygon( 4, x, y, T );
        
        // isoscale tringle
        x[0] = 150;y[0] = 110;
        x[1] = 100;y[1] = 25;
        x[2] = 200;y[2] = 25;
        T.setColor( 0.5f, 0.0f, 0.0f );
        R.drawPolygon( 3, x, y, T );
        
        // Right Tringle
        x[0] = 210;y[0] = 100;
        x[1] = 210;y[1] = 25;
        x[2] = 300;y[2] = 25;
        T.setColor( 0.3f, 0.4f, 0.3f );
        R.drawPolygon( 3, x, y, T );
        
        // convex polygon- pantagon
        x[0] = 350;y[0] = 150;
        x[1] = 300;y[1] = 100;
        x[2] = 325;y[2] = 50;
        x[3] = 375;y[3] = 50;
        x[4] = 400;y[4] = 100;
        T.setColor( 0.4f, 0.5f, 0.1f );
        R.drawPolygon( 5, x, y, T );
        
        // concave polygon-one side concave
        x[0] = 450;y[0] = 200;
        x[1] = 400;y[1] = 100;
        x[2] = 450;y[2] = 150;
        x[3] = 500;y[3] = 100;
        T.setColor( 0.4f, 0.5f, 0.1f );
        R.drawPolygon( 4, x, y, T );
        
        // concave polygon-two side concave
        x[0] = 25;y[0] = 500;
        x[1] = 75;y[1] = 450;
        x[2] = 25;y[2] = 400;
        x[3] = 200;y[3] = 400;
        x[4] = 150;y[4] = 450;
        x[5] = 200;y[5] = 500;
        T.setColor( 0.4f, 0.5f, 0.1f );
        R.drawPolygon( 6, x, y, T );
        
        
        // complicated concave figures- Star
        // LEFT SIDE
        x[0] = 300;  y[0] = 300;
        x[1] = 270;  y[1] = 250;
        x[2] = 220;  y[2] = 240;
        x[3] = 255;  y[3] = 205;
        x[4] = 245;  y[4] = 160;
        x[5] = 300;  y[5] = 190;
        T.setColor( 0.7f, 0.7f, 0.0f );
        R.drawPolygon( 6, x, y, T );
        
        // RIGHT SIDE
        x[0] = 300;  y[0] = 300;
        x[1] = 330;  y[1] = 250;
        x[2] = 380;  y[2] = 240;
        x[3] = 345;  y[3] = 205;
        x[4] = 355;  y[4] = 160;
        x[5] = 300;  y[5] = 190;
        T.setColor( 0.8f, 0.8f, 0.0f );
        R.drawPolygon( 6, x, y, T );
                
        // complicated concave figure
        x[0] = 350;y[0] = 500;
        x[1] = 400;y[1] = 450;
        x[2] = 350;y[2] = 400;
        x[3] = 450;y[3] = 435;
        x[4] = 550;y[4] = 400;
        x[5] = 500;y[5] = 450;
        x[6] = 550;y[6] = 500;
        x[7] = 450;y[7] = 465;
        T.setColor( 1.0f, 0.0f, 0.0f );
        R.drawPolygon( 8, x, y, T );
        
        x[0] = 500;y[0] = 500;
        x[1] = 550;y[1] = 450;
        x[2] = 500;y[2] = 400;
        x[3] = 600;y[3] = 435;
        x[4] = 700;y[4] = 400;
        x[5] = 650;y[5] = 450;
        x[6] = 700;y[6] = 500;
        x[7] = 600;y[7] = 465;
        T.setColor( 1.0f, 1.0f, 1.0f );
        R.drawPolygon( 8, x, y, T );
        
        x[0] = 650;y[0] = 500;
        x[1] = 700;y[1] = 450;
        x[2] = 650;y[2] = 400;
        x[3] = 750;y[3] = 435;
        x[4] = 850;y[4] = 400;
        x[5] = 800;y[5] = 450;
        x[6] = 850;y[6] = 500;
        x[7] = 750;y[7] = 465;
        T.setColor( 0.0f, 0.3f, 0.0f );
        R.drawPolygon( 8, x, y, T );  
        
        
         // ########### Bird ###########
       
        
        x[0] = 620;  y[0] = 60;
        x[1] = 560;  y[1] = 100;
        x[2] = 500;  y[2] = 180;
        T.setColor( 0.5f, 0.5f, 0.5f );
        R.drawPolygon( 3, x, y, T); 
    
        x[0] = 620;  y[0] = 60;    
        x[1] = 500;  y[1] = 180;       
        x[2] = 460;  y[2] = 200;
        x[3] = 520;  y[3] = 200;     
        x[4] = 580;  y[4] = 160;
        x[5] = 700;  y[5] = 170;
        x[6] = 800;  y[6] = 70;
        x[7] = 680;  y[7] = 120;
        T.setColor( 0.3f, 0.3f, 0.3f );
        R.drawPolygon(8, x, y, T );
        
        x[0] = 700;  y[0] = 170;
        x[1] = 665;  y[1] = 230;
        x[2] = 680;  y[2] =240;
        T.setColor( 0.5f, 0.5f, 0.5f );
        R.drawPolygon(3, x, y, T );
        
        x[0] = 700;  y[0] = 170;
        x[1] = 695;  y[1] = 240;
        x[2] = 710;  y[2] =230;
        T.setColor( 0.5f, 0.5f, 0.5f );
        R.drawPolygon(3, x, y, T );
        
        x[0] = 700;  y[0] = 170;
        x[1] = 720;  y[1] = 230;
        x[2] = 730;  y[2] =220;
        T.setColor( 0.5f, 0.5f, 0.5f );
        R.drawPolygon(3, x, y, T );
        
        x[0] = 500 ;  y[0] = 195;
        x[1] = 505;  y[1] = 195;
        x[2] = 500;  y[2] =190;
        x[3] = 505;  y[3] =190;
        
       
        T.setColor( 0.0f, 0.0f, 0.0f );
        R.drawPolygon(4, x, y, T );
       
      
      
        
    }   
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

   public void keyTyped(KeyEvent e)
    {
        // What key did we type?
        char key = e.getKeyChar();

        if( (key == 'Q') || (key == 'q') ) System.exit(0); // quit

    }
   
    static public void main(String[] args)
    {  
        Rasterizer F = new Rasterizer();
	F.T.addKeyListener( F );

        Frame f = new Frame( "Poly Fill Test" );
        f.add("Center", F.T);
        f.pack();
        f.setResizable (false);
        f.setVisible(true);
        
        f.addWindowListener( new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
        }
     );
       
}    
}
            
   

    

    

    
  