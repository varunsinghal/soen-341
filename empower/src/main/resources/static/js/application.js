$(':input').on('focus', function () {
  $(this).attr('autocomplete', 'off')
});


/**
* Open the web socket connection and subscribe the "/notify" channel.
*/
function connect() {
// Create and init the SockJS object
var socket = new SockJS('/ws');
var stompClient = Stomp.over(socket);

// Subscribe the '/notify' channell
stompClient.connect({}, function(frame) {
    stompClient.subscribe('/user/queue/notify', function(notification) {
    // Call the notify function when receive a notification
    notify(notification.body);
    });
});

return;
} // function connect

/**
* Display the notification message.
*/
function notify(message) {
$("#newNotification i").css("display", "inline-block");
$("#notifications-area").prepend(message);
return;
}

/**
* Init operations.
*/
$(document).ready(function() {
// Start the web socket connection.
connect();
});
