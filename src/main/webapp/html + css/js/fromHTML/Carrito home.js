// Variables
window.addEventListener('DOMContentLoaded', (event) => {
const carrito = document.querySelector("#carrito");
const listaCarrito = document.querySelector("#lista-carrito tbody")
const vaciarCarritoBtn = document.querySelector("#vaciar-carrito")
const masvendido = document.querySelector("#mas-vendido")

cargarEventListeners();
function cargarEventListeners() {
    masvendido.addEventListener("click", agregar_producto);

    // Eliminar Producto del Carrito
    carrito.addEventListener("click", eliminarProducto);

    document.addEventListener('DOMContentLoaded', () => {
        articuloCarrito = JSON.parse(localStorage.getItem('carrito')) || [];
        carritoHTML();
    });

    // Vaciar el Carrito
    vaciarCarritoBtn.addEventListener("click", () => {
        
        articuloCarrito = []; //Reseteamos el array

        limpiarHTML();
        sincronizarStorage();

    })

}


let articuloCarrito = []

// Funciones
function agregar_producto(e) {
    e.preventDefault();
    if(e.target.classList.contains("Agregar-carrito")) {
        productoSeleccionado = e.target.parentElement.parentElement.parentElement;
        leerDatosProducto(productoSeleccionado);
    }
}

function eliminarProducto(e) {
    if(e.target.classList.contains('borrar-curso')){
        const productoId = e.target.getAttribute('data-id');
        const existe = articuloCarrito.some(producto => (producto.id === productoId && producto.cantidad > 1));

        if(existe) {
            const productos = articuloCarrito.map(producto => {
                if(producto.id === productoId) {
                    producto.cantidad--; 
                }
                return producto;
            });
            articuloCarrito = [...productos];

        } else {
            articuloCarrito = articuloCarrito.filter(producto => producto.id !== productoId);
        }

        carritoHTML();

    }
}

function leerDatosProducto(producto) {
    // Crear un objeto
    infoProduccto = {
        imagen: producto.querySelector('img').src,
        titulo: producto.querySelector('span').textContent,
        precio: producto.querySelector('.tjv-texto2 span').textContent,
        id: producto.querySelector('a').getAttribute('data-id'),
        cantidad: 1
    }
    
    const existe = articuloCarrito.some(producto => producto.id === infoProduccto.id);

    if(existe) {
        //Actualizamos la cantidad
        const productos = articuloCarrito.map( producto => {
            if(producto.id === infoProduccto.id) {
                producto.cantidad++;
                return producto;
            } else {
                return producto;
            }
        } );
        articuloCarrito = [...productos]
    } else {
        articuloCarrito = [...articuloCarrito, infoProduccto]
    }

    console.log(articuloCarrito);

    carritoHTML();
}
function carritoHTML() {  
    limpiarHTML();

    articuloCarrito.forEach(producto => {
        const {imagen, titulo, precio, cantidad, id} = producto; 
        const row = document.createElement('tr');

        row.innerHTML = `
            <td>
                <img src="${imagen}" padding-right= "15px"></img>
            </td>
            <td width="78px">${titulo}</td>
            <td width ="56px">${precio}</td>
            <td>${cantidad}</td>
            <td>
                <a href="#" class="borrar-curso" data-id="${id}"> X </a>
            </td>
        `;

        listaCarrito.appendChild(row)

    });
    // Agregar al carrito de compras el Storage
    sincronizarStorage();

}

function sincronizarStorage() {
    localStorage.setItem('carrito', JSON.stringify(articuloCarrito));
}

function limpiarHTML() {

    listaCarrito.innerHTML = ""

    while(listaCarrito.firstChild){
        listaCarrito.removeChild(listaCarrito.firstChild);
    }

}

})