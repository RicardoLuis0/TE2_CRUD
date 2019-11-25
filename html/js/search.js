import Product from './products.js';

window.onload=()=>{
	let params=new URLSearchParams(location.search);
	let wrap=document.getElementById("wrap");
	wrap.innerHTML="<div class='centerflex'><form action='busca.html'><input id='q' type='text' name='q' /><input type='submit' value='Buscar' /></form></div>"+wrap.innerHTML;
	let content=document.getElementById("content");
	if(params.has("q")){
		let q=document.getElementById("q");
		q.value=params.get("q");
		Product.searchProducts(params.get("q"),function(prods){
			Product.display(prods,content);
		});
	}else{
		content.innerHTML="<h2>Entre os termos a pesquisar</h2>";
	}
}