function init() {
    document.querySelector("#enviar").addEventListener("click", cadastraCliente);
}
var URL = "http://localhost:8080/interdiciplinar-web/clienteServlet";
function cadastraCliente() {
    var form = document.querySelector("#form"),
            formData = {},
            data = form.dataNascimento.value.split("-"),
            senha = form.senha.value;
    if (senha !== form.confsenha.value) {
        alert("As senhas não estão iguais", "Aviso");
        return;
    }
    
    if(form.nome.value === '' || !data || form.cpf.value === '' || form.cep.value === '' || form.endereco.value === ''
            || form.numero.value === '' || form.bairro.value === '' || form.estado.value === '' || form.estado.value === ''
            || form.cidade.value === '' || form.fone.value === '' || form.email.value === ''){
        alert('Você não preencheu todas as informações');
        return;
    }
    
    formData["nome"] = form.nome.value;
    formData["data_nascimento"] = data[2] + '/' + data[1] + '/' + data[0];
    formData["cpf"] = form.cpf.value;
    formData["cep"] = form.cep.value;
    formData["endereco"] = form.endereco.value;
    formData["numero"] = Number(form.numero.value);
    formData["complemento"] = form.complemento.value;
    formData["bairro"] = form.bairro.value;
    formData["estado"] = form.estado.value;
    formData["cidade"] = form.cidade.value;
    formData["telefone"] = form.fone.value;
    formData["email"] = form.email.value;
    formData["senha"] = senha;
    var http = new XMLHttpRequest();
    http.open("POST", URL, true);
    http.addEventListener("load", function () {
        parseJson(http.responseText);
    });
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send(JSON.stringify(formData));
}
function parseJson(jsonData) {
    var obj = JSON.parse(jsonData),
            inputs;
    inputs = document.getElementsByTagName("input");
        for (var i = 0; i < inputs.length; i++) {
            inputs[i].value = "";
        }
    alert(obj.mensagem);
    window.location.href = "../index.html";
}
init();