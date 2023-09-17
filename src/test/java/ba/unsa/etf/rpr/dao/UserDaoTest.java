package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {
    @Test
    public void testUserConstructor() {
        User user = new User(1, "nadina", "miralem", "nmiralem1@etf.unsa.ba", "nmiralem1");
        assertEquals("nadina", user.getFirstName());
        assertEquals("miralem", user.getLastName());
        assertEquals("nmiralem1@etf.unsa.ba", user.getEmail());
        assertEquals(1, user.getRole());
        assertEquals("nmiralem1", user.getUsername());
    }

    @Test
    public void testUserSettersAndGetters() {
        User user = new User();
        user.setFirstName("nadina");
        user.setLastName("miralem");
        user.setEmail("nmiralem1@etf.unsa.ba");
        user.setRole(1);
        user.setUsername("nmiralem1");

        assertEquals("nadina", user.getFirstName());
        assertEquals("miralem", user.getLastName());
        assertEquals("nmiralem1@etf.unsa.ba", user.getEmail());
        assertEquals(1, user.getRole());
        assertEquals("nmiralem1", user.getUsername());
    }
}