$(async function () {
    await adminAllUsers();

});
const table = $('#allUserTable');

// function SortArray(x, y){
//     return x.localeCompare(y);
// }
//
// function stringRoles (user) {
//     let arRole = [];
//     user.roles.forEach(role =>{
//         arRole.push(role.name.replace("ROLE_",""));
//     })
//     return arRole.sort(SortArray).join(" ");
// }

async function adminAllUsers() {
    table.empty()
    fetch("http://localhost:8090/api/admin")
        .then(res => res.json())
        .then(data => {
            data.forEach(user => {
                let tableWithUsers = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>                         
                            <td>${user.age}</td>
                            <td>${user.email}</td>
                           <td>${stringRoles(user)}</td>
                           
                            
                            <td>
                                <button type="button" class="btn btn-info" data-toggle="modal" id="buttonEdit"
                                data-action="edit" data-id="${user.id}" data-target="#edit">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" data-toggle="modal" id="buttonDelete"
                                data-action="delete" data-id="${user.id}" data-target="#delete">Delete</button>
                            </td>
                        </tr>)`;

                table.append(tableWithUsers);
            })
        })

}
