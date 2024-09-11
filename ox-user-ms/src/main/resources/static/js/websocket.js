document.addEventListener("DOMContentLoaded", function () {
    const socket = new SockJS('http://localhost:9071/ws');
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/change-logs', function (message) {
            const changeLog = JSON.parse(message.body);
            addChangeLogToSidebar(changeLog);
        });
    });

    function addChangeLogToSidebar(changeLog) {
        const logContainer = document.getElementById('changeLogsSidebar');
        const logItem = document.createElement('li');
        logItem.textContent = `${new Date(changeLog.timestamp).toLocaleString()}: ${changeLog.details}`;
        logContainer.appendChild(logItem);
    }
});
