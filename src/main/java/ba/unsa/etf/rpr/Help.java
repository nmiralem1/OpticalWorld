package ba.unsa.etf.rpr;
import java.util.Objects;

/**
 * Information about class Help
 * @author nmiralem1
 */

public class Help {
    private int productId;
    private int orderId;
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Help help = (Help) o;
        return orderId == help.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }

}
