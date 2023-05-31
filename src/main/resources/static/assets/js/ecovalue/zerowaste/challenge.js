//슬라이크 전체 크기(width 구하기)
const ingSlide = document.querySelector(".challenge_ing_wrap");
const endSlide = document.querySelector(".challenge_end_wrap");
let ingSlideWidth = ingSlide.clientWidth;
let endSlideWidth = endSlide.clientWidth;

//버튼 엘리먼트 선택하기
const ingPrevBtn = document.querySelector(".ing_slide_prev_button");
const ingNextBtn = document.querySelector(".ing_slide_next_button");
const endPrevBtn = document.querySelector(".end_slide_prev_button");
const endNextBtn = document.querySelector(".end_slide_next_button");

//슬라이드 전체를 선택해 값을 변경해주기 위해 슬라이드 전체 선택하기
const ingSlideItems = document.querySelectorAll(".ing_slide_item");
const endSlideItems = document.querySelectorAll(".end_slide_item");

//현재 슬라이드 위치가 슬라이드 개수를 넘기지 않게 하기 위한 변수
const ingMaxSlide = ingSlideItems.length;
const endMaxSlide = endSlideItems.length;

//버튼 클릭할 때 마다 현재 슬라이드가 어디인지 알려주기 위한 변수
let currSlide = 1;

//페이지네이션 생성
const ingPagination = document.querySelector(".ing_slide_pagination");
const endPagination = document.querySelector(".end_slide_pagination");

//동적 li 생성 함수
function createIngLi(maxSlide) {
    for (let i = 0; i < maxSlide; i++) {
        if (i === 0) {
            ingPagination.innerHTML += `<li class="active">•</li>`;
        } else {
            ingPagination.innerHTML += `<li>•</li>`;
        }
    }
}
createIngLi(ingMaxSlide);

const ingPaginationItems = document.querySelectorAll(".ing_slide_pagination > li");

function ingNextMove() {
    currSlide++;
    //마지막 슬라이드 이상으로 넘어가지 않게 하기 위해서
    if (currSlide <= ingMaxSlide) {
        //슬라이드를 이동시키기 위한 offset 계산
        const offset = ingSlideWidth * (currSlide - 1);
        //각 슬라이드 아이템의 left 에 offset 적용
        ingSlideItems.forEach((i) => {
            i.setAttribute("style", `left: ${-offset}px`);
        });
        //슬라이드 이동 시 현재 활성화된 pagination 변경
        ingPaginationItems.forEach((i) => i.classList.remove("active"));
        ingPaginationItems[currSlide - 1].classList.add("active");
    } else {
        currSlide--;
    }
}
function ingPrevMove() {
    currSlide--;
    //1번째 슬라이드 이하로 넘어가지 않게 하기 위해서
    if (currSlide > 0) {
        //슬라이드를 이동시키기 위한 offset 계산
        const offset = ingSlideWidth * (currSlide - 1);
        //각 슬라이드 아이템의 left 에 offset 적용S
        ingSlideItems.forEach((i) => {
            i.setAttribute("style", `left: ${-offset}px`);
        });
        //슬라이드 이동 시 현재 활성화된 pagination 변경
        ingPaginationItems.forEach((i) => i.classList.remove("active"));
        ingPaginationItems[currSlide - 1].classList.add("active");
    } else {
        currSlide++;
    }
}

//버튼 엘리먼트에 클릭 이벤트 추가하기
ingNextBtn.addEventListener("click", () => {
    //이후 버튼 누를 경우 현재 슬라이드를 변경
    ingNextMove();
});
//버튼 엘리먼트에 클릭 이벤트 추가하기
ingPrevBtn.addEventListener("click", () => {
    //이전 버튼 누를 경우 현재 슬라이드를 변경
    ingPrevMove();
});

