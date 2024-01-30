const typeArr = ["직관나눔", "갸빠일상", "동행구함", "기타"];
const typeColorArr = ["#ffd3d3", "#fff7d3", "#daffd3", "#d3e5ff"];
const $postType = document.querySelectorAll(".post-inform .post-type");

typeArr.forEach((el, index) => {
    $postType.forEach((type) => {
        if(el == type.innerText){
            type.style.backgroundColor = `${typeColorArr[index]}`;
        }
    })
})

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