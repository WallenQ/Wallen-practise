var ws = null;
var url = "ws://localhost:8080/echo";

function setConnected(connected) {
    document.getElementById('connect').disable = connected;
    document.getElementById('disconnect').disable = !connected;
    document.getElementById('echo').disable = !connected;
}

function conncet() {
    ws = new WebSocket(url);

    ws.onopen = function () {
        setConnected(true);
    };

    ws.onmessage = function (event) {
        log(event.data);
    }

    ws.onclose = function (event) {
        setConnected(false);
        log('Info: Closing Connection.');
    }
}

function disconncet() {
    if (ws != null) {
        ws.close();
        ws = null;
    }
    setConnected(false);
}

function echo() {
    if (ws != null) {
        var message = document.getElementById('message').value;
        log('Sent: ' + message);
        ws.send(message);
    } else {
        alert('connection not established, please connect.')
    }
}

function log(message) {
    var console = document.getElementById('logging');
    var p = document.createElement('p');
    p.appendChild(document.createTextNode(message));
    console.appendChild(p);
    while (console.childNodes.length > 12) {
        console.removeChild(console.firstChild);
    }
    console.scrollTop = console.scrollHeight;
}