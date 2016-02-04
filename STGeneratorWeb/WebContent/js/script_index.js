window.onload = function() {
	$("#input-image").fileinput({showUpload: false, maxFileCount: 1, showPreview: false,minImageWidth: 50, minImageHeight: 50,allowedFileExtensions: ["jpg", "jpge", "png", "gif"],
		elErrorContainer:"#erroUpload",msgInvalidFileExtension:"#{mensagens.erroFormatoArquivo}",browseLabel: "Foto da Carta",browseIcon: '<i class="glyphicon glyphicon-picture"></i> ',
		removeLabel: "Remover",browseClass: "btn btn-info"});
	
	var labels = $("label[for^='modelos']");
	
	for(i = 0 ; i<labels.size();i++){
		labels.get(i).className="img_model"
		labels.get(i).innerHTML = "<img src='modelo?n="+(i+1)+"'style='width:110px'/>";
	}
	
	$('.publico').bootstrapSwitch('state', false);			
};
$(window).bind("pageshow", function() {
	/*
	$("#tituloCarta").val('');
	$("#numeroDaCarta").val('');
	$("#atributo1").val('');
	$("#ValorAtributo1").val('');
	$("#atributo2").val('');
	$("#ValorAtributo2").val('');
	$("#atributo3").val('');
	$("#ValorAtributo3").val('');
	$("#atributo4").val('');
	$("#ValorAtributo4").val('');
	$("#atributo5").val('');
	$("#ValorAtributo5").val('');
	*/
	
	var radios = $("input[id^='modelos']");
	
	for(i = 0 ; i<radios.size();i++){
		radios.get(i).checked = false;
	}			
	
	$("#input-image").val('');
});