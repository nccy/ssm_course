//表单提交
$(function () {
    $("#signup-form").submit(function (event) {
        // 阻止默认的表单提交事件
        event.preventDefault();

        // 获取表单数据
        var formData = new FormData(this);

        // 使用 AJAX 发送登录请求
        $.ajax({
            url: "/course/add_solve", // 替换为实际的后端登录接口地址
            method: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function(result){
                if (result.msg==="success") {
                    let prompt = confirm("添加成功,是否继续添加课程————(..•˘_˘•..)");
                    if(prompt===false)
                    {
                        location.href = "/course/main_page";
                        return false;
                    }
                } else if(result.msg==="imagefail"){
                    alert("添加失败———你上传的图片文件有误！请重新上传正确的图片文件后再次重试————o(╥﹏╥)");
                }else{
                    alert("添加失败———你输入的课程名已经存在！请修改课程名称后再次重试————o(╥﹏╥)");
                }
            },
            //请求失败，包含具体的错误信息
            error : function(e){
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    });
});
//下拉框处理
$(function(){
    // 获取下拉列表元素
    var select = $("#college");
    // 发送AJAX请求获取学院列表
    $.ajax({
        url: "/school/get_option",
        type: "GET",
        dataType: "json",
        success: function(result) {
            $.each(result.data, function(index, item) {
                $("#college").append("<option value='" + item.id + "'>" + item.schoolName + "</option>");
            });
        }
    });
});
//退出添加页面
document.getElementById("btn-exit").onclick = function (){
    location.href = "/course/main_page";
    return false;
}
$('#course-image').on('change', function() {
    var file = this.files[0];
    var reader = new FileReader();
    reader.onload = function(e) {
        $('#image-preview').html('<img src="' + e.target.result + '">');
    }
    reader.readAsDataURL(file);
});