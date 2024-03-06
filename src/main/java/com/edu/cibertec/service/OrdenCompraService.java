package com.edu.cibertec.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.cibertec.model.dao.OrdenCompra;
import com.edu.cibertec.model.dao.Producto;
import com.edu.cibertec.model.dto.OrdenCompraRequest;
import com.edu.cibertec.repository.OrdenCompraRepository;
import com.edu.cibertec.repository.ProductoRepository;

@Service
public class OrdenCompraService {

	@Autowired
	private OrdenCompraRepository ordenCompraRepository;

	@Autowired
	private ProductoRepository productRepository;

	public OrdenCompra guardarOrden(OrdenCompra request) {
		// Calcular el subtotal validando el precio en BD
		Producto objProducto = productRepository.findById(request.getProducto().getId()).orElse(new Producto());

		if (objProducto.getPrecio() != null) {
			BigDecimal cantidad = new BigDecimal(request.getCantidad());
			BigDecimal subTotal = objProducto.getPrecio().multiply(cantidad);
			request.setSubTotal(subTotal);
		} else {
			request.setSubTotal(BigDecimal.ZERO);
		}
		
		request.setFechaOrden(new Date());
		return ordenCompraRepository.save(request);
	}
	
	public OrdenCompraRequest finalizarOrdenCompra(OrdenCompraRequest request) {
		request.getOrdenes().stream().forEach(orden -> {
			Producto objProducto = productRepository.findByNombre(orden.getProducto().getNombre());
			
			if (objProducto.getPrecio() != null) {
				BigDecimal cantidad = new BigDecimal(orden.getCantidad());
				BigDecimal subTotal = objProducto.getPrecio().multiply(cantidad);
				orden.setSubTotal(subTotal);
			} else {
				orden.setSubTotal(BigDecimal.ZERO);
			}
			
			orden.setProducto(objProducto);
			orden.setFechaOrden(new Date());
		});
		
		ordenCompraRepository.saveAll(request.getOrdenes());
		
		return request;
	} 

	public List<OrdenCompra> buscarPorIdOrdenCompra(String idOrden) {
		List<OrdenCompra> ordenes = ordenCompraRepository.findByIdOrden(idOrden);
		return ordenes;
	}
	
	public void eliminarOrdenCompra(Long idOrden) {
		ordenCompraRepository.deleteById(idOrden);
	}
	
	public List<OrdenCompra> buscarPorFechaActual() {
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
        
        System.out.println("fechaInicio" + fechaInicio.toString());
        System.out.println("fechaFin" + fechaFin.toString());
        
        
        List<OrdenCompra> ordenes = ordenCompraRepository.getOrdenesDiaActual(fechaInicio, fechaFin)
        	.stream()
        	.map(resultado -> {
        		Long id = (Long) resultado[0];
        		String idOrden = (String) resultado[1];
        		BigDecimal total = (BigDecimal) resultado[2];
        		
        		OrdenCompra ordenCompra = new OrdenCompra();

        		ordenCompra.setId(id);
        		ordenCompra.setIdOrden(idOrden);
        		ordenCompra.setFechaOrden(fechaActual);
        		ordenCompra.setSubTotal(total);
        		
        		return ordenCompra;
        	})
        	.collect(Collectors.toList());
        
        return ordenes;
	}
	

}
