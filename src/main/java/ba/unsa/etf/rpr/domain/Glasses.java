package ba.unsa.etf.rpr.domain;
import java.util.Objects;

/**
 * Information about class Product
 * @author nmiralem1
 */

public class Glasses implements Idable {
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String category;
    private double price;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name "+ name +
                ", category='" + category + '\'' +
                ", price=" + price +

                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Glasses product = (Glasses) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, price);
    }
}
