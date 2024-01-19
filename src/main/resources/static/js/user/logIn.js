const $loginBtn = document.querySelector("#login-btn");
const $loginForm = document.querySelector("#login-content");
const $loginInputArr = document.querySelectorAll("#login-content > label > input");
const $errorMsg = document.querySelector(".error-msg");

// login validation
const clickLoginBtn = () => {
    let stop = false; // break 역할 (forEach에서 break 못 씀!)
    let count = 0;

    // 모두 입력
    $loginInputArr.forEach((input) => {
        if (!stop) {
            if (input.value) {
                $errorMsg.innerHTML = ``;
                count++;
            } else {
                $errorMsg.classList.add("login-valid");
                // 시간 지연
                setTimeout(function () {
                    $errorMsg.classList.remove("login-valid");
                }, 2000);
                $errorMsg.innerHTML = `<div class="display-flex-set"><img src="./img/warning.png" width="35px"/> ${input.placeholder} 을(를) 입력해 주세요</div>`;
                stop = true;
            }
        }
    });

    if (count == 2) {
        $loginForm.submit();
    }
};

$loginBtn.addEventListener("click", clickLoginBtn);
