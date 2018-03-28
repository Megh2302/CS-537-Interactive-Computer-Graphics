#version 150

// Texture mapping fragment shader
//
// Contributor:  Megh Vankawala

// INCOMING DATA

// Add all variables containing data used by the
// fragment shader for lighting and texture mapping
in vec2 texCoord;

// OUTGOING DATA

// final fragment color
out vec4 fragmentColor;

//For image 
uniform sampler2D t_id1;

void main()
{
   vec4 front_part = texture2D(t_id1, texCoord);
   fragmentColor = front_part;    
}