$(function () {
    $('.admin-user').click(function (e) {
        window.location.href = '/admin/user';
    });

    $('.admin-show-form').click(function (e) {
        window.location.href = '/admin/showForm';
    });

    // modal show

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

});
