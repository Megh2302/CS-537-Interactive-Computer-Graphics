import Jama.*;
import java.util.*;
public class cgfunctions extends simpleCanvas
{
    int count=0;
    Rasterizer rasterizer;
    Clipper clipper;  
    Matrix TM,viewport;
    ArrayList<P_info> polygons;
    float cl,cr,ct,cb;
    cgfunctions (int w, int h)
    {
        super (w, h);
        polygons = new ArrayList();
        rasterizer = new Rasterizer(h); 
        clipper = new Clipper();
        double[][]val = {{1,0,0}, {0,1,0}, {0,0,1}};
        TM = new Matrix(val);
    }  
    //Draw the polygon with the id
    public void drawPoly(int id)
    {
        P_info polygon =polygons.get(id);
        Matrix[] V = new Matrix[polygon.N()];
        for(int i = 0; i < polygon.N(); i++)
        {
            double[][] val = {{polygon.X()[i]}, 
                              {polygon.Y()[i]},
                              {1}};
            V[i] = TM.times(new Matrix(val));
        }
        Matrix[] outpoints = clipper.clipPolygon(polygon.N(),V, cl, cb, cr, ct);
        Matrix[] viewpoints = new Matrix[outpoints.length];
        for(int i = 0; i < outpoints.length; i++)
        {
            viewpoints[i] = viewport.times(outpoints[i]);
        }
        rasterizer.drawPolygon(viewpoints.length, viewpoints, this);
    }
    
    //Adding and storeing of a polygon to the canvas.
    public int addPoly(float x[], float y[], int n)
    {
        polygons.add(new P_info(x, y, n));
        count++;
        return count-1;
    } 
    /*Transformation Matrix*/
    
    //Translation
    public void translate(float x, float y)
    {
        double[][]val = {{1,0,x},
                         {0,1,y},
                         {0,0,1}};      
        TM = new Matrix(val).times(TM);        
    }
    
    //Rotation
    public void rotate(float degrees)
    {
        double[][]val = {{Math.cos(degrees*Math.PI/180),-Math.sin(degrees*Math.PI/180),0}, 
                         {Math.sin(degrees*Math.PI/180),Math.cos(degrees*Math.PI/180),0},
                         {0,0,1}};
        TM = new Matrix(val).times(TM);
    }
    
    //Scaling
    public void scale(float x, float y)
    {
        double[][]val = {{x,0,0},
                         {0,y,0},
                         {0,0,1}};
        TM = new Matrix(val).times(TM);
    }
    
    //jump to the current transformation 
    public void Transformation_clear()
    {
        double[][]vals = {{1,0,0},
                          {0,1,0},
                          {0,0,1}};
        TM = new Matrix(vals);
    }
    
    //setting up the clip-window
    public void Clipwindow(float bottom, float top, float left, float right)
    {
        cl = left;
        cb = bottom;
        cr = right;
        ct = top;
    }
    
    //Setting up Viewport
    public void Viewport(int x, int y, int w, int h)
    {
        double sx = w/(cr - cl);
        double sy = h/(ct - cb);
        double tx = (cr*x -cl*(x + w))/(cr - cl);
        double ty = (ct*y - cb*(y+h))/(ct - cb);

        double[][] vals = {{sx, 0, tx},
                           {0, sy, ty},
                           {0 , 0 , 1}};
        viewport = new Matrix(vals);
    }   
}