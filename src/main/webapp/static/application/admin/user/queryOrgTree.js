function QueryOrgTreeLayer() {
    var htmlStr = "";
    htmlStr += '<div style="width:300px;height:200px" >';
    htmlStr += '<div style=" line-height:30px; text-indent:10px; margin-bottom:20px; background-color:#eee; position:relative;">组织</div>';
    htmlStr += '<div style="display:block; padding-bottom:5px;" align="center"  >';
    htmlStr += '<div><table><tr><td><input type="hidden" id="tmp_orgId" width="50px"/><input type="text" id="tmp_orgName" width="50px"><button onclick="SetQueryOrgTreeParam();">确定</button></td></tr></table></div>';
    htmlStr += '<div id="QueryOrgTree" class="ztree"></div>';
    htmlStr += '</div>';
    htmlStr += '</div>';


    $.layer({
        type: 1,//1：页面 2：frame
        title: false,
        area: ['auto', 'auto'],
        page: {
            html: htmlStr
        }
    });
    //加载树的数据
    loadOrgTree();
}


function loadOrgTree() {
    var setting = {
        data: {
            simpleData: {
                enable: true  //使用简单 Array格式的数据
            }
        },
        async: {
            enable: true, //开启异步加载处理
            url: "/tree/orgSubList",
            autoParam: ["id", "name"],
            dataType: "json",//默认text
            type: "get"//默认post
        }
        , callback: {
            onClick:ClickQueryOrgTreeNodeFunc
        }
    };


    var zNodes = [];

    var zTreeObj = $.fn.zTree.init($("#QueryOrgTree"), setting, zNodes);

}

function ClickQueryOrgTreeNodeFunc(event, treeId, treeNode, clickFlag){
    $("#tmp_orgId").val(treeNode.id);
    $("#tmp_orgName").val(treeNode.name);

}

function SetQueryOrgTreeParam() {
    $("#qry_orgName").val( $("#tmp_orgName").val());
    $("#qry_orgId").val($("#tmp_orgId").val());
    //关闭当前弹出框
    var index = layer.index;
    layer.close(index);
}