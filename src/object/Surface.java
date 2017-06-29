/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.ArrayList;
import util.math.Vector3f;
import util.math.Vector4f;

/**
 *
 * @author Flavio
 */
public class Surface {
    
    protected ArrayList<Vector4f> colors = new ArrayList<Vector4f>();;
    protected ArrayList<Vector4f> positions = new ArrayList<Vector4f>();
    protected int nverts;
    protected int nfaces;
    public long resolution;
    public float a, b, c, d, e, f;
    public final float G = 0.25f;
    
    public Surface(float a, float b, float c, float d, float e, float f, long resolution){
        this.a = a; this.b = b; this.c = c; this.d = d; this.e = e; this.f = f;
        float t = 2.0f / resolution;
        System.out.println(t);

        Vector3f v0 = new Vector3f(-1.0f, -1.0f, -1.0f);

        Cube marching = new Cube();
        
        for (int i = 0; i < resolution; i++) {
            float varX = i * t;
            for (int j = 0; j < resolution; j++) {
                float varY = j * t;
                for (int k = 0; k < resolution; k++) {
                    float varZ = k * t;

                    Vector3f v = new Vector3f(v0.x + varX, v0.y + varY, v0.z + varZ);
                    Vector3f v1 = new Vector3f(v.x, v.y, v.z + t);
                    Vector3f v2 = new Vector3f(v.x + t, v.y, v.z + t);
                    Vector3f v3 = new Vector3f(v.x + t, v.y, v.z);
                    Vector3f v4 = new Vector3f(v.x, v.y + t, v.z);
                    Vector3f v5 = new Vector3f(v.x, v.y + t, v.z + t);
                    Vector3f v6 = new Vector3f(v.x + t, v.y + t, v.z + t);
                    Vector3f v7 = new Vector3f(v.x + t, v.y + t, v.z);

                    marching.setVertex(v, v1, v2, v3, v4, v5, v6, v7);

                    for (int l = 0; l < 6; l++) {
                        geraPontos(a, b, c, d, e, f, G, marching.tetraedros[l]);
                    }
                }
            }
        }
    }
    
    public void geraPontos(float a, float b, float c, float d, float e, float f, float isolevel, Tetrahedra t) {
        for (int i = 0; i < 4; i++) {
            Vector3f v = t.vertices[i];
            //System.out.println(v.x);
            v.value = (float)(((Math.pow((double)v.x, (double)b))*a) +
                (Math.pow((double)v.y, (double)d))*c +
                (Math.pow((double)v.z, (double)f))*e);
        }
        
        drawTetrahedron(this, t.vertices, isolevel);
    }
    
    private void drawTetrahedron(Surface surface, Vector3f[] v, float isolevel) {
        char index = (char)0;
        int countF = 0;
        int countV = 0;
        for (int i = 0; i < 4; ++i)
            if (v[i].value < isolevel)
                index |= (1 << i);

        switch ((int)index) {

            // we don't do anything if everyone is inside or outside
            case 0x00:
            case 0x0F:
                break;

            // only vert 0 is inside
            case 0x01:
                drawVert(surface, v[0], v[1], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[0], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[0], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                break;

            // only vert 1 is inside
            case 0x02:
                drawVert(surface, v[1], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[1], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[1], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                break;

            // only vert 2 is inside
            case 0x04:
                drawVert(surface, v[2], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[2], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[2], v[1], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                break;

            // only vert 3 is inside
            case 0x08:
                drawVert(surface, v[3], v[1], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[3], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[3], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                break;

            // verts 0, 1 are inside
            case 0x03:
                drawVert(surface, v[3], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[2], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[1], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                
                drawVert(surface, v[2], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[2], v[1], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[1], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                break;

            // verts 0, 2 are inside
            case 0x05:
                drawVert(surface, v[3], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[1], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[1], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;

                drawVert(surface, v[1], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[3], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[2], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                break;

            // verts 0, 3 are inside
            case 0x09:
                drawVert(surface, v[0], v[1], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[1], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[0], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;

                drawVert(surface, v[1], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[3], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[0], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                break;

            // verts 1, 2 are inside
            case 0x06:
                drawVert(surface, v[0], v[1], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[0], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[1], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;

                drawVert(surface, v[1], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[0], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[3], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                break;

            // verts 2, 3 are inside
            case 0x0C:
                drawVert(surface, v[1], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[2], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[3], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;

                drawVert(surface, v[2], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[1], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[2], v[1], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                break;

            // verts 1, 3 are inside
            case 0x0A:
                drawVert(surface, v[3], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[1], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[1], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;

                drawVert(surface, v[1], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[2], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[3], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                break;

            // verts 0, 1, 2 are inside
            case 0x07:
                drawVert(surface, v[3], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[3], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[3], v[1], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                break;

            // verts 0, 1, 3 are inside
            case 0x0B:
                drawVert(surface, v[2], v[1], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[2], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[2], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                
                drawVert(surface, v[2], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[2], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[2], v[1], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                break;

            // verts 0, 2, 3 are inside
            case 0x0D:
                drawVert(surface, v[1], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[1], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[1], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                
                drawVert(surface, v[1], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[1], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[1], v[0], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                break;

            // verts 1, 2, 3 are inside
            case 0x0E:
                drawVert(surface, v[0], v[1], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[0], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[0], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                
                drawVert(surface, v[0], v[3], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[0], v[2], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                drawVert(surface, v[0], v[1], isolevel);
                colors.add(new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));
                countV++;
                countF++;
                break;

            // what is this I don't even
            default:
                assert(false);
        }
        
        nfaces += countF;
        nverts += countV;

    }

    private void drawVert(Surface surface, Vector3f p1, Vector3f p2, float isolevel) {
        float v1 = p1.value;
        float v2 = p2.value;

        float x, y, z;

        if (v2 == v1) {
            x = (p1.x + p2.x) / 2.0f;
            y = (p1.y + p2.y) / 2.0f;
            z = (p1.z + p2.z) / 2.0f;
        } else {

            /*

             <----+-----+---+----->
                  v1    |   v2
                     isolevel


             <----+-----+---+----->
                  0     |   1
                      interp

             */


            // interp == 0: vert should be at p1
            // interp == 1: vert should be at p2
            float interp = (isolevel - v1) / (v2 - v1);
            float oneMinusInterp = 1 - interp;

            x = p1.x * oneMinusInterp + p2.x * interp;
            y = p1.y * oneMinusInterp + p2.y * interp;
            z = p1.z * oneMinusInterp + p2.z * interp;
        }
            surface.positions.add(new Vector4f(x, y, z, 1));
            surface.nfaces++;
            surface.nverts++;
        
    }
    
    public float calculatePoints(float x, float y, float z){
        return (float)(((Math.pow((double)x, (double)b))*a) +
                (Math.pow((double)y, (double)d))*c +
                (Math.pow((double)z, (double)f))*e);
    }
    
    public boolean isPointContained(float x, float y, float z){
        return calculatePoints(x, y, z) <= G;
    }
    
    public Vector3f normalize(Vector3f a){
        float scale = 0;
        scale+=a.x*a.x;
        scale+=a.y*a.y;
        scale+=a.z*a.z;
        
        scale = (float) (1/Math.sqrt(scale));
        a.x *= scale;
        a.y *= scale;
        a.z *= scale;
        return a;
    }
}
