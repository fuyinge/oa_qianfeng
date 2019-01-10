<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>

<!-- jQuery框架 -->
<script type="text/javascript"  src="/static/plugin/jquery/jquery-1.9.1.js"></script>
<!-- 弹出框插件 -->
<script type="text/javascript" src="/static/plugin/layer/layer.min.js"></script>
 
<!--树  -->
<link rel="stylesheet" href="/static/plugin/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/static/plugin/zTree/js/jquery.ztree.core.js"></script>

<script type="text/javascript" src="/static/application/admin/user/userMana.js"></script>
<script type="text/javascript" src="/static/utils/getArea.js"></script>
<script type="text/javascript" src="/static/application/admin/user/queryOrgTree.js"></script>
<script type="text/javascript" src="/static/application/admin/user/addOrgTree.js"></script>
<script type="text/javascript" src="/static/application/admin/user/userOper.js"></script>



<body style="background-color: #DCDCDC;">

	<table border="1" width="100%" >
		<tr height="100px">
			<td>
				<table border="1" width="100%" height="90px" >
				
					<tr height="30px">
						<td width="30%">
						所属组织：
						<input type="hidden" id="qry_orgId" >
						<input type="text" id="qry_orgName" readonly="readonly">
						<button onclick="QueryOrgTreeLayer()">选择</button>
						</td>
						<td width="30%">姓名：<input type="text" id="qry_userChName"> </td>
						<td width="30%">电话：<input type="text" id="qry_mobilePhone"> </td>
					</tr>
				
					<tr height="30px">
						<td width="30%">所属省份：<select id="qry_provinceId" style="width: 140px"></select> </td>
						<td width="30%">地址：<select id="qry_cityId" style="width: 140px"></select> </select></td>
						<td width="30%">区县：<select id="qry_countryId" style="width: 140px"></select></select></td>
					</tr>
					
					
					<tr  height="30px">
						<td width="30%"></td>
						<td width="30%"></td>
						<td width="30%" style="text-align:center;">
						<button id="queryButton">查询</button>
						   &nbsp;&nbsp;&nbsp;
						<button id="resetButton">重置</button></td>
					</tr>
						<tr  height="30px">
					    <td width="100%" colspan="3" style="text-align:left;">
					    <button onclick="userOper.addUser();" >新增</button>
					    </td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td ><div id="pageListContainer" ></div></td>
		</tr>
		<tr height="50px">
			<td><div id="pageNumberToolBar" height="40px" style="text-align:center;"></div></td>
		</tr>
	</table>

</body>
</html>