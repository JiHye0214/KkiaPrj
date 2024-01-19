const $slideWrap = document.querySelector("#slide-wrap");
const $slideBtnArr = document.querySelectorAll("#slide-btn-wrap > li");

// slide banner
let index = 1;
$slideBtnArr[0].style.background = `black`;

const slideNext = () => {
    for (let i = 0; i < $slideBtnArr.length; i++) {
        if (index === i) {
            $slideBtnArr[i].style.background = `black`;
        } else {
            $slideBtnArr[i].style.background = ``;
        }
    }

    $slideWrap.style.transform = `translateX(-${index * 310}px)`;
    index++;
    if (index >= $slideBtnArr.length) {
        index = 0;
    }
};

setInterval(slideNext, 4000);
