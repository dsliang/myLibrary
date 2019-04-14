$(document).ready(function () {
    editLocation = function (id) {
        ajax.get(api.url.location, {locationId: id}, function (data) {
            if (data.code != api.code.OK)
                return;

            $('#ff').form('load', data.data);
            $('#dd').dialog({closed: false});
        }, 'json', false);
    };

    deleteLocation = function (id) {
        ajax.get(api.url.deleteLocation, {locationId: id}, function (data) {
            if (data.code != api.code.OK)
                return;

            $('#dg').datagrid('load');
        }, 'json', false)
    };

    function rowOperation(id, rowData, rowIndex) {
        return '<a href="#"  onclick="editLocation(' + id + ')">修改</a>&nbsp&nbsp<a href="#"  onclick="deleteLocation(' + id + ')">删除</a>';
    }

    $('#dg').datagrid({
        url: api.url.listLocation,
        onBeforeLoad: function (param) {
            param.name = $('#name').val();
            return true;
        },
        loadFilter: dataGirdApiAdapter,
        columns: [[
            {field: 'name', title: '馆藏名称', align: 'center', width: 100},
            {field: 'comment', title: '备注', align: 'center', width: 100},
            {field: 'id', title: '操作', align: 'center', width: 100, formatter: rowOperation}
        ]]
    });

    $('#dd').dialog({
        title: '添加馆藏地址',
        iconCls: 'icon-save',
        modal: true,
        closed: true,
        resizable: false,
        width: 400,
        height: 200,
        buttons: [{
            text: '保存',
            handler: function () {
                var b = $('#ff').form('enableValidation').form('validate');
                if (!b)
                    return;

                $('#ff').form('disableValidation');

                var jsonStr = util.form.serializeJosnString($('#ff'));
                console.log(jsonStr);

                ajax.post(api.url.saveLocation, jsonStr, function (data) {
                    if (data.code != api.code.OK)
                        return;

                    $('#ff').form('reset');
                    $('#dd').dialog({closed: true});
                    $('#dg').datagrid('load');
                }, 'json', false);
            }
        }, {
            text: '取消',
            handler: function () {
                $('#ff').form('reset');
                $('#ff').form('disableValidation');
                $('#dd').dialog({closed: true});
            }
        }],
        onClose: function () {
            $('#ff').form('reset');
            $('#ff').form('disableValidation');
            $('#dd').dialog({closed: true});
        }
    });

    $('#query-btn').bind('click', function () {
        $('#dg').datagrid('load')
    });

    $('#add-rule-btn').bind('click', function () {
        $('#dd').dialog({closed: false});
    });
});