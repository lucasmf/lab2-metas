import models.Meta;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by lucas on 12/5/14.
 */
public class UnitTest {

    static Meta meta;

    @Before
    public void setUp() {
        meta = new Meta("Meta", "Meta1", 3, 4);
    }



    @Test
    public void testaConstrutor() {
        assertEquals(meta.getDescricao(), "Meta1");
        assertEquals(meta.getNome(), "Meta");
        assertEquals(meta.getAlcancada(), 0);
        assertEquals(meta.getSemanasRestantes(), 3);
        assertEquals(meta.getPrioridade(), 4);
    }

    public void testaSetters() {
        meta.setAlcancada(1);
        assertEquals(meta.getAlcancada(), 1);
        meta.setDescricao("Descricao");
        assertEquals(meta.getDescricao(), "Descricao");
        meta.setSemanasRestantes(2);
        assertEquals(meta.getSemanasRestantes(), 2);
        meta.setPrioridade(1);
        assertEquals(meta.getPrioridade(), 1);

    }

}
