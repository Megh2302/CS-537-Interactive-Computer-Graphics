public class Boundary 
{
    float llx,lly,urx,ury;
    boolean greater;
   // Creating a clipping boundary
    public Boundary(float llx, float lly, float urx, float ury, boolean greater) 
    {
        this.llx = llx;
        this.lly = lly;
        this.urx = urx;
        this.ury = ury;
        this.greater = greater;
    }
    public float llx() 
    {
        return llx;
    }
    public float urx() 
    {
        return urx;
    }
    public float lly()
    {
        return lly;
    }   
    public float ury() 
    {
        return ury;
    }
    public boolean greater()
    {
        return greater;
    }
}