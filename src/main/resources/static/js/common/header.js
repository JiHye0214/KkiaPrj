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

// 마켓 메세지 버튼
let isTalkOpen = false;
$("#market-message").on("click", function() {

    // 여닫기
    if(!isTalkOpen) {
        $("#market-message-container").css("display", "block");
        isTalkOpen = true;
    } else {
        $("#market-message-container").css("display", "none");
        isTalkOpen = false;
    }
})

// 메세지 큰 알림
const smallAlert = document.querySelectorAll(".unread-mark");
if(smallAlert.length > 0) {
    $("#market-alert").css("display", "block");
} else {
    $("#market-alert").css("display", "");
}

// 팝업
const talkList = document.querySelectorAll(".market-talk-wrap");
const partnerId = document.querySelectorAll(".partnerId");

talkList.forEach((talk, index) => {
    talk.onclick = () => {
        let talkOption = "width = 400px, height = 630px, top = 100px, left = 300px";
        let talkUrl = `/market/talk/${partnerId[index].value}`;
        openTalk = window.open(talkUrl, "chat-popup", talkOption);
    }
})
