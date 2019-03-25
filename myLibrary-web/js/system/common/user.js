$(document).ready(function () {
    editBiblio = function (id) {
        ajax.get(api.url.user, {userId: id}, function (data) {
            if (data.code != api.code.OK)
                return;

            $('#ff').form('load', data.data);
            $('#dd').dialog({closed: false});
        }, 'json');
    };

    deleteBiblio = function (id) {
        ajax.get(api.url.deleteUser, {userId: id}, function (data) {
            if (data.code != api.code.OK)
                return;

            $('#dg').datagrid('load');
        }, 'json')
    };

    function rowOperation(id, rowData, rowIndex) {
        return '<a href="#"  onclick="editBiblio(' + id + ')">修改</a>&nbsp&nbsp<a href="#"  onclick="deleteBiblio(' + id + ')">删除</a>';
    }

    $('#dg').datagrid({
        url: api.url.listUser,
        onBeforeLoad: function (param) {
            param.account = $('#account').val();
            param.status = $('input[name="status"]:checked').val();
            return true;
        },
        loadFilter: dataGirdApiAdapter,
        columns: [[
            {field: 'account', title: '账号', width: 100},
            {field: 'name', title: '姓名', width: 100},
            {field: 'email', title: 'email', width: 100},
            {field: 'status', title: '状态', width: 100},
            {field: 'id', title: '操作', align: 'center', width: 100, formatter: rowOperation}
        ]]
    });

    $('#dd').dialog({
        title: '添加用户',
        iconCls: 'icon-save',
        modal: true,
        closed: true,
        resizable: false,
        width: 400,
        height: 200,
        buttons: [{
            text: '保存',
            handler: function () {
                var jsonStr = util.form.serializeJosnString($('#ff'));
                console.log(jsonStr);

                ajax.post(api.url.saveUser, jsonStr, function (data) {
                    if (data.code != api.code.OK)
                        return;

                    $('#ff').form('reset');
                    $('#dd').dialog({closed: true});
                    $('#dg').datagrid('load');
                }, 'json');
            }
        }, {
            text: '取消',
            handler: function () {
                $('#ff').form('reset');
                $('#dd').dialog({closed: true});
            }
        }]
    });

    $('#query-btn').bind('click', function () {
        $('#dg').datagrid('load')
    });

    $('#add-user-btn').bind('click', function () {
        $('#dd').dialog({closed: false});
    });
});