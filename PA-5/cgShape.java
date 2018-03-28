//
//  cgShape.java
//
//  Class that includes routines for tessellating a number of basic shapes.
//
//  Students are to supply their implementations for the functions in
//  this file using the function "addTriangle()" to do the tessellation.
//

import java.awt.*;
import java.nio.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import java.io.*;


public class cgShape extends simpleShape
{
    /**
     * constructor
     */
    public cgShape()
    {
    }

    /**
     * makeCube - Create a unit cube, centered at the origin, with a given
     * number of subdivisions in each direction on each face.
     *
     * @param subdivision - number of equal subdivisons to be made in each 
     *        direction along each face
     *
     * Can only use calls to addTriangle()
     */
    void CubeTessellation(float x, float y, float z, float sideLen, int subdivisions, int pos, char fixedCoord)
    {
    	float newX,newY,newZ,newSideLen;
    	//x is fixed coordinate
    	if( fixedCoord == 'x' )
        {
    		//add triangles in z direction with increment in y after every z is complete.
    		for( int i = 0 ; i < subdivisions; i++ )
                {
                    for( int j = 0; j < subdivisions; j++ )
                    {		
                    newSideLen = sideLen/subdivisions;
                    if( pos == 1)
                    {
                        newY = y + i*(sideLen/subdivisions);
        		newZ = z - j*(sideLen/subdivisions);	
                        addTriangle(x,newY,newZ, x,newY,newZ-newSideLen, x,newY+newSideLen,newZ-newSideLen);
    	    		addTriangle(x,newY,newZ, x,newY+newSideLen,newZ-newSideLen, x,newY+newSideLen,newZ);
                    }
                    else
                    {
    	    		newY = y + i*(sideLen/subdivisions);
        		newZ = z + j*(sideLen/subdivisions);
                        addTriangle(x,newY,newZ, x,newY,newZ+newSideLen, x,newY+newSideLen,newZ+newSideLen);
    	    		addTriangle(x,newY,newZ, x,newY+newSideLen,newZ+newSideLen, x,newY+newSideLen,newZ);
                    }
                    }
    		}
    	}
    	//y is fixed coordinate
    	else if( fixedCoord == 'y')
        {
            //add triangles horizontally in x direction with increment in z after every x length is complete.
            for( int i = 0 ; i < subdivisions; i++ )
            {
    		for( int j = 0; j < subdivisions; j++ )
                {
                    newSideLen = sideLen/subdivisions;
                    if( pos == 1)
                    {
        		newX = x + i*(sideLen/subdivisions);
        		newZ = z - j*(sideLen/subdivisions);		
    	    		addTriangle(newX,y,newZ, newX+newSideLen,y,newZ-newSideLen, newX,y,newZ-newSideLen);
    	    		addTriangle(newX,y,newZ, newX+newSideLen,y,newZ, newX+newSideLen,y,newZ-newSideLen);
                    }
                    else
                    {
        		newX = x - i*(sideLen/subdivisions);
        		newZ = z - j*(sideLen/subdivisions);
    	    		addTriangle(newX,y,newZ, newX-newSideLen,y,newZ-newSideLen, newX,y,newZ-newSideLen);
    	    		addTriangle(newX,y,newZ, newX-newSideLen,y,newZ, newX-newSideLen,y,newZ-newSideLen);
                    }   
    		}
            }
    	}
        //z is fixed coordinate
    	else
        {
            for( int i = 0 ; i < subdivisions; i++ )
            {
    		for( int j = 0; j < subdivisions; j++ )
                {
                    newSideLen = sideLen/subdivisions;
                    if( pos == 1)
                    {
                        newX = x + j*(sideLen/subdivisions);
                        newY = y + i*(sideLen/subdivisions);			    					
                        addTriangle(newX,newY,z, newX+newSideLen,newY,z, newX+newSideLen,newY+newSideLen,z);
                        addTriangle(newX,newY,z, newX+newSideLen,newY+newSideLen,z, newX,newY+newSideLen,z);
                    }
                    else
                    {
    	    		newX = x - j*(sideLen/subdivisions);
        		newY = y + i*(sideLen/subdivisions);		
    	    		addTriangle(newX,newY,z, newX-newSideLen,newY,z, newX-newSideLen,newY+newSideLen,z);
    	    		addTriangle(newX,newY,z, newX-newSideLen,newY+newSideLen,z, newX,newY+newSideLen,z);
                    }
    		}
            }
    	}	
    }
    
