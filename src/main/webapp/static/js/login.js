//表单提交
$(function () {
    $("#login-form").submit(function (event) {
        // 阻止默认的表单提交事件
        event.preventDefault();

        // 获取用户名和密码
        var email = $("#email").val();
        var password = $("#password").val();

        // 使用 AJAX 发送登录请求
        $.ajax({
            url: "/user/login_solve", // 替换为实际的后端登录接口地址
            method: "POST",
            dataType:'json',//表示返回的数据必须为json，否则：会走下面error对应的方法。
            contentType:'application/json;charset=utf-8',//如果要发送json字符串，此属性不能少；否则，会出现后台会报异常
            data:JSON.stringify({
                email: email, passWord: password
            }),//将json对象转换为json字符串
            success: function(result){
                if (result.msg==="success") {
                    alert("登录成功————欢迎回来！");
                    location.href = "/course/main_page";
                    return false;
                } else {
                    alert("登录失败: 密码或者邮箱错误");
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