//각 페이지네이션 클릭 시 해당 슬라이드로 이동하기
for (let i = 0; i < ingMaxSlide; i++) {
    //각 페이지네이션마다 클릭 이벤트 추가하기
    ingPaginationItems[i].addEventListener("click", () => {
        //클릭한 페이지네이션에 따라 현재 슬라이드 변경해주기(currSlide 는 시작 위치가 1이기 때문에 + 1)
        currSlide = i + 1;
        //슬라이드를 이동시키기 위한 offset 계산
        const offset = ingSlideWidth * (currSlide - 1);
        //각 슬라이드 아이템의 left 에 offset 적용
        ingSlideItems.forEach((i) => {
            i.setAttribute("style", `left: ${-offset}px`);
        });
        //슬라이드 이동 시 현재 활성화된 pagination 변경
        ingPaginationItems.forEach((i) => i.classList.remove("active"));
        ingPaginationItems[currSlide - 1].classList.add("active");
    });
}

function createEndLi(maxSlide) {
    for (let i = 0; i < maxSlide; i++) {
        if (i === 0) {
            endPagination.innerHTML += `<li class="active">•</li>`;
        } else {
            endPagination.innerHTML += `<li>•</li>`;
        }
    }
}
createEndLi(endMaxSlide);

const endPaginationItems = document.querySelectorAll(".end_slide_pagination > li");

function endNextMove() {
    currSlide++;
    //마지막 슬라이드 이상으로 넘어가지 않게 하기 위해서
    if (currSlide <= endMaxSlide) {
        //슬라이드를 이동시키기 위한 offset 계산
        const offset = endSlideWidth * (currSlide - 1);
        //각 슬라이드 아이템의 left 에 offset 적용
        endSlideItems.forEach((i) => {
            i.setAttribute("style", `left: ${-offset}px`);
        });
        //슬라이드 이동 시 현재 활성화된 pagination 변경
        endPaginationItems.forEach((i) => i.classList.remove("active"));
        endPaginationItems[currSlide - 1].classList.add("active");
    } else {
        currSlide--;
    }
}
function endPrevMove() {
    currSlide--;
    //1번째 슬라이드 이하로 넘어가지 않게 하기 위해서
    if (currSlide > 0) {
        //슬라이드를 이동시키기 위한 offset 계산
        const offset = endSlideWidth * (currSlide - 1);
        //각 슬라이드 아이템의 left 에 offset 적용
        endSlideItems.forEach((i) => {
            i.setAttribute("style", `left: ${-offset}px`);
        });
        //슬라이드 이동 시 현재 활성화된 pagination 변경
        endPaginationItems.forEach((i) => i.classList.remove("active"));
        endPaginationItems[currSlide - 1].classList.add("active");
    } else {
        currSlide++;
    }
}

//버튼 엘리먼트에 클릭 이벤트 추가하기
endNextBtn.addEventListener("click", () => {
    //이후 버튼 누를 경우 현재 슬라이드를 변경
    endNextMove();
});
//버튼 엘리먼트에 클릭 이벤트 추가하기
endPrevBtn.addEventListener("click", () => {
    //이전 버튼 누를 경우 현재 슬라이드를 변경
    endPrevMove();
});

//브라우저 화면이 조정될 때 마다 slideWidth 를 변경하기 위해
window.addEventListener("resize", () => {
    ingSlideWidth = ingSlide.clientWidth;
    endSlideWidth = endSlide.clientWidth;
});

//각 페이지네이션 클릭 시 해당 슬라이드로 이동하기
for (let i = 0; i < endMaxSlide; i++) {
    //각 페이지네이션마다 클릭 이벤트 추가하기
    endPaginationItems[i].addEventListener("click", () => {
        //클릭한 페이지네이션에 따라 현재 슬라이드 변경해주기(currSlide 는 시작 위치가 1이기 때문에 + 1)
        currSlide = i + 1;
        //슬라이드를 이동시키기 위한 offset 계산
        const offset = endSlideWidth * (currSlide - 1);
        //각 슬라이드 아이템의 left 에 offset 적용
        endSlideItems.forEach((i) => {
            i.setAttribute("style", `left: ${-offset}px`);
        });
        //슬라이드 이동 시 현재 활성화된 pagination 변경
        endPaginationItems.forEach((i) => i.classList.remove("active"));
        endPaginationItems[currSlide - 1].classList.add("active");
    });
}

