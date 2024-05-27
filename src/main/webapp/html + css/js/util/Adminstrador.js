document.addEventListener("DOMContentLoaded", () => {
    AdminUser();
});

async function AdminUser(category) {
    let items;
    if (category === undefined) {
        items = await fetch("http://localhost:8080/RetoDAW1ModeloAnton/Controller?ACTION=CLIENT.SQL_FIND_ALL");
    }
    const itemsJson = await items.json();
    const tableBody = document.getElementById("table-body");
    tableBody.innerHTML = '';
    console.log(itemsJson);

    for (const item of itemsJson) {
        console.log(item);

        const row = document.createElement("tr");

        const idCell = document.createElement("td");
        idCell.textContent = item.Id_cliente;
        row.appendChild(idCell);

        const userCell = document.createElement("td");
        userCell.textContent = item.Usuario;
        row.appendChild(userCell);

        const passwordCell = document.createElement("td");
        passwordCell.textContent = item.Contrasena;
        row.appendChild(passwordCell);

        tableBody.appendChild(row);
    }
    
    const deleteClient = async (clientId) => {
        const url = `http://localhost:8080/Xeneburguer/Controller?ACTION=CLIENTES.DELETE&ID_CLIENTE=${clientId}`;
        try {
            const response = await fetch(url, { method: 'DELETE' });
            if (response.ok) {
                alert('Cliente eliminado exitosamente.');
                getClients();
            } else {
                throw new Error('Error al eliminar cliente.');
            }
        } catch (error) {
            console.error('Error al eliminar cliente:', error);
        }
    };
    
    const deleteButton = document.getElementById('deleteButton');
    deleteButton.addEventListener('click', () => {
        const clientId = prompt('Ingrese el ID del cliente que desea eliminar:');
        if (clientId !== null && clientId.trim() !== '') {
            deleteClient(clientId);
        } else {
            alert('Debe ingresar un ID válido.');
        }
    });
    


}
