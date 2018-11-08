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
	$.get(urlList, function(data, status) {
		$('#display').html(data);
	});
});


$('#user-btn').click(() => {
	const userName = $('#user-input').val();
	$.get(urlUser + userName, function(data, status) {
		$('#display').html(data);
	});
});

$('#post-btn').click(() => {
	let user = {
			firstName: $('#first-name').val(),
			lastName: $('#last-name').val()
	}
	$.ajax({
		url:urlPOST,
		type:"post",
		dataType:"json",
		contentType:"application/json",
		success: function(){
			$("#display").html("success")
		},
		data: JSON.stringify(user)
	});
	$('#display').html(user.firstName + " : " + user.lastName + " added")
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
		dataType:"json",
		contentType:"application/json",
		success: function(){
			$("#display").html("success")
		},
		data: JSON.stringify(user)
	});
	$('#display').html(user.firstName + " : " + user.lastName + " modified")
});

$('#delete-btn').click(() => {
	let urlSearch = urlDELETE + $('#delete').val()
	$.ajax({
		type:"DELETE",
		url: urlSearch,
	});
	$('#display').html($('#delete').val() + " deleted")
})

