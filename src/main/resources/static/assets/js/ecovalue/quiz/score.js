const quizResultData = JSON.parse(sessionStorage.getItem('quiz-result-data'));
// data가 빈 값인지 확인
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
// 빈값이면 리다이렉트 하는 부분
function redirect(score){
    if(isEmpty(score)){
        window.location.replace("/quiz");
    }
}
redirect(quizResultData);
const score = quizResultData.score;
const scoreElem = document.getElementById('score');
const messageElem = document.getElementById('score-message');
const slideInsert = document.getElementById('slide-container');
const wrongScoreIdList = document.querySelector('.score-wrong-quiz');
const correctScoreIdList = document.querySelector('.score-correct-quiz');
const wrongQuizList = [];
const correctQuizList = [];

// console.log(quizResultData);
// console.log(correctQuizList);
// console.log(wrongQuizList);

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
    const slide = document.createElement('div');
    slide.innerHTML =
        `<div class="mistake-log-name">${wrongQuizList[i].quizId}번 문제 해설</div>` + `<div class="mistake-log-text">${wrongQuizList[i].solve}</div>`;
    slideInsert.appendChild(slide);
    wrongScoreIdList.innerText += `${wrongQuizList[i].quizId},` + '\u00A0';
}

for (let i = 0; i < correctQuizList.length; i++) {
    correctScoreIdList.innerText += `${correctQuizList[i].quizId},` + '\u00A0';
}

if (score >= 0 && score <= 50) {
    messageElem.innerText = '0 ~ 50점 : 무지렁이';
} else if (score >= 51 && score <= 70) {
    messageElem.innerText = '51 ~ 70 : 일반인';
} else if (score >= 71 && score <= 100) {
    messageElem.innerText = '71 ~ 100 : 지구방위대';
}

// sessionStorage.removeItem('quiz-result-data')

