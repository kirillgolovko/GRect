package ru.dev.kirillgolovko.grect;

import ru.dev.kirillgolovko.grect.gcode.parcer.GcodeVectorTypes;
import ru.dev.kirillgolovko.grect.math.Vector3D;

import static org.junit.Assert.*;

public class UtilsTest {

    @org.junit.Test
    public void vector3DtoString() {
        Vector3D vector3D = new Vector3D(-4,-3.2324, -8.9999);

        System.out.println(Utils.Vector3DtoString(vector3D, GcodeVectorTypes.IJK));

        System.out.println(Utils.Vector3DtoString(vector3D, GcodeVectorTypes.XYZ));

    }
}