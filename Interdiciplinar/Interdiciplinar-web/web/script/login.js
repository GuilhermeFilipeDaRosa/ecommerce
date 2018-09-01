function init() {
    document.querySelector("#logar").addEventListener("click", logaUsuario);
}
var URL = "http://localhost:8080/interdiciplinar-web/loginServlet";
function logaUsuario() {
    var form = document.querySelector("#form"),
            formData = {};

    formData["email"] = form.email.value;
    formData["senha"] = form.senha.value;

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
            inputs = document.getElementsByTagName("input");

    for (var i = 0; i < inputs.length; i++) {
        inputs[i].value = "";
    }
    if (obj.mensagem === true) {
        
        alert('logado');
    } else {
        alert('Cadastre-se');
    }
}
init();