
let memberNameElement = document.querySelector('.memberName');
let memberName = memberNameElement ? memberNameElement.textContent : '';


let currentUrl = window.location.pathname;
let boardNumPattern = /\/views\/(\d+)/; // /views/ 다음에 오는 숫자를 정규식으로 매칭
let boardNum = currentUrl.match(boardNumPattern)[1]; // 정규식 매칭 결과를 가져옴

let fullPath = window.location.pathname + window.location.search;
let galleryPatten = /\/upGallery\/views\/(.*)/;
let boardUrlPattern = fullPath.match(galleryPatten)[0];
window.onload = function (){
    let boardUpButton = document.querySelector('.boardUp');
    boardUpButton.onclick=function (){
        if (memberName === null || memberName === '') {
            let confirmResult = confirm('로그인이 필요한 서비스입니다. 로그인하시겠습니까?');
            if (confirmResult) {
                // 예를 선택한 경우 처리 로직
                console.log('사용자가 예를 선택했습니다.');
                // location = "";
                location.href = "/login?redirectURL="+encodeURIComponent(boardUrlPattern);
            } else {
                // 아니오를 선택한 경우 처리 로직
                console.log('사용자가 아니오를 선택했습니다.');
            }
        } else {
            // memberName이 유효한 값인 경우 처리 로직
            console.log('memberName은 유효한 값입니다.');
            // 여기에 로그인 필요 없는 경우 처리 로직을 추가하세요.
            let thumbsUpData = {
                boardId: boardNum,
                loginId: memberName
            };

            let thumbsUpResponseDto = {
                processCompleted: false,
                thumbAct: false,
                boardUpCount: 0
            };

            $.ajax({
                url: '/upGallery/boardUp',
                method: 'POST',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(thumbsUpData),
                // success: function(response) {
                //     // 요청이 성공한 경우의 처리
                //     console.log(response);
                // },
                success: function (res) {
                    console.log("성공", res);
                    thumbsUpResponseDto.processCompleted = res.processCompleted;
                    thumbsUpResponseDto.thumbAct=res.thumbAct;
                    thumbsUpResponseDto.boardUpCount = res.boardUpCount;
                    if (thumbsUpResponseDto.processCompleted){
                        if(thumbsUpResponseDto.thumbAct){
                            document.getElementById('boardUpButton').classList.add('clicked');
                        }
                        else {
                            document.getElementById('boardUpButton').classList.remove('clicked');
                        }
                        document.getElementById("boardUpCount").textContent = thumbsUpResponseDto.boardUpCount;
                    }
                },
                error: function (e) {
                    console.log(e);
                    console.log("실패 :  가져올 수 없음");
                }
            });
        }
    }
}

let init_my_comment_content = document.querySelector(".my_comment_content");
function textarea_auto(obj) {

    // 아직 미완성
    if(obj.scrollHeight>obj.clientHeight){
        obj.style.height = (15+obj.scrollHeight)+"px"
    }
}
if (init_my_comment_content != null){
    init_my_comment_content.textContent="";
}

function submitComment() {
    let boardId = $('#boardId').val();
    let commentContent = $('#comment_content').val();

    commentContent = commentContent.replaceAll(/(\n|\r\n)/g, "<br>");

    $.ajax({
        url: '/upGallery/views/' + boardId,
        type: 'POST',
        data: { comment_content: commentContent },
        success: function(response) {
            console.log("성공 : ",response);
            let newComment = createCommentElement(response);
            console.log("new Comment : ",newComment);

            let hrElement = document.createElement("hr");
            hrElement.classList.add("hr");

            if(document.querySelector('.each_comment')===null){
                $(".commentArea hr:first").before(newComment);
            }
            else {
                $(".each_comment:last").after(newComment);
                $(".each_comment:last").before(hrElement);
            }

            $("#comment_content").val("");
        },
        error: function(e) {
            console.log("실패 : ",e);
        }
    });
}

// 댓글 요소를 생성하는 함수
function createCommentElement(comment) {
    let commentElement = document.createElement("div");
    commentElement.classList.add("each_comment");
    commentElement.classList.add("comment" + comment.commentId);

    // 댓글 아이디 요소 생성
    let commentIdElement = document.createElement("p");
    commentIdElement.classList.add("comment_id");
    commentIdElement.innerText = comment.commentId;
    commentElement.appendChild(commentIdElement);

    // 댓글 작성자 요소 생성
    let commentWriterElement = document.createElement("p");
    commentWriterElement.classList.add("comment_writer");
    commentWriterElement.innerText = comment.nickName;
    commentElement.appendChild(commentWriterElement);

    // 댓글 내용 요소 생성
    let commentContentElement = document.createElement("p");
    commentContentElement.classList.add("comment_content");
    commentContentElement.innerText = comment.contents;
    commentElement.appendChild(commentContentElement);

    // 작성 시간 요소 생성
    let commentDateElement = document.createElement("p");
    commentDateElement.classList.add("comment_date");
    commentDateElement.innerText = comment.date;
    commentElement.appendChild(commentDateElement);



    return commentElement;
}

/**
 * 대댓글 아직 미완성
 */
// function createCommentDiv() {
//     // 메인 div 요소 생성
//     let commentDiv = document.createElement("div");
//     commentDiv.classList.add("add_comment");
//
//     // boardId를 위한 숨은 input 요소 생성
//     let boardIdInput = document.createElement("input");
//     boardIdInput.type = "hidden";
//     boardIdInput.id = "boardId";
//     boardIdInput.value = /* 여기에 boardId 값을 설정하세요 */;
//     commentDiv.appendChild(boardIdInput);
//
//     // 댓글 작성자 닉네임을 위한 p 요소 생성
//     let nickNameParagraph = document.createElement("p");
//     nickNameParagraph.classList.add("comment_nick_name");
//     nickNameParagraph.innerText = /* 여기에 댓글 작성자 닉네임을 설정하세요 */;
//     commentDiv.appendChild(nickNameParagraph);
//
//     // 댓글 내용을 입력하는 textarea 요소 생성
//     let commentTextarea = document.createElement("textarea");
//     commentTextarea.id = "comment_content";
//     commentTextarea.spellcheck = false;
//     commentTextarea.classList.add("my_comment_content");
//     commentTextarea.placeholder = "댓글을 입력하세요";
//     commentTextarea.onkeydown = function() {
//         textarea_auto(this);
//     };
//     commentTextarea.onkeyup = function() {
//         textarea_auto(this);
//     };
//     commentTextarea.onfocus = function() {
//         this.placeholder = '';
//     };
//     commentTextarea.onblur = function() {
//         this.placeholder = '댓글을 입력하세요';
//     };
//     commentDiv.appendChild(commentTextarea);
//
//     // 댓글 등록 버튼을 위한 button 요소 생성
//     let submitButton = document.createElement("button");
//     submitButton.classList.add("comment_submit");
//     submitButton.type = "submit";
//     submitButton.onclick = function() {
//         submitComment();
//     };
//     submitButton.innerText = "등록";
//     commentDiv.appendChild(submitButton);
//
//     // commentDiv를 원하는 요소 (예: parentDiv)에 추가
//     let parentDiv = document.querySelector("#parentDiv");
//     parentDiv.appendChild(commentDiv);
// }