/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function requisicaoHttp(metodo, endereco, assincrona, callback) {
    var http = new XMLHttpRequest();
    http.open(metodo, endereco, assincrona);
    http.addEventListener("load", function () {
        callback(http.responseText);
    });
    http.send();
}

