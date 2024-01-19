// validation
const $errMsgArr = document.querySelectorAll(".err-msg");
const $submitBtn = document.querySelector("#submit-btn");
const $validInputArr = document.querySelectorAll(".valid");
const $submitForm = document.querySelector("form");

$submitBtn.onclick = () => {
    
    let count = 0;
    let checkNum = false;
    let titleCheck = false;
    
    // 제목 25자 이하 
    const checkTitle = (title) => {
        if(title.value.length > 25) {
            $errMsgArr[0].style.display = `block`;
            $errMsgArr[0].innerHTML = `* 제목은 25자 이하로 작성해 주세요`;
        } else {
            $errMsgArr[0].style.display = ``;
            titleCheck = true;
        }
    }

    // 문자열 체크
    const checkNumber = (num) => {
        if(isNaN(num.value)) { // 문자열이면
            $errMsgArr[2].innerText = `* 숫자를 입력해 주세요`
            $errMsgArr[2].style.display = `block`;
            checkNum = false;
        } else {
            $errMsgArr[2].style.display = ``;
            checkNum = true;
        }
    }

    // 모두 입력 
    $validInputArr.forEach((input, index) => {
        if(!input.value) {
            $errMsgArr[index].style.display = `block`;
        } else {
            $errMsgArr[index].style.display = ``;
            if(index == 0) checkTitle(input);
            if(index == 2) checkNumber(input);
            count++;
        }
    })

    // 첨부파일 하나라도 있어야 함
    const $fileInputArr = document.querySelectorAll(".choice-file-btn > input");
    const $fileErrMsg = document.querySelector(".file-err-msg");
    let checkFile = false;

    if($fileInputArr.length > 0) {
        $fileInputArr.forEach((input) => {
            if(!checkFile) {
                if(input.value){
                    checkFile = true;
                    $fileErrMsg.style.display = ``;
                } else {
                    $fileErrMsg.style.display = `block`;
                }
            }
        })
    } else {
        $fileErrMsg.style.display = `block`;
    }

    if(count == 3 && checkNum && checkFile && titleCheck) {
        $submitForm.submit();
    }
}