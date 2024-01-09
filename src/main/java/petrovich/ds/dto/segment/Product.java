package petrovich.ds.dto.segment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
public class Product implements Serializable {

    private static final long serialVersionUID = 8939549535989168093L;

    private String uuid;
    private String code;
    private String name;
    private boolean isGroup;
    private Date date;

    public Product(String uuid) {
        this.uuid = uuid;
    }

    public Product() {
    }

    public static Product withCode(String code) {
        Product product = new Product();
        product.setCode(code);
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return isGroup == product.isGroup &&
                Objects.equals(uuid, product.uuid) &&
                Objects.equals(code, product.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, code, isGroup);
    }
}
