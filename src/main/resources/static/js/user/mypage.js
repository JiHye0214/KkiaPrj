const $fragmentArr = document.querySelectorAll(".fragment");
const $menuBtn = document.querySelectorAll(".mypage-menu-items");
const $menuBtnForm = document.querySelector("#mypage-menu");
const $submitInput = document.querySelector("#submit-input");
const $menuNav = document.querySelector("#menu-nav");

const $diaryWrapper = document.querySelector("#diary-wrapper")
const $userWrapper = document.querySelector("#user-wrapper");

// 왼쪽 ------------------------------------------------------------------------

const clickMenuBtn = (num) => {
    $fragmentArr.forEach((fragment, index) => {
        if (index == num) {
            fragment.style.display = "block";
        } else {
            fragment.style.display = `none`;
        }
    });
};

$menuBtn.forEach((btn, index) => {
    // 색깔 칠하기
    if($menuNav.value == btn.value) {
        btn.style.background = `var(--yellow-btn)`;
    }

    btn.onclick = () => {
        clickMenuBtn(index);
        $submitInput.value = btn.value;
        $menuBtnForm.submit();
    };
});

// 오른쪽 ------------------------------------------------------------------------

// Community ---------------------------------------------------------------------
// 저장한 맛집 각 항목의 지역 태그 색 변경
const colors = ["#eb008a", "var(--kia-red)", "#0066b3", "#f37321", "#bd0d0d", "#909090", "#cf0a2c", "#80c8ff", "#244c81"];
const regionNames = ["고척", "광주", "대구", "대전", "부산", "수원", "인천", "잠실", "창원"];
const regionTags = document.querySelectorAll(".community-region");

regionTags.forEach((tag) => {
    regionNames.forEach((regionName, i) => {
        if (tag.textContent == regionName) {
            tag.style.backgroundColor = colors[i];
        }
    })
})

// 각 파트마다 처음에는 한 줄만 보여주고 모두 보기 누르면 전체 보여주기
const moreBtns = document.querySelectorAll(".moreBtn"); // 6개
const upBtns = document.querySelectorAll(".upBtn"); // 6개
const itemMaxCnt = [5, 3, 6];

console.log("moreBtns :", moreBtns);
console.log("upBtns :", upBtns);

const showHideItems = (wrap, items, maxCnt, btnIdx, clickCnt) => {
    let itemLen = items.length;

    if (itemLen === 0) {
        wrap.style.height = "163px";
    }

    for (let i = maxCnt; i < itemLen; i++) {
        items[i].style.display = "none";
    }

    let ableClickCnt = itemLen / maxCnt;

    moreBtns[btnIdx].onclick = () => {
        clickCnt++;
        if (clickCnt <= ableClickCnt) {
            for (let i = maxCnt; i < itemLen; i++) {
                items[i].style.display = "block";
            }
        }
    };

    upBtns[btnIdx].onclick = () => {
        clickCnt = 0;
        for (let i = maxCnt; i < itemLen; i++) {
            items[i].style.display = "none";
        }
    }
}

// 작성한 마켓 글
let marketClickCnt = 0;
const writeMarketWrap = document.querySelector("#write-market-wrap");
const writeMarketItems = document.querySelectorAll("#write-market-wrap > div");
showHideItems(writeMarketWrap, writeMarketItems, itemMaxCnt[0], 0, marketClickCnt);

// 작성한 맛집 게시판 글
let foodClickCnt = 0;
const writeFoodWrap = document.querySelector("#write-food-wrap");
const writeFoodItems = document.querySelectorAll("#write-food-wrap > div");
showHideItems(writeFoodWrap, writeFoodItems, itemMaxCnt[0], 1, foodClickCnt);

// 작성한 최애 게시판 글
let favoriteClickCnt = 0;
const writeFavoriteWrap = document.querySelector("#write-favorite-wrap");
const writeFavoriteItems = document.querySelectorAll("#write-favorite-wrap > div");
showHideItems(writeFavoriteWrap, writeFavoriteItems, itemMaxCnt[0], 2, favoriteClickCnt);

