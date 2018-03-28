//
// lightingParams.java
//
//  Created by Srinivas Sridharan on 4/27/17.
//
// Contributor:  MEGH VANKAWALA
//
// 20155 version
//

import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;

public class lightingParams
{
// Add any data members you need here.    
// material properties
float[] ambient = { 0.5f, 0.1f, 0.9f, 1.0f };
float[] diffuse = { 0.89f, 0.0f, 0.0f, 1.0f };
float[] specular = { 1.0f, 1.0f, 1.0f, 1.0f };
// lighting data
float[] lightpos = { 0.0f,0.0f, 0.0f, 0.0f };
float[] lightcolor = { 1.0f, 1.0f, 0.0f, 1.0f };
float[] amblight = { 0.5f, 0.5f, 0.5f, 1.0f };
//Reflective characteristics of the teapot and quad
	float ka = (float) 0.5;
	float kd = ( float) 0.7;
	float ks = (float)1.0;
	float specExp = 10.0f;
    /**
     * constructor
     */
    public lightingParams()
    {
    }

    /**
     * This function sets up the lighting, material, and shading parameters
     * for the Phong shader.
     *
     * You will need to write this function, and maintain all of the values
     * needed to be sent to the vertex shader.
     *
     * @param program - The ID of an OpenGL (GLSL) shader program to which
     *    parameter values are to be sent
     *
     * @param gl3 - GL3 object on which all OpenGL calls are to be made
     *
     */
    public void setUpPhong (int program, GL3 gl3)
    {
        // Add your code here.
        gl3.glUniform4fv(gl3.glGetUniformLocation(program, "ambientColor"), 1, ambient, 0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program, "diffuseColor"), 1, diffuse, 0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program, "specularColor"), 1, specular, 0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program, "sourceColor"), 1, lightcolor, 0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program, "sourcePosition"), 1, lightpos, 0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program, "ambientLightColor"), 1, amblight, 0);
        gl3.glUniform1f(gl3.glGetUniformLocation(program, "ambientCoefficient"),ka);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program, "diffuseCoefficient"),kd);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program, "specularCoefficient"),ks);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program, "specularExponent"),specExp);
    }
}
