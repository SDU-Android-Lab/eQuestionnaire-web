var flashvars = {};
flashvars.cssSource = "/public/piecemaker/piecemaker.css";
flashvars.xmlSource = "/public/piecemaker/piecemaker.xml";

var params = {};
params.play = "true";
params.menu = "false";
params.scale = "showall";
params.wmode = "transparent";
params.allowfullscreen = "true";
params.allowscriptaccess = "always";
params.allownetworking = "all";

swfobject.embedSWF('/public/piecemaker/piecemaker.swf', 'piecemaker', '940',
		'375', '10', null, flashvars, params, null);