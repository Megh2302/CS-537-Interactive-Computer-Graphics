public class P_info
{
    float[] x,y ;
    int n;
    //Constructor for a polygon
    public P_info(float[] x, float[] y, int n)
    {
        this.x = x.clone();
        this.y = y.clone();
        this.n = n;
    }
    public float[] X()
    {
        return x;
    }

    public float[] Y()
    {
        return y;
    }

    public int N()
    {
        return n;
    }
}