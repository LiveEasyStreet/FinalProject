const scoreElem = document.getElementById('score');
const messageElem = document.getElementById('score_message');
const score = parseInt(scoreElem.innerText);

const numOfWrongQuiz = 5;
const slideInsert = document.getElementById(`slide_container`);

for (let i = 1; i <= numOfWrongQuiz; i++) {
    const slide = document.createElement('swiper-slide');
    slide.innerText = `틀린문제${i} => numOfWrongQuiz에 저장된 숫자만큼 오답노트가 출력이 된다.
        틀린문제를 DB에서 받아올 때 numOfWrongQuiz로 틀린문제 갯수를 넣어주면 됨`;
    slideInsert.appendChild(slide);
}

let swiper = new Swiper('.mySwiper', {
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
        hideOnClick: true,
    },
    pagination: {
        el: '.swiper-pagination',
        clickable: true,
    },
    keyboard: true,
    cssMode: true,
    loop: true
});

if (score >= 0 && score <= 50) {
    messageElem.innerText = '0 ~ 50점 : 많이 노력해야되겠다는 멘트';
} else if (score >= 51 && score <= 70) {
    messageElem.innerText = '51 ~ 70 : 기초지식은 있다는 멘트';
} else if (score >= 71 && score <= 100) {
    messageElem.innerText = '71 ~ 100 : 지구를 위해 주위 지인분들에게 알려줘야한다는 멘트를 날려준다.';
}
