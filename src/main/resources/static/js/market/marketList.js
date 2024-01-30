$("#initial-btn").on("click", function() {
    $("#search-input").val("");
    document.forms["search-form"].submit();
})

// 검색 결과 없음
const listWrapChild = document.querySelectorAll("#list-wrap a");
if(listWrapChild.length <= 0) {
    $("#list-wrap").text("검색 결과가 없습니다");
    $("#list-wrap").css({"justify-content" : "center",
                         "align-items" : "center",
                         "font-size" : "20px" ,
                         "font-weight" : "600"
    });
}

