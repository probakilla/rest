const urlList = 'http://localhost:8080/HelloWorld/service/users';
const urlUser = 'http://localhost:8080/HelloWorld/service/user/';
const urlPOST = 'http://localhost:8080/HelloWorld/service/register';
const urlPUT = 'http://localhost:8080/HelloWorld/service/user/';
const urlDELETE = 'http://localhost:8080/HelloWorld/service/user/';

class User {
	constructor(firstName, lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	};
	
	toJSON() {
		return {
			firstName: this.firstName,
			lastName: this.lastName
		};
	};
	
	toString() {
		return firstName + " " + lastName;
	}
}
	
$('#btn-list').click(() => {
	$('#display').html(" ");
	$.ajax({
		url:urlList,
		type:"get",
		dataType:"json",
		data: { getParam: 'value' },
		success: function (data) {
	        $.each(data, function(index, user) {
	            $('#display').append($('<div>', {
	                text: user.firstName + " " + user.lastName
	            }));
	        });
		}
	})	
});


$('#user-btn').click(() => {
	$('#display').html(" ");
	const userName = $('#user-input').val();
	$.ajax({
		url:urlUser + userName,
		type:"get",
		dataType:"json",
		data: { getParam: 'value' },
		success: function (data) {
	       $('#display').append($('<div>', {
	           text: data.firstName + " " + data.lastName
	       }));
		}
	})	
});

$('#post-btn').click(() => {
	let user = {
			firstName: $('#first-name').val(),
			lastName: $('#last-name').val()
	}
	$.ajax({
		url:urlPOST,
		type:"post",
		dataType:"plain/text",
		contentType:"application/json",
		success: function(){
			$("#display").html("success")
		},
		complete: function(){
			$('#display').html(firstName + " : " + lastName + " added")
		},
		data: JSON.stringify(user)
	});
});

$('#put-btn').click(() => {
	let user = {
			firstName: $('#first-name-put').val(),
			lastName: $('#last-name-put').val()
	}
	let urlSearch = urlPUT + $('#put-search').val()
	$.ajax({
		url:urlSearch,
		type:"put",
		dataType:"plain/text",
		contentType:"application/json",
		success: function(){
			$("#display").html("success")
		},		
		complete: function(){
			$('#display').html(user.firstName + " : " + user.lastName + " modified")
		},
		data: JSON.stringify(user)
	});
});

$('#delete-btn').click(() => {
	let urlSearch = urlDELETE + $('#delete').val()
	$.ajax({
		type:"DELETE",
		dataType:"plain/text",
		url: urlSearch,		
		success: function(){
			$("#display").html($('#delete').val() + " deleted")
		},		
		complete: function(){
			$('#display').html($('#delete').val() + " deleted")
		},
	});
})

