function requisicaoHttp(metodo, endereco, assincrona, callback) {
    var http = new XMLHttpRequest();
    http.open(metodo, endereco, assincrona);
    http.addEventListener("load", function () {
        callback(http.responseText);
    });
    http.send();
}

