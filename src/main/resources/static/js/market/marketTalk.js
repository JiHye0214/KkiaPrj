$(document).ready(function() {
    // 아래부터 보기
    $("#market-chat-space").scrollTop($("#market-chat-space")[0].scrollHeight);
})

$("#submit-btn").on("click", function() {
    $("#market-chat-form").submit();
    $("#submit-content").val("");
    opener.location.reload();
})
