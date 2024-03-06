package com.edu.cibertec.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.cibertec.model.dao.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	@Query("SELECT p FROM Pedido p WHERE DATE(p.fechaPedido) = CURRENT_DATE")
    List<Pedido> findPedidosByFechaActual();
	List<Pedido> findByFechaPedidoBetween(Date startDate, Date endDate);

}
