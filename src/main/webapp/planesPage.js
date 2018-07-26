function callDone(result){
	var i = 0;
    var t = $('#myTable').DataTable();

    function iter(){
    	if(i < result.length){
    		var plane = JSON.parse(JSON.stringify(result[i]));
				next = new Date(plane.last_maintained);
				next=new Date("12/02/2017");
				now = new Date();
				if(next < now) {
					t.row.add( [plane.plane_id,
	    		            plane.plane_type,
	    		            '<a href="planeDescription.html?plane_type='+plane.plane_type+'"><span class="glyphicon glyphicon-pencil"></span></a>'
											//'<span class="glyphicon glyphicon-warning-sign"></span>'
	    		            //formatDate(new Date(plane.last_maintained)),
	    		            /*
	    		            '<a href="planeDescription.html?plane_id='+plane.plane_id +'&plane_type='+plane.plane_type +
	    		            '"><span class="glyphicon glyphicon-pencil"></span></a>',
							'<span class="glyphicon glyphicon-warning-sign"></span>'
							*/
	    		]).draw(false);
				}
				else {
					t.row.add( [plane.plane_id,
	    		            plane.plane_type,
	    		            '<a href="planeDescription.html?plane_type='+plane.plane_type+'"><span class="glyphicon glyphicon-pencil"></span></a>'
											//'<span></span>'
	    		            //formatDate(new Date(plane.last_maintained)),
	    		            /*
	    		            '<a href="planeDescription.html?plane_id='+plane.plane_id +'&plane_type='+plane.plane_type +
	    		            '"><span class="glyphicon glyphicon-pencil"></span></a>',
											'<span class="glyphicon glyphicon-warning-sign"></span>'
											*/
	    		]).draw(false);
				}
    		i++;
    		iter();
    	}
    }

    iter();
}

$(function(){
	$.getScript('utils.js', function(){
		$.getScript('request.js', function(){
			var utc = new Date().toJSON().slice(0,10);
			getServerData("ws/plane/"+utc,callDone);
	    });
	});
});


$('#searchid').on( 'keyup', function () {
	var table = $('#myTable').DataTable();
    table
        .columns( 0 )
        .search( this.value )
        .draw();
} );

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

/*$('#searchid').on( 'keyup', function () {
	var table = $('#myTable').DataTable();
    table
        .columns( 0 )
        .search('^'+this.value, true, false)
        .draw();
} );*/