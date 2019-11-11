import Product from './products.js';

window.onload=()=>{
	let content=document.getElementById("content");
	let params=new URLSearchParams(location.search);
    console.log(params);
	if(params.has("q")){
		Product.searchProducts(params.get("q"),function(prods){
			Product.display(prods,content);
		});
	}else{
		Product.display([],content);
	}
}