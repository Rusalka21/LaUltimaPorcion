let cantidadSeleccionada = 1;

/* Escucha eventos del DOM*/
document.addEventListener('DOMContentLoaded', function() {
	var ordenarLink = document.getElementById('ordenarLink');
    if (ordenarLink) {
        ordenarLink.addEventListener('click', function(event) {
            event.preventDefault(); // Evita que el enlace siga el href
            irAordenar();
        });
    }
    document.body.addEventListener('click', function(event) {
        if (event.target.classList.contains('eliminar-btn')) {
            var idOrden = event.target.getAttribute('data-id');
            eliminarProducto(idOrden);
        }
        if (event.target.classList.contains('finalizar-btn')) {
			finalizaOrden();
		}
    });
});

/*Funciones de las vistas Catalogos*/
function mostrarModal(nombreProducto, idProducto) {
   console.log('Ingresaste a la funcion mostrarModal() de main.js');
   reiniciarModal();
   let idOrdenCompra = localStorage.getItem('idOrdenCompra');
   if (!idOrdenCompra) {
      idOrdenCompra = generarIdUnico();
      localStorage.setItem('idOrdenCompra', idOrdenCompra);
   }
   const modal = document.getElementById("modal");
   modal.style.display = "block";

   // Actualiza el contenido del modal con el nombre del producto
   const modalContent = document.querySelector(".modal-content");
   modalContent.innerHTML = `
        <span class="close" onclick="cerrarModal()">×</span>
        <p>Añadido al carrito:</p>
        <p>${nombreProducto}</p>
        <div>            
            <button onclick="restarCantidad()">-</button>
            <span id="cantidadProducto">${cantidadSeleccionada}</span>
            <button onclick="sumarCantidad()">+</button>
        </div>
        <button class="purple-button" onclick="continuarCompra(${idProducto}, '${idOrdenCompra}')">Agregar</button>
        <button class="purple-button" onclick="irAculminarCompra()">Ir al carrito</button>
    `;
}

function sumarCantidad() {
   cantidadSeleccionada++;
   actualizarCantidadEnModal();
}

function restarCantidad() {
   if (cantidadSeleccionada > 1) {
      cantidadSeleccionada--;
      actualizarCantidadEnModal();
   }
}

function reiniciarModal() {
   cantidadSeleccionada = 1;
   actualizarCantidadEnModal();
}

function actualizarCantidadEnModal() {
   const cantidadProducto = document.getElementById("cantidadProducto");
   cantidadProducto.textContent = cantidadSeleccionada;
}

function generarIdUnico() {
   const timestamp = new Date().getTime();
   console.log("timestamp " + timestamp);
   return timestamp;
}

function obtenerOrdenCompra() {
   if (idOrdenCompra == null || idOrdenCompra == '') {
      console.log("entro al if para generar id");
      idOrdenCompra = generarIdUnico();
   }
   return idOrdenCompra;
}

function registrarOrdenCompra(idProducto, cantidadProducto, idOrdenCompra) {
   fetch('/api/registrarOrdenCompra', {
         method: 'POST',
         headers: {
            'Content-Type': 'application/json',
         },
         body: JSON.stringify({
            producto: { id: idProducto },
            cantidad: cantidadProducto,
            idOrden: idOrdenCompra,
         }),
      })
      .then(response => response.json())
      .then(data => {
         console.log('Orden de compra registrada:', data);
      })
      .catch(error => {
         console.error('Error al registrar la orden de compra:', error);
      });
}

function continuarCompra(idProducto, idOrdenCompra) {
   console.log('Ingresaste a la funcion continuarCompra() de main.js');
   var cantidadOrden = document.getElementById("cantidadProducto").textContent;
   registrarOrdenCompra(idProducto, cantidadOrden, idOrdenCompra);
   cerrarModal();
}

function cerrarModal() {
   var modal = document.getElementById('modal');
   modal.style.display = 'none';
}

function irAculminarCompra() {
   cerrarModal();
   var idOrdenCompra = localStorage.getItem('idOrdenCompra');
   var paramIdOrdenCompra = 'idOrdenCompra=' + idOrdenCompra
   window.location.href = "/inicio/vendedor/ordenar?" + paramIdOrdenCompra;
}

