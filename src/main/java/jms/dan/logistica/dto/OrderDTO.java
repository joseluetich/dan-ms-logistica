package jms.dan.logistica.dto;

import java.time.Instant;
import java.util.List;

public class OrderDTO {
    private Integer id;
    private List<OrderDetailDTO> details;

    public OrderDTO() {
    }

    public OrderDTO(Integer id, List<OrderDetailDTO> details) {
        this.id = id;
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OrderDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetailDTO> details) {
        this.details = details;
    }
}
