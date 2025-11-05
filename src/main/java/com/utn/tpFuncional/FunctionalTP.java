package com.utn.tpFuncional;

import com.utn.tpFuncional.domain.*;
import java.util.*;
import java.util.stream.Collectors;

public class FunctionalTP {

    public static void main(String[] args) {
        // --- 1. Fuentes de datos de ejemplo ---
        List<Alumno> alumnos = List.of(
                new Alumno("Ana Garcia", 8, "A"),
                new Alumno("Juan Perez", 6, "B"),
                new Alumno("Maria Lopez", 9, "A"),
                new Alumno("Carlos Ruiz", 7, "C"),
                new Alumno("Ester Mota", 5, "B")
        );

        List<Producto> productos = List.of(
                new Producto("Laptop", "Electrónica", 1200.50, 10),
                new Producto("Teclado", "Electrónica", 85.00, 50),
                new Producto("Libro Java", "Libros", 55.99, 100),
                new Producto("Monitor 4K", "Electrónica", 350.00, 5),
                new Producto("Ratón Gamer", "Electrónica", 150.75, 20)
        );

        List<Libro> libros = List.of(
                new Libro("Cien años de soledad", "García Márquez", 496, 25.0),
                new Libro("El Aleph", "Borges", 160, 15.0),
                new Libro("Rayuela", "Cortázar", 632, 28.5),
                new Libro("Ficciones", "Borges", 210, 18.0)
        );

        List<Empleado> empleados = List.of(
                new Empleado("Luis Soto", "Ventas", 2500.0, 35),
                new Empleado("Elena Cruz", "IT", 4500.0, 28),
                new Empleado("David Vera", "Ventas", 1800.0, 42),
                new Empleado("Marta Rey", "IT", 3000.0, 25),
                new Empleado("Ana Gil", "Finanzas", 2200.0, 30)
        );

        System.out.println("=========================================");
        System.out.println("====== CASO PRÁCTICO 1: ALUMNOS =======");
        System.out.println("=========================================");
        casoPractico1(alumnos);

        System.out.println("\n=========================================");
        System.out.println("====== CASO PRÁCTICO 2: PRODUCTOS =======");
        System.out.println("=========================================");
        casoPractico2(productos);

        System.out.println("\n=========================================");
        System.out.println("======== CASO PRÁCTICO 3: LIBROS =========");
        System.out.println("=========================================");
        casoPractico3(libros);

        System.out.println("\n=========================================");
        System.out.println("======== CASO PRÁCTICO 4: EMPLEADOS =======");
        System.out.println("=========================================");
        casoPractico4(empleados);
    }

    // ----------------------------------------------------------------------------------
    // CASO PRÁCTICO 1: Alumno
    // ----------------------------------------------------------------------------------
    public static void casoPractico1(List<Alumno> alumnos) {
        // 1. Obtener nombres aprobados (nota >= 7) en mayúsculas y ordenados.
        List<String> nombresAprobados = alumnos.stream()
                .filter(a -> a.nota() >= 7)
                .map(a -> a.nombre().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
        System.out.println("1. Nombres Aprobados (mayús y ordenados): " + nombresAprobados);

        // 2. Calcular el promedio general de notas.
        double promedioGeneral = alumnos.stream()
                .mapToInt(Alumno::nota)
                .average()
                .orElse(0.0);
        System.out.println("2. Promedio General de Notas: " + String.format("%.2f", promedioGeneral));

        // 3. Agrupar alumnos por curso usando Collectors.groupingBy().
        Map<String, List<Alumno>> alumnosPorCurso = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::curso));
        System.out.println("3. Alumnos Agrupados por Curso: " + alumnosPorCurso);

