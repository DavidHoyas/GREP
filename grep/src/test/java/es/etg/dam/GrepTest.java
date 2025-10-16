package es.etg.dam;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class GrepTest {

    @Test
    public void testLanzarProceso() throws Exception {
        Process p = Grep.lanzar(Grep.COMANDO);
        assertNotNull(p);
    }

    @Test
    public void testEscribir() throws Exception {
        Process p = Grep.lanzar("cat");
        Grep.escribir(p, "Hola");

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        assertEquals("Hola", br.readLine());
    }

    @Test
    public void testLeer() throws Exception {
        Process p = Grep.lanzar("echo hola buenas");
        assertEquals("hola buenas", Grep.leer(p).strip());
    }

}
