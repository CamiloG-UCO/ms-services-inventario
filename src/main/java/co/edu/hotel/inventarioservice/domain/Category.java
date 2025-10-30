package co.edu.hotel.inventarioservice.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    private int id;

    @Column(name = "name")
    private String name;


}
