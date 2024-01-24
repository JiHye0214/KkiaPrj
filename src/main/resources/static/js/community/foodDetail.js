// 지도
const storeWraps = document.querySelectorAll(".txt-wrap");
const storeNames = document.querySelectorAll(".store-name");
const storeLats = document.querySelectorAll(".lat");
const storeLngs = document.querySelectorAll(".lng");

// 마커 배열
var markers = [];

var mapContainer = document.getElementById("map"),
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567),
        level: 3,
    };

var map = new kakao.maps.Map(mapContainer, mapOption);

// 마커를 표시할 위치와 title 객체 배열
var positions = [];

// 맛집 목록이나 마커에 mouseover 했을 때 장소명을 표출할 인포윈도우 생성
var infowindow = new kakao.maps.InfoWindow({zIndex: 1});

// 맛집 목록 또는 마커에 mouseover 했을 때 호출되는 함수
// 인포윈도우에 장소명을 표시
function displayInfowindow(marker, title) {
    var content = '<div class="infoWindow">' + title + "</div>";

    infowindow.setContent(content);
    infowindow.open(map, marker);
}

storeNames.forEach((storeName, i) => {
    let position = {};

    position.title = storeName.textContent;
    position.latlng = new kakao.maps.LatLng(storeLats[i].value, storeLngs[i].value);

    positions.push(position);
});

// 마커 이미지의 이미지 주소
var imageSrc = "/img/location.png";

var bounds = new kakao.maps.LatLngBounds();

for (var i = 0; i < positions.length; i++) {
    // 마커 이미지의 이미지 크기
    var imageSize = new kakao.maps.Size(34, 35);

    // 마커 이미지를 생성
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

    // 마커를 생성
    var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        // title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨
        image: markerImage, // 마커 이미지
    });

    bounds.extend(new kakao.maps.LatLng(storeLats[i].value, storeLngs[i].value));

    // 맛집 목록이 또는 마커에 mouseover 했을 때
    // 해당 장소에 인포윈도우에 장소명을 표시
    // mouseout 했을 때는 인포윈도우를 닫음
    (function (marker, title) {
        kakao.maps.event.addListener(marker, "mouseover", function () {
            displayInfowindow(marker, title);
        });

        kakao.maps.event.addListener(marker, "mouseout", function () {
            infowindow.close();
        });

        storeWraps[i].onmouseover = function () {
            displayInfowindow(marker, title);
        };

        storeWraps[i].onmouseout = function () {
            infowindow.close();
        };
    })(marker, storeNames[i].textContent);
}

map.setBounds(bounds);

// 맛집 글 삭제
const deleteBtn = document.querySelector("#delete-btn");
deleteBtn.onclick = () => {
    if (confirm("글을 삭제하시겠습니까?")) {
        document.forms["delete-form"].submit();
    }
}

// 댓글 작성
const cmtWriteBtn = document.querySelector("#comment-write-btn");
const cmtInput = document.querySelector("#comment-input");
cmtWriteBtn.onclick = () => {
    if (cmtInput.value != "") {
        document.forms["cmt-write-form"].submit();
    }
}

// 댓글 삭제
const deleteBtns = document.querySelectorAll(".comment-delete-btn");
const deleteForms = document.querySelectorAll("[name = 'comment-delete-form']");
deleteBtns.forEach((delBtn, idx) => {
    delBtn.onclick = () => {
        if (confirm("댓글을 삭제하시겠습니까?")) {
            deleteForms[idx].submit();
        }
    }
})
