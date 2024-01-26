// validation
const $errMsgArr = document.querySelectorAll(".err-msg");
const $submitBtn = document.querySelector("#submit-btn");
const $validInputArr = document.querySelectorAll(".valid");

$submitBtn.onclick = () => {
    let count = 0;
    let isNumChecked = false;
    let isTitleChecked = false;

    // 제목 25자 이하 
    const checkTitle = (title) => {
        if (title.value.length > 25) {
            $errMsgArr[0].innerHTML = `* 제목은 25자 이하로 작성해 주세요`;
            $errMsgArr[0].style.display = `block`;
        } else {
            $errMsgArr[0].style.display = ``;
            isTitleChecked = true;
        }
    }

    // 문자열 체크
    const checkNum = (num) => {
        if (isNaN(num.value)) { // 문자열이면
            $errMsgArr[2].innerText = `* 숫자를 입력해 주세요`
            $errMsgArr[2].style.display = `block`;
        } else {
            $errMsgArr[2].style.display = ``;
            isNumChecked = true;
        }
    }

    // 모두 입력 
    $validInputArr.forEach((input, index) => {
        if (!input.value) {
            $errMsgArr[index].style.display = `block`;
        } else {
            $errMsgArr[index].style.display = ``;
            if (index == 0) checkTitle(input);
            if (index == 2) checkNum(input);
            count++;
        }
    })

    // 첨부파일 하나라도 있어야 함
    const $fileInputArr = document.querySelectorAll(".choice-file-btn > input");

    const $writeConfirmTxt = document.querySelectorAll(".confirm-txt");
    const $updateConfirmTxt = document.querySelectorAll(".update-confirm-txt");
    const $fileErrMsg = document.querySelector(".file-err-msg");
    let isFileChecked = false;

    const checkFile = (confirmTxt) => {
        confirmTxt.forEach((txt) => {
            if (!isFileChecked) {
                if (txt.innerText) {
                    isFileChecked = true;
                    $fileErrMsg.style.display = ``;
                } else {
                    $fileErrMsg.style.display = `block`;
                }
            }
        })
    }

    // $updateConfirmTxt 길이가 0 이상이라는 건
    // 수정페이지라는 것이고 기존 이미지가 있다는 것
    // 수정 페이지면서 기존 이미지가 있다면
    if ($updateConfirmTxt.length > 0) {
        isFileChecked = true;
    } else {
        // 작정 페이지거나, 수정 페이지인데 기존 이미지를 다 지웠다면
        if ($writeConfirmTxt.length > 0) {
            // checkFile($writeConfirmTxt);

            $writeConfirmTxt.forEach((txt) => {
                if (!isFileChecked) {
                    if (txt.innerText) {
                        isFileChecked = true;
                        $fileErrMsg.style.display = ``;
                    } else {
                        $fileErrMsg.style.display = `block`;
                    }
                }
            })
        } else {
            $fileErrMsg.style.display = `block`;
        }
    }

    if (count == 3 && isTitleChecked && isNumChecked && isFileChecked) {
        document.forms["write-form"].submit();
    }
}
