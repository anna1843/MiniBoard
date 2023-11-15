// 텍스트 에디터 설정
const editor = document.getElementById('editor');
const btnBold = document.getElementById('btn-bold');
const btnItalic = document.getElementById('btn-italic');
const btnUnderline = document.getElementById('btn-underline');
const btnStrike = document.getElementById('btn-strike');
btnBold.addEventListener('click', function(){
    setStyle('bold');
});
btnItalic.addEventListener('click', function(){
    setStyle('italic');
});
btnUnderline.addEventListener('click', function(){
    setStyle('underline');
});
btnStrike.addEventListener('click', function(){
    setStyle('strikeThrough');
});
function setStyle(style){
    document.execCommand(style);
    focusEditor();
    checkStyle();
}
// 버튼 클릭 시 포커스를 잃기 때문에 다시 포커스를 해줌
function focusEditor(){
    editor.focus({preventScroll: true});
}
editor.addEventListener('keydown', function(){
    checkStyle();
});
editor.addEventListener('mousedown', function(){
    checkStyle();
});
function checkStyle(){
    if(isStyle('bold')){
        btnBold.classList.add('active');
    }else{
        btnBold.classList.remove('active');
    }
    if(isStyle('italic')){
        btnItalic.classList.add('active');
    }else{
        btnItalic.classList.remove('active');
    }
    if(isStyle('underline')){
        btnUnderline.classList.add('active');
    }else{
        btnUnderline.classList.remove('active');
    }
    if(isStyle('strikeThrough')){
        btnStrike.classList.add('active');
    }else{
        btnStrike.classList.remove('active');
    }
}
function isStyle(style){
    return document.queryCommandState(style);
}

// boardCreate
var submit = document.querySelector('#create');
    submit.addEventListener("click", function(){
    console.log($('#editor').val());
    
    var typeRadio = $('input[name=type]:checked').val();
    var contentDiv = document.getElementById("editor").innerHTML // contenteditable설정한거 text로 설정
    const data = {
        title: $('#title').val(),
        writer: $('#writer').val(),
        content: contentDiv,
        boardType: typeRadio
    };

    $.ajax({
        url: "/api/board/create",
        method: "POST",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(data),
        async: false
    }).done(function(){
        console.log("게시글을 작성하였습니다.");
        alert("📝게시글이 작성되었습니다.")
        location.href = "/board/list"
        // 나중에 전체 리스트로 가기
    }).fail(function(){
        console.log("게시글 작성에 실패하였습니다.");
        alert("⛔️게시글이 작성에 실패하였습니다.")
    });
});