function irAordenar() {
	var idOrdenCompra = localStorage.getItem('idOrdenCompra');
  	var paramIdOrdenCompra = 'idOrdenCompra=' + idOrdenCompra
	window.location.href = "/inicio/vendedor/ordenar?" + paramIdOrdenCompra;
}

function realizarBusqueda() {
   var searchTerm = document.getElementById("searchInput").value.toLowerCase();
   var items = document.getElementsByClassName("cake-item");
   for (var i = 0; i < items.length; i++) {
      var nombre = items[i].querySelector("span").textContent.toLowerCase();
      if (nombre.includes(searchTerm)) {
         items[i].style.display = "block";
      } else {
         items[i].style.display = "none";
      }
   }
}

/*Funciones de vista Ordenar*/
function actualizarTotal() {
    var total = 0;
    var filas = document.querySelectorAll('[id^="fila-"]');
    
    filas.forEach(function (fila) {
        if (fila.style.display !== 'none') {
            var idOrden = fila.id.split('-')[1];
            var subtotalElement = document.getElementById('subtotalProducto-' + idOrden);
            total += parseFloat(subtotalElement.innerText);
        }
    });
    
    document.getElementById('total').innerText = 'S/ ' + total.toFixed(2);
}

function actualizarSubtotal(index) {
    var cantidad = parseInt(document.getElementById('cantidadProducto-' + index).innerText);
    var precio = parseFloat(document.getElementById('precioProducto-' + index).innerText);
    var subtotal = cantidad * precio;
    document.getElementById('subtotalProducto-' + index).innerText = subtotal.toFixed(2);
    actualizarTotal();
}

function sumarCantidadRaw(index) {
    var cantidadElement = document.getElementById('cantidadProducto-' + index);
    var cantidad = parseInt(cantidadElement.innerText);
    cantidad++;
    cantidadElement.innerText = cantidad;
    actualizarSubtotal(index);
}

function restarCantidadRaw(index) {
    var cantidadElement = document.getElementById('cantidadProducto-' + index);
    var cantidad = parseInt(cantidadElement.innerText);
    if (cantidad > 1) {
        cantidad--;
        cantidadElement.innerText = cantidad;
        actualizarSubtotal(index);
    }
}

