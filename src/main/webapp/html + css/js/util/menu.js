document.addEventListener("DOMContentLoaded", () => {
    Burger()
  });
  async function Burger(category) {
    let items; if (category === undefined) {
      items = await fetch("http://localhost:8080/RetoDAW1ModeloAnton/Controller?ACTION=PRODUCT.SQL_FIND_ALL&CATEGORY_ID=4");
    }
    const itemsJson = await items.json(); 
    const burgerPage = document.querySelector(".tarjetasmenus");
    burgerPage.innerHTML = ``;
    console.log(itemsJson) 
    for (const item of itemsJson) { 
      console.log(item) 
      const card = document.createElement("div"); card.className = "tarjetasmenus"; card.innerHTML = 
     `
     <div class="tarjeta-horizontal">
     <div class="tj-foto">
       <img src="${item.Img}"  alt="hamburgesa-top">
     </div>
     <div class="tj-texto">
       <span>${item.Nombre}</span>
       <p>${item.Descripcion}
       </p>
     </div>
     <div class="tj-texto-2">
       <span>Price: ${item.Precio}€</span>
       <a href="#" class="btn-carrito" data-id="1">
         <button class="Agregar-carrito">Add to cart</button>
       </a>
     </div>
   </div>

      `; burgerPage.appendChild(card); 
    }
  }