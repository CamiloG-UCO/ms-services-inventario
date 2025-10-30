package co.edu.hotel.inventarioservice.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "general_inventory")
public class GeneralInventory {

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "hotel_id")
    private int hotel_id;

    @Column(name = "user_id")
    private int user_id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private List<Product> products;


    @Column(name = "quantity")
    private int quantity;

    @Column(name = "min_stock")
    private int min_stock;

    @Column(name = "location" )
    private String location;

    @Column(name = "register_code")
    private String register_code;






}
