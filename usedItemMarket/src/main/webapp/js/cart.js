$(function ()
{
    $('.remove-from-cart').click(function ()
    {
        var r = confirm("确定移除此商品？");
        if(r === true)
        {
            var id = $(this).attr("value");
            window.location.href = "/removeFromCart.do?GID=" + id;
        }

    })


    $('.add').click(function (){
        var goodsNum = $(this).siblings('.goods-max-num').attr("value");
        var buyNum = $(this).siblings('.goods-detail').html();

        var max = parseInt(goodsNum);
        var cur = parseInt(buyNum);

        cur++;

        if(cur >= max)
            cur = max;

        $(this).siblings('.goods-detail').html(cur);
    });

    $('.del').click(function (){
        var buyNum = $(this).siblings('.goods-detail').html();

        var min = parseInt("1");
        var cur = parseInt(buyNum);

        // console.log(min);
        // console.log(cur);

        cur--;

        if(min >= cur)
            cur = min;

        $(this).siblings('.goods-detail').html(cur);
    });

    $('.buy-directly').click(function ()
    {
        var r = confirm("确定购买此商品？");

        if(r === true)
        {
            var buynum = $(this).parent().siblings('.goods-info').children("div").children('.goods-detail').html();
            //var uid = $("#userID").attr("value");
            var gid = $(this).attr("value");
            window.location.href="/buyFromCart.do?GID="+gid+"&Number="+buynum;
            // console.log(buynum);
            // console.log(uid);
            // console.log(gid);

        }
    })


})