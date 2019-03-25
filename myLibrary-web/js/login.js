$(document).ready(function () {
    $('.login-btn').bind('click', function () {

            var isValid = $('#ff').form("validate");
            if (!isValid) {
                $.messager.alert('提示', '请输入账户名和密码');
                return;
            }

            var account = $('#account').val();
            var password = $('#password').val();

            ajax.get(api.url.login,
                {account: account, password: password},
                function (data) {
                    if (data.code != api.code.OK)
                        return;
                    window.location.href = '/index.html';
                });
        }
    );
})