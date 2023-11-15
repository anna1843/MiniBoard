$.ajax({
    url: "/api/board/list",
    method: "GET",
    dataType: "json",
    contentType: "application/json",
    async: false,
    success: function (data){
        console.log(data); // boardList의 이름으로 모든 게시판 출력
        console.log(data.boardList.content.length);

        const options = {year: "numeric", month: "long", day: "numeric"}; // 날짜 형식 설정 년,월,일

        str = '<tr>';
        for(var i = 0; i<data.boardList.content.length; i++){
            // 날짜
            const time = new Date(data.boardList.content[i].createTime).toLocaleDateString("ko-KR", options); // createTime형식 설정
            // 유형
            let type = data.boardList.content[i].boardType;
            if(type === "GENERAL"){
                type = "일반게시판"
            }else if(type === "ANONYMOUS"){
                type = "익명게시판"
            }else if(type === "SECRET"){
                type = "비밀게시판"
            }

            var boardId = data.boardList.content[i].id;

            str += '<td>' + (i+1) + '</td>' +
            '<td>' + type + '</td>' +
            '<td class="boardTitle">' + '<a href="/board/detail/' + boardId + '">' + data.boardList.content[i].title + '</a>' + '</td>' +
            '<td>' + data.boardList.content[i].writer + '</td>' +
            '<td>' + time + '</td>' +
            '<td>' + data.boardList.content[i].hit + '</td>' +
            // '<td>' + '<a href="/board/detail/' + boardId + '">' + boardId + '</a>' + '</td>';
            '<input type="text" id="boardIdIn">' + boardId;
            str += '</tr>';

        }$('#board').append(str);
    },error: function (XMLHttpRequest, textStatus, errorThrown) {
        alert("통신 실패.")
    }
})
