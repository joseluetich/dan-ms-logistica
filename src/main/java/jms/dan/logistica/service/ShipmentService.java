package jms.dan.logistica.service;

import jms.dan.logistica.dto.OrderDTO;
import jms.dan.logistica.exceptions.ApiException;
import jms.dan.logistica.model.Shipment;
import jms.dan.logistica.repository.IShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ShipmentService implements IShipmentService {
    final IShipmentRepository shipmentRepository;

    @Autowired
    public ShipmentService(IShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public void createShipment(Shipment shipment) {
        Shipment newShipment = new Shipment();
        newShipment.setCost(shipment.getCost());
        newShipment.setDate(shipment.getDate());
        newShipment.setDestinationAddress(shipment.getDestinationAddress());
        for (OrderDTO order: shipment.getOrders()) {
            checkOrder(order);
        }
        newShipment.setOrders(shipment.getOrders());
        shipmentRepository.save(newShipment);
    }

    @Override
    public Shipment updateShipment(Integer shipmentId, Shipment newShipment) {
        Shipment shipment = checkShipment(shipmentId);
        shipment.setCost(newShipment.getCost());
        shipment.setDate(newShipment.getDate());
        shipment.setDestinationAddress(newShipment.getDestinationAddress());
        shipment.setOrders(newShipment.getOrders());
        shipmentRepository.save(shipment);
        return shipment;
    }

    @Override
    public void deleteShipment(Integer shipmentId) {
        checkShipment(shipmentId);
        shipmentRepository.deleteById(shipmentId);
    }

    @Override
    public void addOrderToShipment(OrderDTO newOrder, Integer shipmentId) {
        checkOrder(newOrder);
        Shipment shipment = checkShipment(shipmentId);
        List<OrderDTO> orders = shipment.getOrders();
        if (orders != null) {
            orders.add(newOrder);
            shipment.setOrders(orders);
        } else {
            List<OrderDTO> newList = new ArrayList<>();
            newList.add(newOrder);
            shipment.setOrders(newList);
        }
        shipmentRepository.save(shipment);
    }

    @Override
    public List<Shipment> getShipments() {
        return shipmentRepository.findAll();
    }

    @Override
    public Shipment getShipmentById(Integer shipmentId) {
        return checkShipment(shipmentId);
    }

    public Shipment checkShipment(Integer shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId).orElse(null);
        if (shipment == null) {
            throw new ApiException(HttpStatus.NOT_FOUND.toString(), "Shipment not found", HttpStatus.NOT_FOUND.value());
        }
        return shipment;
    }

    public void checkOrder(OrderDTO order) {
        WebClient webClient = WebClient.create("http://localhost:8081/api-orders/orders");
        try {
            ResponseEntity<List<OrderDTO>> response = webClient.get()
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toEntityList(OrderDTO.class)
                    .block();
            if (response != null && response.getBody() != null && response.getStatusCode().equals(HttpStatus.OK)) {
                List<OrderDTO> orders = response.getBody();
                if(!orders.contains(order)) {
                    throw new ApiException(HttpStatus.NOT_FOUND.toString(), "Order not found", HttpStatus.NOT_FOUND.value());
                }
            }
        } catch (WebClientException e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "An error has occurred", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
