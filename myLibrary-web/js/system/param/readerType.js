$(document).ready(function () {
    editReaderType = function (id) {
        ajax.get(api.url.ruleOption, null, function (data) {
            if (data.code != api.code.OK)
                return;

            $('#rc').combobox({
                valueField: 'id',
                textField: 'text',
                data: data.data
            });
            ajax.get(api.url.readerType, {readerTypeId: id}, function (data) {
                if (data.code != api.code.OK)
                    return;
                $('#ff').form('load', data.data);
                $('#dd').dialog({closed: false});
            }, 'json');
        }, 'json');
    };

    deleteReaderType = function (id) {
        ajax.get(api.url.deleteReaderType, {readerTypeId: id}, function (data) {
            if (data.code != api.code.OK)
                return;

            $('#dg').datagrid('load');
        }, 'json')
    };

    function rowOperation(id, rowData, rowIndex) {
        return '<a href="#"  onclick="editReaderType(' + id + ')">修改</a>&nbsp&nbsp<a href="#"  onclick="deleteReaderType(' + id + ')">删除</a>';
    }

    $('#dg').datagrid({
        url: api.url.listReaderType,
        onBeforeLoad: function (param) {
            param.readerTypeName = $('#readerTypeName').val();
            param.status = $('input[name="status"]:checked').val();
            return true;
        },
        loadFilter: dataGirdApiAdapter,
        columns: [[
            {field: 'readerTypeName', title: '类型名称', width: 100},
            {field: 'ruleName', title: '借阅规则', width: 100},
            {field: 'status', title: '状态', width: 100},
            {field: 'comment', title: '备注', width: 100},
            {field: 'readerTypeId', title: '操作', align: 'center', width: 100, formatter: rowOperation}
        ]]
    });

    $('#dd').dialog({
        title: '添加读者类型',
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

                ajax.post(api.url.saveReaderType, jsonStr, function (data) {
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
        }],
        onClose: function () {
            $('#ff').form('reset');
            $('#dd').dialog({closed: true});
        }
    });

    $('#query-btn').bind('click', function () {
        $('#dg').datagrid('load')
    });

    $('#add-type-btn').bind('click', function () {
        ajax.get(api.url.ruleOption, null, function (data) {
            if (data.code != api.code.OK)
                return;

            $('#rc').combobox({
                valueField: 'id',
                textField: 'text',
                data: data.data
            })
        }, 'json');
        $('#dd').dialog({closed: false});
    });
});