async function deleteUserData(id) {

    let href = `http://localhost:8080/api/admin/users/${id}`
    $.get(href, function (user) {
        document.getElementById('delete_Id').setAttribute('value', user.id);
        document.getElementById('delete_Username').setAttribute('value', user.username);
        document.getElementById('delete_FirstName').setAttribute('value', user.firstName);
        document.getElementById('delete_LastName').setAttribute('value', user.lastName);
        document.getElementById('delete_Age').setAttribute('value', user.age);
        document.getElementById('delete_Email').setAttribute('value', user.email);
        const inputRoles = document.getElementById('delete_Roles');


        inputRoles.innerHTML = `
        <option value="${dbRoles[0].id}" name="ROLE_USER" >${dbRoles[0].name}</option>
        <option value="${dbRoles[1].id}" name="ROLE_ADMIN" >${dbRoles[1].name}</option>
        <option value="${dbRoles[1].id}" name="ROLE_MANGER" >${dbRoles[2].name}</option>
        `
    })

    document.getElementById('delete-user-button').addEventListener('click', async () =>{
        const res = await fetch(`http://localhost:8080/api/admin/users/${id}`, {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json',
            }
        });
        document.getElementById(`user${id}`).remove();

        $('#deleteModal').modal('toggle');

    })

}