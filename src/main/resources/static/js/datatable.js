$(document).ready(function () {
    $('#tabela').DataTable({
      responsive: {
        details: {
          display: $.fn.dataTable.Responsive.display.modal({
            header: function (row) {
              var data = row.data();
              return 'Detalhes ' + data[1];
            }
          }),
          renderer: $.fn.dataTable.Responsive.renderer.tableAll({
            tableClass: 'table'
          })
        }
      },
      language: {
        lengthMenu: 'Mostrar  _MENU_ ',
        zeroRecords: 'Nenhum Resultado!',
        info: '',
        infoEmpty: '',
        infoFiltered: '',
        search: "Buscar",
        paginate: {
          previous: 'Anterior',
          next: 'Proximo',
        },
      },
    });
  });