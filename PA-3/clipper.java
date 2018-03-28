//
//  Clipper.java
//
//Created on Feb 26, 2017

//@author: Srinivas
//
//  Contributor:  Megh Vankawala
//

///
// Object for performing clipping
//
///
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class clipper
{

    ///
    // clipPolygon
    //
    // Clip the polygon with vertex count in and vertices inx/iny
    // against the rectangular clipping region specified by lower-left corner
    // (llx,lly) and upper-right corner (urx,ury). The resulting vertices are
    // placed in outx/outy.
    //
    // The routine should return the the vertex count of the polygon
    // resulting from the clipping.
    //
    // @param in the number of vertices in the polygon to be clipped
    // @param inx - x coords of vertices of polygon to be clipped.
    // @param iny - y coords of vertices of polygon to be clipped.
    // @param outx - x coords of vertices of polygon resulting after clipping.
    // @param outy - y coords of vertices of polygon resulting after clipping.
    // @param llx - x coord of lower left of clipping rectangle.
    // @param lly - y coord of lower left of clipping rectangle.
    // @param urx - x coord of upper right of clipping rectangle.
    // @param ury - y coord of upper right of clipping rectangle.
    //
    // @return number of vertices in the polygon resulting after clipping
    //
    ///
    public int clipPolygon(int in, float inx[], float iny[],float outx[], float outy[],float llx, float lly, float urx, float ury)
    {
        {        
        ArrayList<Boundary> boundary = new ArrayList<>();        
        boundary.add(new Boundary(urx, lly, llx, lly, true));
        boundary.add(new Boundary(llx, lly, llx, ury, true));
        boundary.add(new Boundary(urx, lly, urx, ury, false));
        boundary.add(new Boundary(llx, ury, urx, ury, false));
        
        ArrayList<ArrayList<Float>> inpoints = new ArrayList<>();
        inpoints.add(new ArrayList<>());
        inpoints.add(new ArrayList<>());
        for(int i = 0; i < in; i++)
        {
            inpoints.get(0).add(inx[i]);
            inpoints.get(1).add(iny[i]);
        }       
        int ol;
        ol= in;
        ArrayList<ArrayList<Float>> outpoints = new ArrayList<>();
        for(Boundary b : boundary)
        {
            
            outpoints = new ArrayList<>();
            outpoints.add(new ArrayList<>());
            outpoints.add(new ArrayList<>());
            if(ol != 0)
            {
                ol = SHPC(inpoints, outpoints, ol, b);
            }
            inpoints = outpoints;
        }     
        for(int i = 0; i < ol; i++)
        {
            outx[i] = outpoints.get(0).get(i);
            outy[i] = outpoints.get(1).get(i);
        }
        return ol; 
        }
    }   
    
    
    public boolean inside(float x, float y, Boundary boundary)
    {
        if(boundary.greater())
        {
            if(boundary.llx() == boundary.urx())
            {
                return x > boundary.llx();
            }
            else
            {
                return y > boundary.lly();
            }
            
        }
        else
        {
           if(boundary.llx() == boundary.urx())
            {
                return x < boundary.llx();
            }
            else
            {
                return y < boundary.lly();
            }
             
        }
    }

    
    public int SHPC(ArrayList<ArrayList<Float>> iv, ArrayList<ArrayList<Float>> ov, int inLength, Boundary clipboundary )
    {
        int ol = 0;
        float px,py;
        px = iv.get(0).get(inLength-1);
        py = iv.get(1).get(inLength-1);
        for(int j = 0; j < inLength; j++)
        {
            float sx = iv.get(0).get(j);
            float sy = iv.get(1).get(j);
            if(inside(sx, sy, clipboundary))
            {
                if(inside(px, py, clipboundary))
                {
                    output(sx, sy, ov);
                    ol++;
                }
                else
                {
                    float[] i = intersect(sx, sy, px, py, clipboundary);
                    output(i[0], i[1], ov);
                    output(sx, sy, ov);
                    ol=ol+2;
                }                                
            } 
            else 
            {
                if(inside(px, py, clipboundary))
                {
                    float[] i = intersect(px, py, sx, sy, clipboundary);
                    output(i[0], i[1], ov);
                    ol=ol+1;
                }                
            }
            px = sx;
            py = sy;
        }
        return ol;
    }
   
    public void output(float x, float y, ArrayList<ArrayList<Float>> op)
    {
        op.get(0).add(x);
        op.get(1).add(y);
    }

    public float[] intersect(float px, float py, float sx, float sy, Boundary boundary)
    {
        float[] p;
        p = new float[2];
        if(boundary.llx() == boundary.urx())
        {
            p[0] = boundary.llx();
            p[1] = (py-sy)/(px-sx)*(boundary.llx() - px) + py;
        }
        else 
        {
         p[0] = (py*sx - sy*px + boundary.lly()*(px-sx))/(py-sy);
         p[1] = boundary.lly();      
        }
        
     return p;   
    }    
}



