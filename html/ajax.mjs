//encodeURIComponent
/**
 * for both ajax functions, data is a map of values to send, key is parameter name, value is parameter value
 */
export function sendHttpPOST(url,method,data){
	try{
		var request = new XMLHttpRequest();
		request.addEventListener("load",method);
		request.open(type,url,true);
		request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		request.send(data);
	}catch(e){
		console.log(e);
		console.log(e.stack);
		return;
	}
}

export function sendHttpGET(url,method,data){
	try{
		var request = new XMLHttpRequest();
		request.addEventListener("load",method);
		request.open(type,url,true);
		request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		request.send(data);
	}catch(e){
		console.log(e);
		console.log(e.stack);
		return;
	}
}