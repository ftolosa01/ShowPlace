package cl.ufro.showplace_api.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "event")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "participants")
	private int participants;

	@Column(name = "participationFee")
	private String participationFee;

	@Column(name = "image")
	private String image;

	@Column(name = "confirmedEvent")
	private boolean confirmedEvent;

	@JoinColumn(name = "booking_id")
	@OneToOne(fetch = FetchType.EAGER)
	private Booking booking;

	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(
			name = "event_pay",
			joinColumns = {@JoinColumn(name = "event_id")},
			inverseJoinColumns = {@JoinColumn(name = "pay_id")}
	)
	@JsonIgnore
	private List<Pay> pay = new ArrayList<Pay>();

	@ManyToMany(mappedBy = "event")
	private List<Customer> customer = new ArrayList<Customer>();

}