package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.Glasses;
import ba.unsa.etf.rpr.domain.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderDaoTest {

    @Test
    void gettersAndSettersTest() {

        Order order = new Order();
        order.setId(21);
        order.setTotal(150);
        order.setGlassesID(new Glasses(13,"naocale","lenses","/images/lece1.png",130));
        order.setUserID(new User());

        assertEquals(21, order.getId());
        assertEquals(150,order.getTotal());
        assertNotNull(order.getGlassesID());
        assertNotNull(order.getUserID());
    }

}