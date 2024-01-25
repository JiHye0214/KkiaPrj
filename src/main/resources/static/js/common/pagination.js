// 1 페이지밖에 없을 때 border-radius
// 페이지네이션 공유하는 마켓, 맛집, 최애, 자유게시판 list 에서 class 에 num 이라는 속성 없어야 함
const pageNums = document.querySelectorAll(".num");
if (pageNums.length == 1) {
    pageNums[0].style.borderRadius = "3px";
}

// 항목이 하나도 없을 때 페이지네이션 border 없애기
const pagingWrap = document.querySelector("#pagination-content");
if (!pagingWrap.childElementCount) {
    pagingWrap.style.border = "none";
}
