package cl.ufro.showplace_api.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;
    private double longitude;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    private String commune;

    private String province;

    private String region;

    private String country;

    @OneToOne(mappedBy = "location")
    private Publication publication;
}