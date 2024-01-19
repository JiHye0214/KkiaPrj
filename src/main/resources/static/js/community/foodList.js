const colorArr = ["#eb008a", "var(--kia-red)", "#0066b3", "#f37321", "#d00e31", "#e3e3e5", "#cf0a2c", "white", "#244c81"];
const $regionArr = document.querySelectorAll("#region-btn-wrap > div");

$regionArr.forEach((region, index) => {
    region.onmouseover = () => {
        region.style.background = `${colorArr[index]}`;
    }
    region.onmouseout = () => {
        region.style.background = ``;
    }
})

