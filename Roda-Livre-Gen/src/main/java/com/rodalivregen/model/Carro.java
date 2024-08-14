package com.rodalivregen.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_carros")
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotBlank(message = "A marca não pode estar em branco.")
	@Size(min = 2, max = 100, message = "O atributo marca deve conter no mínimo 2 e no máximo 100 caracteres.")
	private String marca;

	@NotBlank(message = "O modelo não pode estar em branco.")
	@Size(min = 2, max = 100, message = "O atributo modelo deve conter no mínimo 2 e no máximo 100 caracteres.")
	private String modelo;

	@Min(value = 2005, message = "O ano deve ser no mínimo 2005.")
	@Max(value = 2025, message = "O ano deve ser no máximo 2025.")
	private int ano;

	@NotBlank(message = "O tipo não pode estar em branco.")
	private String tipo;

	private boolean disponibilidade;

	@Min(value = 0, message = "O preço por dia deve ser positivo.")
	private double precoPorDia;
	
	@ManyToOne
	@JsonIgnoreProperties("carro")
	private Reserva reserva;
	
	@ManyToOne
	@JsonIgnoreProperties("carro")
	private Usuario usuario;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public double getPrecoPorDia() {
		return precoPorDia;
	}

	public void setPrecoPorDia(double precoPorDia) {
		this.precoPorDia = precoPorDia;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
