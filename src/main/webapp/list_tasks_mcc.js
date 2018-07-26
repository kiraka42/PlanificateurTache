
function callDone(result){
	var i = 0;
    var planned_task;
    var t = $('#example').DataTable();
    
    function iter(){
    	if(i < result.length){
    		planned_task = JSON.parse(JSON.stringify(result[i]));
    		getServerData("ws/plane/id/"+planned_task.planeId,function(result){
    			var plane = JSON.parse(JSON.stringify(result));
    			var next = new Date(planned_task.date);
    			t.row.add( [planned_task.planeId,
    			            plane.plane_type,
    			            formatDate(next),
    			            planned_task.taskId,
                            '<a href="dateSelection.html?plane_id='+plane.plane_id+'&task_id='+planned_task.taskId+
								'"><span class="glyphicon glyphicon-pencil"></span></a>',
                            '<a href="taskDetails.html?id_task='+planned_task.taskId+'"><span class="glyphicon glyphicon-eye-open"></span></a>'
    			] ).draw(false);
    			i++;
    			iter();
    		});
    	}
    }
    
    iter();
}

$(function(){
	$.getScript('utils.js', function(){
		$.getScript('request.js', function(){
			var utc = new Date().toJSON().slice(0,10);
			getServerData("ws/planned_task/"+utc,callDone);
		});
	});
});
