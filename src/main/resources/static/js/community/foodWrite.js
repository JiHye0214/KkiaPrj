// 맛집 검색 -> 검색 결과 목록 & 지도에 마커

// 마커 배열
var markers = [];

var mapContainer = document.getElementById("map"),
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567),
        level: 3,
    };

// 지도 생성
var map = new kakao.maps.Map(mapContainer, mapOption);

// 장소검색 객체 생성
var ps = new kakao.maps.services.Places();

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우 생성
var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

// 키워드로 장소 검색
// searchPlaces();

// 키워드 검색을 요청하는 함수
function searchPlaces() {
    var keyword = document.getElementById("keyword").value;

    // if (!keyword.replace(/^\s+|\s+$/g, "")) {
    //     alert("키워드를 입력해주세요!");
    //     return false;
    // }

    // 장소검색 객체를 통해 키워드로 장소검색을 요청
    ps.keywordSearch(keyword, placesSearchCB);
}

// 장소검색이 완료됐을 때 호출되는 콜백함수
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출
        displayPlaces(data);

        // 페이지 번호를 표출합니다
        displayPagination(pagination);
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        alert("검색 결과가 존재하지 않습니다.");
        return;
    } else if (status === kakao.maps.services.Status.ERROR) {
        alert("검색 결과 중 오류가 발생했습니다.");
        return;
    }
}

// 검색 결과 목록과 마커를 표출하는 함수
function displayPlaces(places) {
    var listEl = document.getElementById("placesList"),
        menuEl = document.getElementById("menu-wrap"),
        fragment = document.createDocumentFragment(),
        bounds = new kakao.maps.LatLngBounds();

    // 검색 결과 목록에 추가된 항목들을 제거
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();

    for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            marker = addMarker(placePosition, i),
            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기 위해
        // LatLngBounds 객체에 좌표를 추가
        bounds.extend(placePosition);

        // 마커와 검색 결과 항목에 mouseover 했을 때
        // 해당 장소에 인포윈도우에 장소명을 표시
        // mouseout 했을 때는 인포윈도우를 닫음
        (function (marker, title) {
            kakao.maps.event.addListener(marker, "mouseover", function () {
                displayInfowindow(marker, title);
            });

            kakao.maps.event.addListener(marker, "mouseout", function () {
                infowindow.close();
            });

            itemEl.onmouseover = function () {
                displayInfowindow(marker, title);
            };

            itemEl.onmouseout = function () {
                infowindow.close();
            };
        })(marker, places[i].place_name);

        fragment.appendChild(itemEl);
    }

    // 검색 결과 항목들을 검색 결과 목록 Element에 추가
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정
    map.setBounds(bounds);

    // 검색 결과 목록 반환 후 #placesList > .item 들 가지고 와서
    // 각 항목 클릭하면 추천 맛집 항목 추가
    var listItems = document.querySelectorAll(".item");
    var itemTitles = document.querySelectorAll(".item h5");
    addTxtContent(listItems, itemTitles);
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {
    var el = document.createElement("li");
    var itemStr =
        `<span class="markerbg marker_${index + 1}"></span>` +
        `<div class="info">` +
        `<h5>${places.place_name}</h5>
    `;

    if (places.road_address_name) {
        itemStr +=
            `<span>${places.road_address_name}</span>` +
            `<span class="jibun gray">${places.address_name}</span>
        `;
    } else {
        itemStr += `<span>${places.address_name}</span>`;
    }

    itemStr += `</div>`;

    el.innerHTML = itemStr;
    el.className = "item";

    return el;
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
    var imageSrc = "./img/markers.png",
        imageSize = new kakao.maps.Size(40, 46), // 마커 이미지의 크기
        imgOptions = {
            spriteSize: new kakao.maps.Size(38, 691), // 이미지의 크기
            spriteOrigin: new kakao.maps.Point(0, idx * 46), // 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(20, 40), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
        marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage,
        });

    marker.setMap(map); // 지도 위에 마커를 표출
    markers.push(marker); // 배열에 생성된 마커를 추가

    return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거
function removeMarker() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
}

