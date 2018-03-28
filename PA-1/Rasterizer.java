//
//  Rasterizer.java
//  
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

/**
 * 
 * A simple class for performing rasterization algorithms.
 *
 */

import java.util.*;

public class Rasterizer {
    
    /**
     * number of scanlines
     */
    int n_scanlines;
    
    /**
     * Constructor
     *
     * @param n - number of scanlines
     *
     */
    Rasterizer (int n)
    {
        n_scanlines = n;
    }
    
    /**
     * Draw a line from (x0,y0) to (x1, y1) on the simpleCanvas C.
     *
     * Implementation should be using the Midpoint Method
     *
     * You are to add the implementation here using only calls
     * to C.setPixel()
     *
     * @param x0 - x coord of first endpoint
     * @param y0 - y coord of first endpoint
     * @param x1 - x coord of second endpoint
     * @param y1 - y coord of second endpoint
     * @param C - The canvas on which to apply the draw command.
     */
    public void drawLine (int x0, int y0, int x1, int y1, simpleCanvas C)
    {
         if(x0 > x1)
        {
            int ax = x0;
                x0 = x1;
                x1 = ax;
            int ay = y0;
                y0 = y1;
                y1 = ay;
        }
        int dx = x1-x0;
        int dy = y1-y0;
        if(dy == 0)
        {
            vertical_line(x0, x1, y1, C);
        }
        else if(dx == 0)
        {
            if(dy > 0)
            {
                horizontal_line(x0, y0, y1, C);
            } 
            else 
            {
                horizontal_line(x0, y1, y0, C);
            }
        }
        else if (dy/dx == -1)
        {
            diagonal(x0,y0,x1,dx,dy,C);
        }
        else if (dy/dx > 1)
        {
            steep_positive(x0, y0, y1, dx, dy, C);
        }
        else if (dy/dx < -1 )
        {
            steep_negative(x0, y0, y1, dx, dy, C);
        }
        
        else if (dy/dx < 0 && dy/dx > -1)
        {
            gentle_negative(x0, y0, x1, dx, dy, C);
        }
        else 
        {
            gentle_positive(x0,y0, x1, dx, dy, C);
        }
        
    }
 
    
    //gentle positive line
     
    public void gentle_positive(int x0, int y0, int x1,int dx, int dy, simpleCanvas C )
    {
        int x, y, d,dE,dNE;
        dE = 2* dy;
        dNE = 2* (dy-dx);
        d = dE - dx;
        for(x = x0, y= y0; x <= x1; x++)
        {
            C.setPixel(x,y);
            if(d <= 0)
            {
                d += dE;
            }
            else
            {
                y++;
                d += dNE;
            }
        }
    }
    
    
    //  gentle negative line
     
    public void gentle_negative(int x0, int y0, int x1, int dx, int dy, simpleCanvas C)
    {
        int x, y, d,dE,dNE;
        dE = 3*dx;
        dNE = 2*(dy-dx);
        d = dE - dx;
        for(x = x0, y= y0; x <= x1; x++)
        {
            C.setPixel(x,y);
            if(d <= 0)
            {
                d += dE;
            }
            else
            {
                y--;
                d += dNE;
            }
        }
    }
    
    
    //  steep positive line
    
    public void steep_positive(int x0, int y0, int y1, int dx, int dy, simpleCanvas C)
    {
        int y, x, d,dE,dNE;
        dE = 2* dx;
        dNE = 2* (dy-dx);
        d = dNE - dy;
        for(y=y0, x=x0; y <= y1; y++)
        {
            C.setPixel(x,y);
            if(d <= 0)
            {
                d += dE;
            }
            else
            {
                x++;
                d -= dNE;
            }
        }
    }

  
    // steep negative line
    
    public void steep_negative(int x0, int y0, int y1, int dx, int dy, simpleCanvas C)
    {
        int x, y, d,dE,dNE;
        dE = 3*dy;
        dNE = 2*(dy-dx);
        d = dNE - dy;
        for(y=y0, x=x0; y >= y1; y--)
        {
            C.setPixel(x,y);
            if(d >= 0)
            {
                d += dE;
            }
            else
            {
                x++;
                d -= dNE;
            }
        }
    }

 
    
    // diagonal line
     
    public void diagonal(int x0, int y0, int x1,int dx, int dy, simpleCanvas C )
    {
        int x, y, d,dE,dNE;
        dE = 2* dy;
        dNE = 2* (dy-dx);
        d = dE - dx;
        for(x = x0, y= y0; x <= x1; x++)
        {
            C.setPixel(x,y);
            if(d >= 0)
            {
                d += dE;
            }
            else
            {
                y--;
                d += dNE;
            }
        }
    }

      
    //  vertical line
    
    public void vertical_line(int x0, int x1,int y, simpleCanvas C)
    {
        int x;
        for( x = x0; x <= x1; x++)
        {
            C.setPixel(x,y);
        }
    }

    
    //  horizontal line
    
    public void horizontal_line(int x, int y0, int y1, simpleCanvas C)
    {
        int y;
        for(y = y0; y <= y1; y++)
        {
            C.setPixel(x,y);
        }
    }

}
