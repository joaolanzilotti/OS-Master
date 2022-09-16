// Example starter JavaScript for disabling form submissions if there are invalid fields,
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

  $("#cep").keyup(function () {
    let valor = $("#cep").val();
    // let valor = $(this).attr("value")
    console.log(valor.length);
    if (valor.length == 9) {
      consultaEndereco();
    }
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