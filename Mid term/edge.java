import static java.lang.Math.abs;
public class edge implements Comparable<edge>
{
    int ymax,x,dy,dx,sum=0; 
    
    //Creating an Edge
    public int createedge(float x0, float y0, float x1, float y1)
    {    
        int sl;
        
        if(y0>y1)
        {
            sl= (int) y1;
        }
        else
        {
            sl= (int) y0;
        }
        
        if(y0<y1)
        {
            ymax= (int)y1;
        }
        else
        {
            ymax=(int)y0;
        }
        if(y0<y1)
        {
            x=(int)x0;
        }
        else
        {
            x=(int)x1;
        } 
        dx = (int) x1 - (int) x0;
        dy = (int) y1 -(int) y0;
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
    public float dx() 
    {
        return dx;
    }
    public float sum() 
    {
        return sum;
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
