// 定义一个JavaScript函数，在页面加载完成后自动启动
window.onload = function() {
    // 获取输出区域的HTML元素
    let outputArea = document.getElementById("output");

    // 定义一个字符串变量，表示要输出的内容
    let message = "我已再此恭候您许久，接下来让我们开始大干一场吧！";

    // 定义一个计数器，表示当前应该输出message中的第几个字符
    let count = 0;

    let btn = document.getElementById("continue");

    // 定义一个定时器，每隔1秒钟输出message中的下一个字符，直到全部输出为止
    let timerId = setInterval(function() {
        // 如果已经输出完毕，则清除定时器
        if(count >= message.length) {
            btn.style.display="block";
            btn.style.margin="0 auto";
            clearInterval(timerId);
            return;
        }
        // 否则，获取输出字符并将其添加到输出区域的HTML元素中
        let ch = message.charAt(count);
        outputArea.innerHTML += ch;

        // 增加计数器
        count++;
    }, 100);
};
//continue跳转
document.getElementById("continue").onclick = function (){
    location.href = "/user/login_page";
    return false;
}