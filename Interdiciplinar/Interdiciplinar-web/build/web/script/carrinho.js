function init(){
    var URL = "http://localhost:8080/interdiciplinar-web/produtoServlet";
    requisicaoHttp("GET", URL, true, montaGrid);
}
init();


