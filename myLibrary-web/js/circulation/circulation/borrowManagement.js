$(document).ready(function () {
    returnBook = function (id) {
        ajax.get(api.url.return, {collectionId: id}, function (data) {
            if (data.code != api.code.OK)
                return;

            $('#dg').datagrid('load');
        }, 'json', false);
    };

    renewBook = function (id) {
        ajax.get(api.url.renew, {collectionId: id}, function (data) {
            if (data.code != api.code.OK)
                return;

            $('#dg').datagrid('load');
        }, 'json', false)
    };

    function rowOperation(id, rowData, rowIndex) {
        return '<a href="#"  onclick="returnBook(' + id + ',)">归还</a>&nbsp&nbsp<a href="#"  onclick="renewBook(' + id + ')">续借</a>';
    }

    $('#dg').datagrid({
        url: api.url.borrowRecords,
        onBeforeLoad: function (param) {
            var id = $('#readerId').textbox('getValue');
            if (!util.string.isEmpty(id)) {
                param.readerId = id;

                return true;
            } else {
                return false;
            }
        },
        loadFilter: dataGirdApiAdapter,
        columns: [[
            {field: 'title', title: '题名', width: 100},
            {field: 'callNumber', title: '索书号', width: 100},
            {field: 'locationName', title: '馆藏地点', width: 100},
            {field: 'borrowDate', title: '借出日期', width: 100},
            {field: 'returnDate', title: '应还日期', width: 100},
            {field: 'collectionId', title: '操作', align: 'center', width: 100, formatter: rowOperation}
        ]]
    });

    $('#card').textbox('textbox').bind('keydown', function (e) {
        if (e.keyCode == 13) {
            var param = $('#card').textbox('getValue');
            if (util.string.isEmpty(param)) {
                $.messager.alert('提示', '请输入读者证');
                return;
            }

            ajax.get(api.url.readerInfo, {card: param}, function (data) {
                if (data.code != api.code.OK)
                    return;

                $('#ff').form('load', data.data);
            }, 'json', false);
            $('#dg').datagrid('load');
        }
    });

    $('#barcode').textbox('textbox').bind('keydown', function (e) {
        if (e.keyCode == 13) {
            var param = $('#barcode').textbox('getValue');
            if (util.string.isEmpty(param)) {
                $.messager.alert('提示', '请输入条形码');
                return;
            }

            ajax.get(api.url.collectionInfo, {barcode: param}, function (data) {
                if (data.code != api.code.OK)
                    return;

                $('#ff').form('load', data.data);

                var readerId = $('#readerId').textbox('getValue');
                var collectionId = $('#collectionId').textbox('getValue');

                if (util.string.isEmpty(readerId)
                    || util.string.isEmpty(collectionId))
                    return;

                $.messager.confirm('提示', '是否进行图书借阅?', function (r) {
                    if (r) {
                        ajax.get(api.url.borrow, {readerId: readerId, collectionId: collectionId}, function (data) {
                            if (data.code != api.code.OK)
                                return;

                            $.messager.show({
                                title: '提示',
                                msg: '借阅成功',
                                timeout: 5000,
                                showType: 'slide'
                            });
                        }, 'json', false);
                    }
                })
            }, 'json', false)
        }
    });
});