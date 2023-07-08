package cl.ufro.showplace_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "availability")
@Data
public class Availability {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "availabilityStart")
    private LocalDateTime availabilityStart;

    @Column(name = "availabilityEnd")
    private LocalDateTime availabilityEnd;

}