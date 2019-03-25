var api = (function () {
    return {
        code: {
            OK: 0,
            UNAUTHORIZED: 2
        },
        url: {
            login: 'http://localhost/myLibrary/api/common/login',
            logout: 'http://localhost/myLibrary/api/common/logout',

            user: 'http://localhost/myLibrary/api/system/user',
            listUser: 'http://localhost/myLibrary/api/system/user/list',
            saveUser: 'http://localhost/myLibrary/api/system/user/save',
            deleteUser: 'http://localhost/myLibrary/api/system/user/delete',

            rule: 'http://localhost/myLibrary/api/system/rule',
            listRule: 'http://localhost/myLibrary/api/system/rule/list',
            saveRule: 'http://localhost/myLibrary/api/system/rule/save',
            deleteRule: 'http://localhost/myLibrary/api/system/rule/delete',
            ruleOption: 'http://localhost/myLibrary/api/system/rule/option',

            readerType:'http://localhost/myLibrary/api/system/readerType',
            listReaderType:'http://localhost/myLibrary/api/system/readerType/list',
            saveReaderType: 'http://localhost/myLibrary/api/system/readerType/save',
            deleteReaderType:'http://localhost/myLibrary/api/system/readerType/delete'

        }
    };
})();