$(document).ready(function(){

	// ROLLOVER EFFECT
	$('.btn,.socialIcon').mouseover( function() { $(this).fadeTo(300,0.7); });
	$('.btn,.socialIcon').mouseout( function() { $(this).fadeTo(300,1); });
      
	$('.links').hover(function() {
	$(this).animate({ paddingLeft: '10px' }, 150);
	}, function() {
	$(this).animate({ paddingLeft: '0px' }, 150);        
	});
	
});