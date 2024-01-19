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