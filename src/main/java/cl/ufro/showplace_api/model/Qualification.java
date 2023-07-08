package cl.ufro.showplace_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "qualification")
public class Qualification {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "qualification")
	private int qualification;
}