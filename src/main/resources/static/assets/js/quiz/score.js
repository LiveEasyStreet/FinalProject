const quizResultData = JSON.parse(sessionStorage.getItem('quiz-result-data'));
const score = quizResultData.score;
const scoreElem = document.getElementById('score');
const messageElem = document.getElementById('score_message');
const slideInsert = document.getElementById('slide_container');
const wrongScoreIdList = document.querySelector('.score_wrong_quiz');
const correctScoreIdList = document.querySelector('.score_correct_quiz');
const wrongQuizList = [];
const correctQuizList = [];

console.log(quizResultData);
console.log(correctQuizList);
console.log(wrongQuizList);

scoreElem.textContent = score;

for (const key in quizResultData.data) {
    const result = quizResultData.data[key];
    const quiz = quizResultData.map[key];
    if (result) {
        correctQuizList.push(quiz);
    } else {
        wrongQuizList.push(quiz);
    }
}

for (let i = 0; i < wrongQuizList.length; i++) {
    const slide = document.createElement('swiper-slide');
    slide.innerHTML = `<div>${wrongQuizList[i].id}번 문제 해설</div>` + `<div>${wrongQuizList[i].solve}</div>`;
    slideInsert.appendChild(slide);
    wrongScoreIdList.innerText += `${wrongQuizList[i].id},` + '\u00A0';
}

for (let i = 0; i < correctQuizList.length; i++) {
    correctScoreIdList.innerText += `${correctQuizList[i].id},` + '\u00A0';
}

if (score >= 0 && score <= 50) {
    messageElem.innerText = '0 ~ 50점 : 많이 노력해야되겠다는 멘트';
} else if (score >= 51 && score <= 70) {
    messageElem.innerText = '51 ~ 70 : 기초지식은 있다는 멘트';
} else if (score >= 71 && score <= 100) {
    messageElem.innerText = '71 ~ 100 : 지구를 위해 주위 지인분들에게 알려줘야한다는 멘트를 날려준다.';
}

sessionStorage.removeItem('quiz-result-data')

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