function eliminarProducto(idOrden) {
	console.log("Ingreso a la funcion eliminarProducto");
    fetch(`/api/eliminarOrdenCompra/${idOrden}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(response => {
        if (!response.ok) {
			console.log("Ocurrio un error al intentar eliminar la orden");
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        var fila = document.getElementById('fila-' + idOrden);
        if(fila) {
			fila.style.display = 'none';
			actualizarTotal();
			console.log('Orden de compra eliminada correctamente');
		}        
    })
    .catch(error => {
        console.error('Error al intentar eliminar la orden de compra:', error);
    });
}

function finalizaOrden() {
    // Obtener la información de la tabla
    var filas = document.querySelectorAll("#order-list tr");
    var data = [];

    filas.forEach(function (fila) {
        var id = fila.id.replace("fila-", "");
        var idOrdenText = document.getElementById("idOrdenText-" + id).innerText;
        var nombreProducto = document.getElementById("nombreProducto-" + id).innerText;
        var cantidad = document.getElementById("cantidadProducto-" + id).innerText;

        data.push({
            id: id,
            idOrden: idOrdenText,
            producto: {
                nombre: nombreProducto
            },
            cantidad: cantidad
        });
    });

    fetch('/api/finalizarOrdenCompra', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ ordenes: data })		
    })
    .then(response => response.json())
    .then(dt => {
        console.log('Orden de compra registrada:', dt);        
        var nuevaPestana = window.open('', '_blank');        
        nuevaPestana.document.write("<h1>Número de Orden de Compra: " + dt.ordenes[0].idOrden + "</h1>");        
    })
    .catch(error => {
        console.error('Error al registrar la orden de compra:', error);
    });
    
    //Liberar la variable de IdOrdenCompra
	localStorage.removeItem('idOrdenCompra');

    
    setTimeout(function() {
		window.location.href = "/inicio/vendedor";		
	}, 2000);
}

/*Funciones de vista Venta*/
function buscarOrdenPago() {
   var idOrdenPago = document.getElementById("idOrdenPago").value.toLowerCase();
   
   // Realizar la solicitud HTTP usando Fetch
   fetch('/api/buscarOrdenCompra?idOrdenCompra=' + idOrdenPago)
       .then(response => {
           if (!response.ok) {
			   alert('Orden no encontrada');
               throw new Error('Error al buscar la orden de compra');
           }
           return response.json();
       })
       .then(data => {
		   	alert('Orden encontrada!');
            var tBodyDetalleOrden = document.querySelector('#detalleOrden tbody');
            var tBodyDetalleParaPagar = document.querySelector('#detalleParaPagar tbody');
            var total = 0;

            // Limpiar el contenido actual de la tabla
            tBodyDetalleOrden.innerHTML = '';
            tBodyDetalleParaPagar.innerHTML = '';

            data.forEach((ordenCompra, index) => {
			    var row = tBodyDetalleOrden.insertRow();
			    row.setAttribute('id', 'filaDetalleOrden_' + index); 
			    
			    var idCell = row.insertCell(0);
			    var productoCell = row.insertCell(1);
			    var cantidadCell = row.insertCell(2);
			    var precioUnitarioCell = row.insertCell(3);
			    var subTotalCell = row.insertCell(4);
			
			    idCell.textContent = ordenCompra.idOrden;
			    productoCell.textContent = ordenCompra.producto.nombre;
			    cantidadCell.textContent = ordenCompra.cantidad;
			    precioUnitarioCell.textContent = ordenCompra.producto.precio.toFixed(2);
			    subTotalCell.textContent = ordenCompra.subTotal.toFixed(2);
			
			    total += ordenCompra.subTotal;
			});
            
            var rowDetalleParaPagar = tBodyDetalleParaPagar.insertRow();
            rowDetalleParaPagar.setAttribute('id', 'filaTotal_' + 0);
            var montoTotalCell = rowDetalleParaPagar.insertCell(0);
            var otrosDescuentosCell = rowDetalleParaPagar.insertCell(1);
            var totalPagarCell = rowDetalleParaPagar.insertCell(2);
            
            montoTotalCell.textContent = total.toFixed(2);
            otrosDescuentosCell.textContent = "0.00";
            totalPagarCell.textContent = total.toFixed(2);
        })
       .catch(error => {
           console.error('Error:', error);
       });
}

function buscarCliente() {
	var numCliente = document.getElementById("numCliente").value;
	
	fetch('/api/buscarCliente?numeroDocumento=' + numCliente)
	.then(response => {
		if(!response.ok) {
			alert('Cliente no encontrado, debe registrarlo');
			window.location.href = "/inicio/caja/nuevoCliente";
            throw new Error('Error al buscar la orden de compra');            
		}
		console.log(response);
		return response.json();
	})
	.then(data => {
		alert("Cliente encontrado!");
		
		document.getElementById("idCliente").value = data.id;
		document.getElementById("nombreCliente").value = data.nombre;
		document.getElementById("dniRuc").value = data.numeroDocumento;
		document.getElementById("telefono").value = data.telefono;
		document.getElementById("correoElectronico").value = data.email;
		
	})
	.catch(error => {
		alert('Cliente no encontrado, debe registrarlo');
		window.location.href = "/inicio/caja/nuevoCliente";
    	console.error('Error:', error);
    });
}

function realizarPago() {
   var idCliente = document.getElementById("idCliente").value;
   var filas = document.querySelectorAll("#listDetalleOrden tr");
   var detallePedido = [];
   var id = 0;
   
   filas.forEach((fila, index) => {
	  filaSeleccionada = document.getElementById("filaDetalleOrden_" + index);
	  var nombreProducto = filaSeleccionada.cells[1];
	  var cantidad = filaSeleccionada.cells[2];
	  var subTotal = filaSeleccionada.cells[4];
	  
	  detallePedido.push({
		cantidad: parseInt(cantidad.textContent),
		subtotal: parseFloat(subTotal.textContent),
		producto: {
			nombre: nombreProducto.textContent
		}
	  })
   });
 
   var filaTotal = document.getElementById("filaTotal_0");
   var celdaTotal = filaTotal.cells[0];
   var total = parseFloat(celdaTotal.textContent);

	var radiosFormaPago = document.getElementsByName("formaPago");
	var tipoPagoSeleccionado = "";
	
	for (var i = 0; i < radiosFormaPago.length; i++) {
	    if (radiosFormaPago[i].checked) {	        
	        tipoPagoSeleccionado = radiosFormaPago[i].id;
	        break;
	    }
	}
   
   var pedidoRequest = {
      pedido: {
		 id: id,
         numPedido : obtenerNumeroPedido(),
         cliente: {
            id : idCliente
         },
         estado: 'Pagado',
         tipoPago: tipoPagoSeleccionado,
         total: parseFloat(total),
         detalles: detallePedido
      }
   };
   
   console.log(pedidoRequest);

   fetch('/api/registrarPedido', {
      method: 'POST',
      headers: {
         'Content-Type': 'application/json'
      },
      body: JSON.stringify(pedidoRequest)
   })
   .then(response => response.json())
   .then(data => {
		localStorage.setItem('idPedido', data.id);
      	document.getElementById("popupPagoExitoso").style.display = "block";
   })
   .catch(error => {
      console.error('Error:', error);
   });
}

function cerrarPopup() {
   document.getElementById('popup').style.display = 'none';
}

function imprimirPedido() {	
    cerrarPopup();
    
    var idPedido = localStorage.getItem('idPedido');
    if(idPedido) {
		var rutaEspecifica = "http://localhost:8083/inicio/caja/venta/imprimir/" + idPedido;
		window.open(rutaEspecifica, '_blank');		
	}
	
    location.reload();
}

function obtenerNumeroPedido() {
   return Math.floor(Math.random() * 1000) + 1; // Número aleatorio para simular un número de pedido
}


// Función para cerrar el popup
function cerrarPopup() {
   document.getElementById("popupPagoExitoso").style.display = "none";
}

// Variables para el popup
var popup = document.getElementById('popup');
var popupMessage = document.getElementById('popup-message');

function mostrarPopup(message) {
   // Asigna el mensaje al elemento con ID 'popup-message'
   document.getElementById('popup-message').textContent = message;

   // Muestra el popup
   document.getElementById('popup').style.display = 'block';
}

/*Funciones de cierre Caja*/
function irInicioCaja() {
   alert("Volviendo atrás...");
}

function cerrarCaja() {
	console.log("Igreso a la funcion cerrarCaja");
    var fechaSistema = document.getElementById("fechaSistema").value;
    var supervisor = document.getElementById("supervisor").value;
    var fechaCierre = document.getElementById("fechaCierre").value;
    var saldoApertura = parseFloat(document.getElementById("saldoApertura").value) || 0;
    var saldoEfectivo = parseFloat(document.getElementById("saldoEfectivo").value) || 0;
    var pagoTarjeta = parseFloat(document.getElementById("pagoTarjeta").value) || 0;
    var total = saldoApertura + saldoEfectivo + pagoTarjeta;

    var requestObject = {
        caja: {
            fechaSistema: fechaSistema,
            supervisor: supervisor,
            fechaCierre: fechaCierre,
            saldoApertura: saldoApertura,
            saldoEfectivo: saldoEfectivo,
            saldoTarjetaCredito: pagoTarjeta,
            totalDiario: total
        }
    };

    fetch('/api/registrarCuadreCaja', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestObject),
    })
    .then(response => response.json())
    .then(data => {
        var botonCerrarCaja = document.getElementById("cerrarCajaButton");
        botonCerrarCaja.disabled = true;
        botonCerrarCaja.style.backgroundColor = '#d3d3d3';        
        mostrarResumen(data);
        alert("Registro de caja exitoso");
    })
    .catch(error => {
		alert("Este dia ya cuenta con cierre de caja")
        console.error('Error al realizar la solicitud:', error);
    });
}

function mostrarResumen(data) {
    // Mostrar la respuesta del API en los campos correspondientes
    document.getElementById("resumenSaldoApertura").textContent = data.saldoApertura.toFixed(2);
    document.getElementById("resumenSaldoEfectivo").textContent = data.saldoEfectivo.toFixed(2);
    document.getElementById("resumenSaldoTC").textContent = data.saldoTarjetaCredito.toFixed(2);

    // Calcular y mostrar el total
    var total = data.saldoApertura + data.saldoEfectivo + data.saldoTarjetaCredito;
    document.getElementById("resumenTotal").textContent = total.toFixed(2);
}