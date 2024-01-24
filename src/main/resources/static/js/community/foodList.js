// 지역 버튼 생성
const regionNames = ["고척", "광주", "대구", "대전", "부산", "수원", "인천", "잠실", "창원"];
const regionBtnWrap = document.querySelector("#region-btn-wrap");
const regionSubmitInput = document.querySelector("#submit-region");
regionNames.forEach((regionName) => {
    regionBtnWrap.innerHTML += `<div class="region">${regionName}</div>`;
});

const allBtn = document.querySelector(".all");
const regionBtns = document.querySelectorAll(".region");
const colorArr = ["#eb008a", "var(--kia-red)", "#0066b3", "#f37321", "#d00e31", "#e3e3e5", "#cf0a2c", "white", "#244c81"];

// 전체 버튼 클릭
if (regionSubmitInput.value == "") {
    allBtn.classList.add("active");
}

allBtn.onclick = () => {
    if (!allBtn.classList.contains("active")) {
        allBtn.classList.add("active");
        regionSubmitInput.value = "";
        $("[name='region-form']").submit();
    }
}

// 지역 버튼 마우스오버 & 클릭
regionBtns.forEach((region, idx) => {
    if (region.textContent == regionSubmitInput.value) {
        region.classList.add("active");
    }

    region.onmouseover = () => {
        region.style.background = `${colorArr[idx]}`;
    }

    region.onmouseout = () => {
        region.style.background = ``;
    }

    region.onclick = () => {
        if (!region.classList.contains("active")) {
            regionBtns.forEach((el) => {
                el.classList.remove("active");
            })
            region.classList.add("active");

            regionSubmitInput.value = region.textContent;

            $("[name='region-form']").submit();
        }
    }
})

// 각 항목의 지역 태그 색
const regionTags = document.querySelectorAll(".region-tag");
regionTags.forEach((tag) => {
    regionNames.forEach((regionName, i) => {
        if (tag.textContent == regionName) {
            tag.style.backgroundColor = colorArr[i];
        }
    })
})
