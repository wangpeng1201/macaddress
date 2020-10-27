/**
 * @param url      @pathvariable前面的url
 * @param location 跳转页面
 */
function delAjax(url,jumpLink) {
    let idTd = showDeleteModal();
    console.log("idTd:" + idTd);
    $.ajax({
        url: url + idTd,
        type: "post",
        method: "post",
        success: function (result) {
            $('#myModalDel').modal('hide');  //手动关闭
            window.location.href = jumpLink;
        },
        error: function () {
            $('#myModalDel').modal('hide');  //手动关闭
            ShowFailure("请求失败");
        }
    });
}
