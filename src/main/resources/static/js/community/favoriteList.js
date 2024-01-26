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
