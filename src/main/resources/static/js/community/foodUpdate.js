// 원래 있던 항목도 x 버튼 누르면 삭제
let itemWraps = document.querySelectorAll(".item-wrap");
let itemDeletBtns = document.querySelectorAll(".item-delete-btn");

itemDeletBtns.forEach((btn, i) => {
    btn.onclick = () => {
        itemWraps[i].remove();
    };
});
