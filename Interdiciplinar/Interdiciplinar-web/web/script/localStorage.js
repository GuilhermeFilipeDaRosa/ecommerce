function limpaUsuarioLocalStorage() {
    localStorage.setItem("usuario", "");
    localStorage.setItem("cod", "");
    localStorage.setItem("session", "");
}
function isUsuarioLogado(){
    if(localStorage.getItem("usuario") !== null && localStorage.getItem("usuario") !== ''
            && localStorage.getItem("cod") !== null && localStorage.getItem("cod") !== ''){
        return true;
    }
    return false;
}