package jms.dan.logistica.controller;

import jms.dan.logistica.dto.OrderDTO;
import jms.dan.logistica.exceptions.ApiError;
import jms.dan.logistica.exceptions.ApiException;
import jms.dan.logistica.model.Shipment;
import jms.dan.logistica.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {
    final ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping
    public ResponseEntity<?> createShipment(@RequestBody Shipment newShipment) {
        try {
            shipmentService.createShipment(newShipment);
            return ResponseEntity.status(HttpStatus.CREATED).body("Shipment created successfully");
        } catch (ApiException e) {
            return new ResponseEntity<>(
                    new ApiError(e.getCode(), e.getDescription(), e.getStatusCode()),
                    HttpStatus.valueOf(e.getStatusCode()));
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateShipment(@PathVariable Integer id, @RequestBody Shipment newShipment) {
        try {
            Shipment shipment = shipmentService.updateShipment(id, newShipment);
            return ResponseEntity.ok(shipment);
        } catch (ApiException e) {
            return new ResponseEntity<>(
                    new ApiError(e.getCode(), e.getDescription(), e.getStatusCode()),
                    HttpStatus.valueOf(e.getStatusCode()));
        }
    }

    @PostMapping(path = "/{id}/order")
    public ResponseEntity<?> addOrderToShipment(@RequestBody OrderDTO newOrder, @PathVariable Integer id) {
        try {
            shipmentService.addOrderToShipment(newOrder, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order added successfully");
        } catch (ApiException e) {
            return new ResponseEntity<>(
                    new ApiError(e.getCode(), e.getDescription(), e.getStatusCode()),
                    HttpStatus.valueOf(e.getStatusCode()));
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteShipment(@PathVariable Integer id) {
        try {
            shipmentService.deleteShipment(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Shipment deleted successfully");
        } catch (ApiException e) {
            return new ResponseEntity<>(
                    new ApiError(e.getCode(), e.getDescription(), e.getStatusCode()),
                    HttpStatus.valueOf(e.getStatusCode()));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getShipmentById(@PathVariable Integer id) {
        try {
            Shipment shipment = shipmentService.getShipmentById(id);
            return new ResponseEntity<>(shipment, HttpStatus.OK);
        } catch (ApiException e) {
            return new ResponseEntity<>(
                    new ApiError(e.getCode(), e.getDescription(), e.getStatusCode()),
                    HttpStatus.valueOf(e.getStatusCode()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getShipments() {
        return ResponseEntity.ok(shipmentService.getShipments());
    }

}
