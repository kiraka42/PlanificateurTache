
function requestServerData(url,data1,method,dType,cType,success){
	var basic_auth = localStorage.getItem('auth');
	if (basic_auth === null) {
	    $.ajax({
	    	contentType: cType,
	        dataType: dType,
	        url: url,
	        type: method,
	        data: data1
	    }).done(success);
	}
	else {
		$.ajax({
	    	contentType: cType,
	        dataType: dType,
	        url: url,
	        type: method,
	        data: data1,
	        beforeSend: function (xhr) {
				xhr.setRequestHeader ("Authorization", basic_auth);
			}
	    }).done(success);
	}
}

function getServerData(url,success){
    requestServerData(url,{},'GET', "json","",success);
}


function postServerData(url,data,success){
    requestServerData(url,JSON.stringify(data),'POST', "json","application/json",success);
}

function putServerData(url,data,success){
    requestServerData(url,JSON.stringify(data),'PUT', "json","application/json",success);
}

function deleteServerData(url,data,success){
    requestServerData(url,JSON.stringify(data),'DELETE', "json","application/json",success);
}
