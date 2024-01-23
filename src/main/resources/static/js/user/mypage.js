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
                    date.classList.add(`${record.recordResult}`); // 달력에 표시하기
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
        $("#drop-title").css('marginBottom', '15px');
        $("#drop-valid-msg").css('display', 'block');
    } else {
        $("#drop-content").submit();
        $(".drop-input").val("");
    }
})