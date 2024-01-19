// 첨부파일 
$(function () {
    var i = 0;
    $(".picture-btn").click(function () {
        $(".confirm-box-wrap").append(`
        <div class="confirm-box-item display-flex" style="margin-top: 10px">
            <label class="choice-file-btn display-flex">
                <div>파일 선택</div>
                <div class="confirm-txt"></div>
                <input type="file" name="productImg" accept="image/*" style="display: none"/>
            </label>
            <input class="file-delete-btn" type="button" value="삭제" />
        </div>`
        );
        i++;
        confirmBoxSetting();
    });

    // update 첨부파일 지우는 건
    // intelliJ 가서 하기 
    // $(".file-delete-btn").click(function(){
    //     let fileId = $(this).attr('data-fileid-del');
    //     deleteFiles(fileId);
    //     $(this).parent().remove();
    // });
});

// function deleteFiles(fileId){
//     $("#delFiles").append(`<input type='hidden' name='delfile' value='${fileId}'>`);
// }

const confirmBoxSetting = () => {

    // confirm text
    const $fileInputArr = document.querySelectorAll(".choice-file-btn > input");
    const $confirmTxt = document.querySelectorAll(".confirm-txt");

    $fileInputArr.forEach((input, index) => {
        input.onchange = () => {
            const splitPic = input.value.split("");
            const joinPic = splitPic.slice(12).join("");
            $confirmTxt[index].innerText = joinPic;
        }
    });

    // delete
    const $confirmBox = document.querySelectorAll(".confirm-box-item");
    const $deleteBtnArr = document.querySelectorAll(".file-delete-btn");
    $deleteBtnArr.forEach((btn, index) => {
        btn.onclick = () => {
            $confirmBox[index].remove()
        }
    })
}