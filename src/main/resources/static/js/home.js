// TODAY ------------------------------------------------------------------------------------------
$(document).ready(function() {

    // 날씨 -----------------------------------------------------
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

    var $Icon = ($("#weather-icon").text()).substr(0,2);
    $('#weather-icon').append('<i class="' + weatherIcon[$Icon] +'"></i>');

    // 뉴스 -----------------------------------------------------
    const newsTitles = document.querySelectorAll(".news-item");

    newsTitles.forEach((title) => {
        title.innerText = title.innerText.replace("&gt;", ">");
        title.innerText = title.innerText.replace("<b>", "");
        title.innerText = title.innerText.replace("</b>", "");
        title.innerText = title.innerText.replace("&quot;", "");
    })

    // 엔트리 -----------------------------------------------------
    // 마지막은 "선발투수"
    const pitcher = document.querySelectorAll(".entry-index")[9];
    pitcher.innerHTML = "선발투수";

})

// 지도 api -----------------------------------------------------

const lat = $("#latitude").text();
const lon = $("#longitude").text();

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

// slide banner ------------------------------------------------------------------------------------------

const $slideWrap = document.querySelector("#slide-wrap");
const slideWidth = $slideWrap.clientWidth; // 슬라이드 wrap 너비(width)
const slidItem = document.querySelectorAll("#slide-wrap > li");
const $slideBtnArr = document.querySelectorAll("#slide-btn-wrap > li");

// 아직 최애 글 개수가 5개가 안된다면 나머지에 기본 이미지 세팅
let restCnt = 5 - slidItem.length;
for (let i = 0; i < restCnt; i++) {
    $slideWrap.innerHTML += `
        <li>
            <img src="/img/호걸이.png" />
        </li>
    `
}

// 최애 글 이미지 슬라이드
let idx = 0;

const slideNext = () => {
    idx++;
    if (idx >= $slideBtnArr.length) {
        idx = 0;
    }

    let offset = slideWidth * idx;
    $slideWrap.style.transform = `translateX(-${offset}px)`;

    $slideBtnArr.forEach((btn) => btn.classList.remove("active"));
    $slideBtnArr[idx].classList.add("active");
};

setInterval(slideNext, 4000);

// 각 페이지네이션 버튼 클릭 시 해당 슬라이드로 이동하기
$slideBtnArr.forEach((btn, i) => {
    btn.onclick = () => {
        console.log("클릭");

        const offset = slideWidth * i;
        $slideWrap.style.transform = `translateX(-${offset}px)`;

        $slideBtnArr.forEach((btn) => btn.classList.remove("active"));
        btn.classList.add("active");

        idx = i;
    };
});
