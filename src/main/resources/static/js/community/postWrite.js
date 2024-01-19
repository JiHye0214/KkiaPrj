// category
const $typeBtn = document.querySelector("#category");
const $typeWrap = document.querySelector("#category-wrap");
const $typeInput = document.querySelector("#type");
if($typeInput.value) {
    $typeBtn.style.color = `#333`;
}
$typeBtn.onclick = () => {
    $typeBtn.style.color = `#333`;
    $typeWrap.style.display = `block`;
    typeItemSetting();
};
const typeItemSetting = () => {
    const $typeItemArr = document.querySelectorAll(".category-item");
    $typeItemArr.forEach((type) => {
        type.onclick = () => {
            $typeBtn.innerHTML = `${type.innerText}`;
            $typeInput.value = `${type.innerText}`;
            $typeWrap.style.display = `none`;
        };
    });
};

// validation
const $errMsgArr = document.querySelectorAll(".err-msg");
const $submitBtn = document.querySelector("#submit-btn");
const $validInputArr = document.querySelectorAll(".valid");
const $submitForm = document.querySelector("form");

$submitBtn.onclick = () => {
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
        $submitForm.submit();
    }
};
