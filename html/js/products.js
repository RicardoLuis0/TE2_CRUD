import ajax from './ajax.js';

const url="http://localhost:8080/api/products/";

export default class Product {
    id;
    name;
    imgUrl;
    shortDescription;
    longDescription;
    constructor(id,name,imgUrl,shortDescription,longDescription){
        this.id=id;
        this.name=name;
        this.imgUrl=imgUrl;
        this.shortDescription=shortDescription;
        this.longDescription=longDescription;
    }
    
    static getProduct(id,callback){//callback will be executed with a parameter of either product on successful request, or null on failed request
        ajax.sendGET(url+"get",{
            "id":id
        },function(){
            let prod=JSON.parse(this.responseText);
            callback(new Product(prod.id,prod.name,prod.imgUrl,prod.shortDescription,prod.longDescription));//ok
        },function(){
            callback(null);//failed
        });
    }
    
    static searchProducts(query,callback){//callback will be executed with a parameter of an array of Products, on a failed request it is an empty array
        ajax.sendGET(url+"search",{
            "q":query
        },function(){
			let arr=[];
            try{
				let json=JSON.parse(this.responseText);
				for(let key in json){
					let prod=json[key];
					arr.push(new Product(prod.id,prod.name,prod.imgUrl,prod.shortDescription,prod.longDescription));//ok
				}
            }catch(e){
				arr=[];
			}
			callback(arr);
        },function(){
            callback([]);//failed
        });
    }

	static display(arr,element,pre=""){
		if(arr.length==0){
			element.innerHTML=pre+"<h2>Nenhum Produto</h2>";
		}else{
			content.innerHTML=pre;
			for(let product of arr){
				element.innerHTML+="<div class='productbox'><div class='centerflex'><a href='produto.html?id="+product.id+"'><img src='"+product.imgUrl+"'class='productimg'></img></div><h4>"+product.name+"</h4></a><p>"+product.shortDescription+"</p></div>";
			}
		}
	}
}
