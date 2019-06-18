package ru.dev.kirillgolovko.grect.gcode.parcer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ru.dev.kirillgolovko.grect.math.Vector3D;

public class VectorFromTokensBuilder {

    private boolean XyzPresent = false;
    private double x = .0;
    private double y = .0;
    private double z = .0;

    private boolean IjkPresent = false;
    private double i = .0;
    private double j = .0;
    private double k = .0;

    public void addX(double x) {
        this.x = x;
        XyzPresent = true;
    }

    public void addY(double y) {
        this.y = y;
        XyzPresent = true;
    }

    public void addZ(double z) {
        this.z = z;
        XyzPresent = true;
    }

    public void addI(double i) {
        this.i = i;
        IjkPresent = true;
    }

    public void addJ(double j) {
        this.j = j;
        IjkPresent = true;
    }

    public void addK(double k) {
        this.k = k;
        IjkPresent = true;
    }

    public Map<GcofeVectorTypes, Vector3D> bake() {
        Map<GcofeVectorTypes, Vector3D> result = new HashMap<>();
        if(XyzPresent) {
            result.put(GcofeVectorTypes.XYZ, new Vector3D(x, y, z));
        }
        if(IjkPresent) {
            result.put(GcofeVectorTypes.IJK, new Vector3D(i, j, k));
        }
        return Collections.unmodifiableMap(result);
    }
}
