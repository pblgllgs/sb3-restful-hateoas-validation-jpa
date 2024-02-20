package com.pblgllgs.restfulhateoasvalidationjpa.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "specs")
public class Specs implements Serializable {

    @Serial
    private static final long serialVersionUID =2L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "id_specs")
    private long idSpec;
    @CreationTimestamp
    private Date date;

    @OneToOne
    private ProductModel productModel;

    public Specs() {
    }

    public Specs(Date date) {
        this.date = date;
    }

    public long getIdSpec() {
        return idSpec;
    }

    public void setIdSpec(long idSpec) {
        this.idSpec = idSpec;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
