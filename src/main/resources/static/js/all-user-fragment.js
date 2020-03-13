$('.table .eBtn').on('click', function (event) {
    event.preventDefault();
    const href = $(this).attr('href');

    $.get(href, function (user) {
        $('#id').val(user.id);
        $('#username').val(user.username);
        $('#country').val(user.country);
        $('#password').val(user.password);
        $('#email').val(user.email);

    });
    $('#modalEdit').modal('show');
})

$('.table .dBtn').on('click', function (event) {
    event.preventDefault();
    const href = $(this).attr('href');
    $.ajax({
        type: 'get',
        url: href,
        success: function () {
            $('#users-table').load('/api/v1/admin/user');
        }
    })
})