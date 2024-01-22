const $loginBtn = document.querySelector("#login-btn");
const $loginForm = document.querySelector("#login-content");
const $loginInputArr = document.querySelectorAll("#login-content > label > input");
const $errorMsg = document.querySelector(".error-msg");
const $errText = document.querySelector(".error-msg-text");

// login validation
const clickLoginBtn = () => {
    let stop = false; // break 역할 (forEach에서 break 못 씀!)
    let count = 0;

    // 모두 입력
    $loginInputArr.forEach((input) => {
        if (!stop) {
            if (input.value) {
                $errText.innerHTML = ``;
                count++;
            } else {
                $errorMsg.classList.add("login-valid");
                // 시간 지연
                setTimeout(function () {
                    $errorMsg.classList.remove("login-valid");
                }, 2000);
                $errText.innerHTML = `${input.placeholder} 을(를) 입력해 주세요`;
                stop = true;
            }
        }
    });

    if (count == 2) {
        $loginForm.submit();
    }
};

$loginBtn.addEventListener("click", clickLoginBtn);
$loginInputArr[0].addEventListener("keyup", function (event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        $loginBtn.click();
    }
});
$loginInputArr[1].addEventListener("keyup", function (event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        $loginBtn.click();
    }
});

// 서버 validation 보여주기
$(document).ready(function() {
    if($errText.innerText) {
        $errorMsg.classList.add("login-valid");
        setTimeout(function () {
            $errorMsg.classList.remove("login-valid");
        }, 2000);
    }
})
