package jms.dan.logistica.service;

import jms.dan.logistica.dto.OrderDTO;
import jms.dan.logistica.model.Shipment;

import java.util.List;

public interface IShipmentService {
    void createShipment(Shipment newShipment);
    Shipment updateShipment(Integer shipmentId, Shipment newShipment);
    void deleteShipment(Integer shipmentId);
    void addOrderToShipment(OrderDTO orderDTO, Integer shipmentId);
    List<Shipment> getShipments();
    Shipment getShipmentById(Integer shipmentId);
}
