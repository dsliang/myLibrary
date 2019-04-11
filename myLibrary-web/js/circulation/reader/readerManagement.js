$(document).ready(function () {
    editReader = function (id) {
        ajax.get(api.url.readerTypeOption, null, function (data) {
            if (data.code != api.code.OK)
                return;

            $('#rt').combobox({
                valueField: 'id',
                textField: 'text',
                data: data.data
            })

            ajax.get(api.url.reader, {readerId: id}, function (data) {
                if (data.code != api.code.OK)
                    return;
                $('#ff').form('load', data.data);
                $('#dd').dialog({closed: false});
            }, 'json', false);
        }, 'json', false);
    };

    deleteReader = function (id) {
        ajax.get(api.url.deleteReader, {readerId: id}, function (data) {
            if (data.code != api.code.OK)
                return;

            $('#dg').datagrid('load');
        }, 'json', false)
    };

    function rowOperation(id, rowData, rowIndex) {
        return '<a href="#"  onclick="editReader(' + id + ')">修改</a>&nbsp&nbsp<a href="#"  onclick="deleteReader(' + id + ')">删除</a>';
    }

    $('#dg').datagrid({
        url: api.url.listReader,
        onBeforeLoad: function (param) {
            param.readerName = $('#readerName').val();
            param.readerCard = $('#readerCard').val();
            param.status = $('input[name="status"]:checked').val();
            return true;
        },
        loadFilter: dataGirdApiAdapter,
        columns: [[
            {field: 'readerCard', title: '读者证', align: 'center', width: 100},
            {field: 'readerName', title: '姓名', align: 'center', width: 100},
            {field: 'genderName', title: '性别', align: 'center', width: 100},
            {field: 'readerTypeName', title: '读者类型', align: 'center', width: 100},
            {field: 'statusName', title: '状态', align: 'center', width: 100},
            {field: 'readerId', title: '操作', align: 'center', width: 100, formatter: rowOperation}
        ]]
    });

    $('#dd').dialog({
        title: '添加读者',
        iconCls: 'icon-save',
        modal: true,
        closed: true,
        resizable: false,
        width: 400,
        height: 235,
        buttons: [{
            text: '保存',
            handler: function () {
                var b = $('#ff').form('enableValidation').form('validate');
                if (!b)
                    return;

                $('#ff').form('disableValidation');

                var jsonStr = util.form.serializeJosnString($('#ff'));
                console.log(jsonStr);

                ajax.post(api.url.saveReader, jsonStr, function (data) {
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

    $('#add-reader-btn').bind('click', function () {
        ajax.get(api.url.readerTypeOption, null, function (data) {
            if (data.code != api.code.OK)
                return;

            $('#rt').combobox({
                valueField: 'id',
                textField: 'text',
                data: data.data
            })
        }, 'json', false);
        $('#dd').dialog({closed: false});
    });
});