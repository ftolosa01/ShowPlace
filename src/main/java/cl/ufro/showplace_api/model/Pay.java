package cl.ufro.showplace_api.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pay")
public class Pay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "summary")
	private String summary;

	@Column(name = "paymentMethod")
	private String paymentMethod;

	@Column(name = "price")
	private String price;

	@Column(name = "confirmedEventPay")
	private boolean confirmedEventPay;

	@Column(name = "confirmedBookingPay")
	private boolean confirmedBookingPay;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pay")
	private List<Booking> booking = new ArrayList<Booking>();

	@ManyToMany(mappedBy = "pay")
	@JsonIgnore
	private List<Event> event = new ArrayList<Event>();
}