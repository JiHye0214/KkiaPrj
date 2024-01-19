// 지도
const storeNames = document.querySelectorAll(".store-name");
const storeLats = document.querySelectorAll(".lat");
const storeLngs = document.querySelectorAll(".lng");

var mapContainer = document.getElementById("map"),
    mapOption = {
        center: new kakao.maps.LatLng(37.3897, 126.9533556), // 각 지역별 중심 좌표
        level: 3,
    };

var map = new kakao.maps.Map(mapContainer, mapOption);

// 마커를 표시할 위치와 title 객체 배열
var positions = [];

storeNames.forEach((storeName, i) => {
    let position = {};

    position.title = storeName.textContent;
    position.latlng = new kakao.maps.LatLng(storeLats[i].value, storeLngs[i].value);

    positions.push(position);
});

// 마커 이미지의 이미지 주소
var imageSrc = "/img/location.png";

for (var i = 0; i < positions.length; i++) {
    // 마커 이미지의 이미지 크기
    var imageSize = new kakao.maps.Size(35, 35);

    // 마커 이미지를 생성
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

    // 마커를 생성
    var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨
        image: markerImage, // 마커 이미지
    });
}
