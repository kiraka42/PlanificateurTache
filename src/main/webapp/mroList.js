
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
                            '<button id="'+i+'" name="'+planned_task.planningTaskId+'" type="button">Done</button>',
                            '<a href="taskDetails.html?id_task='+planned_task.taskId+'"><span class="glyphicon glyphicon-eye-open"></span></a>'
    			] ).draw(false);
    			i++;
    			iter();
    		});
    	}
    }
    
    iter();
}



$( "#example tbody" ).on( "click", "button",function() {
	var t = $('#example').DataTable();
    var task=this.name;
    postServerData("ws/planned_task/"+task,{},function(){
    	
    });
    var tr=this.closest('tr');
    var row = t.row(tr);
    row.remove().draw(false);
});



$(function(){
	$.getScript('utils.js', function(){
		$.getScript('request.js', function(){
			var utc = new Date().toJSON().slice(0,10);
			console.log(utc);
			var mroid = localStorage.getItem('mro');
			getServerData("ws/planned_task/"+mroid+"/"+utc,callDone);
		});
	});
});

$('#searchid').on( 'keyup', function () {
	var table = $('#example').DataTable();
    table
        .columns( 0 )
        .search( this.value )
        .draw();
} );

$('#searchid').on( 'keyup', function () {
	var table = $('#example').DataTable();
    table
        .columns( 0 )
        .search('^'+this.value, true, false)
        .draw();
} );

/*$('#searchidt').on( 'keyup', function () {
	var table = $('#example').DataTable();
    table
        .columns( 4 )
        .search( this.value )
        .draw();
} );*/

$('#searchidt').on( 'keyup', function () {
	var table = $('#example').DataTable();
    table
        .columns( 4 )
        .search('^'+this.value, true, false)
        .draw();
} );