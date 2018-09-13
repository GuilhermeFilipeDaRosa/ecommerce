function init() {
    var URL = "http://localhost:8080/interdiciplinar-web/carrinhoItensServlet",
            dados = {};
    dados["ccliente"] = Number(localStorage.getItem("cod"));

    requisicaoPost(URL, dados, montaGrid);

}
function montaGrid(dados) {
    //armazena em dvCatalogo a div com id dv-catalogo
    var dvCatalogo = document.querySelector('#itens'),
            i, qtde, dvProduto, dvProdutoNome, imgProduto, dvProdutoPreco, btnAdd,
            produtos = JSON.parse(JSON.parse(dados).produtos);

    //cria uma div
    dvProduto = document.createElement('DIV');
    //define a classe para a div
    dvProduto.className = 'dv-head-carrinho';
    //define um id para a div

    imgProduto = document.createElement('DIV');
    //define a classe para a img
    imgProduto.className = 'dv-head-img-carrinho';
    //define a imagem que será exibida para o produto
    imgProduto.textContent = "Imagem";
    //adicioma a img imgProduto na div dvProduto
    dvProduto.appendChild(imgProduto);

    //cria uma div
    dvProdutoNome = document.createElement('DIV');
    //define a classe para a div
    dvProdutoNome.className = 'dv-head-descricao-carrinho';
    //define o conteudo da div
    dvProdutoNome.textContent = "Descrição";
    //adicioma a div dvProdutoNome na div dvProduto
    dvProduto.appendChild(dvProdutoNome);

    //cria uma div
    dvProdutoPreco = document.createElement('DIV');
    //define a classe para a div
    dvProdutoPreco.className = 'dv-head-preco-carrinho';
    //define o conteudo da div
    dvProdutoPreco.textContent = "Preço";
    //adicioma a div dvProdutoPreco na div dvProduto
    dvProduto.appendChild(dvProdutoPreco);

    //cria uma div
    dvProdutoQtd = document.createElement('DIV');
    //define a classe para a div
    dvProdutoQtd.className = 'dv-head-qtd-carrinho';
    //define o conteudo da div
    dvProdutoQtd.textContent = "Quantidade";
    //adicioma a div dvProdutoPreco na div dvProduto
    dvProduto.appendChild(dvProdutoQtd);
    dvCatalogo.appendChild(dvProduto);

    document.querySelector(".qtd-cart").innerText = produtos.length;
    //percorre cada um dos produtos dentro do objeto infoGridProd
    for (i = 0, qtde = produtos.length; i < qtde; i++) {
        //cria uma div
        dvProduto = document.createElement('DIV');
        //define a classe para a div
        dvProduto.className = 'dv-carrinho-produto';
        //define um id para a div
        dvProduto.id = produtos[i].codigo;

        //cria uma img
        imgProduto = document.createElement('IMG');
        //define a classe para a img
        imgProduto.className = 'img-carrinho-produto';
        //define a imagem que será exibida para o produto
        imgProduto.src = produtos[i].imagem;
        //adicioma a img imgProduto na div dvProduto
        dvProduto.appendChild(imgProduto);

        //cria uma div
        dvProdutoNome = document.createElement('DIV');
        //define a classe para a div
        dvProdutoNome.className = 'dv-carrinho-produto-nome';
        //define o conteudo da div
        dvProdutoNome.textContent = produtos[i].nome;
        //adicioma a div dvProdutoNome na div dvProduto
        dvProduto.appendChild(dvProdutoNome);

        //cria uma div
        dvProdutoPreco = document.createElement('DIV');
        //define a classe para a div
        dvProdutoPreco.className = 'dv-carrinho-produto-preco';
        //define o conteudo da div
        dvProdutoPreco.textContent = 'R$ ' + produtos[i].valor.toFixed(2).replace('.', ',');
        //adicioma a div dvProdutoPreco na div dvProduto
        dvProduto.appendChild(dvProdutoPreco);

        dvProdutoQtd = document.createElement('DIV');
        dvProdutoQtd.className = 'dv-carrinho-produto-qtd';
        dvProdutoQtd.textContent = "1";
        dvProduto.appendChild(dvProdutoQtd);

        dvProdutoQtd = document.createElement('DIV');
        dvProdutoQtd.className = 'dv-carrinho-produto-qtd-add';
        dvProdutoQtd.textContent = "+";
        dvProduto.appendChild(dvProdutoQtd);

        dvProdutoQtd = document.createElement('DIV');
        dvProdutoQtd.className = 'dv-carrinho-produto-qtd-rmv';
        dvProdutoQtd.textContent = "-";
        dvProduto.appendChild(dvProdutoQtd);


        dvCatalogo.appendChild(dvProduto);
    }
    if (localStorage.getItem("usuario") !== null && localStorage.getItem("usuario") !== '') {
        document.querySelector(".barra-topo").style.display = "none";
        document.querySelector(".barra-usuario").style.display = "inline-block";
        document.querySelector("#sair").addEventListener("click", sair);
        document.querySelector("#usuario").innerText = "Olá, " + localStorage.getItem("usuario");
    }
}
function totalCarrinho() {
    cartTotal = document.getElementsByClassName('qtd-cart');
    itensCart = document.querySelectorAll('.dv-carrinho-produto-qtd');
    var i = 0;
    var total = 0;

    while (i < itensCart.length) {
        total = total + parseInt(itensCart[i].textContent);
        i = i + 1;
    }

    cartTotal.innerHTML = total;
}
totalCarrinho();
function efetuaCompra(e) {
    var URL = "http://localhost:8080/interdiciplinar-web/compraServlet",
            dados = {},
            qtde = prompt("Informe a quantidade desejada.", 0);

    if (qtde !== null && qtde > 0) {
        dados["ccliente"] = Number(localStorage.getItem("cod"));
        dados["cproduto"] = Number(e.target.parentNode.id);
        dados["qtde"] = Number(qtde);
        requisicaoPost(URL, dados, resultCompra);
    }
}
function resultCompra(data) {
    var resposta = JSON.parse(data);
}
function sair() {
    document.querySelector(".barra-topo").style.display = "inline-block";
    document.querySelector(".barra-usuario").style.display = "none";
    limpaUsuarioLocalStorage();
    window.location.href = "../index.html";
}
init();

