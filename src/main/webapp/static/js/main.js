//获取用户名
$(function() {
    let displayname = $('#username');
    // 使用 jQuery 发送 GET 请求
    $.get('/user/get_name', function(username) {
        if (username === '') {
            window.location.href = "/user/login_page";
        } else {
            displayname.html("您好！尊敬的用户 "+username);
        }
    });
});
// 发起Ajax请求获取表格数据
$.ajax({
    url: '/course/main_solve',
    method: 'GET',
    success: function(result) {
        // 获取要填充数据的table元素
        const tbody = $('.table tbody');
        $.each(result.data, function(index, item) {
            // 请求成功，遍历数据并生成表格行
            const tr = $('<tr>');
            tr.html(`
        <td>${index + 1}</td>
        <td><img src="/images/${item.image}" class="course-img"></td>
        <td>${item.name}</td>
        <td>${item.hours}</td>
        <td>${item.schools}</td>
        <td><a  class="update" onclick="updateCourse(${item.id})">编辑</a>  
        <a class="delete" onclick="deleteCourse(${item.id})">删除</a></td>
      ` );
            tbody.append(tr);
        });
    },
    error: function() {
        // 请求失败
        console.error('请求失败');
    }
});
//新增键处理
document.getElementById("btn1").onclick = function (){
    location.href = "/course/add_page";
}
//退出键处理
document.getElementById("btn2").onclick = function (){
    let confirmed = confirm("确定要退出吗？");
    if (confirmed) {
        location.href = "/user/exit_page";
    }
}

//删除键处理
function deleteCourse(courseId){
    let answer = confirm("你确定要删除此课程吗?——————三思而后行！！！");
    if (answer) {
        $.ajax({
            method: "POST",
            url: "/course/delete_solve",
            data: {id: courseId},
            success: function(){
                alert("删除成功");
                // 删除成功后刷新页面
                window.location.reload();
            },
            error: function(){
                alert("删除失败，请稍后再试");
            }
        });
    }
}
//修改键处理
function updateCourse(Id){
    // 跳转到编辑页面加上随机参数彻底解决浏览器缓存问题，不用手动刷新
    window.location.href = `/course/update_page?id=${Id}&${Date.now()}`;
}