var basic_Auth;

function getServerData(url, success, failure){
	basic_Auth = "Basic " + btoa($("#username").val() + ":" + $("#password").val());
    $.ajax({
        url: url,
        beforeSend: function (xhr) {
			xhr.setRequestHeader ("Authorization", basic_Auth);
		}
    })
    .done(success)
    .fail(failure);
}

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

function getServerData2(url,success){
    requestServerData(url,{},'GET', "json","",success);
}


function callDone(result){
	localStorage.setItem('auth', basic_Auth);
	localStorage.setItem('role', result);
	if (result === "mro") {
		console.log("mro");
		getServerData2("ws/user/mro/"+$("#username").val(),function(result){
			var mro = JSON.parse(JSON.stringify(result));
			localStorage.setItem('mro',mro.MRO_id);
			window.location.replace("home.html");
		});
	}
	if (result === "mcc") {
		window.location.replace("home.html");
	}
}

function callFail(result){
	alert("Error: wrong username and/or password.");
}

$(function(){
	$("#button").click(function(e){
		e.preventDefault();
		e.stopPropagation();
		getServerData("ws/home",callDone,callFail);
	});
});

