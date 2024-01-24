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

// TODAY ------------------------------------------------------------------------------------------

// 날짜
let today = new Date();
// 여기서 g : 전체 모든 문자열 변경 (global)
// 여기서 /\s/g : 모든 공백 제거
today = today.toLocaleDateString().replace(/\./g, '').replace(/\s/g, '.');
$("#today-date").text(today);

const gameArr = [];
const teamArr = [
    ["NCHT", "Dinos", "/img/team-dinos.png", "창원NC파크", 35.2225335, 128.5823895],
    ["HTSK", "Landers", "/img/team-landers.png", "인천SSG랜더스필드", 37.4370423, 126.6932617],
    ["OBHT", "Bears", "/img/team-bears.png", "잠실종합운동장", 37.5153086, 127.0728063],
    ["HHHT", "Eagles", "/img/team-eagles.png", "한화생명이글스파크", 36.3172026, 127.4285703],
    ["HTWO", "Heros",  "/img/team-heros.png", "고척스카이돔", 37.498182,  126.8670082],
    ["SSHT", "Lions", "/img/team-lions.png", "대구삼성라이온즈파크", 35.8410136, 128.6819955],
    ["HTLT", "Giants", "/img/team-giants.png", "부산사직종합운동장", 35.1940316, 129.0615183],
    ["HTLG", "Twins", "/img/team-twins.png", "잠실종합운동장", 37.5153086, 127.0728063],
    ["KTHT", "Wiz", "/img/team-wiz.png", "수원KT위즈파크", 37.2997553, 127.0096685]
];

// DB
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
    opponent: "SSHT",
})

// 날씨 api
const API_KEY = "e2f1ef8ab369be1544125c13be7aa1d6";
const getWeather = (lat, lon, name) => {
    var apiURI = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}&units=metric&lang=kr`;
    $.ajax({
        url: apiURI,
        dataType: "json",
        type: "GET",
        async: "false",
        success: function (resp) {
            $("#location").text(name);
            $("#temperature").text(resp.main.temp + "°C");
            $("#humidity").text(resp.main.humidity + "%");
            $("#wind").text(resp.wind.speed + "m/s");
            $("#cloud").text(resp.clouds.all + "%");
            $("#pressure").text(resp.main.pressure + "hPa");

            let imgURL = "http://openweathermap.org/img/wn/" + resp.weather[0].icon + ".png";
            $("#weather-icon").attr("src", imgURL);
        },
    });
};

// 화면 그리기
gameArr.forEach((game) => {
    if(game.gameDate == today){ // 경기 하는 날?
        $("#not-today").css("display", "none");

        teamArr.forEach((team) => {
            if(team[0] == game.opponent) { // 상대팀 코드 맞으면

                let name = "";
                let latitude = longitude = 0;

                // 오늘 상대
                if(game.homeGame == "true") {
                    $("#away > span").text(team[1]);
                    $("#away").css("background-image", `url(${team[2]})`);
                    $("#home > span").text("Tigers");
                    $("#home").css("background-image", "url(/img/team-tigers.png)");

                    name = "광주기아챔피언스필드";
                    latitude = 35.1682592;
                    longitude = 126.8884114;
                } else {
                    $("#away > span").text("Tigers");
                    $("#away").css("background-image", "url(/img/team-tigers.png)");
                    $("#home > span").text(team[1]);
                    $("#home").css("background-image", `url(${team[2]})`);

                    name = team[3];
                    latitude = team[4];
                    longitude = team[5];
                }

                // 오늘 날씨
                getWeather(latitude, longitude, name);
            }
        })
    } else {
        $("#not-today").css("display", "flex");
    }
})

