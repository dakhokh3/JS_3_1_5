function getUsers() {
    const url = "http://localhost:8080/api/admin/users";
    const userTable = document.getElementById("users-info");
    let showAllUsers = " ";


    const userInfo = (user) => {
        user.forEach(user => {

            showAllUsers += `
 <tr id="user${user.id}" >
<td class = "id">${user.id}</td>
<td class = "username">${user.username}</td>
<td class = "firstName">${user.firstName}</td>
<td class = "lastName">${user.lastName}</td>
<td class = "age">${user.age}</td>
<td class= "email">${user.email}</td>
<td class = "roles">${user.roles.map(role => " " + role.name.substring(5))}</td> 
<td>
<!--<button type="button" id="delete" name="id" value="1">delete</button>-->
<button type="button" class="btn btn-info editBtn" data-toggle="modal"
                    data-target="#editModal"
                    onclick="editUserData(${user.id})">
                Edit
</button>
<td>
<button type="button" class="btn btn-danger"
        data-toggle="modal"
        data-target="#deleteModal"
        onclick="deleteUserData(${user.id})">
        Delete
</button>
</td>
</tr>`

            userTable.innerHTML = showAllUsers;
        })
    }
    fetch(url)
        .then(response => response.json())
        .then(data => userInfo(data))
        .catch(error => console.log(error))
}
getUsers()


async function showRole() {

    const inputRoles = document.getElementById('new_roles');


    inputRoles.innerHTML = `
        <option value="${dbRoles[0].id}" name="ROLE_USER" >${dbRoles[0].name}</option>
        <option value="${dbRoles[1].id}" name="ROLE_ADMIN" >${dbRoles[1].name}</option>
        <option value="${dbRoles[2].id}" name="ROLE_MANAGER" >${dbRoles[2].name}</option>
        `
}

const dbRoles = [{id: 1, name: "ROLE_USER"}, {id: 2, name: "ROLE_ADMIN"}, {id: 3, name: "ROLE_MANAGER"}];
let roleArray = (options) => {
    let array =[]
    for (let i = 0; i < options.length; i++) {
        if (options[i].selected) {
            let role = {
                id: options[i].value,
                name: dbRoles[i].name
            }

            array.push(role)
        }
    }
    return array
}
