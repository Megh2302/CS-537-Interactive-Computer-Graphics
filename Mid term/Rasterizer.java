import Jama.Matrix;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
public class Rasterizer 
{

    int n_scanlines;

    Rasterizer (int n)
    {
        n_scanlines = n;
    }

    public void drawPolygon(int n, Matrix[] v, cgfunctions C)
    {      
        int ymax, ymin;
        ymax = 0;
        ymin = n_scanlines;
        int i;
        // min and max y values
        for(i= 0; i < n; i++)
        {
            if(ymin > v[i].get(1,0))
            {
                ymin = (int) v[i].get(1,0);
            }
            if(ymax < v[i].get(1,0))
            {
                ymax = (int) v[i].get(1,0);
            }
            
        }
        ArrayList<edge> AEL = new ArrayList<edge>();
        for(i = ymin; i < ymax + 1; i++)
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
            //Adding elements to the AEL and sorting
            HashMap<Integer, ArrayList<edge>> ET = edge_table(n,v);     
            if(ET.containsKey(i))
            {
                AEL.addAll(ET.get(i));
                Collections.sort(AEL);
            }
            for(int j = 0; j < AEL.size(); j+=2)
            {
                edge e1 = AEL.get(j);
                edge e2 = AEL.get(j+1);
                float x0,x1,m;
                if(e1.dy()/e1.dx() > 0)
                {
                    if(e1.sum() > 0)
                    {
                        x0 = e1.x()+1;
                    }
                    else
                    {
                        x0 = e1.x();
                    }
                }
                else
                {
                    if(e1.sum() > 0)
                    {
                        x0 = e1.x();
                    }
                    else
                    {
                        x0 = e1.x()+1;
                    }
                }
                if(e2.dy()/e2.dx() > 0)
                {
                    if(e2.sum() > 0)
                    {
                        x1 = e2.x();
                    }
                    else
                    {
                        x1 = e2.x()-1;
                    }
                }
                else
                {
                    if(e2.sum() > 0)
                    {
                        x1 = e2.x()-1;
                    }
                    else
                    {
                        x1 = e2.x();
                    }
                }
                
            for( m = x0; m < x1; m++)
            {
                C.setPixel((int)m, i);
            }               
            }
            // Adjust the x and the sum 
            for(edge e: AEL)
            {
                e.adjust();
            }
        }
    }

    // making of edge table
    private HashMap<Integer, ArrayList<edge>> edge_table(int n, Matrix[] v)
    {
        HashMap<Integer, ArrayList<edge>> ET = new HashMap<Integer, ArrayList<edge>>();
        
        for(int i = 0; i < n-1; i++)
        {
            adedge(ET, (float) v[i].get(0,0), (float) v[i].get(1,0), (float) v[i+1].get(0,0), (float) v[i+1].get(1,0));
        }
        adedge(ET, (float) v[0].get(0,0), (float) v[0].get(1,0), (float) v[n-1].get(0,0), (float) v[n-1].get(1,0));
        return ET;
    }

    //Adding an edge to the edge table
    private void adedge(HashMap<Integer, ArrayList<edge>> ET, float x0, float y0, float x1, float y1){
        edge e = new edge();
        int sl = e.createedge(x0, y0, x1, y1);
        if(e.dy() != 0){ 
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

}