    public void makeCube (int subdivisions)
    {
        if( subdivisions < 1 )
            subdivisions = 1;
        float x,y,z,sideLen;
        int pos; 
        char fixedCoord;
        sideLen = 1.0f;
        //draw front side of the cube
        x = -0.5f;
        y = -0.5f;
        z = 0.5f;
        pos = 1; //front facing
        fixedCoord = 'z';
        CubeTessellation(x,y,z,sideLen,subdivisions,pos,fixedCoord);
        //draw back face of the cube
        x = 0.5f;
        y = -0.5f;
        z = -0.5f;
        pos = 0; //back facing
        fixedCoord = 'z';
        CubeTessellation(x,y,z,sideLen,subdivisions,pos,fixedCoord);
        //draw left face of the cube
        x = -0.5f;
        y = -0.5f;
        z = -0.5f;
        pos = 0;
        fixedCoord = 'x';
        CubeTessellation(x,y,z,sideLen,subdivisions,pos,fixedCoord);
        //draw right face of the cube
        x = 0.5f;
        y = -0.5f;
        z = 0.5f;
        pos = 1;
        fixedCoord = 'x';
        CubeTessellation(x,y,z,sideLen,subdivisions,pos,fixedCoord);
        //draw top face of the cube
        x = -0.5f;
        y = 0.5f;
        z = 0.5f;
        pos = 1;
        fixedCoord = 'y';
        CubeTessellation(x,y,z,sideLen,subdivisions,pos,fixedCoord);
        //draw bottom face of the cube
        x = 0.5f;
        y = -0.5f;
        z = 0.5f;
        pos = 0;
        fixedCoord = 'y';
        CubeTessellation(x,y,z,sideLen,subdivisions,pos,fixedCoord);
    }
    /**
     * makeCylinder - Create polygons for a cylinder with unit height, centered
     * at the origin, with separate number of radial subdivisions and height 
     * subdivisions.
     *
     * @param radius - Radius of the base of the cylinder
     * @param radialDivision - number of subdivisions on the radial base
     * @param heightDivisions - number of subdivisions along the height
     *
     * Can only use calls to addTriangle()
     */
    public void makeCylinder (float radius, int radialDivisions, int heightDivisions)
    {
        if( radialDivisions < 3 )
            radialDivisions = 3;

        if( heightDivisions < 1 )
            heightDivisions = 1;
        double fullAngle = 360, PI = 3.14159265f;
        //the central angle of each base triangle
        double central_angle = (fullAngle/radialDivisions)*(PI/180);
        float y = 0.5f;               
        //circumference coordinates
        float circ1X = radius;
        float circ1Z = 0f,circ2X,circ2Z; 
        //height of each subdivision
        float newDivHeight = 1.0f/heightDivisions;        
        for(int i = 0; i < radialDivisions; i++)
        {
            circ2X = (float)(radius*Math.cos(central_angle*(i+1)));
            circ2Z = (float)(radius*Math.sin(central_angle*(i+1)));
            // draw lower base circle
            y = -0.5f;
            addTriangle(0f,y,0f, circ1X,y,circ1Z, circ2X,y,circ2Z);
            // draw upper base circle
            y = 0.5f;
            addTriangle(0f,y,0f, circ2X,y,circ2Z, circ1X,y,circ1Z);
            //draw curved surface
            for(int j = 0; j < heightDivisions; j++)
            {
        	y = -0.5f + (newDivHeight)*j;	
        	addTriangle(circ2X,y,circ2Z, circ1X,y,circ1Z, circ1X,y+newDivHeight,circ1Z);
        	addTriangle(circ2X,y,circ2Z, circ1X,y+newDivHeight,circ1Z, circ2X,y+newDivHeight,circ2Z);
            }	
            circ1X = circ2X;
            circ1Z = circ2Z;
        }    
    }
    /**
     * makeCone - Create polygons for a cone with unit height, centered at the
     * origin, with separate number of radial subdivisions and height 
     * subdivisions.
     *
     * @param radius - Radius of the base of the cone
     * @param radialDivision - number of subdivisions on the radial base
     * @param heightDivisions - number of subdivisions along the height
     *
     * Can only use calls to addTriangle()
     */
    public void makeCone (float radius, int radialDivisions, int heightDivisions)
    {
        if( radialDivisions < 3 )
            radialDivisions = 3;

        if( heightDivisions < 1 )
            heightDivisions = 1;
        
        double fullAngle = 360;
        double PI = 3.14159265f;
        //central angle of the base triangles
        double central_angle = (fullAngle/radialDivisions)*(PI/180); 
        float y = 0.5f;
        // circumference coordinates
        float circ1X = radius;
        float circ1Z = 0f, circ2X,circ2Z ;
        float radiusDivLen = 0.5f/heightDivisions;
        float slant1X = circ1X, slant2X, slant3X;
        float slant4X = circ1X-radiusDivLen;
        float slant1Z = circ1Z, slant2Z, slant3Z;
        float slant4Z = circ1Z;
        float newDivHeight = 1.0f/heightDivisions;  
        int i, j;
        for( i = 0; i < radialDivisions; i++)
        {
            //new circumference coordinates
            circ2X = (float)(radius*Math.cos(central_angle*(i+1)));
            circ2Z = (float)(radius*Math.sin(central_angle*(i+1)));	
            //curved surface
            slant1X = circ1X;
            slant1Z = circ1Z;
            slant2X = circ2X;
            slant2Z = circ2Z;
            for( j = 0; j < heightDivisions; j++)
            {
                slant3X = (float)((radius-radiusDivLen*(j+1))*Math.cos(central_angle*(i+1)));
        	slant3Z = (float)((radius-radiusDivLen*(j+1))*Math.sin(central_angle*(i+1)));
        	slant4X = (float)((radius-radiusDivLen*(j+1))*Math.cos(central_angle*(i)));
        	slant4Z = (float)((radius-radiusDivLen*(j+1))*Math.sin(central_angle*(i)));
        	y = -0.5f + (newDivHeight)*j;	
        	addTriangle(slant2X,y,slant2Z, slant1X,y,slant1Z, slant4X,y+newDivHeight,slant4Z);
        	addTriangle(slant2X,y,slant2Z, slant4X,y+newDivHeight,slant4Z, slant3X,y+newDivHeight,slant3Z);
        	slant1X = slant4X;
        	slant2X = slant3X;
        	slant1Z = slant4Z;
        	slant2Z = slant3Z;
            }	
            circ1X = circ2X;
            circ1Z = circ2Z;
            //lower base circle
            y = -0.5f;
            addTriangle(0f,y,0f, circ1X,y,circ1Z, circ2X,y,circ2Z);    
        }
    }
    /**
     * makeSphere - Create sphere of a given radius, centered at the origin, 
     * using spherical coordinates with separate number of thetha and 
     * phi subdivisions.
     *
     * @param radius - Radius of the sphere
     * @param slides - number of subdivisions in the theta direction
     * @param stacks - Number of subdivisions in the phi direction.
     *
     * Can only use calls to addTriangle
     */
    public void makeSphere (float radius, int slices, int stacks)
    {
        if( slices < 3 ) slices = 3;
        if( stacks < 3 ) stacks = 3;
        
        double a = 2.0 / (1 + Math.sqrt(5.0));
        
		Point[] verts = new Point[12];
		verts[0] = new Point(0.0, a, -1.0).normalize().scale(radius);
		verts[1] = new Point(-a, 1.0, 0.0).normalize().scale(radius);
		verts[2] = new Point(a, 1, 0).normalize().scale(radius);
		verts[3] = new Point(0.0, a, 1).normalize().scale(radius);
		verts[4] = new Point(-1, 0, a).normalize().scale(radius);
		verts[5] = new Point(0, -a, 1).normalize().scale(radius);
		verts[6] = new Point(1, 0, a).normalize().scale(radius);
		verts[7] = new Point(1, 0, -a).normalize().scale(radius);
		verts[8] = new Point(0, -a, -1).normalize().scale(radius);
		verts[9] = new Point(-1, 0, -a).normalize().scale(radius);
		verts[10] = new Point(-a, -1, 0).normalize().scale(radius);
		verts[11] = new Point(a, -1, 0).normalize().scale(radius);

		process(verts[0], verts[1], verts[2], slices, radius);
		process(verts[3], verts[2], verts[1], slices, radius);
		process(verts[3], verts[4], verts[5], slices, radius);
		process(verts[3], verts[5], verts[6], slices, radius);
		process(verts[0], verts[7], verts[8], slices, radius);
		process(verts[0], verts[8], verts[9], slices, radius);
		process(verts[5], verts[10], verts[11], slices, radius);
		process(verts[8], verts[11], verts[10], slices, radius);
		process(verts[1], verts[9], verts[4], slices, radius);
		process(verts[10], verts[4], verts[9], slices, radius);
		process(verts[2], verts[6], verts[7], slices, radius);
		process(verts[11], verts[7], verts[6], slices, radius);
		process(verts[3], verts[1], verts[4], slices, radius);
		process(verts[3], verts[6], verts[2], slices, radius);
		process(verts[0], verts[9], verts[1], slices, radius);
		process(verts[0], verts[2], verts[7], slices, radius);
		process(verts[8], verts[10], verts[9], slices, radius);
		process(verts[8], verts[7], verts[11], slices, radius);
		process(verts[5], verts[4], verts[10], slices, radius);
		process(verts[5], verts[11], verts[6], slices, radius);

        
    }
    
