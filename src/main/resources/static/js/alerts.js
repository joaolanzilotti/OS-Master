var titulo;
var mensagem;

function alertaCadastro(titulo , mensagem) {
    iziToast.success({
        title: titulo,
        message: mensagem,
        progressBarColor: 'black',

    });
};

function alertaEditar(titulo, mensagem) {
    iziToast.success({
        title: titulo,
        message: mensagem,
        progressBarColor: 'black',

    });
};

function alertaRemover(titulo, mensagem) {
    iziToast.warning({
        title: titulo,
        titleColor: 'white',
        message: mensagem,
        backgroundColor: 'rgba(255,0,0,0.7)',
        messageColor: 'white',
        progressBarColor: 'black',
    });
};

function alerta(titulo, mensagem){
    iziToast.warning({
        title: titulo,
        message: mensagem,
    });
};

function info(){
    iziToast.info({
        title: 'Hello',
        message: 'Welcome!',
    });
};

function sucess(){
    iziToast.success({
        title: 'OK',
        message: 'Successfully inserted record!',
    });
};

function warning(){
    iziToast.warning({
        title: 'Caution',
        message: 'You forgot important data',
    });
};

function error(){
    iziToast.error({
        title: 'Error',
        message: 'Illegal operation',
    });    
};