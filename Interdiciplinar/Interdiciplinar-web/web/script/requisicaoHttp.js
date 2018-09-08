function requisicaoHttp(metodo, endereco, assincrona, callback, variavel) {
    var http = new XMLHttpRequest();
    http.open(metodo, endereco, assincrona);
    http.addEventListener("load", function () {
        callback(http.responseText);
    });
    if(variavel === null || variavel === ''){
        http.send();
    }else{
        http.send(JSON.stringify(variavel));
    }
}

