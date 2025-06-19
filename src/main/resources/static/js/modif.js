$(document).ready(function () {
    // Inisialisasi DataTables dan tombol Tambah
    $('#dataTable').DataTable({
        dom:
            "<'row'<'col-md-6'l><'col-md-6 d-flex justify-content-end align-items-center'f>>" +
            "<'row'<'col-sm-12'tr>>" +
            "<'row'<'col-md-5'i><'col-md-7'p>>",
        initComplete: function () {
            var btn = $('<button>')
                .addClass('btn btn-primary btn-sm ml-2')
                .attr('id', 'btnTambah')
                .html('<i class="fa fa-plus me-2"></i> Add')
                .css('margin-top', '-3px');

            $('#dataTable_filter').append(btn);
        }
    });

    // Tombol Tambah diklik
    $(document).on('click', '#btnTambah', function () {
        $('#modalTambah').modal('show');
    });

    // Tombol Edit diklik
    $(document).on('click', '.btn-edit', function () {
        const id = $(this).data('id');
        const nama = $(this).data('username');
        const email = $(this).data('email');
        const noTelp = $(this).data('notelp');
        const departemen = $(this).data('departemen');
        const jabatan = $(this).data('jabatan');

        // Set nilai ke form edit
        $('#editUserId').val(id);
        $('#editUserName').val(nama);
        $('#editEmail').val(email);
        $('#editNoTelp').val(noTelp);
        $('#editDepartement').val(departemen);
        $('#editJabatan').val(jabatan);

        // Tampilkan modal
        $('#modalEdit').modal('show');
    });
});