package com.project.clinica.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_detalle_requerimiento")
public class BienHasRequerimiento {
	
	@EmbeddedId
	private BienHasRequerimientoPK pk;
	
	@ManyToOne
	@JoinColumn(name = "cod_bien",insertable = false,updatable = false,referencedColumnName = "cod_bien")
	private Bien bien;//ASOC
	
	@ManyToOne
	@JoinColumn(name = "cod_reque",insertable = false,updatable = false,referencedColumnName = "cod_reque")
	private Requerimiento requerimiento;//ASOC
	
	@Column(name = "can")
	private int cantidad;

	public BienHasRequerimientoPK getPk() {
		return pk;
	}

	public void setPk(BienHasRequerimientoPK pk) {
		this.pk = pk;
	}

	public Bien getBien() {
		return bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}

	public Requerimiento getRequerimiento() {
		return requerimiento;
	}

	public void setRequerimiento(Requerimiento requerimiento) {
		this.requerimiento = requerimiento;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
