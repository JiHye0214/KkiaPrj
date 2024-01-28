$(document).ready(function() {
    if($("#category").text() != "카테고리"){
        $("#type").val($("#category").text());
        $("#category").css("color", "#333");
    }
})

// category
let openToggle = false;
let colorIndex = null;
const $typeBtn = document.querySelector("#category");
const $typeWrap = document.querySelector("#category-wrap");
const $typeInput = document.querySelector("#type");
const $typeItemArr = document.querySelectorAll(".category-item");

$typeBtn.onclick = () => { // 선택창 열기
    if(!openToggle){
        $typeBtn.style.color = `#333`;
        $typeWrap.style.display = `block`;
        typeItemSetting();
        openToggle = true;
    } else {
        $typeBtn.style.color = ``;
        $typeWrap.style.display = `none`;
        openToggle = false;
    }
};
const typeItemSetting = () => {
    $typeItemArr.forEach((type, index) => {
        type.onclick = () => {
            colorIndex = index;
            $typeBtn.style.color = `#333`;
            $typeBtn.innerHTML = `${type.innerText}`;
            $typeInput.value = `${type.innerText}`;
            $typeWrap.style.display = `none`;
            openToggle = false;
        };

        // 색깔 칠하기
        if(colorIndex == index) {
            type.style.background = `var(--yellow-btn)`;
        } else {
            type.style.background = ``;
        };
    });
};

// validation
const $errMsgArr = document.querySelectorAll(".err-msg");
const $validInputArr = document.querySelectorAll(".valid");

$("#submit-btn").on("click", function() {
    let count = 0;
    let titleCheck = false;

    // 제목 25자 이하
    const checkTitle = (title) => {
        if (title.value.length > 25) {
            $errMsgArr[0].style.display = `block`;
            $errMsgArr[0].innerHTML = `* 제목은 25자 이하로 작성해 주세요`;
        } else {
            $errMsgArr[0].style.display = ``;
            titleCheck = true;
        }
    };

    // 모두 입력
    $validInputArr.forEach((input, index) => {
        if (!input.value) {
            $errMsgArr[index].style.display = `block`;
        } else {
            $errMsgArr[index].style.display = ``;
            if (index == 0) checkTitle(input);
            count++;
        }
    });

    if (count == 3 && titleCheck) {
        document.forms["write-form"].submit();
    }
})

