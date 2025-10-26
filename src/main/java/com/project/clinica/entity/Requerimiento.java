package com.project.clinica.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_requerimiento")
public class Requerimiento {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="cod_reque")
	private Integer codigo ;
	@Column(name="nom_reque")
	private String nombreRequerimiento;
	@Column(name="fec_reque")
	private LocalDate fecha;
	@Column(name="cod_usu")
	private int codigoUsuario;
	@Column(name="est_reque")
	private String estado;
	
	@OneToMany(mappedBy = "requerimiento")
	private List<BienHasRequerimiento> detalle;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombreRequerimiento() {
		return nombreRequerimiento;
	}

	public void setNombreRequerimiento(String nombreRequerimiento) {
		this.nombreRequerimiento = nombreRequerimiento;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<BienHasRequerimiento> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<BienHasRequerimiento> detalle) {
		this.detalle = detalle;
	}
	
	
}
