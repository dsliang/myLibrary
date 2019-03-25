var ajax = (function () {
    function _completeHandler(XMLHttpRequest, textStatus) {
        var res = XMLHttpRequest.responseText;
        var data = JSON.parse(res);

        if (data.code == 0)
            return;
        if (data.code == api.code.UNAUTHORIZED) {
            alert("错误处理(2)" + data.msg);
            // window.location.replace("/login.html");
        } else {
            alert("错误处理(else)" + data.msg);
        }
    }

    function _get(url, data, success, dataType) {
        $.ajax({
            url: url,
            data: data,
            dataType: dataType,
            success: success,
            complete: _completeHandler
        });
    }

    function _post(url, data, success, dataType) {
        $.ajax({
            url: url,
            data: data,
            type: "POST",
            contentType: 'application/json;charset=utf-8',
            dataType: dataType,
            success: success,
            complete: _completeHandler
        });
    }

    return {
        get: _get,
        post: _post
    }
})();

var util = function () {
    function _serializeObject(form) {
        var o = {};
        var a = form.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    }

    function _serializeJosnString(form) {
        var obj = _serializeObject(form);
        var jsonString = JSON.stringify(obj);
        return jsonString;
    }

    return {
        form: {
            serializeObject: _serializeObject,
            serializeJosnString: _serializeJosnString
        }

    }
}();

//把接口返回值转成datagird需要的格式
function dataGirdApiAdapter(result) {
    var value = {
        total: 0,
        rows: []
    };

    if (result.code == api.code.OK) {
        value.total = result.data.total;
        value.rows = result.data.rows;
    } else {
        $.messager.alert(result.msg);
    }

    return value;
}

