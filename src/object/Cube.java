package object;

import java.util.ArrayList;
import util.math.Vector3f;
import util.math.Vector4f;

/**
 *
 * @author marcoslage
 */
class Cube {
    
    Tetrahedra[] tetraedros = new Tetrahedra[6];
    
    
    
    public Cube(Vector3f v0,Vector3f v1,Vector3f v2,Vector3f v3,Vector3f v4,Vector3f v5,Vector3f v6,Vector3f v7){
        
        Tetrahedra t1 = new Tetrahedra(v0, v7, v3, v2);
        Tetrahedra t2 = new Tetrahedra(v0, v7, v2, v6);
        Tetrahedra t3 = new Tetrahedra(v0, v4, v7, v6);
        Tetrahedra t4 = new Tetrahedra(v0, v1, v6, v2);
        Tetrahedra t5 = new Tetrahedra(v0, v4, v6, v1);
        Tetrahedra t6 = new Tetrahedra(v5, v1, v6, v4);
        
        tetraedros[0] = t1;
        tetraedros[1] = t2;
        tetraedros[2] = t3;
        tetraedros[3] = t4;
        tetraedros[4] = t5;
        tetraedros[5] = t6;
        
        
        
    }
    public Cube(){
     tetraedros = new Tetrahedra[6];   
    }
    public void setVertex(Vector3f v0,Vector3f v1,Vector3f v2,Vector3f v3,Vector3f v4,Vector3f v5,Vector3f v6,Vector3f v7){
        Tetrahedra t1 = new Tetrahedra(v0, v7, v3, v2);
        Tetrahedra t2 = new Tetrahedra(v0, v7, v2, v6);
        Tetrahedra t3 = new Tetrahedra(v0, v4, v7, v6);
        Tetrahedra t4 = new Tetrahedra(v0, v1, v6, v2);
        Tetrahedra t5 = new Tetrahedra(v0, v4, v6, v1);
        Tetrahedra t6 = new Tetrahedra(v5, v1, v6, v4);
        
        tetraedros[0] = t1;
        tetraedros[1] = t2;
        tetraedros[2] = t3;
        tetraedros[3] = t4;
        tetraedros[4] = t5;
        tetraedros[5] = t6;
    }
//    protected ArrayList<Vector4f> colors;
//    protected ArrayList<Vector4f> positions;
//    
//    protected int nverts = 8;
//    protected int nfaces = 12;
//    
//    public Cube(){
//        positions = new ArrayList<>(8);
//        colors    = new ArrayList<>(8);
//        
//        // Fill the vertices
//        positions.add( new Vector4f(-0.5f,-0.5f, 0.5f, 1.0f) );
//        positions.add( new Vector4f(-0.5f, 0.5f, 0.5f, 1.0f) );
//        positions.add( new Vector4f( 0.5f, 0.5f, 0.5f, 1.0f) );
//        positions.add( new Vector4f( 0.5f,-0.5f, 0.5f, 1.0f) );
//        positions.add( new Vector4f(-0.5f,-0.5f,-0.5f, 1.0f) );
//        positions.add( new Vector4f(-0.5f, 0.5f,-0.5f, 1.0f) );
//        positions.add( new Vector4f( 0.5f, 0.5f,-0.5f, 1.0f) );
//        positions.add( new Vector4f( 0.5f,-0.5f,-0.5f, 1.0f) );
//
//        // Fill the colors
//        colors.add( new Vector4f( 1.0f, 0.0f, 0.0f, 1.0f) ); 
//        colors.add( new Vector4f( 1.0f, 0.0f, 0.0f, 1.0f) ); 
//        colors.add( new Vector4f( 1.0f, 0.0f, 0.0f, 1.0f) ); 
//        colors.add( new Vector4f( 1.0f, 0.0f, 0.0f, 1.0f) ); 
//        colors.add( new Vector4f( 1.0f, 0.0f, 0.0f, 1.0f) ); 
//        colors.add( new Vector4f( 1.0f, 0.0f, 0.0f, 1.0f) ); 
//        colors.add( new Vector4f( 1.0f, 0.0f, 0.0f, 1.0f) ); 
//        colors.add( new Vector4f( 1.0f, 0.0f, 0.0f, 1.0f) ); 
//    }
}
