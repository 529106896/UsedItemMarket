$(function (){
    $('.searchBtn').click(function (){
        var Keyword = $('.searchIn')[0].value;
        //if (Keyword != null)
        window.location.href='/goToSearch.do?Keywords='+Keyword+"&pages=0";
        window.event.returnValue=false;
        //console.log(window.location);
    });
})