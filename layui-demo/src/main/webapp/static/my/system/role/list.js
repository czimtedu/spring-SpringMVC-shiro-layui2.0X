layui.use('table', function() {
	var table = layui.table;
	parent.tableRender = table.render({
		elem : '#roleTable',
		url : rootPath + '/role/pagelist',
		method : 'post',
		cols : [ [ {
			type : 'numbers',
			title : '序号'
		}, {
			unresize : true,//列宽是否禁止拖动(默认  false)
			field : 'name',
			title : '角色名称',
		}, {
			unresize : true,//列宽是否禁止拖动(默认  false)
			field : 'key',
			title : '唯一标识',
		}, {
			unresize : false,//列宽是否禁止拖动(默认  false)
			field : 'description',
			title : '工作内容',
		},{
			unresize : true,//列宽是否禁止拖动(默认  false)
			field : 'createTime',
			title : '创建时间',
			sort : true
		},{
			unresize : true,//列宽是否禁止拖动(默认  false)
			field : 'updateTime',
			title : '修改时间',
		}, {
			unresize : true,//列宽是否禁止拖动(默认  false)
			fixed : 'right',
			align : 'center',
			title : '操作',
			toolbar : '#buttons'// '<div>'+buttons1+'</div>'
		} ] ],
		id : 'roleReload',
		//size : 'sm', //sm （小尺寸） lg （大尺寸）
		//cellMinWidth : 50, // 用于全局定义所有常规单元格的最小宽度（默认 60）
		page : pageObj(), // 自定义分页对象
		limit : 20, // 每页默认显示的数量--默认(10)  优先级低于 page 参数的 laypage 参数
		initSort : {
			field : 'createTime' // 排序字段，对应 cols 设定的各字段名
			,
			type : 'desc' // 排序方式 asc: 升序、desc: 降序、null: 默认排序
		},
		done : function(res, curr, count) {// 表格渲染完成回调
			lastLoad();		//表格渲染完成后操作	
		}
	});
	
	
	table.on('sort(role)', function(obj) { // 注：tool是工具条事件名，test是table原始容器的属性
		var searchParams = $("#searchForm").serializeJson();
		// 把排序字段赋值给json对象
		searchParams.column = obj.field;
		searchParams.sort = obj.type;
		table.reload('roleReload', {
			initSort : obj // 记录初始排序，如果不设的话，将无法标记表头的排序状态。 layui 2.1.1 新增参数
			,
			where : searchParams
		});
	});
	
	
	// 监听工具条
	table.on('tool(role)', function(obj) {
		var data = obj.data;
		if (obj.event === 'detail') {
			parent.layer_form = layer_form = parent.layer.open({
				title : "角色详情",
				type : 2,
				area : [ "700px", "85%" ],
				content : rootPath + '/role/getDetails/' + data.id
			});
		} else if (obj.event === 'del') {
			parent.layer_form = layer_form = parent.layer.open({
				title : "编辑角色",
				type : 2,
				area : [ "700px", "80%" ],
				content : rootPath + '/role/xx'
			});
		} else if (obj.event === 'edit') {
			parent.layer_form = layer_form = parent.layer.open({
				title : "编辑角色",
				type : 2,
				area : [ "700px", "80%" ],
				content : rootPath + '/role/editUI/' + data.id
			});
		}
	});
	
	// 条件搜索
	$('#toSearch').on('click', function() {
		parent.my_reload();
		return false;// 不加return false 会重复加载页面
	});
	
	// 添加
	$("#toAdd").click("click", function() {
		toAdd();
	});
});

//带条件重新加载页面数据
parent.my_reload = function() {
	var searchParams = $("#searchForm").serializeJson();
	parent.tableRender.reload({
		page: pageObj(),
		where : searchParams
	});
}

//添加页面弹出框
function toAdd() {
	parent.layer_form = layer_form = parent.layer.open({
		title : "添加角色",
		type : 2,
		area : [ "700px", "80%" ],
		content : rootPath + '/role/addUI'
	});
}