async function editUserData(id) {

    let href = `http://localhost:8080/api/admin/users/${id}`;


         $.get(href, function (user) {
             document.getElementById('id').setAttribute('value', user.id);
             document.getElementById('username').setAttribute('value', user.username);
             document.getElementById('firstName').setAttribute('value', user.firstName);
             document.getElementById('lastName').setAttribute('value', user.lastName);
             document.getElementById('age').setAttribute('value', user.age);
             document.getElementById('email').setAttribute('value', user.email);
             document.getElementById('password').setAttribute('value', user.password);

           const inputRoles = document.getElementById('roles');

           inputRoles.innerHTML = `
            <option value="${dbRoles[0].id}" name="ROLE_USER" >${dbRoles[0].name}</option>
            <option value="${dbRoles[1].id}" name="ROLE_ADMIN" >${dbRoles[1].name}</option>
            <option value="${dbRoles[2].id}" name="ROLE_MANAGER" >${dbRoles[2].name}</option>
            `
        })

    document.getElementById('edit-user-button').addEventListener('click', async () => {
        const inputId = document.getElementById('id');
        const inputUsername = document.getElementById('username');
        const inputFirstName = document.getElementById('firstName');
        const inputLastName = document.getElementById('lastName');
        const inputAge = document.getElementById('age');
        const inputEmail = document.getElementById('email');
        const inputPassword = document.getElementById('password');


        const id = inputId.value;
        const username = inputUsername.value;
        const firstName = inputFirstName.value;
        const lastName = inputLastName.value;
        const age = inputAge.value;
        const email = inputEmail.value;
        const password = inputPassword.value;
        const listRoleEditUser = roleArray(document.getElementById('roles'))


        if (id && username && lastName && firstName && age && email && password) {

            const res = await fetch("http://localhost:8080/api/admin/users", {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ id, username, firstName ,lastName, age, email, password, roles: listRoleEditUser})
            });
            const result = await res.json();
            console.log(result);
            await editUserInTable(result);
            $('#editModal').modal('toggle');
        }
    })
}



async function editUserInTable(result) {

    const id = result.id;
    const res = await fetch(`http://localhost:8080/api/admin/users/${id}`);
    const user = await res.json();

    document.getElementById(`user${user.id}`).remove();
    let strRoles = '';

    user.roles.forEach((role) => {
        strRoles += role.name.substring(5) + ' ';
    })
    getUsers()


}