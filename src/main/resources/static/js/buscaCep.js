$(document).ready(function () {

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