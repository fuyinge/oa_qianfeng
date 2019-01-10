<!-- 评论分页 -->
<#if total??>
	<#if (total > 0)>
		<div >
			<div >
				<#if current != 1>
					<#-- 后台current从1开始计算,前台从0开始 -->
					<a  href="javascript:loadUserPage(${current}-2)">上一页</a>
				</#if>
				<#if startPage != 1>
					<a  href="javascript:loadUserPage(0)">1</a>
				</#if>
				<#if page == 1>
					<a  href="javascript:loadUserPage(0)" >1</a>
				<#else>
					<#if startPage != 1>
						<span>...</span>
					</#if>
					<#list startPage..endPage as index>
						<#if current == index>
							<a  href="javascript:void(0)" >${index}</a>
						<#else>
							<a  href="javascript:loadUserPage(${index}-1)">${index}</a>
						</#if>
					</#list>
					<#if endPage != page>
						<span>...</span>
						<a  href="javascript:loadUserPage(${page}-1)">${page}</a>
					</#if>
				</#if>
				<#if page != current>
					<a  href="javascript:loadUserPage(${current})">下一页</a>
				</#if>
			</div>
		</div>	
	<#else>
		<div >
			<div >
				<a  href="javascript:void(0)">上一页</a>
				<a  href="javascript:void(0)" >1</a>
				<a  href="javascript:void(0)">下一页</a>
			</div>
		</div>
	</#if>
<#else>
	<div >
		<div >
			<a  href="javascript:void(0)">上一页</a>
			<a  href="javascript:void(0)" >1</a>
			<a  href="javascript:void(0)">下一页</a>
		</div>
	</div>
</#if>
