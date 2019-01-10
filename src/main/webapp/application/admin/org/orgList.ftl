
<table width="100%" border="1" cellpadding="0" cellspacing="0">

	<tr>
		<th>组织名称</th>
		<th>组织状态</th>
		<th>显示顺序</th>
		<th>操作</th>
	</tr>

<#if orgList??>
	<#if orgList?size==0>
	<tr>
        <td colspan="4" style="text-align: center;">暂无组织信息</td>
    </tr>
	<#else >
		<#list orgList as org>
			<tr>
				<td><#if org.orgName??>${org.orgName}</#if></td>
				<td>
					<#if org.state??>
				    	<#if org.state==1>可用</#if>
				    	<#if org.state==2>不可用</#if>
					</#if>
				</td>
				<td><#if org.dispIndex??>${org.dispIndex}</#if></td>
				<td><a href="javascript:void();" onclick="javascript:orgOper.editOrg('<#if org.orgId??>${org.orgId}</#if>')" style="cursor: pointer;">编辑</a>
					<a href="javascript:void();" onclick="javascript:orgOper.showOrg('<#if org.orgId??>${org.orgId}</#if>')" style="cursor: pointer;">查看</a> <a
					href="javascript:void();" onclick="javascript:orgOper.deleteOrg('<#if org.orgId??>${org.orgId}</#if>')" style="cursor: pointer;">删除</a></td>
			</tr>
		</#list>
	</#if>
</#if>
</table>


<script type="text/javascript">
	totalCount = "<#if total??>${total}</#if>";
</script>
