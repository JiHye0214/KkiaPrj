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

// 팝업
$("#market-message").on("click", function() {
    let talkOption = "width = 450px, height = 630px, top = 100px, left = 300px";
    let talkUrl = `/market/talk-list`;
    openTalk = window.open(talkUrl, "chat-popup", talkOption);
})
