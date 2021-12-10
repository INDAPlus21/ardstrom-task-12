package com.example.ardstromtask12;
public class PVector { // vectorclass for up to 3 dimensions

    public double x;
    public double y;
    public double z;

    PVector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    PVector(double x, double y, double z) {
        this(x,y);
        this.z = z;
    }

    public void add(PVector v) {
        y = y + v.y;
        x = x + v.x;
        z = z + v.z;
    }
    public void sub(PVector v){
        y = y - v.y;
        x = x - v.x;
        z = z - v.z;
    }
    public void mult(double scalar){
        x *= scalar;
        y *= scalar;
        z *= scalar;
    }
    public void div(double scalar){
        x /= scalar;
        y /= scalar;
        z /= scalar;
    }
    public double mag() {
        return Math.sqrt(x*x + y*y + z*z);
    }
    public void normalize() {
        double magnitude = mag();
        if (magnitude != 0) {
            div(magnitude);
        }
    }
    public void limit(double max) {
        if (mag() > max) {
            normalize();
            mult(max);
        }
    }


    //Static functions

    public static PVector add(PVector v1, PVector v2) {
        return (new PVector(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z));
    }
    public static PVector sub(PVector v1, PVector v2) {
        return (new PVector(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z));
    }
    public static PVector mult(PVector v1, double scalar) {
        return (new PVector(v1.x * scalar, v1.y * scalar, v1.z * scalar));
    }
    public static PVector div(PVector v1, double scalar) {
        return (new PVector(v1.x /scalar, v1.y / scalar, v1.z / scalar));
    }


}