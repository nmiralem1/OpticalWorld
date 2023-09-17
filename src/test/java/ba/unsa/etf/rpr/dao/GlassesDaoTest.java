package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Glasses;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GlassesDaoTest {

    Glasses glasses1 = new Glasses(13,"naocale","lenses","/images/lece1.png",130);
    Glasses glasses2 = new Glasses(14,"naocale","lenses","/images/lece1.png",160);

    @Test
    public void testGettersAndSetters() {

        glasses1.setId(1);


        assertEquals(1, glasses1.getId());

    }
    @Test
    public void testEquals() {
        assertNotEquals(glasses1, glasses2);
    }

    @Test
    public void testHashCode() {
        assertNotEquals(glasses1.hashCode(), glasses2.hashCode());
    }
}
