function init() {
    document.querySelector("#logar").addEventListener("click", logaUsuario);
}
var URL = "http://localhost:8080/interdiciplinar-web/loginServlet";
function logaUsuario() {
    var form = document.querySelector("#form"),
            formData = {};

    formData["email"] = form.email.value;
    formData["senha"] = form.senha.value;

    //requisicaoHttp("POST", URL, true, parseJson);

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
    if (obj.logou === true) {
        localStorage.setItem("usuario", obj.usuario);
        localStorage.setItem("cod",obj.codCliente);
        localStorage.setItem("session", obj.session);
        window.location.href = "../index.html";
    } else {
        localStorage.setItem("usuario", "");
        localStorage.setItem("cod", "");
        localStorage.setItem("session", "");
        alert('Senha ou usuário incorretos, se não possui conta você deve se cadastrar.');
    }
}
init();