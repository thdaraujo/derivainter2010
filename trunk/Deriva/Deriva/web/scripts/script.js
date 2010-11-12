$(document).ready(function(){     

	/* Converting the #box div into a bounceBox: */
	$('#box').bounceBox();

        	/* Listening for the click event and toggling the box: */
	$('a.button').click(function(e){
		$('#box').bounceBoxToggle();
		e.preventDefault();
	});

        $('a.button').live('teste', function(e){
		$('#box').bounceBoxToggle();
		e.preventDefault();
	});        
        
	/* When the box is clicked, hide it: */
	$('#botao').click(function(){
		$('#box').bounceBoxHide();
            });
});



