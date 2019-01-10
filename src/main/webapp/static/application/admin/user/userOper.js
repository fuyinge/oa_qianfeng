function UserOper() {

    this.addUser = function () {
        var htmlStr = userOper.initHtml();
        $.layer({
            type: 1,
            title: false,
            area: ['auto', 'auto'],
            page: {
                html: htmlStr
            }
        });

        //初始化省市县
        CascadeArea(null, null, null, "provinceId", "cityId", "countryId");
        //初始化年月日
        InitDate();
    }

    this.insertUser = function () {
        var userId = $("#userId").val();
        var param = new Object();
        param.userId = userId;
        param.orgId = $("#orgId").val();
        param.userChName = $("#userChName").val();
        param.mobilePhone = $("#mobilePhone").val();
        param.email = $("#email").val();
        param.userName = $("#userName").val();
        param.userPassword = $("#userPassword").val();
        param.userSex = $('input[name="userSex"]:checked').val();

        var year = $("#year").val();
        var month = $("#month").val();
        var day = $("#day").val();
        var birthday_temp = year + "-" + month + "-" + day;
        param.birthday_temp = birthday_temp;

        var provinceId = $("#provinceId").val();
        var cityId = $("#cityId").val();
        var countryId = $("#countryId").val();

        if (provinceId) {
            param.provinceName = $("#provinceId").find("option:selected").text();
        }

        if (cityId) {
            param.cityName = $("#cityId").find("option:selected").text();
        }

        if (countryId) {
            param.contryName = $("#countryId").find("option:selected").text();
        }

        param.provinceId = provinceId;
        param.cityId = cityId;
        param.countryId = countryId;


        var url = "/user/addUser";
        var desc = "新增";
        if (userId) {
            url = "/user/updateUser";
            desc = "修改";
        }

        $.ajax({
            url: url,
            async: true,
            type: "POST",
            data: param,
            success: function (data) {
                //{"isSuccess",true}
                var obj = jQuery.parseJSON(data);
                if (obj.isSuccess == true) {
                    var alerts = layer.alert(desc + "成功", 1, function () {
                        layer.close(alerts);
                        location.reload();
                    });
                } else {
                    var alerts = layer.alert(desc + "失败", 5, function () {
                        layer.close(alerts);
                    });
                }
            }
        })
    }

    this.showUser = function (userId) {
        var htmlStr = userOper.initHtml();
        $.layer({
            type: 1,
            title: false,
            area: ['auto', 'auto'],
            page: {
                html: htmlStr
            }
        });
        var param = new Object();
        param.userId = userId;

        $.getJSON("/user/getUserByUserId", param, function (data) {
            $("#userId").val(data.userId);
            $("#orgId").val(data.orgId);
            $("#orgName").val(data.orgName);
            $("#userChName").val(data.userChName);
            $("#mobilePhone").val(data.mobilePhone);
            $("#email").val(data.email);
            $("#userName").val(data.userName);
            $("#userPassword").val(data.userPassword);

            $("input[name='userSex'][value=" + data.userSex + "]").attr("checked", true);

            var birthday_temp = data.userBirthday;
            //把日期变成毫秒数
            var bithday_time = Date.parse(birthday_temp);
            //通过毫秒数构造一个日期对象
            var date = new Date(bithday_time);
            //得到年份
            var year = date.getFullYear();
            var month = date.getMonth()+1;
            var day = date.getDate();

            if(month<10){
                month = "0"+month;
            }
            if(day<10){
                day = "0"+day;
            }

            InitDate();
            $("#year").val(year);
            $("#month").val(month);
            $("#day").val(day);

            var provinceId=data.provinceId;
            var cityId=data.cityId;
            var countryId=data.countryId;

            CascadeArea(provinceId, cityId, countryId,"provinceId", "cityId", "countryId");
            $("#provinceId").val(provinceId);
            $("#cityId").val(cityId);
            $("#countryId").val(countryId);

            $("#saveButton").hide();
        })


    }
    
    this.editUser = function (userId) {
        var htmlStr = userOper.initHtml();
        $.layer({
            type: 1,
            title: false,
            area: ['auto', 'auto'],
            page: {
                html: htmlStr
            }
        });
        var param = new Object();
        param.userId = userId;

        $.getJSON("/user/getUserByUserId", param, function (data) {
            $("#userId").val(data.userId);
            $("#orgId").val(data.orgId);
            $("#orgName").val(data.orgName);
            $("#userChName").val(data.userChName);
            $("#mobilePhone").val(data.mobilePhone);
            $("#email").val(data.email);
            $("#userName").val(data.userName);
            $("#userPassword").val(data.userPassword);

            $("input[name='userSex'][value=" + data.userSex + "]").attr("checked", true);

            var birthday_temp = data.userBirthday;
            //把日期变成毫秒数
            var bithday_time = Date.parse(birthday_temp);
            //通过毫秒数构造一个日期对象
            var date = new Date(bithday_time);
            //得到年份
            var year = date.getFullYear();
            var month = date.getMonth()+1;
            var day = date.getDate();

            if(month<10){
                month = "0"+month;
            }
            if(day<10){
                day = "0"+day;
            }

            InitDate();
            $("#year").val(year);
            $("#month").val(month);
            $("#day").val(day);

            var provinceId=data.provinceId;
            var cityId=data.cityId;
            var countryId=data.countryId;

            CascadeArea(provinceId, cityId, countryId,"provinceId", "cityId", "countryId");
            $("#provinceId").val(provinceId);
            $("#cityId").val(cityId);
            $("#countryId").val(countryId);
        })
    }

    this.initHtml = function () {
        var htmlStr = '';
        htmlStr += '<div style="width:600px;" >';
        htmlStr += '<div style=" line-height:30px; text-indent:10px; margin-bottom:20px; background-color:#eee; position:relative;">添加用户</div>';
        htmlStr += '<div style="display:block; padding-bottom:20px;" align="center"  >';

        htmlStr += '<table border="0" >';

        htmlStr += '<tr style="display:none">';
        htmlStr += '<td colspan="2">';
        htmlStr += '<span>用户ID:</span>';
        htmlStr += '<input type="text"  id="userId" style="width:240px;">';
        htmlStr += '</td>';
        htmlStr += '</tr>';

        htmlStr += '<tr>';
        htmlStr += '<td>';
        htmlStr += '所属组织：';
        htmlStr += '<input type="text" id="orgName"  style="width:110px;" readonly="readonly" >';
        htmlStr += '<input type="hidden" id="orgId">';
        htmlStr += '<button onclick="QueryUserTreeLayer();">选择</button>';
        htmlStr += '</td>';
        htmlStr += '<td>';
        htmlStr += '<span>姓名:</span>';
        htmlStr += '<input type="text"  id="userChName" style="width:240px;">';
        htmlStr += '</td>';
        htmlStr += '</tr>';

        htmlStr += '<tr>';
        htmlStr += '<td>';
        htmlStr += '性别：<input type="radio" name="userSex"  value="1">男';
        htmlStr += '<input type="radio" name="userSex"   value="2">女';
        htmlStr += '<input type="radio" name="userSex"   value="3">保密';
        htmlStr += '</td>';
        htmlStr += '<td>';
        htmlStr += '生日： <select id="year"  name="year"></select>年';
        htmlStr += '<select id="month" name="month"></select>月';
        htmlStr += '<select id="day"  name="day"></select>日';
        htmlStr += '</td>';
        htmlStr += '</tr>';

        htmlStr += '<tr>';
        htmlStr += '<td>';
        htmlStr += '<span>电话:</span>';
        htmlStr += '<input type="text"  id="mobilePhone" style="width:240px;">';
        htmlStr += '</td>';
        htmlStr += '<td>';
        htmlStr += '<span>邮件:</span>';
        htmlStr += '<input type="text"  id="email" style="width:240px;">';
        htmlStr += '</td>';
        htmlStr += '</tr>';

        htmlStr += '<tr>';
        htmlStr += '<td>';
        htmlStr += '<span>用户名:</span>';
        htmlStr += '<input type="text"  id="userName" style="width:240px;">';
        htmlStr += '</td>';
        htmlStr += '<td>';
        htmlStr += '<span>密码:</span>';
        htmlStr += '<input type="text"  id="userPassword" style="width:240px;">';
        htmlStr += '</td>';
        htmlStr += '</tr>';

        htmlStr += '<tr>';
        htmlStr += '<td>';
        htmlStr += '<span>省份:</span>';
        htmlStr += '<select id="provinceId" style="width:240px;">';
        htmlStr += '</select>';
        htmlStr += '</td>';
        htmlStr += '<td>';
        htmlStr += '<span>地市:</span>';
        htmlStr += '<select id="cityId" style="width:240px;">';
        htmlStr += '</select>';
        htmlStr += '</td>';
        htmlStr += '</tr>';

        htmlStr += '<tr>';
        htmlStr += '<td>';
        htmlStr += '<span>区县:</span>';
        htmlStr += '<select id="countryId" style="width:240px;">';
        htmlStr += '</select>';
        htmlStr += '</td>';
        htmlStr += '<td>';
        htmlStr += '</td>';
        htmlStr += '</tr>';

        htmlStr += '<tr id="saveUserTr"><td colspan="2" align="center">';
        htmlStr += '<input id="saveButton" name="" type="button"  onclick="userOper.insertUser();" value="保存"  />';
        htmlStr += '</td></tr>';
        htmlStr += '</table>';
        htmlStr += '</div>';
        htmlStr += '</div>';

        return htmlStr;

    }


}

function InitDate() {
    var date = new Date();
    //2019
    var year = date.getFullYear();
    //1929
    var lowYear = year - 90;
    var yearOption = $("#year");
    for (var y = lowYear; y <= year; y++) {
        yearOption.append("<option value='" + y + "'>" + y + "</option>");
    }

    var monthOption = $("#month");
    for (var m = 1; m <= 12; m++) {
        if (m < 10) {
            m = '0' + m;
        }
        monthOption.append("<option value='" + m + "'>" + m + "</option>");
    }

    var dayOption = $("#day");
    for (var d = 1; d <= 31; d++) {
        if (d < 10) {
            d = '0' + d;
        }
        dayOption.append("<option value='" + d + "'>" + d + "</option>");
    }

}
