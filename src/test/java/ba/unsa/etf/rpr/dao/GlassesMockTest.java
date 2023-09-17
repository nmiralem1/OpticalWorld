package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.business.GlassesManager;
import ba.unsa.etf.rpr.dao.GlassesDao;
import ba.unsa.etf.rpr.dao.GlassesDao;
import ba.unsa.etf.rpr.domain.Glasses;
import ba.unsa.etf.rpr.exceptions.GlassesException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GlassesMockTest {

    private Glasses glasses = new Glasses(13,"naocale","lenses","/images/lece1.png",130);

    private GlassesManager gm;
    @Mock private GlassesDao gmdao;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Postavljanje ponašanja mock objekta
        gm = new GlassesManager();
    }

    @Test
    void constructorTest() {
        assertEquals(13, glasses.getId());
        assertEquals(130, glasses.getPrice());
        assertEquals("naocale", glasses.getName());
        assertEquals("lenses", glasses.getCategory());

    }

    @Test
    void equalsTest() {
        Glasses other = new Glasses(15,"naocale","lenses","/images/lece1.png",160);
        // Provjera jednakosti sa drugom sobom istih svojstava
        assertNotEquals(other, glasses);
    }

    @Test
    void addTest() throws GlassesException {
        gmdao.add(glasses);
        verify(gmdao).add(glasses);
    }

    @Test
    void updateTest() throws GlassesException {
        glasses.setPrice(190);
        gmdao.update(glasses);
        verify(gmdao).update(glasses);
    }

}