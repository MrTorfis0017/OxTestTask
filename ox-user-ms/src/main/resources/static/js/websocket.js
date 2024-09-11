// websocket.js
const socket = new SockJS('/ws');
const stompClient = new Stomp.over(socket);

stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);

    stompClient.subscribe('/topic/task-updates', function (message) {
        const taskUpdate = JSON.parse(message.body);
        console.log('Task Update:', taskUpdate);

        showNotification(`Task ${taskUpdate.taskId} has been updated. New status: ${taskUpdate.status}`);
    });
});

function showNotification(message) {
    const notification = document.createElement('div');
    notification.className = 'notification';
    notification.innerText = message;

    document.body.appendChild(notification);

    setTimeout(() => {
        notification.remove();
    }, 5000);
}
