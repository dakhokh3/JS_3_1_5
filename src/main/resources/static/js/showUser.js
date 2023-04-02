// $.ajax("http://localhost:8080/api/user", {
//     dataType: "json", //или, например, "text"
//     success: function (msg) { //msg - то, что придет с сервера, респонз
//         alert("Прибыли данные: " + msg);
//     }
// })
//
// $.ajax("http://localhost:8080/api/user", {
//     dataType: "json",
//     success: function (msg) {
//         const div = $("#users");
//         msg.forEach(function (el) {
//             $("<div></div>").text(el.id).appendTo(div);
//         })
//     }
// })



const url = "http://localhost:8080/api/user";
const userTable = document.getElementById("user-info");
let userShow = " ";

const userInfo = (user) => {

    userShow += `
<tr>
<td class = "id">${user.id}</td>
<td class = "username">${user.username}</td>
<td class = "firstName">${user.firstName}</td>
<td class = "lastName">${user.lastName}</td>
<td class = "age">${user.age}</td>
<td class= "email">${user.email}</td>
<td class = "roles">${user.roles.map(role => " " + role.name.substring(5))}</td>
</tr>`

    userTable.innerHTML = userShow;
}
fetch(url)
    .then(response => response.json())
    .then(data => userInfo(data))
    .catch(error => console.log(error))