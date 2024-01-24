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

// 오늘
let today = new Date();
// 여기서 g : 전체 모든 문자열 변경 (global)
// 여기서 /\s/g : 모든 공백 제거
today = today.toLocaleDateString().replace(/\./g, '').replace(/\s/g, '.');
$("#today-date").text(today);

const gameArr = [];
const teamArr = [
    ["NCHT", "Dinos", "/img/team-dinos.png", "창원NC파크", 35.2228007, 128.5820053],
    ["HTSK", "Landers", "/img/team-landers.png", "인천SSG랜더스필드", 37.4369986, 126.6932732],
    ["OBHT", "Bears", "/img/team-bears.png", "잠실종합운동장", 37.5121518, 127.0719083],
    ["HHHT", "Eagles", "/img/team-eagles.png", "한화생명이글스파크", 36.3170827, 127.4291626],
    ["HTWO", "Heros",  "/img/team-heros.png", "고척스카이돔", 37.4982302,  126.8671030],
    ["SSHT", "Lions", "/img/team-lions.png", "대구삼성라이온즈파크", 35.8410568, 128.6816571],
    ["HTLT", "Giants", "/img/team-giants.png", "부산사직종합운동장", 35.1940153, 129.0615412],
    ["HTLG", "Twins", "/img/team-twins.png", "잠실종합운동장", 37.5121518, 127.0719083],
    ["KTHT", "Wiz", "/img/team-wiz.png", "수원KT위즈파크", 37.2997194, 127.0097703]
];

// 일정 DB
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
    gameDate: "2024.1.25",
    homeGame: "false",
    opponent: "HHHT",
})

// 날씨 api -----------------------------------------------------
//const API_KEY = "e2f1ef8ab369be1544125c13be7aa1d6";
const API_KEY = $("#weather-api-key").text();
console.log(API_KEY);
const getWeather = (lat, lon, name) => {

    const weatherIcon = {
        '01' : 'fas fa-sun fa-3x',
        '02' : 'fas fa-cloud-sun fa-3x',
        '03' : 'fas fa-cloud fa-3x',
        '04' : 'fas fa-cloud-meatball fa-3x',
        '09' : 'fas fa-cloud-sun-rain fa-3x',
        '10' : 'fas fa-cloud-showers-heavy fa-3x',
        '11' : 'fas fa-poo-storm fa-3x',
        '13' : 'far fa-snowflake fa-3x',
        '50' : 'fas fa-smog fa-3x'
    };

    let apiURI = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}&units=metric&lang=kr`;
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

            var $Icon = (resp.weather[0].icon).substr(0,2);
            $('#weather-icon').append('<i class="' + weatherIcon[$Icon] +'"></i>');
        },
    });
};

// 지도 api -----------------------------------------------------
const getPlace = (lat, lon) => {
    var mapContainer = document.querySelector('#place-map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    var map = new kakao.maps.Map(mapContainer, mapOption); // map 생성

    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커이미지 주소
        imageSize = new kakao.maps.Size(64, 69), // 마커이미지 크기
        imageOption = {offset: new kakao.maps.Point(27, 69)};

    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
        markerPosition  = new kakao.maps.LatLng(lat, lon); // 마커 표시 위치

    var marker = new kakao.maps.Marker({ position: markerPosition, image: markerImage }); // 마커 생성
    marker.setMap(map); // 마커를 지도에 표시
}

// 일정 + 날씨 그리기 -----------------------------------------------------
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
                    latitude = 35.1682047;
                    longitude = 126.8891093;
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
                // 오늘 구장 지도
                getPlace(latitude, longitude);
            }
        })
    } else {
        $("#not-today").css("display", "flex");
    }
})

// 엔트리 -----------------------------------------------------
const entryArr = [1, 5, 47, 34, 30, 3, 8, 59, 16, 54];
const playerArr = [];

// 엔트리 DB
const $player = document.querySelectorAll(".player-wrap .player-item");
$player.forEach((player) => {
    let arr = player.innerText.split("/");
    playerArr.push({
        name : arr[0],
        number : arr[1]
    })
})

entryArr.forEach((num, index) => { // 내가 쓴 배열
    playerArr.forEach((player) => { // 전체 선수 배열
        if(num == player.number) {
            $("#main-today-entry").append(`
                <li class="display-flex">
                    <div class="entry-index">${index + 1}</div>
                    <div class="entry-player">${player.name}</div>
                </li>
            `)
        }
    })
})

// 마지막은 "선발투수"
const pitcher = document.querySelectorAll(".entry-index")[9];
pitcher.innerHTML = "선발투수";

