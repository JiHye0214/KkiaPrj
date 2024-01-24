// 1 페이지밖에 없을 때 border-radius
const pageNums = document.querySelectorAll(".num");
if (pageNums.length == 1) {
    console.log("하나");
    pageNums[0].style.borderRadius = "3px";
}

// 항목이 하나도 없을 때 페이지네이션 border 없애기
const pagingWrap = document.querySelector("#pagination-content");
if (!pagingWrap.childElementCount) {
    pagingWrap.style.border = "none";
}
