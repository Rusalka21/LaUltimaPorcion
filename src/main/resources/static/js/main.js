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
    alert('Culminar compra');
    cerrarModal();
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
    // Lógica para realizar el pedido y actualizar la tabla (puede ser con AJAX, etc.)
    // ...

    // Mostrar el popup con el mensaje de pedido realizado
    mostrarPopup('Pedido realizado con éxito!');
}

function mostrarPopup(message) {
    popupMessage.textContent = message;
    popup.style.display = 'block';
}

function cerrarPopup() {
    popup.style.display = 'none';
}

function imprimirPedido() {
    // Lógica para imprimir el pedido (puede ser con window.print(), etc.)
    // ...
    cerrarPopup(); // Cerrar el popup después de imprimir
}