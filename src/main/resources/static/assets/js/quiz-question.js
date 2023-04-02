let counted_quiz = 1;
let swiper = document.querySelector('.mySwiper').swiper;
let plusNumber = (value) => {
    if (value === 'O' || value === 'X') {
        counted_quiz++;
        document.getElementById('Quiz_count').innerHTML = `${counted_quiz} / 10`
    }
    if (counted_quiz === 10) {
        counted_quiz = 0;
    }

}

let moveToNextSlide = () => {
    swiper.slideNext();
}

