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
            success: function (u) {
                $updatingNode = $('td:contains(' + u.id + ')').parent();
                $updatingNode.html(oneTr(u));
                $('#modal-edit-close').click();
            }
        });
        return false;
    });

    //new user
    $('#saveUser').on('submit', function (event) {
        event.preventDefault();
        let $form = $('#saveUser');
        $.ajax({
            type: 'post',
            url: "/api/v1/admin/saveUser",
            data: $form.serialize(),
            success: function (user) {
                $('#saveUser').trigger('reset');
                $('#users-table-tab').click();
                appendNewUser(user);
            }
        })
    });

    //edit
    $('.table').on('click', '.eBtn', function (event) {
        event.preventDefault();
        let n = $(this).parent().parent();
        $('#id').val($(n).children('td:eq(0)').text());
        $('#username').val($(n).children('td:eq(1)').text());
        $('#email').val($(n).children('td:eq(2)').text());
        $('#password').val($(n).children('td:eq(3)').text());
        $('#country').val($(n).children('td:eq(4)').text());
        $('#modalEdit').modal('show');
    });

    //delete
    $('#user-item').on('click', '.dBtn', function (event) {
        event.preventDefault();
        let n = $(this).parent().parent();
        const href = $(this).attr('href');
        $.ajax({
            type: 'get',
            url: href,
            success: function () {
               n.html('');
            }
        });
    });

    function appendNewUser(u) {
        $('#user-item').append('<tr >' + oneTr(u) + '</tr>');
    }

    function oneTr(u) {
        return '<td>' + u.id + '</td> ' +
            '<td>' + u.username + '</td> ' +
            '<td>' + u.email + '</td> ' +
            '<td>' + u.password + '</td> ' +
            '<td>' + u.country + '</td> ' +
            '<td>' + u.roles +
            '</td> ' + '<td class="d-flex justify-content-around"> ' +
            '<a class="btn btn-primary eBtn" href="/api/v1/admin/findOne/' + u.id + '">Edit</a> ' +
            '<a class="btn btn-danger dBtn" onclick="if (!(confirm(\'Are you sure you want to delete this user?\'))) return false"' +
            ' href="/api/v1/admin/delete/' + u.id + '">Delete</a></td> '
    }
});
