$("#market-delete-btn").on("click", function(){
    let ask = confirm("삭제하시겠습니까?");

    if(ask) {
        $("#market-delete-form").submit();
    }
})