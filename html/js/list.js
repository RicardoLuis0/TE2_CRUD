import Product from './products.js';

window.onload=()=>{
	let content=document.getElementById("content");
    Product.searchProducts("",function(prods){
		Product.display(prods,content);
    });

}