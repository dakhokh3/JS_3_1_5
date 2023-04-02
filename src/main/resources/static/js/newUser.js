


document.getElementById('profile-tab').addEventListener('click', showRole)

document.getElementById('addNewUser').addEventListener('click', createUser)

async function createUser() {
    const inputUsername = document.getElementById('new_Username');
    const inputLastName = document.getElementById('new_lastName');
    const inputFirstName = document.getElementById('new_firstName');
    const inputAge = document.getElementById('new_age');
    const inputEmail = document.getElementById('new_email');
    const inputPassword = document.getElementById('new_password');
    

    const username = inputUsername.value;
    const firstName = inputFirstName.value;
    const lastName = inputLastName.value;
    const age = inputAge.value;
    const email = inputEmail.value;
    const password = inputPassword.value;
    let listRoles = roleArray(document.getElementById('new_roles'));
   

    if (username && firstName && lastName && age && email && password && (listRoles.length != 0)) {

        let res = await fetch("http://localhost:8080/api/admin/users", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username,firstName, lastName, age, email, password, roles: listRoles })
        });
        const result = await res.json();

    }

        inputUsername.value = ''
        inputFirstName.value = ''
        inputLastName.value = ''
        inputAge.value = ''
        inputEmail.value =''
        inputPassword.value = ''
    getUsers()

}
