package com.cgvsu.math;

// Это заготовка для собственной библиотеки для работы с линейной алгеброй
public class Vector3f {
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean equals(Vector3f other) {
        // todo: желательно, чтобы это была глобальная константа
        final float eps = 1e-7f;
        return Math.abs(x - other.x) < eps && Math.abs(y - other.y) < eps && Math.abs(z - other.z) < eps;
    }

    public Vector3f subtract(Vector3f other) {
        return new Vector3f(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector3f cross(Vector3f other) {
        float newX = this.y * other.z - this.z * other.y;
        float newY = this.z * other.x - this.x * other.z;
        float newZ = this.x * other.y - this.y * other.x;

        return new Vector3f(newX, newY, newZ);
    }

    public Vector3f normalize() {
        float length = (float) Math.sqrt(x * x + y * y + z * z);

        if (length != 0) {
            float invLength = 1.0f / length;
            return new Vector3f(x * invLength, y * invLength, z * invLength);
        } else {
            // Возвращаем нулевой вектор, если длина равна 0 (избегаем деления на 0)
            return new Vector3f(0, 0, 0);
        }
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
