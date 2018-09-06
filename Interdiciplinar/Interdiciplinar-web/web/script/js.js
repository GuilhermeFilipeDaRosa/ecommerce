
//função para ser executada ao carregar a página
function init() {
    var URL = "http://localhost:8080/interdiciplinar-web/produtoServlet";

    requisicaoHttp("GET", URL, true, montaGrid);
    
}

function montaGrid(dados) {
    //armazena em dvCatalogo a div com id dv-catalogo
    var dvCatalogo = document.querySelector('#dv-catalogo'),
            i, qtde, dvProduto, dvProdutoNome, imgProduto, dvProdutoPreco, btnAdd,
            produtos = JSON.parse(JSON.parse(dados).produtos);
    //percorre cada um dos produtos dentro do objeto infoGridProd
    for (i = 0, qtde = produtos.length; i < qtde; i++) {
        //cria uma div
        dvProduto = document.createElement('DIV');
        //define a classe para a div
        dvProduto.className = 'dv-produto';
        //define um id para a div
        dvProduto.id = produtos[i].codigo;

        //cria uma div
        dvProdutoNome = document.createElement('DIV');
        //define a classe para a div
        dvProdutoNome.className = 'dv-produto-nome';
        //define o conteudo da div
        dvProdutoNome.textContent = produtos[i].nome;
        //adicioma a div dvProdutoNome na div dvProduto
        dvProduto.appendChild(dvProdutoNome);

        //cria uma img
        imgProduto = document.createElement('IMG');
        //define a classe para a img
        imgProduto.className = 'img-produto';
        //define a imagem que será exibida para o produto
        imgProduto.src = produtos[i].imagem;
        //adicioma a img imgProduto na div dvProduto
        dvProduto.appendChild(imgProduto);

        //cria uma div
        dvProdutoPreco = document.createElement('DIV');
        //define a classe para a div
        dvProdutoPreco.className = 'div-produto-preco';
        //define o conteudo da div
        dvProdutoPreco.textContent = 'R$ ' + produtos[i].valor.toFixed(2).replace('.', ',');
        //adicioma a div dvProdutoPreco na div dvProduto
        dvProduto.appendChild(dvProdutoPreco);
        ;

        //cria um button
        btnAdd = document.createElement('BUTTON');
        //define a classe para o button
        btnAdd.className = 'btn-adicionar';
        //define o conteudo do button
        btnAdd.textContent = 'Adicionar';
        //adicioma o button btnAdd na div dvProduto
        btnAdd.addEventListener('click', adicionaCarrinho);
        dvProduto.appendChild(btnAdd);

        //adicioma a div dvProduto na div dvCatalogo
        dvCatalogo.appendChild(dvProduto);
    }
    
    if(localStorage.getItem("usuario") !== null && localStorage.getItem("usuario") !== ''){
        document.querySelector(".barra-topo").style.display = "none";
        document.querySelector(".barra-usuario").style.display = "";
        document.querySelector("#usuario").value = "Olá, "+localStore.getItem("usuario");
    }
    
}
function adicionaCarrinho(e) {
    if (localStorage.getItem("usuario") !== null && !localStorage.getItem("usuario").equals("")) {
        if (confirm("Você deseja adicionar este produto ao carrinho?")) {

            var URL = "http://localhost:8080/interdiciplinar-web/carrinhoServlet",
                    dados = {};

            dados["cproduto"] = Number(e.target.parentNode.id);
            dados["ccliente"] = 1;

            var http = new XMLHttpRequest();
            http.open("POST", URL, true);
            http.addEventListener("load", function () {
                parseJson(http.responseText);
            });
            http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            http.send(JSON.stringify(dados));
        }
    }else{
        alert("Você precisa estar logado para adicionar um item ao carrinho.");
    }
}
;
function parseJson(response) {
    var obj = JSON.parse(response);
    alert(obj.mensagem);
}
;
init();
