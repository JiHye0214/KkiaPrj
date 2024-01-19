var mapContainer = document.getElementById("map"),
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567),
        level: 3,
    };

var map = new kakao.maps.Map(mapContainer, mapOption);
