package jms.dan.logistica.model;

import jms.dan.logistica.dto.OrderDTO;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double cost;
    private Instant date;
    private String destinationAddress;
    @Transient
    private List<OrderDTO> orders;

    public Shipment() {
    }

    public Shipment(Integer id, Double cost, Instant date, String destinationAddress, List<OrderDTO> orders) {
        this.id = id;
        this.cost = cost;
        this.date = date;
        this.destinationAddress = destinationAddress;
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
