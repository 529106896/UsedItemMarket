$(function()
{
    $('.loginBtn').click(function()
    {
        var login_sNum = $('input_username').val();
        var login_password = $('input_password').val();
        if(login_sNum===''||login_password==='')
        {
            alert('学号或密码不能为空');
            return;
        }
        $(this).submit();
    });
})