import Product from './products.js';

window.onload=()=>{

    Product.searchProducts("",function(prods){
		let content=document.getElementById("content");
		if(prods.length==0){
			content.innerHTML="<p>No Products</p>";
		}else{
			content.innerHTML="";
			for(let product of prods){
				content.innerHTML+="<div class='productbox'><div class='centerimg'><a href='produto.html?id="+product.id+"'><img src='"+product.imgUrl+"'class='productimg'></img></div><h4>"+product.name+"</h4></a><p>"+product.shortDescription+"</p></div>";
			}
		}
    });

}