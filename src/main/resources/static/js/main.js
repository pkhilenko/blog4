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
                $('#users-table').load('/api/v1/admin/user');
            }
        })
    })
});
