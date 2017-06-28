/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import util.math.Vector3f;

/**
 *
 * @author Flavio
 */
public class Tetrahedra {
    public Vector3f[] vertices;
    
    public Tetrahedra(Vector3f v0, Vector3f v1, Vector3f v2, Vector3f v3){
        
        vertices = new Vector3f[4];
        
        vertices[0] = v0;
        vertices[1] = v1;
        vertices[2] = v2;
        vertices[3] = v3;
        
    }

}
