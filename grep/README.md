# GREP

## Introducción

Proyecto en Java que demuestra cómo ejecutar procesos externos (el comando `grep`) desde un programa, enviarles texto y capturar su salida.

---

## Estructura del proyecto

```
- grep
  - src
    - main
      - java
        - es/etg/dam
          - Grep.java
    - test
      - java
        - es/etg/dam
          - GrepTest.java
  - .gitignore
  - pom.xml
  - README.md
```

- `Grep.java`: clase principal que ejecuta `grep` sobre un texto predefinido y muestra las líneas que coinciden.  
- `GrepTest.java`: pruebas unitarias que validan la ejecución de procesos, escritura y lectura de datos.

---

## Grep.java

```java
// Lanzar proceso
Process p = lanzar(COMANDO);

// Enviar texto al proceso
escribir(p, CONTENIDO);

// Leer salida y mostrar
System.out.println(RESULTADO + leer(p));
p.waitFor(); 
```

### Metodos

```java
// Metodo lanzar
public static Process lanzar(String comando) throws Exception {
    return Runtime.getRuntime().exec(comando);
}

// Metodo escribir
public static void escribir(Process p, String contenido) throws Exception {
    OutputStream out = p.getOutputStream();
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
    pw.println(contenido);
    pw.close();
}

// Metodo leer
public static String leer(Process p) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
    StringBuilder sb = new StringBuilder();
    String linea;
    while ((linea = br.readLine()) != null) {
        sb.append(linea).append("\n");
    }
    br.close();
    return sb.toString();
}
```

---

## GrepTest.java

```java
// Lanzamiento de procesos (lanzar).
@Test
public void testLanzarProceso() throws Exception {
    Process p = Grep.lanzar(Grep.COMANDO);
    assertNotNull(p);
}

// Escritura de texto en procesos (escribir)
@Test
public void testEscribir() throws Exception {
    Process p = Grep.lanzar("cat");
    Grep.escribir(p, "Hola");

    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
    assertEquals("Hola", br.readLine());
}

// Lectura de texto en procesos (leer)
@Test
public void testLeer() throws Exception {
    Process p = Grep.lanzar("echo hola buenas");
    assertEquals("hola buenas", Grep.leer(p).strip());
}
```

---

## Tecnologías utilizadas

- Java 17
- JUnit 5
- Comando del sistema `grep`