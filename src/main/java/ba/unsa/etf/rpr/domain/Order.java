package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Information about class Order
 * @author nmiralem1
 */

public class Order implements Idable {

    private Glasses glassesID;
    private User userID;
    private int total,id;

    public Order() {
    }

    public Order(Glasses glassesID, User userID, int total,int id) {
        this.glassesID = glassesID;
        this.userID = userID;
        this.total = total;
        this.id=id;
    }

    public Order(Glasses glassesID, User userID, int total) {
        this.glassesID = glassesID;
        this.userID = userID;
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return total == order.total && id == order.id && glassesID.equals(order.glassesID) && userID.equals(order.userID);
    }

    @Override
    public String toString() {
        return "Order{" +
                "glassesID=" + glassesID +
                ", userID=" + userID +
                ", total=" + total +
                ", id=" + id +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(glassesID, userID, total, id);
    }

    public Glasses getGlassesID() {
        return glassesID;
    }

    public void setGlassesID(Glasses glassesID) {
        this.glassesID = glassesID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
