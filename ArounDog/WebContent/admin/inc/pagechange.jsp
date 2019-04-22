<%@ page contentType="text/html; charset=UTF-8"%>
$(function(){
	$($("button")[0]).click(function(){
		userList();
	});
	$($("button")[1]).click(function(){
		reportList();
	});
	$($("button")[2]).click(function(){
		adoptList();
	});
	$($("button")[3]).click(function(){
		freeBoadList();
	});
	$($("button")[4]).click(function(){
		adoptManagerList();
	});
});

function openPage(pageName,elmnt,color) {
	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
	  //tabcontent[i].style.display = "none";
	}
	tablinks = document.getElementsByClassName("tablink");
	for (i = 0; i < tablinks.length; i++) {
	  tablinks[i].style.backgroundColor = "";
	}
	document.getElementById(pageName).style.display = "block";
	elmnt.style.backgroundColor = color;
}
function userList(){
	location.href="/users";
}
function reportList(){
	location.href="/reports";
}
function adoptList(){
	location.href="/adopts";
}
function freeBoadList(){
	location.href="/freeboards";
}
function adoptManagerList(){
	location.href="/adoptmanagers";
}