// 검색 결과 목록 하단에 페이지 번호를 표시는 함수
function displayPagination(pagination) {
    var paginationEl = document.getElementById("pagination"),
        fragment = document.createDocumentFragment(),
        i;

    // 기존에 추가된 페이지번호를 삭제
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild(paginationEl.lastChild);
    }

    for (i = 1; i <= pagination.last; i++) {
        var el = document.createElement("a");
        el.href = "#";
        el.innerHTML = i;

        if (i === pagination.current) {
            el.className = "on";
        } else {
            el.onclick = (function (i) {
                return function () {
                    pagination.gotoPage(i);
                };
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
}

// 검색 결과 목록 또는 마커를 클릭했을 때 호출되는 함수
// 인포윈도우에 장소명을 표시
function displayInfowindow(marker, title) {
    var content = '<div class="infoWindow" style="padding: 5px 0 7px 10px; z-index: 1;">' + title + "</div>";

    infowindow.setContent(content);
    infowindow.open(map, marker);
}

// 검색 결과 목록의 자식 Element를 제거하는 함수
function removeAllChildNods(el) {
    while (el.hasChildNodes()) {
        el.removeChild(el.lastChild);
    }
}

// ====================================================================================================

// 검색 결과 항목 (or 마커 선택) 시 추천 맛집 아이템 추가하는 함수
function addTxtContent(listItems, itemTitles) {
    listItems.forEach(function (item, i) {
        item.onclick = function () {
            $("#item-content-wrap").append(`
                <div class="item-wrap">
                    <div class="txt-wrap display-flex">
                        <div class="store-name">${itemTitles[i].textContent}</div>
                        <textarea class="store-info" placeholder="소개 내용을 입력해 주세요" spellcheck="false" name="submit-info-${i}"></textarea>
                        <button type="button" class="item-delete-btn"></button>
                    </div>
                    <p class="err-msg content-err-msg">* 내용을 입력해 주세요</p>
                </div>
            `);

            var itemWraps = document.querySelectorAll(".item-wrap");
            var itemDeletBtns = document.querySelectorAll(".item-delete-btn");
            itemDeletBtns.forEach((btn, i) => {
                btn.onclick = () => {
                    console.log("x클릭");
                    itemWraps[i].remove();
                };
            });
        };
    });
}

// ====================================================================================================

const regionNames = ["고척", "광주", "대구", "대전", "부산", "수원", "인천", "잠실", "창원"];

const titleInput = document.querySelector("#input-title");
const titleSubmitInput = document.querySelector("#submit-title");
const regionSubmitInput = document.querySelector("#submit-region");

const selectedRegion = document.querySelector("#selected-region");
const regionWrap = document.querySelector("#region-wrap");

titleInput.onchange = () => {
    titleSubmitInput.value = titleInput.value;
};

// 지역 선택창
regionNames.forEach((regionName) => {
    regionWrap.innerHTML += `<div class="region">${regionName}</div>`;
});
const regionDiv = document.querySelectorAll(".region");

regionDiv.forEach((region) => {
    region.addEventListener("click", () => {
        // 방금 클릭한 지역이 이미 클릭되어 있었다면
        // 해당 지역 "clicked" 해제
        // 글자를 "지역" 으로 변경
        if (region.classList.contains("clicked")) {
            region.classList.remove("clicked");
            selectedRegion.textContent = "지역";
        }
        // 방금 클릭한 지역이 지역 중에 첫 클릭이거나
        // 방금 클릭한 지역 이전에 클릭되어 있던 다른 지역이 있었다면
        // 모든 지역 "clicked" 해제 후 방금 클릭한 지역 "clicked" 추가
        // 글자를 해당 지역 이름으로 변경
        else {
            regionDiv.forEach((el) => {
                el.classList.remove("clicked");
            });
            region.classList.add("clicked");

            selectedRegion.textContent = region.textContent;
        }

        regionSubmitInput.value = region.textContent;
    });
});

selectedRegion.addEventListener("click", () => {
    // 지역 선택창 닫혀있었다면
    if (regionWrap.style.display !== "block") {
        $("#region-wrap").slideDown("fast");
    }
    // 지역 선택창 열려있었다면
    else {
        $("#region-wrap").slideUp("fast");
    }

    // 지역 선택 버튼 클릭되어 있었고
    // 글자가 "지역" 이라면
    if (selectedRegion.classList.contains("clicked") && selectedRegion.textContent == "지역") {
        selectedRegion.classList.remove("clicked");
    }
    // 지역 선택 버튼 클릭되어 있지 않았거나
    // 글자가 특정 지역이라면
    else {
        selectedRegion.classList.add("clicked");
    }
});

$(document).click(function (e) {
    if (e.target.className == "selected-region") {
        return false;
    } else if (e.target.className == "selected-region clicked") {
        return false;
    } else if (e.target.className == "region") {
        return false;
    } else if (e.target.className == "region clicked") {
        return false;
    }

    $("#region-wrap").slideUp("fast");

    if (selectedRegion.textContent == "지역") {
        selectedRegion.classList.remove("clicked");
    } else {
        selectedRegion.classList.add("clicked");
    }
});

// ====================================================================================================

// validation
const writeBtn = document.querySelector("#submit-btn");
const errMsgs = document.querySelectorAll(".err-msg");

writeBtn.addEventListener("click", () => {
    document.forms["write-form"].submit();
});

// 제목, 지역, 맛집 소개 반드시 작성
// 맛집 하나 이상 선택
// 추가한 추천 맛집 항목은 주소 or 좌표도 같이 제출 (상세페이지에서 마커 표시하기 위해)
// (이미 선택한 맛집 추가 선택 안되게)
// (마커 클릭 시에도 맛집 항목에 추가되게 - ...하지말자...)

// 입력 없이 검색하면 검색어 입력 요구
// 위 line 47, 50 - alert 말고 로그인 페이지같은 alert로

// 인포윈도우 구려
// 커스텀 오버레이로?? 하지만... 너무 괴로운걸
