//URI de WebSockets
var wsURI = 'ws://' + document.location.host + document.location.pathname.substr(0, document.location.pathname.indexOf("/faces")) + "/websocket";
console.log(wsURI);
//Declaracion del websocket
var websocket = new WebSocket(wsURI);
//Variables
var isConectado = false;
//Componentes
var formulario = document.getElementById("formulario");
var usuarioOrMensaje = document.getElementById("txtUsuarioOrMensaje");
var usuarios = document.getElementById("usuariosConectados");
var mensajes = document.getElementById("chatLog");
var salida = document.getElementById("salida");
var btnConectar = document.getElementById("btnConectar");
var btnEnviar = document.getElementById("btnEnviar");
var btnDesconectar = document.getElementById("btnDesconectar");
//Variables
var nombreUsuario;
//Eventos de websocket
websocket.onopen = function (evt) {
    onOpen(evt);
};
websocket.onclose = function (evt) {
    onClose(evt);
};
websocket.onerror = function (evt) {
    onError(evt);
};
websocket.onmessage = function (evt) {
    onMessage(evt);
};

function conectar() {
    if (usuarioOrMensaje.value) {
        nombreUsuario = usuarioOrMensaje.value;
        websocket.send(nombreUsuario + " se ha unido");
        document.getElementById("btnEnviar").style.display = "initial";
        btnEnviar.style.display = "initial";
        btnDesconectar.style.display = "initial";
        btnConectar.style.display = "none";
        usuarioOrMensaje.value = "";
        isConectado = true;
    } else {
        alert("Ponte un nombre de usuario");
    }
}

function enviar_mensaje() {
    websocket.send(nombreUsuario + ": " + usuarioOrMensaje.value);
    usuarioOrMensaje.value = "";
}

function onOpen() {
    writeToScreen("CONECTADO A WEBSOCKET CHAT");
}

function onClose() {
    writeToScreen("DESCONECTADO DE WEBSOCKET CHAT");
}

function onMessage(evt) {
    writeToScreen("RECIBIENDO " + evt.data);
    if (evt.data.indexOf("se ha unido") !== -1) {
        usuarios.value = usuarios.value + evt.data.substr(0, evt.data.indexOf(" se ha unido")) + "\n";
    } else {
        mensajes.innerHTML += evt.data + "\n";
    }
}

function onError(evt) {
    writeToScreen('<span style="color: red;">HA OCURRIDO UN ERROR</span>' + evt.data);
}

function desconectar() {
    websocket.close();
    btnEnviar.style.display = "none";
    btnDesconectar.style.display = "none";
    btnConectar.style.display = "initial";
}

function writeToScreen(mensaje) {
    var pre = document.createElement("p");
    pre.style.wordWrapp = "break-word";
    pre.innerHTML = mensaje;
    salida.appendChild(pre);
}

function pressAction(evt){
    if(evt.keyCode === 13){
        if(isConectado === false){
            conectar();
        } else {
            enviar_mensaje();
        }
    }
}