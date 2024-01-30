const searchInput = document.querySelector("#search-input");
const initialBtn = document.querySelector("#initial-btn");
const searchBtn = document.querySelector("#search-btn");

// 검색어 초기화
initialBtn.onclick = () => {
    searchInput.value = "";
    document.forms["search-form"].submit();
}

// 검색
searchBtn.onclick = () => {
    // 검색어 입력 안 되어있으면 검색 버튼 동작하지 않도록
    if (!searchInput.value == "") {
        document.forms["search-form"].submit();
    }
}

// 검색 결과 없음
const listWrapChild = document.querySelectorAll("#list-wrap a");
if(listWrapChild.length <= 0) {
    $("#list-wrap").text("검색 결과가 없습니다");
    $("#list-wrap").css({"justify-content" : "center",
                         "align-items" : "center",
                         "font-size" : "20px" ,
                         "font-weight" : "600",
                         "height" : "464px"
    });
}

// ====================================================================================================

// 목록에서 최애 글 좋아요(하트)
const likeForms = document.querySelectorAll(".like-form");
const heartBtns = document.querySelectorAll(".heart");
const isLikeClicked = document.querySelectorAll(".isLikeClicked"); // 클릭되어있는 버튼인지 여부
const isLoggedIn = document.querySelector("#isLoggedIn"); // 로그인 여부

heartBtns.forEach((heart, i) => {
    // 로그인 한 유저에 따라 목록의 각 항목 heart 색 변경
    if (isLikeClicked[i].value == "true") {
        heart.style.fill = "rgb(255, 110, 110)";
    } else {
        heart.style.fill = "white";
    }

    // 로그인 한 상태라면 like-form[i] 제출
    // 로그인 안 한 상태라면 로그인 페이지로 이동
    heart.onclick = () => {
        if (isLoggedIn.value) {
            likeForms[i].submit();
        } else {
            location.href = "/user/logIn";
        }
    }
})
