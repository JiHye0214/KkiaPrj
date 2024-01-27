const $findMenuArr = document.querySelectorAll(".find-menu-item");
const $findFormArr = document.querySelectorAll("#find-wrapper > form");
const $findBtn = document.querySelectorAll(".find-btn");
const $errorMsg = document.querySelector(".error-msg");
const $errText = document.querySelector(".error-msg-text");

// find menu
for (let i = 0; i < $findMenuArr.length; i++) {
    $findMenuArr[i].onclick = () => {
        $findMenuArr.forEach((menu, index) => {
            if (index == i) {
                menu.style.background = `rgb(222, 222, 222)`;
                $findFormArr[index].style.display = `block`;
            } else {
                menu.style.background = `#f0f0f0`;
                $findFormArr[index].style.display = `none`;
            }
        });
    };
}

// click btn
$findBtn.forEach((btn, index) => {
    btn.onclick = () => {
        validation(index);
    };
});

// validation
const validation = (index) => {
    let count = 0;
    const child = [];
    child.push($findFormArr[index].children[1]);
    child.push($findFormArr[index].children[2]);

    for (let i = 0; i < 2; i++) {
        if (child[i].value == "") {
            $errorMsg.classList.add("login-valid");
            // 시간 지연
            setTimeout(function () {
                $errorMsg.classList.remove("login-valid");
            }, 2000);
            $errText.innerHTML = `${child[i].placeholder} 을(를) 입력해 주세요`;
            break;
        } else {
            $errText.innerHTML = ``;
            count++;
        }
    }

    if (count == 2) {
        $findFormArr[index].submit();
    }
};

// 서버 validation 보여주기
$(document).ready(function() {

    console.log($errText.innerText);

    if($errText.innerText) {
        $errorMsg.classList.add("login-valid");
        setTimeout(function () {
            $errorMsg.classList.remove("login-valid");
        }, 2000);
    }
})