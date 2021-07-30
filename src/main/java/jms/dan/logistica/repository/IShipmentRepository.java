package jms.dan.logistica.repository;

import jms.dan.logistica.dto.OrderDTO;
import jms.dan.logistica.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShipmentRepository extends JpaRepository<Shipment, Integer> {

}
