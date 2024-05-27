document.addEventListener("DOMContentLoaded", () => {
    Acomp()
  });
  async function Acomp(category) {
    let items; if (category === undefined) {
      items = await fetch("http://localhost:8080/RetoDAW1ModeloAnton/Controller?ACTION=PRODUCT.SQL_FIND_ALL&CATEGORY_ID=3");
    }
    const itemsJson = await items.json(); 
    const AcompPage = document.querySelector(".comEstan");
    AcompPage.innerHTML = ``;
    console.log(itemsJson) 
    for (const item of itemsJson) { 
      console.log(item) 
      const card = document.createElement("div"); card.className = "producto"; card.innerHTML = 
     `

     <div class="fotoprod"> <img src="${item.Img}"></div>
     <div class="texto">
       <div class="nombre">
         <p>${item.Nombre}</p>
       </div>
       <div class="ingredientes">
         <p><span class="texto2">Descripcion:</span><br>${item.Descripcion}</p>
       </div>
       <div class="partebaja">
         <div class="precio">
           <p>${item.Precio}</p>
         </div>
         <a href="#" data-id="1">
           <button class="botoncarro">Carrito</button>
         </a>
       </div>
     </div>
      `; AcompPage.appendChild(card); 
    }
  }