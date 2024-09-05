package com.rodalivregen.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="tb_reservas")
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

    @NotNull(message = "A data de início não pode ser nula.")
    private int diasalugados;
    
    private float valortotal;
    
    @OneToOne
	@JsonIgnoreProperties({"reserva", "reservas"})
	private Carro carro;
    
    @OneToOne
    @JsonIgnoreProperties("reserva")
    private Usuario usuario;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public int getDiasalugados() {
		return diasalugados;
	}

	public void setDiasalugados(int diasalugados) {
		this.diasalugados = diasalugados;
	}

	public float getValortotal() {
		return valortotal;
	}

	public void setValortotal(float valortotal) {
		this.valortotal = valortotal;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}