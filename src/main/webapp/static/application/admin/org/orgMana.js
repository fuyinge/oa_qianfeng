var pageSize = 3;
var totalCount;
var orgOper;
$(function(){
    //实例化对象
    orgOper = new OrgOper();
    loadOrgPage(0);

    //查询按钮
    $("#queryButton").bind("click",function(){
        loadOrgPage(0);
    })

    //重置按钮
    $("#resetButton").bind("click",function () {
        $("#qry_orgName").val('');
        $("#qry_state").val('');
        $("#qry_orgParentId").val('');
        $("#qry_orgParentName").val('');
        loadOrgPage(0);
    })
})

function loadOrgPage(startPage){
    var param = new Object();
    param.startIndex = startPage*pageSize;
    param.pageSize = pageSize;
    param.orgName=$("#qry_orgName").val();
    param.state = $("#qry_state").val();
    param.orgParentId = $("#qry_orgParentId").val();

    $.ajax({
        url:"/org/orgPage",
        async:true,
        type:"GET",
        data:param,
        success:function (data) {
            $("#pageListContainer").empty();
            $("#pageListContainer").html(data);
            //加载组织页码
            loadOrgPageNumber(startPage);
        }
    })
}

function loadOrgPageNumber(startPage){
    var param = new Object();
    param.startIndex = startPage*pageSize;
    param.pageSize = pageSize;
    param.total = totalCount;

    $.ajax({
        url:"/org/getPageNumber",
        async:true,
        type:"GET",
        data:param,
        success:function(data){
            $("#pageNumberToolBar").empty();
            $("#pageNumberToolBar").html(data);
        }
    })
}