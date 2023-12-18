package com.cgvsu.math;

import java.util.Objects;

public class Vector3f {
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f() { }

    public boolean equals(Vector3f other) {
        final float eps = 1e-7f;
        return Math.abs(x - other.x) < eps && Math.abs(y - other.y) < eps && Math.abs(z - other.z) < eps;
    }

    public static Vector3f fromTwoPoints(Vector3f vertex1, Vector3f vertex2) {
        return new Vector3f(vertex2.x - vertex1.x,
                vertex2.y - vertex1.y,
                vertex2.z - vertex1.z);
    }

    public void cross(Vector3f v1, Vector3f v2) {
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Vector3f can not be null");
        }

        float x = v1.y * v2.z - v1.z * v2.y;
        float y = v2.x * v1.z - v2.z * v1.x;

        this.z = v1.x * v2.y - v1.y * v2.x;
        this.x = x;
        this.y = y;
    }

    public Vector3f add(Vector3f v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
        return this;
    }

    public Vector3f div(float scalar) {
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
        return this;
    }

    public float magnitude() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3f round(int decimalPlaces) {
        float factor = (float) Math.pow(10, decimalPlaces);
        float newX = Math.round(x * factor) / factor;
        float newY = Math.round(y * factor) / factor;
        float newZ = Math.round(z * factor) / factor;
        return new Vector3f(newX, newY, newZ);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Vector3f other = (Vector3f) obj;

        final float eps = 1e-7f;
        return Math.abs(x - other.x) < eps && Math.abs(y - other.y) < eps && Math.abs(z - other.z) < eps;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return String.format("(%f, %f, %f)", x, y, z);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    float x, y, z;
}
