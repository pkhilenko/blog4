$(function () {
    //update user
    $('#modalEditForm').on('submit', function (event) {
        event.preventDefault();
        $this = $(this);
        const href = $this.attr('action');
        $.ajax({
            type: 'post',
            url: href,
            data: $this.serialize(),
            success: function (response) {
                $('#users-table').load('/api/v1/admin/user');
                $('#modal-edit-close').click();
            }
        })
    })

    //new user
    $('#saveUser').on('submit', function (event) {
        event.preventDefault();
        let $form = $('#saveUser');
        $.ajax({
            type: 'post',
            url: "/api/v1/admin/saveUser",
            data: $form.serialize(),
            success: function (response) {
                $('#saveUser').trigger('reset');
                $('#users-table-tab').click();
                $.get('/api/v1/admin/user', function (users) {
                    console.log(users);
                    $('#user-item').html('');
                    users.forEach(u => showAll(u));
                });
            }
        })
    })

    function showAll(u) {
        $('#user-item').append(
            '<tr >' +
            '<td>' + u.username + '</td> ' +
            '<td>' + u.email + '</td> ' +
            '<td>' + u.country + '</td> ' +
            '<td>' + u.roles +
            '</td> ' + '<td class="d-flex justify-content-around"> ' +
            '<a class="btn btn-primary eBtn" href="/api/v1/admin/findOne/' + u.id + '">Edit</a> ' +
            '<a class="btn btn-danger dBtn" onclick="if (!(confirm(\'Are you sure you want to delete this user?\'))) return false"' +
            ' href="/api/v1/admin/delete/' + u.id + '">Delete</a></td> ' +
            '</tr>'
        )
    }
});
