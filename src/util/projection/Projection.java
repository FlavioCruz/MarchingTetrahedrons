package util.projection;

import util.math.FastMath;
import util.math.Matrix4f;

/**
 *
 * @author mlage
 */
public class Projection {
    
    private float fovY   = 0.0f;
    private float aspect = 0.0f;
    private float zNear  = 0.0f;
    private float zFar   = 0.0f;
        private float t   = -1.0f;
            private float b   = 1.0f;
                private float r   = 1.0f;
                    private float l   = -1.0f;
    
    public Projection(float fovY, float aspect, float zNear, float zFar){
        this.fovY   = fovY;
        this.aspect = aspect;
        this.zNear  = zNear;
        this.zFar   = zFar;
//        this.t = top;
//        this.b = bottom;
//        this.l = left;
//        this.r = right;
    }
    
    public Matrix4f perspective() {
        float angle = fovY / 2.0f * FastMath.DEG_TO_RAD;
        float cotangent = FastMath.cos(angle) / FastMath.sin(angle);
        float top = zNear * (FastMath.sin(angle)/FastMath.cos(angle));
        float bottom = -top;
        float right = top * aspect;
        float left = -right;
        float m11 = (2 * zNear)/(right - left);
        float m22 = (2 * zNear)/(top - bottom);
        float m31 = (right + left)/(right - left);
        float m32 = (top + bottom)/(top - bottom);  
        float m33 = (-1) * ((zFar + zNear)/(zFar - zNear));
        float m43 = ((-2) * (zFar * zNear))/(zFar - zNear);
        float e = cotangent / aspect; // focal length of the camera

        float fpn = zFar + zNear;  // far plus near
        float fmn = zFar - zNear;  // far minus near

        Matrix4f tempMatrix = new Matrix4f();
        tempMatrix.m11 = e;     tempMatrix.m21 = 0.0f;       tempMatrix.m31 = 0.0f;        tempMatrix.m41 = 0.0f;
        tempMatrix.m12 = 0.0f;  tempMatrix.m22 = cotangent;  tempMatrix.m32 = 0.0f;        tempMatrix.m42 = 0.0f;
        tempMatrix.m13 = 0.0f;  tempMatrix.m23 = 0.0f;       tempMatrix.m33 = -(fpn/fmn);  tempMatrix.m43 = -2.0f * zNear * zFar / fmn;
        tempMatrix.m14 = 0.0f;  tempMatrix.m24 = 0.0f;       tempMatrix.m34 = -1.0f;       tempMatrix.m44 = 0.0f;


        return tempMatrix;
    }
    
        public Matrix4f orthogonal() {

        float fpn = zFar + zNear;  // far plus near
        float fmn = zFar - zNear;  // far minus near

        Matrix4f tempMatrix = new Matrix4f();

        tempMatrix.setToIdentity();
        tempMatrix.m33 = 0;
        

        return tempMatrix;
    }
}
