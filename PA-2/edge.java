
import static java.lang.Math.abs;

public class edge implements Comparable<edge>
{

    int ymax,x,dy,dx,sum=0;
    
    // Creates an Edge
    public int createedge(int x0, int y0, int x1, int y1)
    {    
        int sl;
        
        if(y0>y1)
        {
            sl=y1;
        }
        else
        {
            sl= y0;
        }
        
        if(y0<y1)
        {
            ymax=y1;
        }
        else
        {
            ymax=y0;
        }
        if(y0<y1)
        {
            x=x0;
        }
        else
        {
            x=x1;
        }
        
        dx = x1 - x0;
        dy = y1 - y0;
        return sl;
    }   
    public int ymax() 
    {
        return ymax;
    }    
    public int x() 
    {
        return x;
    }   
    public int dy() 
    {
        return dy;
    }

    
    public void adjust()
    {
        sum = sum + abs(dx);
        while(abs(dy) < sum)
        {
            if(dy/(float)dx >= 0)
            {
                x = x + 1;
            }
            else 
            {
                x = x - 1;
            }
            sum = sum - abs(dy);
        }
         
    }

     // Compares to edges
        public int compareTo(edge m) 
        {
            if(m.x == x)
            {
                return new Float(dx/(float)dy).compareTo(m.dx/(float)m.dy);
            }
            else
            {
                return new Integer(x).compareTo(m.x);
            }
        }
}
