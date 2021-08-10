$(function(){
    $("#next_page").click(function ()
    {
        var pages = parseInt($(this).attr("value"));
        var sortKind = $("#sortByPriceASC").attr("value");
        var keywords = $(".searchIn").attr("value");
        pages += 12;
        window.location.href = "/changePage.do?pages="+pages.toString()+"&sortKind=" + sortKind + "&keywords="+keywords;
    });

    $("#last_page").click(function ()
    {
        var keywords = $(".searchIn").attr("value");
        var pages = parseInt($(this).attr("value"));
        var sortKind = $("#sortByPriceASC").attr("value");
        pages -= 12;
        window.location.href = "/changePage.do?pages="+pages.toString()+"&sortKind=" + sortKind + "&keywords="+keywords;
    });

    $("#sortByPriceASC").click(function ()
    {
        var keywords = $(".searchIn").attr("value");
        var sortKind = $(this).attr("value");
        window.location.href = "/changePage.do?pages=0&sortKind=0&keywords=" + keywords;
    });

    $("#sortByPriceDESC").click(function ()
    {
        var keywords = $(".searchIn").attr("value");
        window.location.href = "/changePage.do?pages=0&sortKind=1&keywords=" + keywords;
    });

    $("#sortByNumberASC").click(function ()
    {
        var keywords = $(".searchIn").attr("value");
        window.location.href = "/changePage.do?pages=0&sortKind=2&keywords=" + keywords;
    });

    $("#sortByNumberDESC").click(function ()
    {
        var keywords = $(".searchIn").attr("value");
        window.location.href = "/changePage.do?pages=0&sortKind=3&keywords=" + keywords;
    });
});