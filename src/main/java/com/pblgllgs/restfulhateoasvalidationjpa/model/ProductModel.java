package com.pblgllgs.restfulhateoasvalidationjpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_products", uniqueConstraints = {@UniqueConstraint(name = "name_UK", columnNames = "name")})
public class ProductModel extends RepresentationModel<ProductModel> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id_product"
    )
    private UUID idProduct;
    private String name;
    private BigDecimal value;

    @OneToOne(
            mappedBy = "productModel",
            fetch = FetchType.EAGER
    )
    @JsonIgnore
    private Specs spec;

    public UUID getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Specs getSpec() {
        return spec;
    }

    public void setSpec(Specs spec) {
        this.spec = spec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductModel model = (ProductModel) o;
        return Objects.equals(idProduct, model.idProduct) && Objects.equals(name, model.name) && Objects.equals(value, model.value) && Objects.equals(spec, model.spec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idProduct, name, value, spec);
    }
}
