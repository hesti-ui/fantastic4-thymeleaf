$(document).ready(function () {
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

    $(document).on('click', '#btnTambah', function () {
    $('#modalTambah').modal('show');
  });
});