// 최애 글 삭제
const deleteBtn = document.querySelector("#delete-btn");

if (deleteBtn) {
    deleteBtn.onclick = () => {
        if (confirm("글을 삭제하시겠습니까?")) {
            document.forms["delete-form"].submit();
        }
    }
}
