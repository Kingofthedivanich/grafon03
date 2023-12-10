package com.cgvsu.model;
import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;

import java.util.*;

public class Model {

    public ArrayList<Vector3f> vertices = new ArrayList<Vector3f>();
    public ArrayList<Vector2f> textureVertices = new ArrayList<Vector2f>();
    public ArrayList<Vector3f> normals = new ArrayList<Vector3f>();
    public ArrayList<Polygon> polygons = new ArrayList<Polygon>();

    public void calculateNormals() {

        for (int i = 0; i < vertices.size(); i++) {
            normals.add(new Vector3f(0, 0, 0));
        }

        for (Polygon polygon : polygons) {
            List<Integer> vertexIndices = polygon.getVertexIndices();
            if (vertexIndices.size() < 3) {
                throw new IllegalArgumentException("Polygon must have at least 3 vertices");
            }

            Vector3f v1 = vertices.get(vertexIndices.get(0));
            Vector3f v2 = vertices.get(vertexIndices.get(1));
            Vector3f v3 = vertices.get(vertexIndices.get(2));

            Vector3f edge1 = v2.subtract(v1);
            Vector3f edge2 = v3.subtract(v1);

            Vector3f zeroVector = new Vector3f(0, 0, 0);

            // Проверка, что векторы не являются нулевыми
            if (!edge1.equals(zeroVector) && !edge2.equals(zeroVector)) {
                Vector3f normal = edge1.cross(edge2).normalize();

                for (int index : vertexIndices) {
                    Vector3f currentNormal = normals.get(index);
                    Vector3f updatedNormal = new Vector3f(
                            currentNormal.getX() + normal.getX(),
                            currentNormal.getY() + normal.getY(),
                            currentNormal.getZ() + normal.getZ()
                    );
                    normals.set(index, updatedNormal);
                }
            }
        }

        for (int i = 0; i < normals.size(); i++) {
            Vector3f normalizedNormal = normals.get(i).normalize();
            normals.set(i, normalizedNormal);
        }
    }
}
