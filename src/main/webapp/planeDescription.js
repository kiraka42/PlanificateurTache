
function callDone(result){
	var i = 0;
   	var planned_task;
    var t = $('#example').DataTable();

    function iter(){
    	if(i < result.length){
   			planned_task = JSON.parse(JSON.stringify(result[i]));
    		//getServerData("ws/task/id/"+planned_task.airPlaneType,function(result){
				var plane = JSON.parse(JSON.stringify(result));
	    		//var last = new Date(plane.last_maintained);
	    		//var next = new Date(planned_task.date);
				//var now = new Date();
				//if(next < now) {
				var planeId=getParameterByName('plane_id');
					t.row.add( [planned_task.taskId,
								//formatDate(last),
								//formatDate(next),
								
								'<a href="dateSelection.html?plane_id='+planeId+'&task_id='+planned_task.taskId+
								'"><span class="glyphicon glyphicon-pencil"></span></a>',
								'<a href="taskDetails.html?id_task='+planned_task.taskId+'"><span class="glyphicon glyphicon-eye-open"></span></a>',
								'<span class="glyphicon glyphicon-warning-sign"></span>'
					] ).draw(false);
				/*}
				else {
					t.row.add( [planned_task.taskId,
				            //formatDate(last),
				            //formatDate(next),
                            '<a href="dateSelection.html"><span class="glyphicon glyphicon-pencil"></span></a>',
                            '<a href="taskDetails.html"><span class="glyphicon glyphicon-eye-open"></span></a>',
							'<span></span>'
					] ).draw(false);
				}*/
	    		i++;
	    		iter();
	    	//});
    	}
    }

    iter();
}

$(function(){
	$.getScript('utils.js', function(){
		$.getScript('request.js', function(){
		   	var utc = new Date().toJSON().slice(0,10);
		   	var planeType=getParameterByName('plane_type');
		  	getServerData("ws/task/plane_type/" + planeType,callDone);
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

/*$('#searchidt').on( 'keyup', function () {
	var table = $('#example').DataTable();
    table
        .columns( 0 )
        .search( this.value )
        .draw();
} );*/

$('#searchidt').on( 'keyup', function () {
	var table = $('#example').DataTable();
    table
        .columns( 0 )
        .search('^'+this.value, true, false)
        .draw();
} );
