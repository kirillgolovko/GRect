package ru.dev.kirillgolovko.grect.math;

/**
 * @author kirill
 */
public class Vector3D {

    public final double x;

    public final double y;

    public final double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3D zeros(){
        return new Vector3D(0, 0, 0);
    }
}
