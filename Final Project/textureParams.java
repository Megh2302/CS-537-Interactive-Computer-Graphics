//
//  textureParams.java
//
//  Created by Srinivas Sridharan on 4/27/17.
//
//  Contributor:  Megh Vankawala
//
//  Simple class for setting up the textures for the textures
//  assignment.
//
//  20155 version
//

import java.io.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;
import com.jogamp.opengl.util.texture.*;

public class textureParams
{

    // Add any data members you need here.
    Texture t_id1, t_id2;

    /**
     * constructor
     */
    public textureParams()
    {
    }

    /**
     * This function loads texture data to the GPU.
     *
     * You will need to write this function, and maintain all of the values
     * needed to be sent to the various shaders.
     *
     * @param gl3 - GL3 object on which all OpenGL calls are to be made
     *
     */
    public void loadTexture( GL3 gl3 )
    {
        try 
        {
            //Read both the images and assign to texture object
            InputStream stream1 = new BufferedInputStream(new FileInputStream("smiley2.png"));
            t_id1 = TextureIO.newTexture(stream1,false, "png");                         
        }
        catch (IOException e) 
        {
            System.exit(1);
        }
    }

    /**
     * This function sets up the parameters for texture use.
     *
     * You will need to write this function, and maintain all of the values
     * needed to be sent to the various shaders.
     *
     * @param program - The ID of an OpenGL (GLSL) program to which
     *    parameter values are to be sent
     *
     * @param gl3 - GL3 object on which all OpenGL calls are to be made
     *
     */
    public void setUpTexture( int program, GL3 gl3 )
    {
        //Binding texture to texture units
    	gl3.glActiveTexture(GL3.GL_TEXTURE0 + 1);
    	t_id1.bind(gl3);
    	//get locations of images
    	int value1 = gl3.glGetUniformLocation(program, "t_id1");
    	//Connect images to texture units
    	gl3.glUniform1i(value1,1);
        
    }
}
