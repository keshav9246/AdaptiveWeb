/**
 * 
 * 
 * 
 */


function getData()
{
$.post("profile.jsp", function(req,res)
		{
	console.dir(req.body.name);
		})	
}