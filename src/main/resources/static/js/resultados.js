$(function() {
	$('.mesRef').datepicker({
		format : "mm/yyyy",
		language : "pt-BR",
		autoclose : true,
		orientation : "bottom",
	});
	
	$('.datepicker').datepicker({
		format : "dd/mm/yyyy",
		language : "pt-BR",
		autoclose : true,
		orientation : "bottom",
	});
	
	
	$('[rel="tooltip"]').tooltip();
	
	$('.hm-link-entregar').on('click', function(event) {
		event.preventDefault();
		var botaoEntregar = $(event.currentTarget);
		var urlEtregar = botaoEntregar.attr("href");

		var response = $.ajax({
			url 	: 	urlEtregar,
			type 	:	'PUT'
		});
		
		response.done(function(e) {
			var idResultado = botaoEntregar.data('id');
			$('[data-id='+idResultado+ ']').hide();
			$('[data-entrega-id='+idResultado+ ']').html(e[0]);
			botaoEntregar.hide();
			$('[data-role='+idResultado+']').html('<span class="label label-success">'+ e[1] +'</span>');
		});
		
		response.fail(function(e) {
			console.log(e);
			alert('Erro ao entregar resultado');
		});
		
	});
});