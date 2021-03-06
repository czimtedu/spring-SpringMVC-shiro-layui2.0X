/**
 * js表格分页工具组件
 * @author liuchengyong
 * @date 2017/3/7
 * @version 1.0v
 */
 (function(){
	 
		layTable = function(params,callback){
		layui.use(['form'], function () {});
		//全局属性
		var g_conf = {
			columns : [],
			layTableId : 'l_t_id', // 加载表格存放位置的ID
			ajaxUrl : '', // 访问后台地址
			usePage : true,// 是否分页
			serNumber : false,// 是否显示序号
			serNumberWidth : "30px",
			local:false,//是否本地分页,即返回所有数据,让前端分页
			localData:[],//本地数据集
			records : 'records',// 远程数据
			pageNow : 'pageNow',// 当前页码 或 当前第几页
			totalPages : 'pageCount',// 总页数
			totalRecords : 'rowCount',// 总记录数
			groups : '5',// 分页时，最多显示几个页码
			data : {}, // 发送给后台的数据 是json数据 例如{nama:"a",age:"100"}....
			pageSize : 10, // 每页显示多少条数据
			checkbox : false,// 是否显示复选框
			checkValue : 'id',// 当checkbox为true时，需要设置存放checkbox的值字段 默认存放字段id的值
			checkboxWidth : "30px",
			ajaxType : 'POST',
			dataType : 'json',
			selectedOne:false,//单选
			trColor:'',//改变行样式
			isAutoWrap:true,//td内容是否自动换行
			async:true

		}
		

		//行属性
		var c_conf = {
			//属性别名，表别名，后台排序时，会拼接传递到后台
			c_alias:null,
			c_key : null,
			c_name : null,
			width : 'auto',
			height : 'auto',
			align : 'center',
			hide : false,
			isSort:false,
			renderData : null //function( rowindex ,data, rowdata, colkey)
		}

		//数据处理
		var  c_conf_array = [];
		g_conf = $.extend(g_conf, params);

		for ( var i = 0; i < g_conf.columns.length; i++) {
				c_conf_array.push(c_conf);
		}
		for ( var i = 0; i < c_conf_array.length; i++) {
				for ( var p in c_conf_array[i])
					if (c_conf_array[i].hasOwnProperty(p) && (!g_conf.columns[i].hasOwnProperty(p)))
						g_conf.columns[i][p] = c_conf_array[i][p];
		}


		//本地数据请求
		var  localRequest = function(){
			return {records:g_conf.localData};
		}

		//请求远程数据
		var	syncRequest = function(){
			
			var json = null;
			var layer_index;
			try {
				layer_index = parent.layer.msg('加载中', {
					icon: 16
					,shade: 0.01,
					time: 0 //不自动关闭
				});
				$.ajax({
					type : g_conf.ajaxType,
					async : g_conf.async,
					data : g_conf.data,
					url : g_conf.ajaxUrl,
					dataType : g_conf.dataType,
					success : function(data) {
						json = data;
					},
					error : function(msg) {
						if(console) console.debug("远程数据获取失败");
					}
				});
			
			} catch (e) {
				if(console) console.debug(e);
			}
			parent.layer.close(layer_index);
			return json;
				
		}
		
		//请求远程数据
		var	asyncRequest = function(){
			
			var layer_index;
			try {
				layer_index = parent.layer.msg('加载中', {
					icon: 16
					,shade: 0.01,
					time: 0 //不自动关闭
				});
				$.ajax({
					type : g_conf.ajaxType,
					async : g_conf.async,
					data : g_conf.data,
					url : g_conf.ajaxUrl,
					dataType : g_conf.dataType,
					success : function(data) {
						parent.layer.close(layer_index);
						var result = {};
						_result = data;
						if(_result){
							result.pageSize = g_conf.pageSize;
							result.pageNow = _result[g_conf.pageNow];
							result.totalRecords = _result[g_conf.totalRecords];
							result.totalPages = _result[g_conf.totalPages];
							result.records = _result[g_conf.records];
						}
						
						//当前数据行赋值
						currentData = result;

						//执行创建
						createTable(result);

						//执行回掉函数
						if(callback)
							callback(g_conf.columns,currentData);
					},
					error : function(msg) {
						if(console) console.debug("远程数据获取失败");
						parent.layer.close(layer_index);
					}
				});
			
			} catch (e) {
				if(console) console.debug(e);
				parent.layer.close(layer_index);
			}
		}
			
		//刷新,或者叫做重新加载数据
		var loadData = function(){
			boot();
		}

		//设置参数
		var setOptions = function(params) {
			var data;
			if(params.data){
				data = $.extend(g_conf.data, params.data);
				params.data = data;
			}
			$.extend(g_conf, params);
			boot();
		};


		//获取选中的checkbox
		var getSelectedCheckbox = function(layTableId){

			var _layTableId = layTableId;
			if(layTableId==''||layTableId==undefined){
				_layTableId = g_conf.layTableId;
			}
			var arr = [];
			$("#" + _layTableId + " tbody input[type='checkbox']:checked").each(function() {
				arr.push($(this).val());
			});
			return arr;
		}


		//获取处理过后的行信息
		var getColumn = function(){
			return g_conf.columns;
		};


		//获取当前数据集，若是没有分页则是所有数据，若分了页则是当前页的数据
		var getCurrentData = function(){
			return currentData;
		}



		//排序,需要加入underscore
		var sortLocal = function(sort,result,key){

			var _array =_.sortBy(result.records, key);

			if(sort=="desc"){
				 result.records = _array.reverse();
			}else{
				 result.records=_array;
			}
			return result;
		}

		//选择行
		var selectRow = function(pagId) {
			var ck = getSelectedCheckbox(pagId);
			var json = getCurrentData().records;
			 var ret = [];
			 $.each(json, function(d) {
				 $.each(ck, function(c) {
					if(ck[c] == json[d][g_conf.checkValue])
				    ret.push(json[d]);
				 });
			 });
			 return ret;
		};


		var resultJSONData = function(){

			if(g_conf.local){
				return localRequest();
			}
			return getCurrentData();
		}


		//表格头创建
		var createHead = function(){

			var theadHtml = "";
			//序号字段
			if (g_conf.serNumber) {
				theadHtml = "<th style='width:"
				          + g_conf.serNumberWidth
						  + ";text-align:center;font-weight: bold;'>序号</th>";
			}
			//checkbox
			if(g_conf.checkbox){

				theadHtml += "<th style='width:"
						  + g_conf.checkboxWidth
						  + ";text-align:center;'><input type='checkbox' lay-skin='primary' lay-filter='allChoose'></th>";
			}
			//列标题
			var columns = g_conf.columns;
			for (var i = 0; i < columns.length; i++) {
				if(!columns[i].hide){

					theadHtml += "<th lay-filter='sort' style='font-weight: bold;cursor:pointer;width:"
							  + columns[i].width
							  + ";height:" + columns[i].height
							  +";text-align:" + columns[i].align + ";'>"

					if(columns[i].isSort){

					    if( g_conf.data.current == columns[i].c_key ){
							theadHtml += columns[i].c_name
								  + '<span class="' + (g_conf.data.sort=="asc"?"wj-glyph-up":"wj-glyph-down") + '" title="'+columns[i].c_key+',' + g_conf.data.sort + '" alias="'+(!!columns[i].alias?columns[i].alias:"")+'"></span>';
						}else{
							theadHtml += columns[i].c_name
								  + '<span class="wj-glyph-up" title="'+columns[i].c_key+',asc" alias="'+(!!columns[i].alias?columns[i].alias:"")+'"></span>';
						}

					}else{
						theadHtml += columns[i].c_name + "</th>";
					}
				}

			}
			return theadHtml;
		}

		//表格体创建
		var createBody = function(result){

			var tbody = "";
			//列标题
			var columns = g_conf.columns;

			if (result){
				//获取数据对象
				var records = result.records;

				if (records && records.length > 0) {
					for (var i = 0; i < records.length; i++) {
						if(g_conf.trColor){
							tbody += "<tr style = background-color:"+g_conf.trColor(records[i])+">";
						}else{
							tbody +="<tr>";
						}
						
						//序号
						if (g_conf.serNumber) {
							tbody += "<td style='width:"+ g_conf.serNumberWidth
								  + ";text-align:center;'>"
								  + ((+(result.pageNow?result.pageNow:1) - 1) * +(result.pageSize?result.pageSize:0) + i + 1)
								  + "</td>";
						}
						//checkbox
						if(g_conf.checkbox){
							tbody += "<td style='width:" + g_conf.checkboxWidth
								  + ";text-align:center;'><input type='checkbox' lay-skin='primary' lay-filter='oneChoose'  value='"+records[i][g_conf.checkValue]+"'></td>";
						}
						//数据渲染
						for (var j = 0; j < columns.length; j++) {

							if(!columns[j].hide){
								//单元格的数据
								var value = null;
								var myclms = records[i];
								//针对dept.name对象的对象获值
								if(myclms && columns[j].c_key.indexOf('.') != -1){
									var clms = columns[j].c_key.split('.');
									for(var x=0;x<clms.length;x++){
										if(myclms)
										myclms = myclms[clms[x]];
									}
								}else{
									myclms = myclms[columns[j].c_key];
								}

								//获取json对象值
								if (columns[j].renderData) {
									value = columns[j].renderData(i,myclms,records[i],columns[j].c_key);
								}else {
									value = myclms;
								}
								value = value != null && (value || !isNaN(value)) ? value : '';
								var filedTitle =  value;//title默认为value值
								if(columns[j].title || columns[j].title == ''){
									filedTitle = columns[j].title;
								}
								var wrap = g_conf.isAutoWrap ?  ";word-wrap: break-word;":";overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"
								//渲染单元格及样式
								tbody += "<td title='"+ filedTitle +"' style='width:" + columns[j].width
									  + ";height:" + columns[j].height
									  +";text-align:" + columns[j].align + ""+wrap+"'>";
								tbody += value + "</td>";
							}
						}
						tbody += "</tr>";
					}
				}
			}
			//没有数据
			if(tbody == ""){
				var show_num = 0;
				for(var i in columns){
					if(!columns[i].hide){
						show_num++;
					}
				}
				tbody += "<tr><td style='text-align:center;' colspan = '"
					  + (show_num + (g_conf.serNumber ? 1 : 0) + (g_conf.checkbox ? 1 : 0))+"'>搜索结果为空</td></tr>"
			}
			return tbody;
		}

		//创建pagerId
		var creatPagerId = function(){
			var pagerId = null;
			//初始化分页容器的ID
			if (!pagerId) {
				pagerId = "pagerId" + new Date().getTime();
			}
			return pagerId;
		}

		//创建按钮组
		var creatBtnItem = function(){
			/*var btnItem = "<div class='layui-btn-group' style='float:left;margin:10px 0;'>"
						+ "<button class='layui-btn layui-btn-primary layui-btn-small'>"
						+ "增加"
						+ "</button>"
						+ "<button class='layui-btn layui-btn-primary layui-btn-small'>"
						+ "编辑"
						+ "</button>"
						+ "<button class='layui-btn layui-btn-primary layui-btn-small'>"
						+ "删除"
						+ "</button>"
						+ "<button class='layui-btn layui-btn-primary layui-btn-small'>"
						+ "权限设置"
						+ "</button>"
						+ "</div>"*/
						
			return "";
		}

		//渲染表格
		var applayToHtml = function(theadHtml,bodyHtml,pagerId,newBtn){

			var html = "<div>"
					 + " <table class='layui-table' style='table-layout: fixed;'>"
					 + "    <thead> "
					 + "        <tr>"
					 +             theadHtml
					 + "        </tr>"
					 + "    </thead>"
					 + "    <tbody >" + bodyHtml + "</tbody>"
					 + " </table>"
					 + " <div style='margin-top:-10px;' class='page-bot-item' id = '" + pagerId + "'>"
					 + 				newBtn
					 + "<div style='clear:both;'></div>"
					 +"  </div>"
					 + "</div>";

			$("#" + g_conf.layTableId).html(html);

			//此处必须要加上，不然layui的checkbox渲染会出问题
			layui.use(['form'], function () {

				var form = layui.form();
				form.render('checkbox');
				//全选
			    form.on('checkbox(allChoose)', function(data){
					var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
					child.each(function(index, item){
					  item.checked = data.elem.checked;
					});
					form.render('checkbox');
				});

				//单选更新
				form.on('checkbox(oneChoose)', function(data){
					var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
					var checked = $(data.elem).parents('table').find('tbody :checkbox:checked');
					if(child.length == checked.length) {
						$(data.elem).parents('table').find('thead input[type="checkbox"]')[0].checked = true;
					}else{
						$(data.elem).parents('table').find('thead input[type="checkbox"]')[0].checked = false;
					}
					form.render('checkbox');
				});
				//单选
				if(g_conf.selectedOne){
					//单选时禁用thead中的checkbox
					$('thead input[type="checkbox"]').attr({"disabled":"disabled"});
					form.on('checkbox(oneChoose)', function(data){
						var checked = $(data.elem).parents('table').find('tbody :checkbox:checked');
						if(checked.length>1) {
							for (var i = 0; i < checked.length; i++) {
								if(data.value!=checked[i].value){
									$(data.elem).parents('table').find('tbody :checkbox:checked')[i].checked = false;
									form.render('checkbox');
								}
							}
						}
					});
				}

				//排序
				$("th[lay-filter='sort']").on("click",function(){
					var $span = $(this).find("span");
					var alias = $span.attr("alias");
					var _is = $span.length > 0 ? true :false;
					if(!_is) return ;
					var th = $span[0];
					var  t = th.title.split(",");
					var sc = "";
					if(t[1]=="asc"){
						th.className = "wj-glyph-down";
						th.title = t[0] + ",desc";
						sc="desc";
					}else{
						th.className = "wj-glyph-up";
						th.title = t[0] + ",asc";
						sc="asc";
					}
					g_conf.data = $.extend(g_conf.data, {
						column:!!alias?alias+"."+t[0]:t[0],sort:sc,current:t[0]
					});
					//var result = sortLocal(sc,getCurrentData(),t[0]);
					boot();
					//createTable(result);
				})
			});
		}


		//创建分页
		var  createPager = function(result,pagerId,newBtn){

				 var pagerContent = "";

				if(result && result.records && result.records.length>0){

					var leftInfo = '总&nbsp;' + result.totalRecords + '&nbsp;条&nbsp;&nbsp;'
							 +'每页&nbsp;' + g_conf.pageSize + '&nbsp;条&nbsp;&nbsp;'
							 +'共&nbsp;' + result.totalPages + '&nbsp;页';

					pagerContent += newBtn
								 +	"  <div style='display: inline-block;margin:16px 0;font-size:12px;' id =\"" + pagerId + "-left\"><a onclick='javascript:;'>" + leftInfo + "</a></div>"
								 + "   <div style='display: inline-block;' id=\"" + pagerId + "-right\"></div>"
								 + "   <div style='clear:both;'></div>"

				}

				if(pagerContent == "") return ;

				$("#" + pagerId).html(pagerContent);

				//构建右边分页
				layui.use(['laypage'], function () {
					var laypage = layui.laypage
					, layer = layui.layer;
						laypage({
						  cont: pagerId + "-right"
						  , curr: result.pageNow
						  , pages: result.totalPages //总页数
						  , skip: true
						  , layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
						  , groups: g_conf.groups //连续显示分页数
						  ,prev: '<em><</em>'
						  ,next: '<em>></em>'
						  , jump: function (obj, first) {
							  if (!first) {
								  boot(obj.curr);
							  }
						  }
						});
				});
		}


		//创建表格
		var  createTable = function (result) {
			var headHtml = createHead();
			var bodyHtml = createBody(result);
			var newBtn = creatBtnItem();
			var pagerId = creatPagerId();
			applayToHtml(headHtml,bodyHtml,pagerId,newBtn);
			if(g_conf.usePage){
				createPager(result,pagerId,newBtn)
			}
			lastLoad();

		}
		
		//创建完表格后执行操作
		var lastLoad = function(){
			$(".layui-table-btn").css('display','block');
			try {
				resetButton();
			} catch (e) {
			}
		} 
		
		
			//当前数据行数
		var  currentData = [];

		//生成表格启动函数
		var  boot = function(pageNum){

			g_conf.data.pageNow = pageNum ? pageNum : 1;
			g_conf.data.pageSize = g_conf.pageSize;
			var result = {};

			if(g_conf.local){

				result = localRequest();

				if(g_conf.data.column){

					result = sortLocal(g_conf.data.sort,result,g_conf.data.column);
				}
				if(g_conf.usePage){

					result.pageSize = g_conf.pageSize;
					result.pageNow = g_conf.data.pageNow;
					result.totalRecords = result.records.length;
					result.totalPages = Math.ceil(result.records.length/g_conf.pageSize);
					result.records = result.records.slice((g_conf.data.pageNow - 1)*g_conf.pageSize,g_conf.data.pageNow*g_conf.pageSize);
				}
				
				//当前数据行赋值
				currentData = result;

				//执行创建
				createTable(result);

				//执行回掉函数
				if(callback)
					callback(g_conf.columns,currentData);
				
			}else if(!g_conf.async){
				
				var _result =  syncRequest();
				
				if(_result){
					result.pageSize = g_conf.pageSize;
					result.pageNow = _result[g_conf.pageNow];
					result.totalRecords = _result[g_conf.totalRecords];
					result.totalPages = _result[g_conf.totalPages];
					result.records = _result[g_conf.records];
				}
				
				//当前数据行赋值
				currentData = result;

				//执行创建
				createTable(result);

				//执行回掉函数
				if(callback)
					callback(g_conf.columns,currentData);
				
			}else{//异步处理
				
				asyncRequest();
				
			}
			
		}

		boot();

		return {
			setOptions : setOptions,//自定义条件查询
			loadData : loadData,//重新加载数据
			getSelectedCheckbox : getSelectedCheckbox,//获取选择的行的Checkbox值
			selectRow : selectRow,// 选中行事件
			resultJSONData : resultJSONData,//返回列表的所有json数据
			getColumn:getColumn,//导出数据
			getCurrentData:getCurrentData,//获取表格的当前页json数据
			getGridHeight : $("#tableGrid").height(),//获取grid高度
			getGridWidth : $("#tableGrid").width(),//获取grid宽度
		};
	 }

 })();
