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
const $slideBtnArr = document.querySelectorAll("#slide-btn-wrap > li");

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


