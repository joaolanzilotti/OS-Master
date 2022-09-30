// Example starter JavaScript for disabling form submissions if there are invalid fields,
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
$(function () {
  $('.selectpicker').selectpicker();
});
$(document).ready(function () {
  //$("#submit").on("click",function(){
  //    console.log("botao clicado");
  //    alert('aaa');
  //});
  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  const forms = document.querySelectorAll('.needs-validation');

  // Loop over them and prevent submission
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
      }

      form.classList.add('was-validated');
    }, false);
  });
  $("#campoCpf").mask("999.999.999-99");
  $('#dinheiro').mask('000.000.000.000,00');
  $('#phone').mask('(00) 0000-0000');
  $('#phoneCell').mask('(00) 00000-0000');
  $('#cep').mask('00000-000');
  $('#numeroEndereco').mask('0000');
  $('#codigoProduto').mask('9999999999999');
  //$('#nascimento').mask('0000000000');
  $("#cep").keyup(function () {
    let valor = $("#cep").val();
    // let valor = $(this).attr("value")
    console.log(valor.length);
    if (valor.length == 9) {
      consultaEndereco();
    }
  });


  $('#money').on('keydown', function (e) {
    // tab, esc, enter
    if ($.inArray(e.keyCode, [9, 27, 13]) !== -1 ||
      // Ctrl+A
      (e.keyCode == 65 && e.ctrlKey === true) ||
      // home, end, left, right, down, up
      (e.keyCode >= 35 && e.keyCode <= 40)) {
      return;
    }

    e.preventDefault();

    // backspace & del
    if ($.inArray(e.keyCode, [8, 46]) !== -1) {
      $(this).val('');
      return;
    }

    var a = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "`"];
    var n = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "0"];

    var value = $(this).val();
    var clean = value.replace(/\./g, '').replace(/,/g, '').replace(/^0+/, '');

    var charCode = String.fromCharCode(e.keyCode);
    var p = $.inArray(charCode, a);

    if (p !== -1) {
      value = clean + n[p];



      var formatted = '';
      for (var i = 0; i < value.length; i++) {
        var sep = '';
        if (i == 2) sep = '.';
        formatted = value.substring(value.length - 1 - i, value.length - i) + sep + formatted;
      }

      $(this).val(formatted);
      console.log(formatted);
    }

    return;

  });

  $('#moneyProduto').on('keydown', function (e) {
    // tab, esc, enter
    if ($.inArray(e.keyCode, [9, 27, 13]) !== -1 ||
      // Ctrl+A
      (e.keyCode == 65 && e.ctrlKey === true) ||
      // home, end, left, right, down, up
      (e.keyCode >= 35 && e.keyCode <= 40)) {
      return;
    }

    e.preventDefault();

    // backspace & del
    if ($.inArray(e.keyCode, [8, 46]) !== -1) {
      $(this).val('');
      return;
    }

    var a = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "`"];
    var n = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "0"];

    var value = $(this).val();
    var clean = value.replace(/\./g, '').replace(/,/g, '').replace(/^0+/, '');

    var charCode = String.fromCharCode(e.keyCode);
    var p = $.inArray(charCode, a);

    if (p !== -1) {
      value = clean + n[p];



      var formatted = '';
      for (var i = 0; i < value.length; i++) {
        var sep = '';
        if (i == 2) sep = '.';
        formatted = value.substring(value.length - 1 - i, value.length - i) + sep + formatted;
      }

      $(this).val(formatted);
      console.log(formatted);
    }

    return;

  });



});

function consultaEndereco() {
  let cep = document.querySelector('#cep').value;

  if (cep.length !== 9) {
    alert('CEP Inválido');
    return;
  }


  let url = `http://viacep.com.br/ws/${cep}/json/`;

  var logradouro = document.getElementById("logradouro");
  var bairro = document.getElementById("bairro");
  var localidade = document.getElementById("localidade");
  var uf = document.getElementById("uf");

  fetch(url).then(function (response) {
    response.json().then(function (data) {
      console.log(data)
      logradouro.value = data.logradouro;
      bairro.value = data.bairro;
      localidade.value = data.localidade;
      uf.value = data.uf;
      if (data.erro == "true") {
        logradouro.value = "";
        bairro.value = "";
        localidade.value = "";
        uf.value = "";
      }
    })
  });
}

/*.catch(err => {
  logradouro.value = "ds";
  bairro.value = "sd";
  localidade.value = "";
  uf.value = "";
  alert("erro");
})*/