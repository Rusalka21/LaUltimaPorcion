package com.edu.cibertec.model.dao;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "caja")
public class Caja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "fecha_sistema")
	private Date fechaSistema;
	@Column(name = "supervisor")
	private String supervisor;
	@Column(name = "fecha_cierre")
	private Date fechaCierre;
	@Column(name = "saldo_apertura")
	private BigDecimal saldoApertura;
	@Column(name = "saldo_efectivo")
	private BigDecimal saldoEfectivo;
	@Column(name = "saldo_tarjeta_credito")
	private BigDecimal saldoTarjetaCredito;
	@Column(name = "total_diario")
	private BigDecimal totalDiario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaSistema() {
		return fechaSistema;
	}
	public void setFechaSistema(Date fechaSistema) {
		this.fechaSistema = fechaSistema;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public BigDecimal getSaldoApertura() {
		return saldoApertura;
	}
	public void setSaldoApertura(BigDecimal saldoApertura) {
		this.saldoApertura = saldoApertura;
	}
	public BigDecimal getSaldoEfectivo() {
		return saldoEfectivo;
	}
	public void setSaldoEfectivo(BigDecimal saldoEfectivo) {
		this.saldoEfectivo = saldoEfectivo;
	}
	public BigDecimal getSaldoTarjetaCredito() {
		return saldoTarjetaCredito;
	}
	public void setSaldoTarjetaCredito(BigDecimal saldoTarjetaCredito) {
		this.saldoTarjetaCredito = saldoTarjetaCredito;
	}
	public BigDecimal getTotalDiario() {
		return totalDiario;
	}
	public void setTotalDiario(BigDecimal totalDiario) {
		this.totalDiario = totalDiario;
	}

}
