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

// 날짜
let today = new Date();
// 여기서 g : 전체 모든 문자열 변경 (global)
// 여기서 /\s/g : 모든 공백 제거
today = today.toLocaleDateString().replace(/\./g, '').replace(/\s/g, '.');
$("#today-date").text(today);

const gameArr = [];
const teamArr = [
    ["NCHT", "Dinos", "/img/team-dinos.png"],
    ["HTSK", "Landers", "/img/team-landers.png"],
    ["OBHT", "Bears", "/img/team-bears.png"],
    ["HHHT", "Eagles", "/img/team-eagles.png"],
    ["HTWO", "Heros",  "/img/team-heros.png"],
    ["SSHT", "Lions", "/img/team-lions.png"],
    ["HTLT", "Giants", "/img/team-giants.png"],
    ["HTLG", "Twins", "/img/team-twins.png"],
    ["KTHT", "Wiz", "/img/team-wiz.png"]
];

const $game = document.querySelectorAll(".game-wrap .game-item");
$game.forEach((game) => {
    let arr = game.innerText.split("/");
    gameArr.push({
        gameDate : arr[0],
        homeGame : arr[1],
        opponent : arr[2],
    })
})

// test
gameArr.push({
    gameDate: "2024.1.24",
    homeGame: "false",
    opponent: "HTSK",
})

gameArr.forEach((game) => {
    if(game.gameDate == today){ // 경기 하는 날?
        $("#not-today").css("display", "none");

        teamArr.forEach((team) => {
            if(team[0] == game.opponent) { // 상대팀 코드 맞으면
                if(game.homeGame == "true") { // 홈경기면
                    $("#away > span").text(team[1]);
                    $("#away").css("background-image", `url(${team[2]})`);
                    $("#home > span").text("Tigers");
                    $("#home").css("background-image", "url(/img/team-tigers.png)");
                } else {
                    $("#away > span").text("Tigers");
                    $("#away").css("background-image", "url(/img/team-tigers.png)");
                    $("#home > span").text(team[1]);
                    $("#home").css("background-image", `url(${team[2]})`);

                }
            }
        })
    } else {
        $("#not-today").css("display", "flex");
    }
})

