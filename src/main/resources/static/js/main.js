function mostrarModal() {
    var modal = document.getElementById('modal');
    modal.style.display = 'block';
}

function cerrarModal() {
    var modal = document.getElementById('modal');
    modal.style.display = 'none';
}

function continuarCompra() {
    alert('Continuar compra');
    cerrarModal();
}

function culminarCompra() {
    // Realiza alguna acción adicional si es necesario
    cerrarModal();

    // Redirige a la página de ordenar
    window.location.href = "../paginas/Ordenar.html";
}

/*Ordenar*/
// Lógica para agregar productos a la tabla
function agregarProducto(nombre, precio) {
    var orderList = document.getElementById('order-list');
    
    var newRow = orderList.insertRow();
    var cell1 = newRow.insertCell(0);
    var cell2 = newRow.insertCell(1);
    var cell3 = newRow.insertCell(2);
    var cell4 = newRow.insertCell(3);
    var cell5 = newRow.insertCell(4);

    cell1.innerHTML = nombre;
    cell2.innerHTML = '$' + precio.toFixed(2);
    cell3.innerHTML = '<input type="number" value="1" min="1" onchange="actualizarTotal()">';
    cell4.innerHTML = '$<span class="subtotal">0.00</span>';
    cell5.innerHTML = '<button class="purple-button" onclick="eliminarProducto(this)">Eliminar</button>';

    actualizarTotal();
}

// Lógica para eliminar productos de la tabla
function eliminarProducto(btn) {
    var row = btn.parentNode.parentNode;
    row.parentNode.removeChild(row);
    actualizarTotal();
}

// Lógica para actualizar el total al cambiar la cantidad
function actualizarTotal() {
    var rows = document.getElementById('order-list').getElementsByTagName('tr');
    var total = 0;

    for (var i = 1; i < rows.length; i++) {
        var quantity = parseInt(rows[i].getElementsByTagName('input')[0].value, 10);
        var price = parseFloat(rows[i].cells[1].innerHTML.replace('$', ''));
        var subtotal = quantity * price;

        rows[i].cells[3].getElementsByTagName('span')[0].innerHTML = subtotal.toFixed(2);
        total += subtotal;
    }

    document.getElementById('total').innerHTML = total.toFixed(2);
}

// Lógica para simular la acción de ordenar
function ordenar() {
    alert('¡Orden realizada con éxito!');
}

// Variables para el popup
var popup = document.getElementById('popup');
var popupMessage = document.getElementById('popup-message');

function realizarPedido() {
    // Lógica para realizar el pedido y obtener un número de pedido
    // ...

    // Número de pedido simulado (reemplázalo con tu lógica real)
    var numeroPedido = obtenerNumeroPedido();

    // Mensaje específico del pedido con HTML personalizado
    var mensajePedido = `
        
        Tu número de pedido es: #${numeroPedido}
        Por favor, dirígete a caja para completar tu compra.
    `;

    // Mostrar el popup con el mensaje de pedido realizado
    mostrarPopup(mensajePedido);
}

function obtenerNumeroPedido() {
    // Lógica para obtener un número de pedido (simulado aquí)
    return Math.floor(Math.random() * 1000) + 1; // Número aleatorio para simular un número de pedido
}

function mostrarPopup(message) {
    // Asigna el mensaje al elemento con ID 'popup-message'
    document.getElementById('popup-message').textContent = message;
    
    // Muestra el popup
    document.getElementById('popup').style.display = 'block';
}


function cerrarPopup() {
    popup.style.display = 'none';
}

function imprimirPedido() {
    // Lógica para imprimir el pedido (puede ser con window.print(), etc.)
    // ...
    cerrarPopup(); // Cerrar el popup después de imprimir
}

/*carrito */
// main.js

// main.js

let carrito = [];

let cantidadSeleccionada = 1;

function mostrarModal(nombreProducto) {
    const modal = document.getElementById("modal");
    modal.style.display = "block";
    
    // Actualiza el contenido del modal con el nombre del producto
    const modalContent = document.querySelector(".modal-content");
    modalContent.innerHTML = `
        <span class="close" onclick="cerrarModal()">&times;</span>
        <p>Añadido al carrito:</p>
        <p>${nombreProducto}</p>
        <div>
            <span id="cantidadProducto">${cantidadSeleccionada}</span>
            <button onclick="restarCantidad()">-</button>
            <button onclick="sumarCantidad()">+</button>
        </div>
        <button class="purple-button" onclick="continuarCompra()">Continuar comprando</button>
        <button class="purple-button" onclick="culminarCompra()">Ir al carrito</button>
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

function actualizarCantidadEnModal() {
    const cantidadProducto = document.getElementById("cantidadProducto");
    cantidadProducto.textContent = cantidadSeleccionada;
}

/*cc */
function cerrarCaja() {
    alert("Caja cerrada");
}

function imprimir() {
    alert("Imprimiendo...");
}

function regresar() {
    alert("Volviendo atrás...");
}

/*Pago*/
// Función para mostrar el popup de pago exitoso
function realizarPago() {
    // Agrega aquí la lógica para procesar el pago
    // Mostrar el popup cuando el pago sea exitoso
    document.getElementById("popupPagoExitoso").style.display = "block";
}

// Función para cerrar el popup
function cerrarPopup() {
    document.getElementById("popupPagoExitoso").style.display = "none";
} 
