
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


// let thumbUp = function (){
//     $.ajax({
//         url : "",
//         type : "POST",
//         data : JSON.stringify()
//     })
// }