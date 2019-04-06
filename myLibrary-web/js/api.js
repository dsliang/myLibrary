var _env = 'local';

var api = (function (env) {

    var server;

    if (env == 'local') {
        server = 'http://localhost';
    } else if (env == 'cloud') {
        server = 'http://94.191.3.36';
    }

    return {
        code: {
            OK: 0,
            UNAUTHORIZED: 401
        },
        url: {
            auth: server+'/myLibrary/api/common/auth',
            login: server+'/myLibrary/api/common/login',
            logout: server+'/myLibrary/api/common/logout',

            user: server+'/myLibrary/api/system/user',
            listUser: server+'/myLibrary/api/system/user/list',
            saveUser: server+'/myLibrary/api/system/user/save',
            deleteUser: server+'/myLibrary/api/system/user/delete',

            rule: server+'/myLibrary/api/system/rule',
            listRule: server+'/myLibrary/api/system/rule/list',
            saveRule: server+'/myLibrary/api/system/rule/save',
            deleteRule: server+'/myLibrary/api/system/rule/delete',
            ruleOption: server+'/myLibrary/api/system/rule/option',

            readerType: server+'/myLibrary/api/system/readerType',
            listReaderType: server+'/myLibrary/api/system/readerType/list',
            saveReaderType: server+'/myLibrary/api/system/readerType/save',
            deleteReaderType: server+'/myLibrary/api/system/readerType/delete',
            readerTypeOption: server+'/myLibrary/api/system/readerType/option',

            reader: server+'/myLibrary/api/circulation/reader',
            readerInfo: server+'/myLibrary/api/circulation/reader/info',
            listReader: server+'/myLibrary/api/circulation/reader/list',
            saveReader: server+'/myLibrary/api/circulation/reader/save',
            deleteReader: server+'/myLibrary/api/circulation/reader/delete',

            location: server+'/myLibrary/api/system/location',
            listLocation: server+'/myLibrary/api/system/location/list',
            saveLocation: server+'/myLibrary/api/system/location/save',
            deleteLocation: server+'/myLibrary/api/system/location/delete',
            locationOption: server+'/myLibrary/api/system/location/option',

            biblio: server+'/myLibrary/api/catalog/biblio',
            listBiblio: server+'/myLibrary/api/catalog/biblio/list',
            saveBiblio: server+'/myLibrary/api/catalog/biblio/save',
            deleteBiblio: server+'/myLibrary/api/catalog/biblio/delete',

            collection: server+'/myLibrary/api/catalog/collection',
            collectionInfo: server+'/myLibrary/api/catalog/collection/info',
            listCollection: server+'/myLibrary/api/catalog/collection/list',
            saveCollection: server+'/myLibrary/api/catalog/collection/save',
            deleteCollection: server+'/myLibrary/api/catalog/collection/delete',

            borrow: server+'/myLibrary/api/circulation/borrow',
            return: server+'/myLibrary/api/circulation/return',
            renew: server+'/myLibrary/api/circulation/renew',
            borrowRecords: server+'/myLibrary/api/circulation/borrow/records'
        }
    };
})(_env);