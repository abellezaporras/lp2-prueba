package com.project.clinica.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_tipo_medicamento")
public class Tipo {
	@Id
	@Column(name = "cod_tipo")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	@Column(name = "nom_tipo")
	private String nombre;
	//relación UNO a MUCHOS
	@OneToMany(mappedBy = "tipoMedicamento")
	@JsonIgnore
	private List<Medicamento> lista;
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Medicamento> getLista() {
		return lista;
	}
	public void setLista(List<Medicamento> lista) {
		this.lista = lista;
	}
	
	
}
