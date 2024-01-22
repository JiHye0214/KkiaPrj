const $signupBtn = document.querySelector("#signup-btn");
const $signupForm = document.querySelector("#signup-content");
const $signupInputArr = document.querySelectorAll("input.signup-input");
const $errorMsg = document.querySelector(".error-msg");
const $errText = document.querySelector(".error-msg-text");

let regex = new RegExp("[a-z0-9]+@[a-z]+.[a-z]{2,3}");
let message = "";

let stop = false;
let count = 0;
let checkId = (checkPw1 = checkPw2 = checkEmail = false);

const clickSignupBtn = () => {
    count = 0;
    checkId = checkPw1 = checkPw2 = checkEmail = false;
    if (stop) stop = false; // 없으면 한 번 에러나면 계속 stop = true

    for (let i = 0; i < $signupInputArr.length; i++) {
        // 모두 입력
        if (!stop) {
            if ($signupInputArr[i].value != "") {
                count++;
                callback(i);
            } else {
                message = `${$signupInputArr[i].placeholder} 을(를) 입력해 주세요`;
                setErrMsg(message);
                break;
            }
        }
    }

    if (count == 6 && checkId && checkPw1 && checkPw2 && checkEmail) {
        $signupForm.submit();
    }
};

const callback = (index) => {
    switch (index) {
        case 0:
            if ($signupInputArr[index].value.length >= 6) {
                checkId = true;
            } else {
                message = `아이디는 6자 이상으로 입력해 주세요`;
                stop = true;
                setErrMsg(message);
            }
            break;

        case 1:
            if ($signupInputArr[index].value.length >= 6) {
                checkPw1 = true;
            } else {
                message = `비밀번호는 6자 이상으로 입력해 주세요`;
                stop = true;
                setErrMsg(message);
            }
            break;

        case 2:
            if ($signupInputArr[1].value == $signupInputArr[2].value) {
                checkPw2 = true;
            } else {
                message = `비밀번호가 일치하지 않습니다`;
                stop = true;
                setErrMsg(message);
            }
            break;

        case 4:
            if (regex.test($signupInputArr[4].value)) {
                checkEmail = true;
            } else {
                message = `이메일 형식이 올바르지 않습니다`;
                stop = true;
                setErrMsg(message);
            }
            break;
    }
};

const setErrMsg = (message) => {
    $errorMsg.classList.add("login-valid");
    setTimeout(function () {
        $errorMsg.classList.remove("login-valid");
    }, 2000);
    $errText.innerHTML = `${message}`;
};

$signupBtn.addEventListener("click", clickSignupBtn);

// 이거 되면 나 천재임 --> 나 천재다 !
// 서버 validation 보여주기
$(document).ready(function() {
    if($errText.innerText) {
        $errorMsg.classList.add("login-valid");
        setTimeout(function () {
            $errorMsg.classList.remove("login-valid");
        }, 2000);
    }
})