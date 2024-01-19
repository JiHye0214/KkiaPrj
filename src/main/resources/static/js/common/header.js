const $menuArr = document.querySelectorAll("header .header-menu");
const setMenuArr = ["game", "market", "community"];

// 어느 페이지
let link = document.location.href;
const linkArr = link.split("/");

setMenuArr.forEach((menu, index) => {
    if (menu == linkArr[3]) {
        $menuArr[index].classList.add("current-menu");
    }
});
