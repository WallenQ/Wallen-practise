var ws = null;
var url = "ws://localhost:8080/echo";
//var url = "ws://echo.websocket.org";

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('echo').disabled = !connected;
}

function connect() {
    ws = new WebSocket(url);
    console.log(111)

    ws.onopen = function () {
        setConnected(true);
    }

    ws.onmessage = function (event) {
        log(event.data);
    }

    ws.onclose = function (event) {
        setConnected(false);
        log('Info: Closing Connection.')
    }

    ws.onerror = function (error) {
        if ("WebSocket" in window) {
            console.log("WebSocket is supported by your browser!");
        } else {
            console.error("WebSocket is NOT supported by your browser!");
        }
        log('Error: ' + error);
        console.error('WebSocket Error:', error);
    }
}

function disconnect() {
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