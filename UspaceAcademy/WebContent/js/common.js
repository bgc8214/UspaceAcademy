$(document).ready(function() {
	$('#gnb > li > a').on('mouseenter', function() {
		$(this).next().stop().slideDown();
	}).parent().on('mouseleave', function() {
		$('ul', this).stop().slideUp();
	});
});