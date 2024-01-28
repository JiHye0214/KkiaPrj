// 최애 글 삭제
const deleteBtn = document.querySelector("#delete-btn");

if (deleteBtn) {
    deleteBtn.onclick = () => {
        if (confirm("글을 삭제하시겠습니까?")) {
            document.forms["delete-form"].submit();
        }
    }
}

// ====================================================================================================

// 상세에서 최애 글 좋아요(하트)
const likeForm = document.querySelector("#like-form");
const heartBtn = document.querySelector("#heart");
const isLikeClicked = document.querySelector("#isLikeClicked"); // 클릭되어있는 버튼인지 여부

// 로그인 한 유저에 따라 항목의 heart 색 변경
if (isLikeClicked.value == "true") {
    heartBtn.style.fill = "rgb(255, 110, 110)";
} else {
    heartBtn.style.fill = "white";
}

heartBtn.onclick = () => {
    likeForm.submit();
}
