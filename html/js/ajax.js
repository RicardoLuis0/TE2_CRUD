//raw ajax request
function sendAJAX(url,type,data,callbackOk,callbackFail){
	console.log("url="+url);
	console.log("type="+type);
	console.log("data="+data);
	try{
		var request = new XMLHttpRequest();
		request.addEventListener("load",callbackOk);
		request.addEventListener("abort",callbackFail);
		request.addEventListener("error",callbackFail);
		request.addEventListener("timeout",callbackFail);
		request.open(type,url,true);
		request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		if(data!==null){
			request.send(data);
		}else{
			request.send();
		}
		return request;
	}catch(e){
		console.log(e);
		console.log(e.stack);
		return null;
	}
}

//decode assoc array as url encoded parameters
function decodeData(data){
	var output="?";
	for(var key in data){
		if(output.length>1){
			output+="&";
		}
		output+=encodeURIComponent(key)+"="+encodeURIComponent(data[key]);
	}
	return output;
}

export default class ajax {

	static sendPOST(url,data,callbackOk,callbackFail){
		return sendAJAX(url,"POST",decodeData(data),callbackOk,callbackFail);
	}
	
	static sendGET(url,data,callbackOk,callbackFail){
		return sendAJAX(url+decodeData(data),"GET",null,callbackOk,callbackFail);
	}

}

// for both ajax functions, data is a map of values to send, key is parameter name, value is parameter value, TODO

