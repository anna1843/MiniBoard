// var boardId = document.getElementById('boardIdIn').value;
// console.log(boardId)
// var boardId = document.getElementById("").value;
// console.log(boardId);

const boardId = window.location.pathname.split('/').pop();

console.log(boardId)

$.ajax({
    url: "/api/board/detail/" + boardId,
    method: "GET",
    dataType: "json",
    contentType: "application/json",
    async: false,
    success: function (data){
        console.log(data);
    }
})