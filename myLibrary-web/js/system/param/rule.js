$(document).ready(function () {
    editRule = function (id) {
        ajax.get(api.url.rule, {ruleId: id}, function (data) {
            if (data.code != api.code.OK)
                return;

            $('#ff').form('load', data.data);
            $('#dd').dialog({closed: false});
        }, 'json', false);
    };

    deleteRule = function (id) {
        ajax.get(api.url.deleteRule, {ruleId: id}, function (data) {
            if (data.code != api.code.OK)
                return;

            $('#dg').datagrid('load');
        }, 'json', false)
    };

    function rowOperation(id, rowData, rowIndex) {
        return '<a href="#"  onclick="editRule(' + id + ')">修改</a>&nbsp&nbsp<a href="#"  onclick="deleteRule(' + id + ')">删除</a>';
    }

    $('#dg').datagrid({
        url: api.url.listRule,
        onBeforeLoad: function (param) {
            param.name = $('#name').val();
            param.status = $('input[name="status"]:checked').val();
            return true;
        },
        loadFilter: dataGirdApiAdapter,
        columns: [[
            {field: 'name', title: '规则名称', align: 'center', width: 100},
            {field: 'borrowNumber', title: '借阅册数', align: 'center', width: 100},
            {field: 'borrowDays', title: '借阅天数', align: 'center', width: 100},
            {field: 'renewalTimes', title: '续借次数', align: 'center', width: 100},
            {field: 'renewalDays', title: '续借天数', align: 'center', width: 100},
            {field: 'statusName', title: '状态', align: 'center', width: 100},
            {field: 'id', title: '操作', align: 'center', width: 100, formatter: rowOperation}
        ]]
    });

    $('#dd').dialog({
        title: '添加流通规则',
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

                ajax.post(api.url.saveRule, jsonStr, function (data) {
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