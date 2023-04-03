var swiper = new Swiper(".mySwiper", {
    pagination: {
        el: ".swiper-pagination",
        type: "fraction",
    },
    navigation: {
        nextEl: ".arrow-right",
        prevEl: ".arrow-left",
    },
});

const oButton = document.querySelector(".quiz-o");
const xButton = document.querySelector(".quiz-x");

oButton.addEventListener('click', () => {
    swiper.slideNext();
});

xButton.addEventListener('click', () => {
    swiper.slideNext();
})