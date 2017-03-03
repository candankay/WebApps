window.onload = function() {
	$('#resultsBlock').hide();
};

function sonucGetir() {
	
    if ($('#fromDate').val() == '' || $('#toDate').val() == '' ||
    		$('#merchant').val() == '' || $('#acquirer').val() == ''){
    	alert("Parametreler Eksik");
    	return;
    }
    var params ={ fromDate: $('#fromDate').val(), 
        	toDate:$('#toDate').val(),
        	merchant:$('#merchant').val(),
        	acquirer:$('#acquirer').val()
        	};
    $.ajax({
        type: "GET",
        url: "reportData?data="+encodeURIComponent(JSON.stringify(params)),
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
   		 dataType: "json"
    	,
         success:function(data){
        	var res = data.response; 
     	 	console.log(res);
     	 	var txt='';
     	 	for(i=0;i<res.length;i++){
     	 		txt = txt + "<tr><td>" + res[i].count + "</td><td>" + res[i].total + "</td><td>" + res[i].currency + "</td></tr>";
     	 	}
     	 	console.log(txt);
     	 	$('#table1').append(txt);
     	 	$('#resultsBlock').show();
         }
   });
}