    void process(Point p1, Point p2, Point p3, int sub, float radius)
    {
	if (sub > 1) 
        {
            Point mid1 = Point.MidPointOnSphere(p1, p2, radius);
            Point mid2 = Point.MidPointOnSphere(p3, p2, radius);
            Point mid3 = Point.MidPointOnSphere(p1, p3, radius);
            // subdivide
            process(p1, mid1, mid3, sub - 1, radius);
            process(p2, mid2, mid1, sub - 1, radius);
            process(p3, mid3, mid2, sub - 1, radius);
            process(mid1, mid2, mid3, sub - 1, radius);
	} 
        else 
        {
            addTriangle(p1, p2, p3);
	}
	}

	void addTriangle(Point p1, Point p2, Point p3) 
        {
		addTriangle((float) p1.x, (float) p1.y, (float) p1.z, (float) p2.x,(float) p2.y, (float) p2.z, (float) p3.x, (float) p3.y,(float) p3.z);
	}
        static class Point 
        {
            double x,y,z;
            public Point() 
            {
		x = y = z = 0;
            }
            public static Point MidPointOnSphere(Point p1, Point p2,float radius) 
            {
		Point m = new Point();
		m.x = ((p1.x - p2.x) / 2) + p2.x;
		m.y = ((p1.y - p2.y) / 2) + p2.y; 
		m.z = (p1.z - p2.z) / 2 + p2.z;
                return m.normalize().scale(radius);
            }
            public Point(double x, double y, double z)
            {
		this.x = x;
		this.y = y;
		this.z = z;
            }
            public Point normalize()
            {
		double div = Math.sqrt(x * x + y * y + z * z);
		x = x/div;
		y = y/div;
		z = z/div;
		return this;
            }
            public Point setOrigin(Point O)
            {
		x = x - O.x;
		y = y - O.y;
		z = z - O.z;
		return this;
            }
            public Point scale(double val)
            {
		x = x*val;
		y = y*val;
		z = z*val;
		return this;
            }
	}
}
