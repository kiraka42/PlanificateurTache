	function fn(e) {	
		var file = e.target.files[0];		
		if(e.target.id == "mpdFile"){
			
			parseToJsonTask(file);
		}else{
			parseToJsonFlight(file);
		}		
	}
	
	function success(){
		console.log("success")
	}
	function toarray(str){
		var s = str.split(",")
	}
	
	function parseToJsonTask(file){
		var reader = new FileReader();
	
	    reader.onload = function(e){
	        var data = e.target.result;
	        var workbook = XLSX.read(data, {type : 'binary'});
	        var table = document.getElementById("task");
			var pre = document.getElementById("pre");
			pre.style.visibility="visible";
			var table_row1 = table.insertRow(0);
			table_row1.insertCell(0).innerHTML="taskId";
			table_row1.insertCell(1).innerHTML="periodicity";
			table_row1.insertCell(2).innerHTML="airPlaneType";
			table_row1.insertCell(3).innerHTML="ATA_category";
			table_row1.insertCell(4).innerHTML="hangar";
			table_row1.insertCell(5).innerHTML="duration";
			table_row1.insertCell(6).innerHTML="action_list";
			table_row1.insertCell(7).innerHTML="pieces_list";
			table_row1.insertCell(8).innerHTML="qualifications_required";
			
	        workbook.SheetNames.forEach(function(sheetName){
	            // Here is your object
	            var XL_row_object = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheetName]);
	            var json_object = JSON.stringify(XL_row_object);
	       
	            for(var i = 0;i<XL_row_object.length;i++){
	            	var table_row1= table.insertRow(i+1);
	            	var obj={};
	            	obj["taskId"]=parseInt(XL_row_object[i]["taskId"]);
	    			obj["periodicity"]=parseInt(XL_row_object[i]["periodicity"]);
	    			obj["airPlaneType"]=XL_row_object[i]["airPlaneType"];			
	    			obj["ATA_category"]=XL_row_object[i]["ATA_category"];
	    			obj["hangar"]=JSON.parse(XL_row_object[i]["hangar"]);
	    			obj["duration"]=XL_row_object[i]["duration"];			
	    			obj["action_list"]=XL_row_object[i]["action_list"].split(",");
	    			obj["tools_list"]=XL_row_object[i]["tools_list"].split(",");
	    			obj["pieces_list"]=XL_row_object[i]["pieces_list"].split(",");
	    			obj["qualifications_required"]=XL_row_object[i]["qualifications_required"];
	    			putServerData("ws/task/add",obj,success);
	    			
	    			table_row1.insertCell(0).innerHTML=obj["taskId"];
	    			table_row1.insertCell(1).innerHTML=obj["periodicity"];
	    			table_row1.insertCell(2).innerHTML=obj["airPlaneType"];
	    			table_row1.insertCell(3).innerHTML=obj["ATA_category"];
	    			table_row1.insertCell(4).innerHTML=obj["hangar"];
	    			table_row1.insertCell(5).innerHTML=obj["duration"];
	    			table_row1.insertCell(6).innerHTML=obj["action_list"];
	    			table_row1.insertCell(7).innerHTML=obj["pieces_list"];
	    			table_row1.insertCell(8).innerHTML=obj["qualifications_required"];
	            }
	            
	        })
	
	    };
	
	    reader.onerror = function(ex){
	        console.log(ex);
	    };

    reader.readAsBinaryString(file);
};
function parseToJsonFlight(file){
	var reader = new FileReader();

    reader.onload = function(e){
        var data = e.target.result;
        var workbook = XLSX.read(data, {type : 'binary'});
        var table = document.getElementById("flight");
		var pre = document.getElementById("pre");
		pre.style.visibility="visible";
		var table_row1 = table.insertRow(0);
		table_row1.insertCell(0).innerHTML="flight_id";
		table_row1.insertCell(1).innerHTML="planeId";
		table_row1.insertCell(2).innerHTML="commercial_number";
		table_row1.insertCell(3).innerHTML="departure_airport";
		table_row1.insertCell(4).innerHTML="arrival_airport";
		table_row1.insertCell(5).innerHTML="date_of_departure";
		table_row1.insertCell(6).innerHTML="date_of_arrival";
		workbook.SheetNames.forEach(function(sheetName){
            // Here is your object
            var XL_row_object = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheetName]);
            var json_object = JSON.stringify(XL_row_object);
            var list =[];
            for(var i = 0;i<XL_row_object.length;i++){
            	var table_row1= table.insertRow(i+1);
            	var obj={};
    			obj["commercial_number"]=XL_row_object[i]["commercial_number"];
    			obj["planeId"]=parseInt(XL_row_object[i]["planeId"]);
    			obj["departure_airport"]=parseInt(XL_row_object[i]["departure_airport"]);			
    			obj["arrival_airport"]=parseInt(XL_row_object[i]["arrival_airport"]);
    			obj["date_of_departure"]=XL_row_object[i]["date_of_departure"];
    			obj["date_of_arrival"]=XL_row_object[i]["date_of_arrival"];	
    			obj["flight_id"]=parseInt(XL_row_object[i]["flight_id"]);
    			putServerData("ws/flight/add",obj,success);
    			
    			table_row1.insertCell(0).innerHTML=obj["flight_id"];
    			table_row1.insertCell(1).innerHTML=obj["planeId"];
    			table_row1.insertCell(2).innerHTML=obj["commercial_number"];
    			table_row1.insertCell(3).innerHTML=obj["departure_airport"];
    			table_row1.insertCell(4).innerHTML=obj["arrival_airport"];
    			table_row1.insertCell(5).innerHTML=obj["date_of_departure"];
    			table_row1.insertCell(6).innerHTML=obj["date_of_arrival"];
            }
            
        })

    };

    reader.onerror = function(ex){
        console.log(ex);
    };

reader.readAsBinaryString(file);
};