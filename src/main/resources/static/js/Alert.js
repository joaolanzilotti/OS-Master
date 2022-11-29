function alertaProdutoCadastrado() {
    iziToast.success({
        title: 'SUCESSO',
        message: 'Produto Cadastrado!',
        progressBarColor: 'black',

    });
};

function alertaProdutoEditado() {
    iziToast.success({
        title: 'SUCESSO',
        message: 'Produto Editado!',
        progressBarColor: 'black',

    });
};

function alertaProdutoRemovido() {
    iziToast.warning({
        title: 'ATENÇÃO',
        titleColor: 'white',
        message: 'Produto Removido com Sucesso!',
        backgroundColor: 'rgba(255,0,0,0.7)',
        messageColor: 'white',
        progressBarColor: 'black',
    });
};