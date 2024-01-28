$("#post-delete-btn").on("click", function(){
    if(confirm("삭제하시겠습니까?")) {
        $("#post-delete-form").submit();
    }
})