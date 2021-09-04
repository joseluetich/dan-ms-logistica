package jms.dan.logistica.repository;

import jms.dan.logistica.dto.OrderDTO;
import jms.dan.logistica.model.Shipment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Repository
public class ShipmentRepository {
    private static final List<Shipment> shipmentList = new ArrayList<>();
    private static Integer ID_GEN = 1;
    /*
    public void createShipment(Shipment newShipment) {
        newShipment.setId(ID_GEN++);
        shipmentList.add(newShipment);
    }

    public List<Shipment> getShipments() {
        return shipmentList;
    }

    public Shipment getShipmentById(Integer id) {
        OptionalInt indexOpt = IntStream.range(0, shipmentList.size()).filter(i -> shipmentList.get(i).getId().equals(id)).findFirst();
        if (indexOpt.isPresent()) {
            return shipmentList.get(indexOpt.getAsInt());
        }
        return null;
    }

    public void deleteShipment(Integer shipmentId) {
        OptionalInt indexOpt = IntStream.range(0, shipmentList.size()).filter(i -> shipmentList.get(i).getId().equals(shipmentId)).findFirst();
        if (indexOpt.isPresent()) {
            shipmentList.remove(indexOpt.getAsInt());
        }
    }

    public Shipment updateShipment(Integer shipmentId, Shipment newShipment) {
        OptionalInt indexOpt = IntStream.range(0, shipmentList.size())
                .filter(i -> shipmentList.get(i).getId().equals(shipmentId))
                .findFirst();
        if (indexOpt.isPresent()) {
            shipmentList.set(indexOpt.getAsInt(), newShipment);
            return newShipment;
        }
        return null;
    }

    public void addOrderToShipment(Integer shipmentId, OrderDTO newOrder) {
        OptionalInt indexOpt = IntStream.range(0, shipmentList.size())
                .filter(i -> shipmentList.get(i).getId().equals(shipmentId))
                .findFirst();
        if (indexOpt.isPresent()) {
            List<OrderDTO> orders = shipmentList.get(indexOpt.getAsInt()).getOrders();
            if (orders != null) {
                orders.add(newOrder);
            } else {
                List<OrderDTO> newList = new ArrayList<>();
                newList.add(newOrder);
                shipmentList.get(indexOpt.getAsInt()).setOrders(newList);
            }
        }
    }*/
}
