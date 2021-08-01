<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>taobao首页</title>
    <script src="/js/jquery/jquery-2.2.1.min.js"></script>
    <script>
        function createOrder(){
            $.ajax({url:"/order/create",success:function(result){
                    $("#div1").html(result);
                }});
        }

        function createOrderGet(){
            $.get("/order/create", function(result){
            });
        }
    </script>
</head>
<body style="height: 100%; width: 100%; overflow: hidden;background-color:white;">
<button onclick="createOrder()">下单</button>
<a href="/order/create">下单2</a>
${createOrderResult!''}
</body>
</html>

