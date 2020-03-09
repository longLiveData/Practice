一、前端移动web开发

移动端和电脑端html显示有差异，使用框架将大大减少开发代码量

本项目需要的逻辑简单，页面不多，使用jquery mobile框架

```html
<!DOCTYPE html>
<html>
<head>
<!-- 设置页面宽度为设置宽度 不可缩放-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 引用框架文件 -->
<link rel="stylesheet" href="https://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="https://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

<!-- css设置元素样式 -->
<style>
    textarea.ui-input-text { min-height: 80px; text-align: center; }
</style>

<!-- js中设置元素属性或样式 -->
<script>
    function add(){
        $('#startbtn').parent("div").css('display','none'); //隐藏
        $('#termInput').parent("div").css('display',''); //显示
        document.getElementById("showRes").value = showStr;
    }
</script>

</head>
<body>
<!-- data-role有page，header，content，footer -->
<!-- 可以使用框架预置的class中的ui -->
<div data-role="page" id="pageone">
    <div data-role="header">
        <h1>党课小测试</h1>
    </div>
    
    <div data-role="content" class="ui-content">
        <div>
            <div id="inst">
                <p>三、正式测试</p> 
                <p>下面为正式测试。</p>
                <p>请判断以下出现的词条是否为描述“中国共产党或其相关政策”。若是点"YES"，否则点"NO"。</p>
                <p>准备好了点"开始测试"开始.</p> 
            </div>
        </div>
        
        <input id="startbtn" type="button" value="开始测试" onclick="showNext()">    
        <input id="termInput" type="text" style="font-size: 23px;text-align: center" readonly><br>
        <input id="yes" type="button" value="YES" style="background-color:green;font-size: 20px;" onclick="clickYes()"><br>
        <input id="no" type="button" value="NO" style="background-color:red;font-size: 20px;" onclick="clickNo()">

        <input id="resbtn" type="button" onclick=goToResult(); value="查看结果">
        <div>
            <textarea id="showRes" readonly="readonly" ></textarea>
        </div>
    </div>
</div> 
</body>
</html>

```

