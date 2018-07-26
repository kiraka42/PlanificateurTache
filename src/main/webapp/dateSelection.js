
function callDone(result){
	var i = 0;
	
    var table = $('#myTable').DataTable();
    table.clear();
    function iter(){
    	if(i < result.length){
    		var flight = JSON.parse(JSON.stringify(result[i]));
    		var departureAirport;
    		var arrivalAirport;
    		getServerData("ws/flight/"+flight.departure_airport,function(departureAirport){
    			//departureAirport = JSON.parse(JSON.stringify(airportDepart[0]));
    			//console.log(departureAirport.location);
    			table.row.add( [departureAirport.airport_id,
					departureAirport.name,
					departureAirport.location,
					'Departure',
					flight.date_of_departure,
            		//formatDate(new Date(plane.last_maintained)),
            		'<input id="time'+departureAirport.airport_id +'"type="text" name="timepicker" class="timepicker"/>',
            		'<input id="check'+departureAirport.airport_id +'" class="checks" type="checkbox" onchange="choisirAirport(this)"/>'
            		 ]).draw(false);
    			$('#time'+departureAirport.airport_id).timepicker( {
    		        showAnim: 'blind'
    		    } );
    		});
    		getServerData("ws/flight/"+flight.arrival_airport,function(arrivalAirport){
    			//arrivalAirport = JSON.parse(JSON.stringify(airportArrival[0]));
    			table.row.add( [arrivalAirport.airport_id,
    				arrivalAirport.name,
    				arrivalAirport.location,
    				'Arrival',
    				flight.date_of_arrival,
            		//formatDate(new Date(plane.last_maintained)),
            		'<input id="time'+arrivalAirport.airport_id +'"type="text"  name="timepicker" class="timepicker"/>',
            		'<input id="check'+arrivalAirport.airport_id +'" class="checks" type="checkbox" onchange="choisirAirport(this)"/>'
            		 ]).draw(false);
    			$('#time'+arrivalAirport.airport_id).timepicker();
    		});
    		i++;
    		iter();
    	}
    }

    iter();
    
    $('.timepicker').timepicker( {
        showAnim: 'blind'
    } );
}

function getPlaneinformation(){
	$.getScript('request.js', function(){
		var idPlane=getParameterByName("plane_id");
		var d=$("#datepicker").val();
		var initial = d.split(/\//);
		var date= [ initial[2], initial[0],initial[1] ].join('-'); 
		getServerData("ws/flight/"+idPlane+"/"+date,callDone);
	});
}
function printHoure(element){
	var check = null;
	var checks= $('.checks');
	for(var i=0; i < checks.length; i++){
			if(checks[i].checked==true){
				check = checks[i];
				break;
			}
	}
	
	if(check == null){
		alert("choisir un aeroport");
	}else{
		var d=$("#datepicker").val();
		var time = $("#time"+check.id.slice(5)).val();
		if(time.length < 5)
			alert("choisir une heure correcte");
		else{
			var date=new Date(d).toISOString().slice(0,10);
			var dateTime=date+'T'+time+'Z';
			var idAirport = check.id.slice(5);
			console.log(dateTime);
			var plane = getParameterByName("plane_id");
			var task = getParameterByName("task_id");
			/*$.post("ws/planned_task/"+task+"/"+plane+"/" + idAirport +"/" + dateTime,function(result){
				
			});*/
			console.log(idAirport);
			postServerData("ws/planned_task/"+task+"/"+plane+"/" + idAirport +"/" + dateTime,{},function(){
				
			});
			alert("Maintenance scheduled !");
			/*$.ajax({
		        type: 'POST',
		        url: "ws/planed_task/"+task+"/"+plane+"/" + idAirport +"/" + dateTime,
		        success: function() {$.publish('basket-event')},
		        error: function(xhr) { alert(xhr.responseText) }
		      });*/
		}
	}
	//console.log(time);
	
}
function choisirAirport(event){
	
	
	var checks= $('.checks');
	for(var i=0; i < checks.length; i++){
			checks[i].checked=false;
	}
	event.checked = true;
}


function setTask(){
	$.getScript('request.js', function(){
		var idPlane=3;
		var d=$("#datepicker").val();
		var date=new Date(d).toISOString().slice(0,10);
		var plane = getParameterByName("plane_id");
		var task = getParameterByName("task_id");
		console.log(date.toISOString().slice(0,10));
		getServerData("ws/planed_task/"+task+"/"+plane+"/"+date,callDone);
	});
}
function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}