// 작성한 자유 게시판 글
let postClickCnt = 0;
const writePostWrap = document.querySelector("#write-post-wrap");
const writePostItems = document.querySelectorAll("#write-post-wrap > div");
showHideItems(writePostWrap, writePostItems, itemMaxCnt[0], 3, postClickCnt);

// 저장한 맛집
let saveFoodClickCnt = 0;
const saveFoodWrap = document.querySelector("#save-food-wrap");
const saveFoodItems = document.querySelectorAll("#save-food-wrap > div");
showHideItems(saveFoodWrap, saveFoodItems, itemMaxCnt[1], 4, saveFoodClickCnt);

// 마음에 든 사진
let likeFavoriteClickCnt = 0;
const likeFavoriteWrap = document.querySelector("#like-favorite-wrap");
const likeFavoriteItems = document.querySelectorAll("#like-favorite-wrap > div");
showHideItems(likeFavoriteWrap, likeFavoriteItems, itemMaxCnt[2], 5, likeFavoriteClickCnt);

// User --------------------------------------------------------------------------
if($userWrapper != null){
    const $passwordInputArr = document.querySelectorAll("#user-password > input");
    const $userErrMsg = document.querySelectorAll(".user-inform-items > .error-msg");
    const $userForm = document.querySelector("#user-content-wrap");

    $("#user-modify-btn").on("click", function() {
        let count = 0;
        let pwValidMode = false;
        let pwValid = false;

        $passwordInputArr.forEach((input) => {
            if (input.value != "") {
                count++;
            }
        });

        // 비번 검증 시작
        if (count > 0) {
            pwValidMode = true;
        }

        if (pwValidMode) {
            if (count < 3) {
                $userErrMsg[0].innerHTML = `* 세 칸 모두 입력해 주세요`;
                return true;
            }
            if ($passwordInputArr[1].value.length < 6) {
                $userErrMsg[0].innerHTML = `* 비밀번호는 6자리 이상으로 입력해 주세요`;
                return true;
            }
            if ($passwordInputArr[1].value == $passwordInputArr[2].value) {
                pwValid = true;
            } else {
                $userErrMsg[0].innerHTML = `* 신규 비밀번호와 비밀번호 확인이 같지 않습니다`;
            }
        }

        if (pwValid && count == 3) {
            $userForm.submit();
        } else if (!pwValidMode) {
            $userForm.submit();
        }
    });

    // onchange --> 이미지 바꾸기
    const userPic = document.querySelector("#user-picture-wrap #user-pic");

    $("#user-picture-wrap > #file").on("change", function(event) {
        var reader = new FileReader();
        reader.onload = function(event) {
          userPic.setAttribute("src", event.target.result);
        };
        reader.readAsDataURL(event.target.files[0]);
    });
}

