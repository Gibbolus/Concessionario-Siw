package it.uniroma3.siw.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String modello;

	private String marca;

	private Integer km;

	private String urlImage;

	@ManyToOne(cascade = CascadeType.DETACH)
	public Supplier supplier;

	@OneToMany(mappedBy = "car", cascade = CascadeType.DETACH)
	public List<OptionalCar> optionals;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	public List<Review> review;
	
	@Transient
	private MultipartFile immagine;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Review> getReviews() {
		return review;
	}

	public void setReviews(List<Review> reviews) {
		this.review = reviews;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getKm() {
		return km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<OptionalCar> getOptionals() {
		return optionals;
	}

	public void setOptionals(List<OptionalCar> optionals) {
		this.optionals = optionals;
	}

}
