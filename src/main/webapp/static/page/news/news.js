define([
    '/widget/ui/utils/utils.js',
    "/widget/ui/dialog/dialog.js",
    '/widget/ui/form-handle/form-handle.js',
    '/widget/ui/pagination/pagination.js'
],function(Utils,Dialog,formHandle,Pagination){
    var $mod = $('.news');
    var $pagination = $mod.find('.pagination');
    var listCount = window.PageData.listCount;
    var init = function(){
        initPagination();
    }()

    function initPagination(){
        var urlData = Utils.getUrlParams();
        var curPage = Number(urlData.whichPage);
        console.log(curPage);
        pagination = new Pagination({
            container:$pagination,
            perCount:10,
            totalCount:listCount,
            onRefresh:function(curPage,callback){
                callback(listCount);
            }
        })
        pagination.initPage(curPage);
        pagination.on("refreshinstance", function (cur) {
            var param = $.param($.extend(urlData,{whichPage:cur}));
            console.log(param);
            location.href = "/news.htm?" + param;
        });
    }

})