$(document).ready(function() {

    /*
     * WebSocket
     */
    var target = ".draggable";
    var WS = window['MozWebSocket'] ? MozWebSocket : WebSocket
    var wsSocket = new WS(WS_LOCATION);
    wsSocket.onmessage = function(event) {
        var data = JSON.parse(event.data);
        if(data.error) {
            wsSocket.close();
        }
        if(data.x != undefined && data.y != undefined && data.username != USERNAME) {
            $(target).css("top", data.x);
            $(target).css("left", data.y);
        }
    };

    var sendMessage = function(x, y) {
        wsSocket.send(JSON.stringify(
            {
                x: x,
                y: y
            }
        ));
    };

    $(target).draggable({
        drag: function() {
            sendMessage($(this).css("top"), $(this).css("left"));
        }
    });

});