//드래그(스와이프) 이벤트를 위한 변수 초기화
let startPoint = 0;
let endPoint = 0;

//클릭 이벤트 (드래그)
ingSlide.addEventListener("mousedown", (e) => {
    console.log("mousedown", e.pageX);
    startPoint = e.pageX; // 마우스 드래그 시작 위치 저장
});

ingSlide.addEventListener("mouseup", (e) => {
    console.log("mouseup", e.pageX);
    endPoint = e.pageX; // 마우스 드래그 끝 위치 저장
    if (startPoint < endPoint) {
        // 마우스가 오른쪽으로 드래그 된 경우
        console.log("prev move");
        ingPrevMove();
    } else if (startPoint > endPoint) {
        // 마우스가 왼쪽으로 드래그 된 경우
        console.log("next move");
        ingNextMove();
    }
});
//클릭 이벤트 (드래그)
endSlide.addEventListener("mousedown", (e) => {
    console.log("mousedown", e.pageX);
    startPoint = e.pageX; //마우스 드래그 시작 위치 저장
});

endSlide.addEventListener("mouseup", (e) => {
    console.log("mouseup", e.pageX);
    endPoint = e.pageX; //마우스 드래그 끝 위치 저장
    if (startPoint < endPoint) {
        //마우스가 오른쪽으로 드래그 된 경우
        console.log("prev move");
        endPrevMove();
    } else if (startPoint > endPoint) {
        //마우스가 왼쪽으로 드래그 된 경우
        console.log("next move");
        endNextMove();
    }
});

//챌린지 버튼 이벤트
const challengeIngBtn = document.querySelector(".challenge_ing_btn");
const challengeEndBtn = document.querySelector(".challenge_end_btn");

//챌린지 진행/종료
const ingWrap = document.querySelector(".challenge_ing_wrap");
const endWrap = document.querySelector(".challenge_end_wrap");

function zIndexInit() {
    ingWrap.style.zIndex = "0";
    endWrap.style.zIndex = "0";
}

//클릭 이벤트
challengeIngBtn.addEventListener("click", () => {
    console.log("show challenge_ing");

    challengeIngBtn.style.transition = "0.5s";
    challengeIngBtn.style.color = "white";

    challengeEndBtn.style.transition = "0.5s";
    challengeEndBtn.style.color = "rgb(51, 51, 51)";

    zIndexInit()
    ingWrap.style.zIndex = "1";
    endWrap.style.zIndex = "-1";
});
challengeEndBtn.addEventListener("click", () => {
    console.log("show challenge_end");

    challengeEndBtn.style.transition = "0.5s";
    challengeEndBtn.style.color = "white";

    challengeIngBtn.style.transition = "0.5s";
    challengeIngBtn.style.color = "rgb(51, 51, 51)";

    zIndexInit()
    endWrap.style.zIndex = "1";
    ingWrap.style.zIndex = "-1";
});

//마우스오버 이벤트
challengeIngBtn.addEventListener("mouseover", () => {

    challengeIngBtn.style.transition = "0s";
    if (challengeIngBtn.style.color === "rgb(51, 51, 51)") {
        challengeIngBtn.style.color = "white";
    }
});
challengeEndBtn.addEventListener("mouseover", () => {

    challengeEndBtn.style.transition = "0s";
    if (challengeEndBtn.style.color === "rgb(51, 51, 51)") {
        challengeEndBtn.style.color = "white";
    }
});

//마우스아웃 이벤트
challengeIngBtn.addEventListener("mouseout", () => {

    challengeIngBtn.style.transition = "0s";
    if (challengeEndBtn.style.color === "rgb(51, 51, 51)") {
        challengeIngBtn.style.color = "white";
    } else {
        challengeIngBtn.style.color = "rgb(51, 51, 51)";
    }
});
challengeEndBtn.addEventListener("mouseout", () => {

    challengeEndBtn.style.transition = "0s";
    if (challengeIngBtn.style.color === "rgb(51, 51, 51)") {
        challengeEndBtn.style.color = "white";
    } else {
        challengeEndBtn.style.color = "rgb(51, 51, 51)";
    }
});