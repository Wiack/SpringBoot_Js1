$(async function () {
    await thisUser();
});

function SortArray(x, y){
    return x.localeCompare(y);
}

function stringRoles (user) {
    let arRole = [];
    user.roles.forEach(role =>{
        arRole.push(role.name.replace("ROLE_",""));
    })
    return arRole.sort(SortArray).join(" ");
}

async function thisUser() {
    fetch("http://localhost:8090/api/user")
        .then(res => res.json())
        .then(data => {

            let roles = stringRoles(data);

            let user = `$(
            <tr>
                <td>${data.id}</td>
                <td>${data.firstName}</td>
                <td>${data.lastName}</td>
                <td>${data.age}</td>
                <td>${data.email}</td>
                <td>${roles}</td>)`;

            $('#userEmail').append(data.email);
            $('#useroles').append(roles);
            $('#userPage').append(user);
        })
}



