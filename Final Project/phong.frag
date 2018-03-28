#version 150

// Phong fragment shader
//
// Contributor:  Megh Vankawala

// INCOMING DATA

// Add all variables containing data used by the
// fragment shader for lighting and shading
uniform vec4 ambientColor ;
uniform vec4 diffuseColor;
uniform vec4 specularColor;
uniform vec4 sourceColor;
uniform vec4 ambientLightColor;
uniform float ambientCoefficient;
uniform float diffuseCoefficient;
uniform float specularCoefficient;
uniform float specularExponent;

// OUTGOING DATA

// final fragment color
out vec4 fragmentColor;

varying vec3 vN;
varying vec3 vP;
varying vec3 light;

void main()
{
	// Add all necessary code to implement the
    // fragment shader portion of Phong shading
    fragmentColor = vec4( 1.0, 1.0, 1.0, 1.0 );
    vec3 N = normalize ( vN );
    vec3 L = normalize ( light - vP);
    vec3 R = normalize ( reflect( L, N));
    vec3 V = normalize ( vP );
    
    //formula for Phong shading
    vec4 ambient_part, diffuse_part, specular_part;
    
    ambient_part = ambientColor * ambientCoefficient * ambientLightColor;
    diffuse_part = diffuseColor * diffuseCoefficient * dot (L, N);
    specular_part = specularColor * specularCoefficient * pow ( max ( dot ( V, R), 0.0 ), specularExponent);
    
    fragmentColor = ambient_part + sourceColor * (diffuse_part + specular_part); 
    
}