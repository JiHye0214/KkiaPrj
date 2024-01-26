 $(".file-delete-btn").click(function(){
     let fileId = $(this).attr('fileNumber');
     deleteFiles(fileId);
     $(this).parent().remove();
 });

 function deleteFiles(fileId){
     $("#delFiles").append(`<input type='hidden' name='delfile' value='${fileId}'>`);
 }