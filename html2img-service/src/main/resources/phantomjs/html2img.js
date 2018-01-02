/**
 * Created by lenovo on 2018/1/2.
 */
console.log("开始截图...");
var tt = new Date().getTime();
var page = require('webpage').create(),
    system = require('system'),
    url = system.args[1],
    outFilePath = system.args[2];

url = "http://www.oschina.net/";
outFilePath = "/opt/html2img.jpg";

page.viewportSize = {width: 1349, height: 728};
page.open(url, function (status) {
    var bodyRect = page.evaluate(function () {
        return document.body.getBoundingClientRect();
    });
    console.log("宽：" + bodyRect.width + "，高：" + bodyRect.height);
    page.clipRect = {
        top: bodyRect.top,
        left: bodyRect.left,
        width: bodyRect.width,
        height: bodyRect.height
    };
    window.setTimeout(function () {
        var imgByte = page.renderBase64("png");
        page.close();
        console.log("截图完成！耗时：" + (new Date().getTime() - tt));
        console.log("==============");
        console.log(imgByte);
        phantom.exit();
    }, 10);
});