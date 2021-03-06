var _userInfo;

$(document).ready(function () {
    /*
     * 刷新tabs
     * **/
    $('#tabs').tabs({
        tools: [{
            iconCls: 'icon-reload',
            border: false,
            handler: function () {
                $('#datagrid').datagrid('reload');
            }
        }]
    });

    /*
    *触发侧边栏
    */
    $('.side-tree a').bind("click", function () {
        var title = $(this).text();
        var url = $(this).attr('data-link');
        var iconCls = $(this).attr('data-icon');
        var iframe = $(this).attr('iframe') == 1 ? true : false;
        addTab(title, url, iconCls, iframe);
    });

    /**
     * Name 移除菜单选项
     */
    function removeTab() {
        var tabPanel = $('#tabs');
        var tab = tabPanel.tabs('getSelected');
        if (tab) {
            var index = tabPanel.tabs('getTabIndex', tab);
            tabPanel.tabs('close', index);
        }
    }

    /**
     * 切换侧边栏
     * **/
    $(".menu-pane li").bind('click', function () {
        console.log($(this).data('index'));
        $(this).addClass('active');
        $(".frist-menu").hide();
        $(".frist-menu").eq($(this).data('index')).show();
    })

    /**
     * Name 添加菜单选项
     * Param title 名称
     * Param href 链接
     * Param iconCls 图标样式
     * Param iframe 链接跳转方式（true为iframe，false为href）
     */
    function addTab(title, href, iconCls, iframe) {
        var tabPanel = $('#tabs');
        if (!tabPanel.tabs('exists', title)) {
            var content = '<iframe scrolling="auto" frameborder="0"  src="' + href + '" style="width:100%;height:100%;"></iframe>';
            if (iframe) {
                tabPanel.tabs('add', {
                    title: title,
                    content: content,
                    iconCls: iconCls,
                    fit: true,
                    cls: 'pd3',
                    closable: true
                });
            }
            else {
                tabPanel.tabs('add', {
                    title: title,
                    href: href,
                    iconCls: iconCls,
                    fit: true,
                    cls: 'pd3',
                    closable: true
                });
            }
        }
        else {
            tabPanel.tabs('select', title);
        }
    }

    $('.logout').bind('click', function () {
        $.get(api.url.logout,
            null,
            function (data) {
                if (data.code != api.code.OK)
                    return;

                window.location.href = '/login.html';
            }
        )
    })

    /**
     * 是否登录
     */
    ajax.get(api.url.auth, null, function (data) {
        if (data.code != api.code.OK)
            return;

        _userInfo = data.data;
        $('#user-name').text(_userInfo.name);
    }, 'json', false);
})