const sub_tab = document.querySelector("#nav_contents_wrap");

let intervalarr = [];
let interval = setInterval(() => subbar_click(), 3000);
intervalarr.push(interval);

const cleararr = function () {
  for (interval of intervalarr) {
    clearInterval(interval);
  }
};

const sample_text = [
  "오염된 비닐은 재활용이 불가해요",
  "택배 상자는 송장 스티커나 테이프를 제거해서 버려요",
  "우유팩과 종이컵은 물로 잘 헹군 후 따로 분리 배출해요",
  "영수증·전표·코팅지·음식물 등 오염물질이 묻은 종이·부직포는 일반 쓰레기에 버려야 해요",
  "깨진 유리는 재활용 되지 않으므로 신문지에 감싸 일반 쓰레기에 버려요",
  "대형마트/편의점/슈퍼마켓 에서 보증금을 환불받고 빈 용기를 반환할 수 있어요",
  "컵라면 용기처럼 코팅이 되거나 색이 있는 스티로폼은 재활용이 안되므로 종량제 봉투에 버려야 해요",
];

document
    .querySelector(".menu_wrap")
    .addEventListener("mouseenter", function () {
      sub_tab.style.display = "none";
    });
document
    .querySelector(".menu_wrap")
    .addEventListener("mouseleave", function () {
      sub_tab.style.display = "flex";
    });

const start = function () {
  cleararr();
  subbar_click();
  interval = setInterval(() => subbar_click(), 3000);
  intervalarr.push(interval);
};

const subbar_click = function () {
  let before_text = document.querySelector(".tip_comment").textContent;
  let next_index = 0;
  for (let i = 0; i < sample_text.length - 1; i++) {
    if (before_text === sample_text[i]) {
      next_index = i + 1;
      break;
    }
  }
  document.querySelector(".tip_comment").textContent = sample_text[next_index];
};
