Trabajo Práctico: Programación Funcional en Java (Streams y Lambdas)
Este repositorio contiene la resolución del Trabajo Práctico de Programación Funcional para la asignatura Programación III de la Tecnicatura Universitaria en Programación a Distancia (UTN).

El objetivo es practicar el uso de operaciones intermedias y terminales de la API de Streams en Java, junto con Expresiones Lambda y Collectors, para procesar colecciones de forma declarativa.


Organización del Repositorio
El proyecto está estructurado como un proyecto Maven o Gradle (si usas IntelliJ por defecto) y sigue la siguiente organización de paquetes dentro de la carpeta src/main/java:

tp-funcional-utn/
└── src/
    └── main/
        └── java/
            └── com.utn.tpfuncional/
                ├── FunctionalTP.java      <- Clase Principal con la lógica de los 4 Casos.
                └── domain/
                    ├── Alumno.java        <- Entidad Caso 1
                    ├── Producto.java      <- Entidad Caso 2
                    ├── Libro.java         <- Entidad Caso 3
                    └── Empleado.java      <- Entidad Caso 4

Cómo Ejecutar los Ejercicios
Este proyecto es un programa de consola estándar de Java.

Requisitos
Java Development Kit (JDK): Versión 17 o superior (necesaria para el uso de record y características modernas de Stream).

IDE: IntelliJ IDEA (recomendado) o VS Code con extensión Java.

Pasos de Ejecución
Clonar el Repositorio:

Bash

git clone https://www.youtube.com/watch?v=44ziZ12rJwU
cd tp-funcional-utn
Abrir en IDE: Importe la carpeta tp-funcional-utn en su IDE de preferencia (IntelliJ lo detectará automáticamente como proyecto Java).

Ejecutar la Clase Principal:

Localice la clase com.utn.tpfuncional.FunctionalTP.

Ejecute el método main() (botón verde de "Run" o clic derecho > Run 'main()').

Salida Esperada: El programa imprimirá en la consola la resolución paso a paso de los cuatro casos prácticos, mostrando los resultados de cada operación de Stream.
