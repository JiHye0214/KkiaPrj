const $findMenuArr = document.querySelectorAll(".find-menu-item");
const $findFormArr = document.querySelectorAll("#find-wrapper > .find-content");
const $findBtn = document.querySelectorAll(".find-btn");
const $errorMsg = document.querySelector(".error-msg");
const $errText = document.querySelector(".error-msg-text");

// find menu
$findMenuArr.forEach((menu, index) => {
    // 색깔 칠하기
    if($("#what-nav").val() == menu.value) {
        menu.style.background = "rgb(222,222,222)";
    }
});

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
    child.push($findFormArr[index].children[0]);
    child.push($findFormArr[index].children[1]);

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
    if($errText.innerText) {
        $errorMsg.classList.add("login-valid");
        setTimeout(function () {
            $errorMsg.classList.remove("login-valid");
        }, 2000);
    }
})

// 비밀번호 변경
const $pwInputArr = document.querySelectorAll(".password > input");

$("#submit-btn").on("click", function() {

    let stop = false;
    let pwCheck1 = false;
    let pwCheck2 = false;
    let count = 0;

    $pwInputArr.forEach((input) => {
        if(!stop) {
            if(input.value == "") {
                $("#result-err").html(`${input.placeholder} 을(를) 입력해 주세요`);
                stop = true;
            } else {
                $("#result-err").html(``);
                count++;
            }
        }
    })

    if(count == 3) {
        if($pwInputArr[1].value.length < 6) {
            $("#result-err").html("비밀번호는 6자 이상으로 입력해 주세요");
        } else {
            $("#result-err").html(``);
            pwCheck1 = true;
        }
    }

    if(pwCheck1) {
        if($pwInputArr[1].value != $pwInputArr[2].value) {
            $("#result-err").html("신규 비밀번호와 비밀번호 확인이 다릅니다");
        } else {
            $("#result-err").html(``);
            pwCheck2 = true;
        }
    }

    if(count == 3 && pwCheck1 && pwCheck2) {
         $("#find-change-pw").submit();
    }
})