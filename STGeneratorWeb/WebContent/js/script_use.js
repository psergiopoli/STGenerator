$(document).ready(function(){			
	$("#next").attr("href","?page="+(pagAtual+1));
	$("#back").attr("href","?page="+(pagAtual-1));
	
	var html = "";
	
	var init = 2;
	max = pagTotal+2;
	
	if(pagTotal>10){
		var max = 10;
	}
	
	if(pagAtual>=30){
		init = pagAtual -9;
		max = 2 + pagAtual;
	}
	
	for(i = init ; i<max;i++){
		html = html+'<li id="page_'+i+'"><a href="?page='+i+'">'+i+'</a></li>';
	}
	
	$("#page_1").after(html);			
	
	if(pagAtual>=10){
		$("#page_1").remove();
	}
	
	if(pagAtual==1){
		$("#back_").attr("class","disabled");
		$("#back").attr("disabled","disabled");
	}			

	if((pagAtual-1)==pagTotal){
		$("#next_").attr("class","disabled");
		$("#next").attr("disabled","disabled");
	}
	
	$("#page_"+pagAtual).attr("class","active");
	
	$("li > a").on("click", function(event){
	    if ($(this).is("[disabled]")) {
	        event.preventDefault();
	    }
	});
});