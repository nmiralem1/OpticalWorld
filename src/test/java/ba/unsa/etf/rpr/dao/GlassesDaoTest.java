package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.business.GlassesManager;
import ba.unsa.etf.rpr.domain.Glasses;


import ba.unsa.etf.rpr.exceptions.GlassesException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GlassesDaoTest {

    Glasses glasses1 = new Glasses(13,"naocale","lenses","/images/lece1.png",130);
    Glasses glasses2 = new Glasses(14,"naocale","lenses","/images/lece1.png",160);

    private GlassesManager gm;
    private Glasses g;
    private GlassesDaoSQLImpl gmdao;
    private List<Glasses> glasses;

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
