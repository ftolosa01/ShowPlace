package cl.ufro.showplace_api.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "booking")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Booking {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "bookingStart")
	private LocalDateTime bookingStart;

	@Column(name = "bookingEnd")
	private LocalDateTime bookingEnd;

	@Column(name = "price")
	private String price;

	@Column(name = "confirmedReservation")
	private boolean confirmedReservation;

	@OneToOne(mappedBy = "booking", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
	private Event event;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pay_id",updatable = false)
	private Pay pay;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "publication_id")
	private Publication publication;


}