let itemWraps = document.querySelectorAll(".item-wrap");
let itemDeletBtns = document.querySelectorAll(".item-delete-btn");

itemDeletBtns.forEach((btn, i) => {
    btn.onclick = () => {
        itemWraps[i].remove();
    };
});
