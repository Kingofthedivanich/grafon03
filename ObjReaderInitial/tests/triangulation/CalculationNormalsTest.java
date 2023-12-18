package triangulation;

import com.cgvsu.math.CalculationNormals;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import com.cgvsu.math.Vector3f;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CalculationNormalsTest {

    private Model model;

    @Before
    public void setUp() {
        // Создаем модель и добавляем несколько полигонов и вершин
        model = new Model();
        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(1, 0, 0));
        model.vertices.add(new Vector3f(0, 1, 0));

        Polygon polygon1 = new Polygon();
        polygon1.addVertexIndex(0);
        polygon1.addVertexIndex(1);
        polygon1.addVertexIndex(2);
        model.addPolygon(polygon1);
    }

    @Test
    public void testCalculateNormals() {
        CalculationNormals.calculateNormals(model);

        // Проверяем, что количество нормалей совпадает с количеством вершин модели
        assertEquals(model.vertices.size(), model.normals.size());

        // Проверяем, что нормали вычислены правильно (например, что они не нулевые)
        for (Vector3f normal : model.normals) {
            assertEquals(1.0, normal.magnitude(), 0.0001); // Проверяем, что длина нормали равна 1 с некоторой погрешностью
        }
    }

    @Test
    public void testCalculateNormalForVertex() {
        ArrayList<Vector3f> normals = new ArrayList<>();
        normals.add(new Vector3f(1, 0, 0));
        normals.add(new Vector3f(0, 1, 0));

        // Вычисляем нормаль для вершины
        Vector3f result = CalculationNormals.calculateNormalForVertex(normals);

        // Печатаем результат для отладки
        System.out.println("Result: " + result.toString());

        // Проверяем, что результат верный
        assertEquals(new Vector3f(0.5f, 0.5f, 0), result);
    }

    @Test
    public void testCalculateNormalForPolygon() {
        // Создаем полигон с индексами вершин
        Polygon polygon = new Polygon();
        polygon.addVertexIndex(0);
        polygon.addVertexIndex(1);
        polygon.addVertexIndex(2);

        // Вычисляем нормаль для полигона
        Vector3f result = CalculationNormals.calculateNormalForPolygon(model, polygon);

        // Проверяем, что результат верный
        assertEquals(new Vector3f(0, 0, 1), result);
    }
}