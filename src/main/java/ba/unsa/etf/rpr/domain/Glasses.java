package ba.unsa.etf.rpr.domain;
import java.util.Objects;

/**
 * Information about class Glasses
 * @author nmiralem1
 */

public class Glasses implements Idable {
    private int id;
    private String name, category, image;
    private double price;

    public Glasses() {
    }

    public Glasses(int id, String name, String category, String image, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.image = image;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Glasses glasses = (Glasses) o;
        return id == glasses.id && Double.compare(price, glasses.price) == 0 && Objects.equals(name, glasses.name) && Objects.equals(category, glasses.category) && Objects.equals(image, glasses.image);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, image, price);
    }
    @Override
    public String toString() {
        return "Glasses{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
