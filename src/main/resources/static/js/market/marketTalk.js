$(document).ready(function() {
//    // 진짜 js 알다가도 모르겠다
//    // opener 라는 부모 정보를 불러오는 친구가 있네 ?
//    const marketInform = opener.document.getElementById("market-inform").value;
//
//    // 정보 가공
//    const informItem = marketInform.split("/");
//
//    $("#recipientId").val(informItem[0]);
//    $("#writer-pic").attr("src", `/img/${informItem[1]}`);
//    $("#writer-nickname").text(informItem[2]);

    // 아래부터 보기
    $("#market-chat-space").scrollTop($("#market-chat-space")[0].scrollHeight);
})

$("#submit-btn").on("click", function() {
    $("#market-chat-form").submit();
    $("#submit-content").val("");
})
