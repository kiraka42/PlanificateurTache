function callDone(result){
	//getServerData("ws/task/id/"+result["taskId"],function(result){
	//getServerData("ws/task/id/"+120,function(result){
		$("#period").text(result["periodicity"]);
		$("#ata").text(result["ATA_category"]);
		$("#airPlaneType").text(result["airPlaneType"]);
		$("#hangar").text(JSON.stringify(result["hangar"]));
		$("#time").text(result["duration"]);
		$("#grade").text(result["qualifications_required"]);
		  
		for(i=0;i<result["tools_list"].length;i++){
			$( "#ul1" ).append( "<li>"+result["tools_list"][i]+"</li>" );			  
		}
		for(i=0;i<result["pieces_list"].length;i++){
			$( "#ul2" ).append( "<li>"+result["pieces_list"][i]+"</li>" );			  
		}
		for(i=0;i<result["action_list"].length;i++){
			$( "#ul3" ).append( "<li>"+result["action_list"][i]+"</li>" );			  
		}		 
	$("#tid").text(JSON.stringify(result["taskId"]));

}

$(function(){
	$.getScript('utils.js', function(){
		$.getScript('request.js', function(){
			var idTask=getParameterByName('id_task');
			getServerData("ws/task/id/"+idTask,callDone);
		});
	});
});
function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    console.log(url);
    console.log(regex);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}