        // 4. Obtener los 3 mejores promedios. (Top 3 por nota descendente)
        List<String> top3Nombres = alumnos.stream()
                .sorted(Comparator.comparing(Alumno::nota).reversed())
                .limit(3)
                .map(Alumno::nombre)
                .collect(Collectors.toList());
        System.out.println("4. Nombres del Top 3 por Nota: " + top3Nombres);
    }

    // ----------------------------------------------------------------------------------
    // CASO PRÁCTICO 2: Producto
    // ----------------------------------------------------------------------------------
    public static void casoPractico2(List<Producto> productos) {

        // 1. Listar los productos con precio mayor a 100, ordenados por precio descendente.
        List<Producto> productosCarosOrdenados = productos.stream()
                .filter(p -> p.precio() > 100.0)
                .sorted(Comparator.comparing(Producto::precio).reversed())
                .collect(Collectors.toList());
        System.out.println("1. Productos > 100 (ordenados): " + productosCarosOrdenados);

        // 2. Agrupar productos por categoría y calcular el stock total.
        Map<String, Integer> stockTotalPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::categoria,
                        Collectors.summingInt(Producto::stock) // Downstream Collector
                ));
        System.out.println("2. Stock Total por Categoría: " + stockTotalPorCategoria);

        // 3. Generar un String separando con ";" cada producto que contenga nombre y precio.
        String reporteProductos = productos.stream()
                .map(p -> p.nombre() + " (" + String.format("%.2f", p.precio()) + ")")
                .collect(Collectors.joining("; "));
        System.out.println("3. Reporte de Productos (String): " + reporteProductos);

        // 4. Calcular el precio promedio general y por categoría.
        double promedioPrecioGeneral = productos.stream()
                .mapToDouble(Producto::precio)
                .average()
                .orElse(0.0);
        System.out.println("4a. Precio Promedio General: " + String.format("%.2f", promedioPrecioGeneral));

        Map<String, Double> promedioPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::categoria,
                        Collectors.averagingDouble(Producto::precio) // Downstream Collector
                ));
        System.out.println("4b. Promedio por Categoría: " + promedioPorCategoria);
    }

    // ----------------------------------------------------------------------------------
    // CASO PRÁCTICO 3: Libro
    // ----------------------------------------------------------------------------------
    public static void casoPractico3(List<Libro> libros) {

        // 1. Listar los títulos de los libros con más de 300 páginas, ordenados alfabéticamente.
        List<String> titulosLargos = libros.stream()
                .filter(l -> l.paginas() > 300)
                .map(Libro::titulo)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("1. Títulos con más de 300 páginas: " + titulosLargos);

        // 2. Calcular el promedio de páginas de todos los libros.
        double promedioPaginas = libros.stream()
                .mapToInt(Libro::paginas)
                .average()
                .orElse(0.0);
        System.out.println("2. Promedio de Páginas: " + String.format("%.2f", promedioPaginas));

        // 3. Agrupar los libros por autor y contar cuántos libros tiene cada uno.
        Map<String, Long> conteoPorAutor = libros.stream()
                .collect(Collectors.groupingBy(
                        Libro::autor,
                        Collectors.counting() // Downstream Collector
                ));
        System.out.println("3. Conteo de Libros por Autor: " + conteoPorAutor);

        // 4. Obtener el libro más caro de la lista.
        Optional<Libro> libroMasCaro = libros.stream()
                .max(Comparator.comparing(Libro::precio));
        System.out.println("4. Libro Más Caro: " + libroMasCaro.map(Libro::titulo).orElse("No encontrado"));
    }

    // ----------------------------------------------------------------------------------
    // CASO PRÁCTICO 4: Empleado
    // ----------------------------------------------------------------------------------
    public static void casoPractico4(List<Empleado> empleados) {

        // 1. Obtener la lista de empleados cuyo salario sea mayor a 2000, ordenados por salario descendente.
        List<Empleado> empleadosSalarioAlto = empleados.stream()
                .filter(e -> e.salario() > 2000.0)
                .sorted(Comparator.comparing(Empleado::salario).reversed())
                .collect(Collectors.toList());
        System.out.println("1. Empleados con Salario > 2000 (ordenados): " + empleadosSalarioAlto);

        // 2. Calcular el salario promedio general.
        double promedioSalarioGeneral = empleados.stream()
                .mapToDouble(Empleado::salario)
                .average()
                .orElse(0.0);
        System.out.println("2. Salario Promedio General: " + String.format("%.2f", promedioSalarioGeneral));

        // 3. Agrupar los empleados por departamento y calcular la suma de salarios de cada uno.
        Map<String, Double> sumaSalariosPorDepartamento = empleados.stream()
                .collect(Collectors.groupingBy(
                        Empleado::departamento,
                        Collectors.summingDouble(Empleado::salario) // Downstream Collector
                ));
        System.out.println("3. Suma de Salarios por Departamento: " + sumaSalariosPorDepartamento);

        // 4. Obtener los nombres de los 2 empleados más jóvenes.
        List<String> dosMasJovenes = empleados.stream()
                .sorted(Comparator.comparing(Empleado::edad)) // Ordenar por edad ascendente
                .limit(2) // Tomar los primeros 2
                .map(Empleado::nombre) // Mapear solo a los nombres
                .collect(Collectors.toList());
        System.out.println("4. Nombres de los 2 Empleados más Jóvenes: " + dosMasJovenes);
    }
}