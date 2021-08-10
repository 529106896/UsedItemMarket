function validate1()
{
    var password = document.getElementById("password").value;
    var rePassword = document.getElementById("rePassword").value;
    var oPwd = document.getElementById("originPwd").value;
    console.log(oPwd);
    if(password === oPwd)
    {
        document.getElementById("passwordError").innerHTML="不可与初始密码一致！";
        document.getElementById("submit").disabled = true;
    }
    else
    {
        if (6 - password.length > 0)
        {
            document.getElementById("passwordError").innerHTML="密码长度过短，应为6~18位";
            document.getElementById("submit").disabled = true;
        }
        else if (password.length > 18)
        {
            document.getElementById("passwordError").innerHTML="密码长度过长，应为6~18位";
            document.getElementById("submit").disabled = true;
        }
        else {
            document.getElementById("passwordError").innerHTML="";
            document.getElementById("submit").disabled = false;
        }
    }
}

function validate2()
{
    var password = document.getElementById("password").value;
    var rePassword = document.getElementById("rePassword").value;
    var oPwd = document.getElementById("originPwd").value;
    console.log(oPwd);
    if (password !== rePassword)
    {
        document.getElementById("passwordError").innerHTML="两次输入密码不一致";
        document.getElementById("submit").disabled = true;
    }
    else
    {
        if (6 - password.length > 0)
        {
            document.getElementById("passwordError").innerHTML="密码长度过短，应为6~18位";
            document.getElementById("submit").disabled = true;
        }
        else if (password.length > 18)
        {
            document.getElementById("passwordError").innerHTML="密码长度过长，应为6~18位";
            document.getElementById("submit").disabled = true;
        }
        else {
            document.getElementById("passwordError").innerHTML="";
            document.getElementById("submit").disabled = false;
        }
    }
}