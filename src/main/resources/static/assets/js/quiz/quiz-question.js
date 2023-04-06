let swiper = new Swiper(".mySwiper", {
    pagination: {
        el: ".swiper-pagination",
        type: "fraction",
    },
});

const oButton = document.querySelector(".quiz-o");
const xButton = document.querySelector(".quiz-x");
const leftArrow = document.querySelector(".arrow-left");
const rightArrow = document.querySelector(".arrow-right");

let counted_quiz = 1;
const Quiz_User_Submit = {
    1: [false, false],
    2: [false, false],
    3: [false, false],
    4: [false, false],
    5: [false, false],
    6: [false, false],
    7: [false, false],
    8: [false, false],
    9: [false, false],
    10: [false, false]
}


const nextSlide = () => {
    if (counted_quiz < 10) {
        counted_quiz++;
    }
    setTimeout(() => {
        swiper.slideNext();
    }, 500);
};
const offbutton = () => {
    document.querySelector(".quiz_start_ox_view").style.pointerEvents = "none";
    setTimeout(() => {
        document.querySelector(".quiz_start_ox_view").style.pointerEvents = "auto";
    }, 500);
};
const nextSlideNoLack = () => {
    if (counted_quiz < 10) {
        counted_quiz++;
    }
    swiper.slideNext();
};
const prevSlide = () => {
    if (counted_quiz > 1) {
        counted_quiz--;
    }
    swiper.slidePrev();
}

leftArrow.addEventListener('click', prevSlide)

rightArrow.addEventListener('click', nextSlideNoLack);


let plusNumber = (value) => {
    if (value === 'O' || value === 'X') {

        let quizUserAnswer = document.querySelector(`.quiz-user-answer${counted_quiz}`);

        if (value === 'O') {
            Quiz_User_Submit[counted_quiz][0] = true;
            if (Quiz_User_Submit[counted_quiz][1] == false) {
                Quiz_User_Submit[counted_quiz][1] = true;
            }
            quizUserAnswer.textContent = 'O';

        } else {
            Quiz_User_Submit[counted_quiz][0] = false;
            if (Quiz_User_Submit[counted_quiz][1] == false) {
                Quiz_User_Submit[counted_quiz][1] = true;
            }
            quizUserAnswer.textContent = 'X';
        }
        console.log("위치 : ", counted_quiz, "값 : ", Quiz_User_Submit[counted_quiz][0], "변환 여부 : ", Quiz_User_Submit[counted_quiz][1]);
        nextSlide()
        offbutton()
    }
}

let submitData = {};
let setSubmitData = () => {
    submitData = {};
    for (let i = 1; i <= 10; i++) {
        if (Quiz_User_Submit[i][0] === quizList[i-1].answer) {
            submitData[quizList[i - 1].id] = true
        } else {
            submitData[quizList[i - 1].id] = false
        }
    }
    console.log(submitData);
    // console.log(typeof submitData[Object.keys(submitData)[0]]);
};
let quiz_submit = () => {
    let submit_index = new Array();
    for (let i = 1; i <= 10; i++) {
        if (Quiz_User_Submit[i][1] == false) {
            submit_index.push(i);
        }
    }
    if (submit_index.length > 0) {
        alert("모든 문제를 푸셔야 제출할 수 있습니다. \n" +
            "안푼 문제 : " + submit_index.join(' '));
    } else {
        setSubmitData();

        $.ajax({
            type: "POST",
            url: "/quiz/sendData",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(submitData),
            // * 성공시
            success: function (res) {
                console.log("성공", res);
                sessionStorage.setItem('quiz-result-data', JSON.stringify(res));
                window.location.href = "/quiz/score";
            },
            // # 실패시
            error: function (e) {
                console.log(e);
                console.log("실패");
            }
        })
    }

}

console.log(quizList);

