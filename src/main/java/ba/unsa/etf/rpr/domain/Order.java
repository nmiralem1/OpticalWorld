package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Information about class Order
 * @author nmiralem1
 */

public class Order implements Idable {
    private Glasses glasses;
    private User userId;
    private int total;

    public Order(int total, User user2, Glasses glasses) {
    }

    public Order() {

    }

    public Glasses getGlasses(){ return glasses;}
    public void setGlasses(Glasses glasses){ this.glasses=glasses;}

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
    public void setTotal(int total){ this.total=total;}
    public int getTotal(){ return total;}

    @Override
    public String toString() {
        return "Order{" +
                "glasses=" + glasses +
                ", userId='" + userId + '\'' +
                ", total= " + total+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return glasses == order.glasses;
    }

    @Override
    public int hashCode() {
        return Objects.hash(glasses, userId,total);
    }


    @Override
    public void setId(int id) {

    }

    @Override
    public int getId() {
        return 0;
    }
}
