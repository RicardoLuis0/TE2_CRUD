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
            console.log("responseText: "+this.responseText);
            var prod=JSON.parse(this.responseText);
            callback(new Product(prod.id,prod.name,prod.imgUrl,prod.shortDescription,prod.longDescription));//ok
        },function(){
            callback(null);//failed
        });
    }
    
    static searchProducts(query,callback){//callback will be executed with a parameter of an array of Products, on a failed request it is an empty array
        ajax.sendGET(url+"search",{
            "q":query
        },function(){
            console.log("responseText: "+this.responseText);
            var json=JSON.parse(this.responseText);
            var arr=[];
            for(var key in json){
                var prod=json[key];
                arr.push(new Product(prod.id,prod.name,prod.imgUrl,prod.shortDescription,prod.longDescription));//ok
            }
            callback(arr);
        },function(){
            callback([]);//failed
        });
    }

}
