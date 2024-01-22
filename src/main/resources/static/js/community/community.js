// 스크롤 항상 아래로
$(document).ready(function () {
    $("#chatting-wrap").scrollTop($("#chatting-wrap")[0].scrollHeight);
});

$("#chatting-form > input:last-child").click(function(){
    if($("#chatting-form > input:first-child").val() != "") {
        $("#chatting-form").submit();
    }
})