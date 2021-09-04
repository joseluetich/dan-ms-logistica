package jms.dan.logistica.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String origin;
    private String destination;
    private Integer duration;
    @OneToOne
    private Truck truck;
    @OneToMany
    private List<Shipment> shipments;

    public Route() {
    }

    public Route(Integer id, String origin, String destination, Integer duration, Truck truck, List<Shipment> shipments) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.truck = truck;
        this.shipments = shipments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public List<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(List<Shipment> shipments) {
        this.shipments = shipments;
    }
}

