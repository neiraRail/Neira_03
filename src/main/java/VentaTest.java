import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VentaTest {

    @Test
    public void validarOpcionTestFalse() {
        int opcion=10;
        int nroOpciones=3;
        boolean flag=Venta.validarOpcion(opcion,nroOpciones);
        assertFalse(flag);
    }
    @Test
    public void validarOpcionTestTrue(){
        int opcion=3;
        int nroOpciones=3;
        boolean flag=Venta.validarOpcion(opcion,nroOpciones);
        assertTrue(flag);
    }
}