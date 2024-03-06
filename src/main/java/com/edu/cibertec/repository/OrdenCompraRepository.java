package com.edu.cibertec.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.cibertec.model.dao.OrdenCompra;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {

	List<OrdenCompra> findByIdOrden(String idOrdenCompra);

	List<OrdenCompra> findByFechaOrdenBetween(Date fechaInicio, Date fechaFin);

	@Query("SELECT ROW_NUMBER() OVER (ORDER BY idOrden) AS id,"
			+ " idOrden,\r\n"
			+ " SUM(subTotal) AS Total"
			+ " FROM OrdenCompra"
			+ " WHERE fechaOrden BETWEEN :fechaInicio AND :fechaFin"
			+ " GROUP BY idOrden")
	List<Object[]> getOrdenesDiaActual(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

}
