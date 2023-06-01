// [마우스 호버] 액션에 사용 할 변수
const subMenu = document.querySelector("#nav-contents-wrap");

// 문구 추천 롤링 초기 실행
let intervalArr = [];
let interval = setInterval(() => rotate(), 3000);
intervalArr.push(interval);

// 인터벌 초기화 메서드
const clearArr = function () {
    for (interval of intervalArr) {
        clearInterval(interval);
    }
};

// 롤링 으로 보여질 추천 문구 배열
const textArr = [
    "오염된 비닐은 재활용이 불가해요",
    "택배 상자는 송장 스티커나 테이프를 제거해서 버려요",
    "우유팩과 종이컵은 물로 잘 헹군 후 따로 분리 배출해요",
    "영수증·전표·코팅지·음식물 등 오염물질이 묻은 종이·부직포는 일반 쓰레기에 버려야 해요",
    "깨진 유리는 재활용 되지 않으므로 신문지에 감싸 일반 쓰레기에 버려요",
    "대형마트/편의점/슈퍼마켓 에서 보증금을 환불받고 빈 용기를 반환할 수 있어요",
    "컵라면 용기처럼 코팅이 되거나 색이 있는 스티로폼은 재활용이 안되므로 종량제 봉투에 버려야 해요",
];

// textArr 의 길이 변수로 설정
const textArrLength = textArr.length;


// [마우스 호버] 시 [menu_wrap] 안보이게 설정
document
    .querySelector(".menu-wrap")
    .addEventListener("mouseenter", function () {
        subMenu.style.display = "none";
    });


// [마우스 아웃] 시 [menu_wrap] 보이게 설정
document
    .querySelector(".menu-wrap")
    .addEventListener("mouseleave", function () {
        subMenu.style.display = "flex";
    });

// 아래쪽 화살표 버튼 클릭시 [textArr]에서 다음 문구로 넘김
const downButton = function () {
    clearArr();
    rotate();
    interval = setInterval(() => rotate(), 3000);
    intervalArr.push(interval);
};

// 위쪽 화살표 버튼 클릭시 [textArr]에서 이전 문구로 넘김
const upButton = function () {
    clearArr();
    backRotate();
    interval = setInterval(() => rotate(), 3000);
    intervalArr.push(interval);
};

// 정상 방향 순회
const rotate = function () {
    let number = indexFind()+1;
    if (number === textArrLength) {
        number = 0;
    }
    // console.log(number)
    document.querySelector(".tip-comment").textContent = textArr[number];
};

// 역 방향 순회
const backRotate = function () {
    let number = indexFind()-1;
    if (number === -1) {
        number = textArrLength-1;
    }
    // console.log(number)
    document.querySelector(".tip-comment").textContent = textArr[number];
};


// textArr 에서 현재 인덱스 번호를 찾는 메서드
const indexFind = function () {
    let beforeText = document.querySelector(".tip-comment").textContent;
    for (let i = 0; i < textArrLength; i++) {
        if (beforeText === textArr[i]) {
            return i;
        }
    }
}

function isEmpty(data){
    if(typeof (data) ==='object'){
        if(!data){
            return true;
        }else if (JSON.stringify(data)==='{}' || JSON.stringify(data)==='[]'){
            return true;
        }
        return false;
    }
    else if (typeof(data) === 'string'){
        if(!data.trim()){
            return true;
        }
        return false;
    }
    else if (typeof(data)==='undefined'){
        return true;
    }
    else if(isNaN(data)=== true){
        return true;
    }
    else{
        return false;
    }
}
function userMatching(){
    if(document.querySelector(".member")!=null){

        document.querySelector(".member").addEventListener("mouseenter", function () {
            subMenu.style.display = "none";
        });
        document.querySelector(".member").addEventListener("mouseleave", function () {
            subMenu.style.display = "flex";
        });
    }
}
userMatching();


let currentPageUrl = location.href;
let returnUrl = currentPageUrl.substring(currentPageUrl.indexOf("/", 8));
let loginButton = document.getElementById("login_button");
if(loginButton != null){
    loginButton.href = "/login?redirectURL=" + encodeURIComponent(returnUrl);
}