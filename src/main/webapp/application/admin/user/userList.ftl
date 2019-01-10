<#if userList??>
<table width="100%" border="1" cellpadding="0" cellspacing="0">

	<tr><th>姓名</th><th>电话</th><th>邮箱</th><th>性别</th><th>生日</th><th>地址</th><th>操作</th></tr>
	
	<#if userList?size == 0>
	   <tr><td colspan="7" style="text-align:center;">暂无员工</td></tr>
	<#else> 
	<#list userList as user>
	<tr>
		<td><#if user.userChName??>${user.userChName}</#if></td>
		<td><#if user.mobilePhone??>${user.mobilePhone}</#if></td>
		<td><#if user.email??>${user.email}</#if></td>
		<td>
		<#if user.userSex??>
		
		<#if 1==user.userSex>
	    	男
		</#if>
		
		<#if 2==user.userSex>
	    	女
		</#if>
		 <#if 3==user.userSex>
	    	保密
		</#if>
		</#if>
		</td>
		
		<td><#if user.userBirthday??>${user.userBirthday?string("yyyy-MM-dd")}</#if></td>
		
		<td><#if user.provinceName??>${user.provinceName}</#if><#if user.cityName??>${user.cityName}</#if><#if user.contryName??>${user.contryName}</#if></td>
		<td>
		<a  href="javascript:void();"  onClick="javascript:userOper.editUser('<#if user.userId??>${user.userId}</#if>')" style="cursor:pointer;">编辑</a>
		<a  href="javascript:void();"  onClick="javascript:userOper.showUser('<#if user.userId??>${user.userId}</#if>')" style="cursor:pointer;">查看</a>
		<a  href="javascript:void();"  onClick="javascript:userOper.deleteUser('<#if user.userId??>${user.userId}</#if>')" style="cursor:pointer;")">删除</a>
		</td>
	</tr>
	</#list> 
	</#if>
</table>
</#if>

<script type="text/javascript">
	totalCount = "<#if total??>${total}</#if>";
</script>