// Diary --------------------------------------------------------------------------
if($diaryWrapper != null) {
    const $modalDate = document.querySelector("#modal-date");
    const $modalPlace = document.querySelector("#modal-region");
    const $modalResultArr = document.querySelectorAll("#result-wrap > div");
    const $modalMemo = document.querySelector("#memo");

    const resultArr = ["win", "draw", "lose", "cancel"];
    const recordArr = [];
    let recordWin= 0;

    // DB 받아온 기록
    const $record = document.querySelectorAll(".record-wrap .record-item");
    $record.forEach((record) => {
        let arr = record.innerText.split("/");
        recordArr.push({
            recordDate : arr[0],
            recordPlace : arr[1],
            recordResult : arr[2],
            recordMemo : arr[3]
        })
        if(arr[2] == "win") {
            recordWin++;
        }
    })

    // 승률 계산
    let rate = recordWin / recordArr.length;
    $("#win-rate").text(rate.toFixed(3));
    $("#win-whole").text(`(${recordWin} / ${recordArr.length})`);

    // 매달 배열 셋팅
    const diarySetting = () => {
        const $month = document.querySelector(".year-month");
        const $dateArr = document.querySelectorAll(".dates .day span");

        // 기록 셋팅 + 모달창 열리기
        $dateArr.forEach((date) => {

            let that = `${$month.innerText}.${date.innerText}`;

            recordArr.forEach((record) => {
                if(record.recordDate == that) { // 기록 있으면
                    date.classList.add('record'); // 달력에 표시하기 (공통 class)

                    if(record.recordResult == "win"){
                        date.classList.add('win');
                    } else if(record.recordResult == "draw"){
                        date.classList.add('draw');
                    } else if(record.recordResult == "lose"){
                        date.classList.add('lose');
                    } else {
                        date.classList.add('cancel');
                    }
                }
            })

            date.onclick = () => {
                $("#diary-wrapper > #diary-modal-wrap").css("visibility", "visible");
                $("#modal-add-btn").css("display", "block");
                $("#update-delete-wrap").css("display", "none");

                // 시작 셋팅
                $modalDate.innerHTML = `${$month.innerText}.${date.innerText}`; // 모달 날짜 표시 (공통)
                $("#modal-date-input").val($("#modal-date").html());
                $modalPlace.value = `광주기아챔피언스필드`;
                $modalMemo.value = ``;
                $modalResultArr.forEach((modalResult) => {
                    modalResult.style.opacity = ``;
                })

                recordArr.forEach((record) => {
                    let recordDateLast = record.recordDate.split(".")[2];
                    if(recordDateLast == date.innerHTML) { // 기록 있으면
                        index = resultArr.indexOf(`${record.recordResult}`);
                        $modalResultArr[index].style.opacity = `1`; // 모달 결과 표시
                        $modalPlace.value = record.recordPlace; // 모달 지역 표시
                        $modalMemo.value = record.recordMemo; // 모달 메모 표시
                        $("#modal-add-btn").css("display", "none");
                        $("#update-delete-wrap").css("display", "flex");
                    }
                })

            };
        });
        // 모달창 닫기
        $("#close-btn").on("click", function() {
            $("#diary-wrapper > #diary-modal-wrap").css("visibility", "hidden");
        });
    };

    // 모달 작성 ------------------------------------------------------------
    let resultCheck = false;

    // 결과 선택
    $modalResultArr.forEach((result, index) => {
        result.onclick = () => {
            $("#modal-result-input").val(resultArr[index]);
            resultCheck = true;
            for (let i = 0; i < $modalResultArr.length; i++) {
                if (i == index) {
                    $modalResultArr[i].style.opacity = `1`;
                } else {
                    $modalResultArr[i].style.opacity = ``;
                }
            }
        };
    });

    // ✅ 직관 기록 추가
    $("#modal-add-btn").on("click", function() {
        if (!resultCheck) {
            $(".diary-modal-items .error-msg").html(`* 경기 결과를 체크해 주세요`);
        } else {
            $("#diary-form").submit();
        }
    })

    // 직관 기록 수정
    $("#modal-update-btn").on("click", function() {
        $("#diary-form").submit();
    })
    // 직관 기록 삭제
    $("#modal-delete-btn").on("click", function() {
        let ask = confirm("삭제하시겠습니까?");
        if(ask) {
            $("#diary-form").attr("action", "/user/deleteGameRecord");
            $("#diary-form").submit();
        }
    })

    // 달력 그리기 ------------------------------------------------------------
    $(document).ready(function () {
        calendarInit();
    });

    /*
        달력 렌더링 할 때 필요한 정보 목록

        현재 월(초기값 : 현재 시간)
        금월 마지막일 날짜와 요일
        전월 마지막일 날짜와 요일
    */

    function calendarInit() {
        // 날짜 정보 가져오기
        var date = new Date(); // 현재 날짜(로컬 기준) 가져오기
        var utc = date.getTime() + date.getTimezoneOffset() * 60 * 1000; // uct 표준시 도출
        var kstGap = 9 * 60 * 60 * 1000; // 한국 kst 기준시간 더하기
        var today = new Date(utc + kstGap); // 한국 시간으로 date 객체 만들기(오늘)

        var thisMonth = new Date(today.getFullYear(), today.getMonth(), today.getDate());
        // 달력에서 표기하는 날짜 객체

        var currentYear = thisMonth.getFullYear(); // 달력에서 표기하는 연
        var currentMonth = thisMonth.getMonth(); // 달력에서 표기하는 월
        var currentDate = thisMonth.getDate(); // 달력에서 표기하는 일

        // kst 기준 현재시간
        // console.log(thisMonth);

        // 캘린더 렌더링
        renderCalender(thisMonth);

        function renderCalender(thisMonth) {
            // 렌더링을 위한 데이터 정리
            currentYear = thisMonth.getFullYear();
            currentMonth = thisMonth.getMonth();
            currentDate = thisMonth.getDate();

            // 이전 달의 마지막 날 날짜와 요일 구하기
            var startDay = new Date(currentYear, currentMonth, 0); // 0번째는 지난달 마지막날을 의미한다
            var prevDate = startDay.getDate();
            var prevDay = startDay.getDay();

            // 이번 달의 마지막날 날짜와 요일 구하기
            var endDay = new Date(currentYear, currentMonth + 1, 0);
            var nextDate = endDay.getDate();
            var nextDay = endDay.getDay();

            // 현재 월 표기
            $(".year-month").text(currentYear + "." + (currentMonth + 1));

            // 렌더링 html 요소 생성
            calendar = document.querySelector(".dates");
            calendar.innerHTML = "";

            // 지난달
            for (var i = prevDate - prevDay + 1; i <= prevDate; i++) {
                calendar.innerHTML = calendar.innerHTML + '<div class="day prev disable">' + i + "</div>";
            }
            // 이번달
            for (var i = 1; i <= nextDate; i++) {
                calendar.innerHTML = calendar.innerHTML + '<div class="day current display-flex-set"><span>' + i + "</span></div>";
            }
            // 다음달
            for (var i = 1; i <= (7 - nextDay == 7 ? 0 : 7 - nextDay); i++) {
                calendar.innerHTML = calendar.innerHTML + '<div class="day next disable">' + i + "</div>";
            }

            // 오늘 날짜 표기
            if (today.getMonth() == currentMonth) {
                todayDate = today.getDate();
                var currentMonthDate = document.querySelectorAll(".dates .current");
                currentMonthDate[todayDate - 1].classList.add("today");
            }

            // ✅ 캘린더 바뀔 때마다 배열 셋팅
            diarySetting();
        }

        // 이전달로 이동
        $(".go-prev").on("click", function () {
            thisMonth = new Date(currentYear, currentMonth - 1, 1);
            renderCalender(thisMonth);
        });

        // 다음달로 이동
        $(".go-next").on("click", function () {
            thisMonth = new Date(currentYear, currentMonth + 1, 1);
            renderCalender(thisMonth);
        });
    }
}

// drop user ------------------------------------------------------------
$("#drop-user-btn").click(function() {
    let drop = confirm("정말 탈퇴하시겠습니까?");
    if(drop) {
        $("#drop-modal-wrap").css('visibility', 'visible');
    }
})

$("#drop-close-btn").click(function() {
    $("#drop-modal-wrap").css('visibility', '');
    $(".drop-input").val("");
})

$("#drop-submit-btn").click(function() {
    if(!$(".drop-input").val()) {
        $("#drop-title").css('marginBottom', '20px');
        $("#drop-valid-msg").text("* 비밀번호를 입력해 주세요");
    } else {
        $("#drop-content").submit();
        $("#drop-valid-msg").text("");
        $(".drop-input").val("");
    }
})