package petrovich.ds.dto.segment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import petrovich.ds.campaign.segment.Product;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductGroup implements Serializable {

    private static final long serialVersionUID = -1586320337909190685L;
    @NotEmpty
    private String id;
    /**
     * Идентификатор общей группы товаров {@code ProductGroupDefinition}.
     */
    private String groupId;
    private String name;
    private List<Product> products;

    public ProductGroup(List<Product> products) {
        this.products = products;
    }

    public Set<String> productIds() {
        return products.stream().map(Product::getUuid).collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductGroup that = (ProductGroup) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getProducts(), that.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getProducts());
    }
}
