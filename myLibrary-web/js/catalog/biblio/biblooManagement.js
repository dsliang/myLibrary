var _row;

$(document).ready(function () {

    editBiblio = function (id) {
        ajax.get(api.url.biblio, {biblioId: id}, function (data) {
            if (data.code != api.code.OK)
                return;
            $('#ff-biblio').form('load', data.data);
            $('#dd-biblio').dialog({closed: false});
        }, 'json', false);
    };

    deleteBiblio = function (id) {
        ajax.get(api.url.deleteBiblio, {biblioId: id}, function (data) {
            if (data.code != api.code.OK)
                return;

            $('#dg-biblio').datagrid('load');
        }, 'json', false)
    };

    function biblioRowOperation(id, rowData, rowIndex) {
        return '<a href="#"  onclick="editBiblio(' + id + ')">修改</a>&nbsp&nbsp<a href="#"  onclick="deleteBiblio(' + id + ')">删除</a>';
    }

    $('#dg-biblio').datagrid({
        url: api.url.listBiblio,
        onBeforeLoad: function (param) {
            var p = $('#titleOrIsbn').val();
            if (!util.string.isEmpty(p)) {
                param.titleOrIsbn = p;
            }

            return true;
        },
        onClickRow: function (index, row) {
            _row = row;
            $('#biblioId').val(row.id);
            $('#dg-collection').datagrid('load');
        },
        loadFilter: dataGirdApiAdapter,
        columns: [[
            {field: 'isbn', title: 'ISBN', width: 100},
            {field: 'title', title: '提名', width: 100},
            {field: 'author', title: '著者', width: 100},
            {field: 'categoryNumber', title: '分类号', width: 100},
            {field: 'press', title: '出版社', width: 100},
            {field: 'price', title: '价格', width: 100},
            {field: 'id', title: '操作', align: 'center', width: 100, formatter: biblioRowOperation}
        ]]
    });

    $('#dd-biblio').dialog({
        title: '添加书目',
        iconCls: 'icon-save',
        modal: true,
        closed: true,
        resizable: false,
        width: 400,
        height: 320,
        buttons: [{
            text: '保存',
            handler: function () {
                var jsonStr = util.form.serializeJosnString($('#ff-biblio'));
                console.log(jsonStr);

                ajax.post(api.url.saveBiblio, jsonStr, function (data) {
                    var b = $('#ff-biblio').form('enableValidation').form('validate');
                    if (!b)
                        return;

                    $('#ff-biblio').form('disableValidation');

                    if (data.code != api.code.OK)
                        return;

                    $('#ff-biblio').form('reset');
                    $('#dd-biblio').dialog({closed: true});
                    $('#dg-biblio').datagrid('load');
                }, 'json', false);
            }
        }, {
            text: '取消',
            handler: function () {
                $('#ff-biblio').form('reset');
                $('#ff-biblio').form('disableValidation');
                $('#dd-biblio').dialog({closed: true});
            }
        }],
        onClose: function () {
            $('#ff-biblio').form('reset');
            $('#ff-biblio').form('disableValidation');
            $('#dd-biblio').dialog({closed: true});
        }
    });

    $('#query-btn').bind('click', function () {
        $('#dg-biblio').datagrid('load')
    });

    $('#add-biblio-btn').bind('click', function () {
        $('#dd-biblio').dialog({closed: false});
    });

    //初始化馆藏相关的控件
    editCollection = function (id) {
        ajax.get(api.url.collection, {collectionId: id}, function (data) {
            if (data.code != api.code.OK)
                return;
            $('#ff-collection').form('load', data.data);
            $('#dd-collection').dialog({closed: false});
        }, 'json', false);
    };

    deleteCollection = function (id) {
        ajax.get(api.url.deleteCollection, {collectionId: id}, function (data) {
            if (data.code != api.code.OK)
                return;

            $('#dg-collection').datagrid('load');
        }, 'json', false)
    };

    function CollectionRowOperation(id, rowData, rowIndex) {
        return '<a href="#"  onclick="editCollection(' + id + ')">修改</a>&nbsp&nbsp<a href="#"  onclick="deleteCollection(' + id + ')">删除</a>';
    }

    $('#dg-collection').datagrid({
        url: api.url.listCollection,
        onBeforeLoad: function (param) {
            var p = $('#biblioId').val()
            if (util.string.isEmpty(p))
                return false;

            param.biblioId = p;
            return true;
        },
        loadFilter: dataGirdApiAdapter,
        columns: [[
            {field: 'barcode', title: '条形码', width: 100},
            {field: 'callNumber', title: '索书号', width: 100},
            {field: 'locationName', title: '馆藏地址', width: 100},
            {field: 'statusName', title: '馆藏状态', width: 100},
            {field: 'collectionId', title: '操作', align: 'center', width: 100, formatter: CollectionRowOperation}
        ]]
    });

    $('#dd-collection').dialog({
        title: '添加书目',
        iconCls: 'icon-save',
        modal: true,
        closed: true,
        resizable: false,
        width: 600,
        height: 320,
        buttons: [{
            text: '保存',
            handler: function () {
                var b = $('#ff-collection').form('enableValidation').form('validate');
                if (!b)
                    return;

                $('#ff-collection').form('disableValidation');

                var jsonStr = util.form.serializeJosnString($('#ff-collection'));
                console.log(jsonStr);

                ajax.post(api.url.saveCollection, jsonStr, function (data) {
                    if (data.code != api.code.OK)
                        return;

                    $('#ff-collection').form('reset');
                    $('#dd-collection').dialog({closed: true});
                    $('#dg-collection').datagrid('load');
                }, 'json', false);
            }
        }, {
            text: '取消',
            handler: function () {
                $('#ff-collection').form('reset');
                $('#ff-collection').form('disableValidation');
                $('#dd-collection').dialog({closed: true});
            }
        }],
        onClose: function () {
            $('#ff-collection').form('reset');
            $('#ff-collection').form('disableValidation');
            $('#dd-collection').dialog({closed: true});
        }
    });

    $('#add-collection-btn').bind('click', function () {
        var biblioId = $('#biblioId').val()
        if (util.string.isEmpty(biblioId))
            return;

        ajax.get(api.url.locationOption, null, function (data) {

            if (data.code != api.code.OK)
                return;

            $('#cc').combobox({
                valueField: 'id',
                textField: 'text',
                data: data.data
            })
        }, 'json', false);
        var serialNumber = 0;
        for (var i = 0; i < _row.serialNumbers.length; i++) {
            serialNumber = Math.max(serialNumber, _row.serialNumbers[i]);
        }
        serialNumber = serialNumber > 0 ? serialNumber : "";
        $("#callNumber").textbox('setValue', _row.categoryNumber + "/" + serialNumber);
        $("#bibId").textbox('setValue', _row.id);
        $('#dd-collection').dialog({closed: false});
    });
});