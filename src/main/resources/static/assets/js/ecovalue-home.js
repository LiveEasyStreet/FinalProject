const canvas = document.getElementById("my-roulette");
const ctx = canvas.getContext("2d");


const product = [
    "백반", '부대찌개', "케밥", "중식", "우육면", "칼국수집", '국밥집', "일식집", "롯데리아",
];

const colors = ["#dc0936", "#e6471d", "#f7a416", "#efe61f", "#60b236", "#209b6c", "#169ed8", "#3f297e", "#87207b", "#be107f", "#e7167b"];

const newMake = () => {
    const [cw, ch] = [canvas.width / 2, canvas.height / 2];
    const arc = Math.PI / (product.length / 2);

    for (let i = 0; i < product.length; i++) {
        ctx.beginPath();
        ctx.fillStyle = colors[i % (colors.length -1)];
        ctx.moveTo(cw, ch);
        ctx.arc(cw, ch, cw, arc * (i - 1), arc * i);
        ctx.fill();
        ctx.closePath();
    }

    ctx.fillStyle = "#fff";
    ctx.font = "18px serif";
    ctx.textAlign = "center";

    for (let i = 0; i < product.length; i++) {
        const angle = (arc * i) + (arc / 2);

        ctx.save()  ;

        ctx.translate(
            cw + Math.cos(angle) * (cw - 50),
            ch + Math.sin(angle) * (ch - 50),
        );

        ctx.rotate(angle + Math.PI / 2);

        product[i].split(" ").forEach((text, j) => {
            ctx.fillText(text, 0, 30 * j);
        });

        ctx.restore();
    }
}

const rotateRoulette = () => {
    canvas.style.transform = `initial`;
    canvas.style.transition = `initial`;

    setTimeout(() => {

        const ran = Math.floor(Math.random() * product.length);

        const arc = 360 / product.length;
        const rotate = (ran * arc) + 3600 + (arc * 3) - (arc/4);

        canvas.style.transform = `rotate(-${rotate}deg)`;
        canvas.style.transition = `2s`;

        setTimeout(() => alert(`오늘의 저녁은?! ${product[ran]} 어떠신가요?`), 2000);
    }, 1);
};

newMake();