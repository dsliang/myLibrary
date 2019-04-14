$(document).ready(function () {
    $('.login-btn').bind('click', function () {
            var account = $('#account').val();
            var password = $('#password').val();

            if (util.string.isEmpty(account)
                || util.string.isEmpty(password)) {
                $.messager.alert('提示', '请输入账户名和密码');
                return;
            }

            ajax.get(api.url.login,
                {account: account, password: password},
                function (data) {
                    if (data.code != api.code.OK)
                        return;
                    window.location.href = '/index.html';
                }, 'json', false);
        }
    );
})