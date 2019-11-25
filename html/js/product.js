import Product from './products.js';

window.onload=()=>{
	let params=new URLSearchParams(location.search);
	let error="<h2>Produto inválido</h2>";
	let content=document.getElementById("content");
	if(params.has("id")){
		Product.getProduct(params.get("id"),(product)=>{
			if(product===null){
				content.innerHTML=error;
			}else{
				content.innerHTML="<div class='prod'><div class='prodleft'><img src='"+product.imgUrl+"'></div><div class='prodright'><h2 style='text-align:center;'>"+product.name+"</h2><p>"+product.longDescription+"</p></div></div>";
			}
		});
	}else{
		content.innerHTML=error;
	}

}