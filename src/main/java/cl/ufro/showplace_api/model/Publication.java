package cl.ufro.showplace_api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "publication")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Publication {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "pricePerHour")
    private String pricePerHour;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    private Customer tenant;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publication")
    private List<Booking> booking = new ArrayList<Booking>();

    @Column(name = "paymentPolicy")
    @Enumerated(value = EnumType.STRING)
    private PaymentPolicy paymentPolicy;

    @Column(name = "typeOfActivity")
    @Enumerated(value = EnumType.STRING)
    private TypeOfActivity typeOfActivity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_id", referencedColumnName = "id")
    public Availability availability;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "publication_id")
    public List<Qualification> qualification = new ArrayList<Qualification>();

    @Column(name = "collectionPolicy")
    @Enumerated(value = EnumType.STRING)
    private CollectionPolicy collectionPolicy;

}