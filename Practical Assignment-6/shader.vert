//
// Vertex shader for the transformation assignment
//

#version 150

// attribute:  vertex position
in vec4 vPosition;

// model transformation data
uniform vec3 theta;
uniform vec3 trans;
uniform vec3 scale;

// camera data
uniform vec3 cPosition;
uniform vec3 cLookAt;
uniform vec3 cUp;

// projection data
uniform float left;
uniform float right;
uniform float top;
uniform float bottom;
uniform float near;
uniform float far;

// projection type
uniform int doOrtho;


mat4 projectionTransform()
{
	if( doOrtho == 1 ) 
	{
		return mat4( (2.0/(right-left)), 0.0, 0.0, 0.0,
						0.0, (2.0 /(top-bottom)), 0.0, 0.0,
						0.0, 0.0, (-2.0/(far - near)), 0.0,
						(-1.0*(right+left)/(right-left)),
						(-1.0*(top+bottom)/(top-bottom)),
						(-1.0*(far+near)/(far-near)), 1.0 );
	
	} 
	else 
	{
		return mat4( (2.0*near)/(right-left), 0.0, 0.0, 0.0,
						  0.0, ((2.0*near)/(top-bottom)), 0.0, 0.0,
						  ((right+left)/(right-left)),((top+bottom)/(top-bottom)),((-1.0*(far+near)) / (far-near)), -1.0,
						  0.0, 0.0, ((-2.0*far*near)/(far-near)), 0.0 );
	}
}

void main()
{
    vec3 angles = radians (theta);
    vec3 c = cos (angles);
    vec3 s = sin (angles);
	vec3 nVec = normalize( cPosition - cLookAt );
	vec3 uVec = normalize( cross (normalize(cUp), nVec) );
	vec3 vVec = normalize( cross (nVec, uVec) );
    
    // rotation matricies
    mat4 rx = mat4 (1.0,  0.0,  0.0,  0.0, 
                    0.0,  c.x,  s.x,  0.0,
                    0.0, -s.x,  c.x,  0.0,
                    0.0,  0.0,  0.0,  1.0);
                    
    mat4 ry = mat4 (c.y,  0.0, -s.y,  0.0, 
                    0.0,  1.0,  0.0,  0.0,
                    s.y,  0.0,  c.y,  0.0,
                    0.0,  0.0,  0.0,  1.0);

    mat4 rz = mat4 (c.z, -s.z,  0.0,  0.0, 
                    s.z,  c.z,  0.0,  0.0,
                    0.0,  0.0,  1.0,  0.0,
                    0.0,  0.0,  0.0,  1.0);

    mat4 transMat = mat4 ( 1.0, 0.0, 0.0, 0.0,
						   0.0, 1.0, 0.0, 0.0,
					       0.0, 0.0, 1.0, 0.0,
						   trans.x, trans.y, trans.z, 1.0);
							
    mat4 scaleMat = mat4 (scale.x,  0.0,     0.0,     0.0,
                          0.0,      scale.y, 0.0,     0.0, 
                          0.0,      0.0,     scale.z, 0.0,
                          0.0,      0.0,     0.0,     1.0);
						  
	mat4 view = mat4( uVec.x, vVec.x, nVec.x, 0.0,
					  uVec.y, vVec.y, nVec.y, 0.0,
					  uVec.z, vVec.z, nVec.z, 0.0,
					  -1.0*(dot(uVec, cPosition)),
					  -1.0*(dot(vVec, cPosition)),
					  -1.0*(dot(nVec, cPosition)), 1.0 );
	      
    gl_Position = projectionTransform()*view*transMat * rz * ry * rx *scaleMat* vPosition;
}
