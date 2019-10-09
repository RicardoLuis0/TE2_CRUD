import Product from './products.js';

window.test=()=>{

    Product.getProduct("1",function(prod){
        console.log(prod);
    });
    
    Product.searchProducts("",function(prod){
        console.log(prod);
    });

}