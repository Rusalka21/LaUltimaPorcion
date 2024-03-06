package com.edu.cibertec.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.edu.cibertec.model.dao.DetallePedido;
import com.edu.cibertec.model.dao.Pedido;
import com.edu.cibertec.model.dao.Producto;
import com.edu.cibertec.model.dto.PedidoRequest;
import com.edu.cibertec.repository.DetallePedidoRepository;
import com.edu.cibertec.repository.PedidoRepository;
import com.edu.cibertec.repository.ProductoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private DetallePedidoRepository detallePedidoRepository;

	@Autowired
	private ProductoRepository productoRepository;

	public Pedido guardarPedido(PedidoRequest request) {
		Pedido objPedido = new Pedido();
		objPedido.setDetalles(null);

		if (request.getPedido() != null) {
			Pedido requestPedido = request.getPedido();
			objPedido.setEstado(requestPedido.getEstado());
			objPedido.setFechaPedido(new Date());
			objPedido.setCliente(requestPedido.getCliente());
			objPedido.setNumPedido(requestPedido.getNumPedido());
			objPedido.setTipoPago(requestPedido.getTipoPago());
			objPedido.setTotal(requestPedido.getTotal());
		}

		Pedido pedidoGuardado = pedidoRepository.save(objPedido);

		if (!request.getPedido().getDetalles().isEmpty() && request.getPedido().getDetalles() != null) {
			guardarDetallePedido(pedidoGuardado, request.getPedido().getDetalles());
		}

		return pedidoRepository.findById(pedidoGuardado.getId())
				.orElseThrow(() -> new IllegalArgumentException("Ocurrio un error al procesar el pedido"));
	}

	private void guardarDetallePedido(Pedido pedidoGuardado, List<DetallePedido> detallePedidoRequest) {
		List<DetallePedido> detallePedidos = new ArrayList<>();

		detallePedidos = detallePedidoRequest.stream().map(request -> {
			DetallePedido objDetallePedido = new DetallePedido();
			Producto objProducto = productoRepository.findByNombre(request.getProducto().getNombre());

			objDetallePedido.setCantidad(request.getCantidad());
			objDetallePedido.setProducto(objProducto);
			System.out.println("revisar" + request.getSubtotal());
			objDetallePedido.setSubtotal(request.getSubtotal());
			objDetallePedido.setPedido(pedidoGuardado);

			return objDetallePedido;
		}).collect(Collectors.toList());

		detallePedidoRepository.saveAll(detallePedidos);
	}

	public Pedido buscarPedidoPorId(Long id) {
		return pedidoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Ocurrio un error al procesar el pedido"));
	}

	public List<Pedido> listarPedidosFechaAcutal() {
		Date fechaActual = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaActual);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date fechaInicio = calendar.getTime();

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date fechaFin = calendar.getTime();

		return pedidoRepository.findByFechaPedidoBetween(fechaInicio, fechaFin);
	}
	
	public List<Pedido> listarPedidos() {
		return pedidoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

}
