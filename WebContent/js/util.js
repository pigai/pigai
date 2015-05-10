/**
 * JS工具类
 */

/**
 * 用于判断浏览器版本
 * 判断某种浏览器只需用if(Sys.ie)或 if(Sys.firefox)等形式，
 * 而判断浏览器版本只需用if(Sys.ie == '8.0')或if(Sys.firefox == '3.0')等形式
 */
var Sys = {};
var ua = navigator.userAgent.toLowerCase();
var s;
(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
(s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
(s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
(s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;

var platform_path = "/oa";

/**
 * 阻止浏览器默认操作
 */
function stopDefault(e) {
	if (e && e.preventDefault) {
		e.preventDefault();
	} else {
		window.event.returnValue = false;
	}
	return false;
}

/**
 * 阻止jquery事件冒泡
 * @param e
 */
function stopPro(e) {
    if (e && e.stopPropagation) {
      //W3C取消冒泡事件
      e.stopPropagation();
    } else {
      //IE取消冒泡事件
      window.event.cancelBubble = true;
    }
  };

/**
 * 改变iframe的大小
 * @param obj
 */
function iframeResize(obj){
	iframeResizeH(obj, 0);
}

/**
 * 改变iframe高度，并增加h的高度
 * @param obj
 * @param h
 */
function iframeResizeH(obj, h) {
	var win=obj;
    if (document.getElementById){
      if (win && !window.opera){
       if (win.contentDocument && win.contentDocument.body.offsetHeight)
        win.height = win.contentDocument.body.offsetHeight + h;
       else if(win.Document && win.Document.body.scrollHeight)
        win.height = win.Document.body.scrollHeight + h;
      }
    }
}

/**
 * 刷新当前页面
 */
function refreshPage() {
	window.location.reload();
}

/**
 * 定时刷新当前页面
 * 
 * @param time 时间(s)
 */
function refreshAfterTime(time) {
	if (isNaN(time) || time < 0) {
		time = 0;
	}
	setInterval("refreshPage()", time);
}

/**
 * 定时跳转到指定路径
 * 
 * @param time 时间(s)
 */
function goAfterTime(url, time) {
	if (isNaN(time) || time < 0) {
		time = 0;
	}
	setInterval("go('" + url + "')", time);
}

/**
 * 定时执行一个函数
 * @param fn 函数
 * @param time 时间(s)
 */
function doAfterTime(fn, time) {
	if (isNaN(time) || time < 0) {
		time = 0;
	}
	if(fn) {
		setInterval(fn,time);
	}
}

/**
 * 转到指定页面
 * 
 * @param url
 */
function go(url) {
	window.location.href = url;
}

/**
 * 跳转到指定页面，并把当前页面地址作为参数传过去
 * @param url
 */
function goWithUrl(url) {
	var currentUrl = encodeURIComponent(window.location.href);
	if(url.indexOf("?") > -1) {
		url += "&from_url=" + currentUrl;
	} else {
		url += "?from_url=" + currentUrl;
	}
	go(url);
}

function encodeURL(url) {
	return encodeURIComponent(url);
}

/**
 * JS中获取页面根路径的方法，同java中basePath
 */
var basePath = function() {
	var location = (window.location + '').split('/');
	var basePath = location[0] + '//' + location[2] + '/' + location[3];
	return basePath;
};

/**
 * 全选
 * @param obj 全选复选框本身，一般传this即可
 * @param name 需要全选的复选框名称
 * @param propName 属性名
 */
function checkAllForProp(obj, name, propName) {
	$("input[" + propName + "='" + name + "']").prop("checked", $(obj).prop("checked"));
}

/**
 * 全选
 * @param obj 全选复选框本身，一般传this即可
 * @param name 需要全选的复选框名称
 */
function checkAll(obj, name) {
	checkAllForProp(obj, name, "name");
}

/**
 * 根据input名称获取所有值的字符串，格式如：ss1,ss2,ss3
 * @param name
 * @returns {String}
 */
function getValuesByName(name) {
	var str = "";
	var u = $("input[name='" + name + "']");
	$.each(u, function() {
		if(stringNotNull(str)) {
			str += ",";
		}
		str += this.value;
	});
	return str;
}

/**
 * 判断字符串是否为空
 * 
 * @param str
 * @returns {Boolean}
 */
function stringNotNull(str) {
	return str != undefined && str != null && str != "";
}

function ajaxRequest(url, data, type,async, callback) {
	data = stringNotNull(data) ? data:"{}";
	$.ajax({
		url : url,
		type : type,
		data : data,
		async : async,
		dataType : "json",
		success : function(result) {
			if (callback) {
				callback(result);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
	    //  console.log(url+"Ajax错误：" + textStatus);
         // alert("Ajax错误：" + textStatus);
		}
	});
}

/**
 * 封装的jQuery的ajax方法（POST）
 * 
 * @param url
 *            请求的URL
 * @param data
 *            发送的数据，JSON格式
 * @param callback
 *            回调函数，有一个JSON类型的参数
 */
function postAjaxRequest(url, data, callback) {
	ajaxRequest(url, data, "post",true, callback);
}

/**
 * 封装的jQuery的ajax方法（POST）
 * 
 * @param url
 *            请求的URL
 * @param data
 *            发送的数据，JSON格式
 * @param callback
 *            回调函数，有一个JSON类型的参数
 */
function syncPostAjaxRequest(url, data, callback) {
	ajaxRequest(url, data, "post",false, callback);
}

/**
 * 封装的jQuery的ajax方法（GET）
 * 
 * @param url
 *            请求的URL
 * @param data
 *            发送的数据，JSON格式
 * @param callback
 *            回调函数，有一个JSON类型的参数
 */
function syncGetAjaxRequest(url, data, callback) {
	ajaxRequest(url, data, "get",false, callback);
}

/**
 * 封装的jQuery的ajax方法（GET）
 * 
 * @param url
 *            请求的URL
 * @param data
 *            发送的数据，JSON格式
 * @param callback
 *            回调函数，有一个JSON类型的参数
 */
function getAjaxRequest(url, data, callback) {
	ajaxRequest(url, data, "get",true, callback);
}

/**
 * 封装的jQuery的ajax方法（delete）
 * 
 * @param url
 *            请求的URL
 * @param data
 *            发送的数据，JSON格式
 * @param callback
 *            回调函数，有一个JSON类型的参数
 */
function deleteAjaxRequest(url, data, callback) {
	var index = url.indexOf("?", 0);
	var method_delete = (-1 == index ? "?" : "&") + "_method=delete";
	postAjaxRequest(url + method_delete, data, callback);
}

/**
 * 批量删除数据
 * @param url 删除的url
 * @param iptName 复选框名称, 同时也是参数名，用于在后台获取数据
 */
function batchDelete(url, iptName, paramName) {
	var ids = "";
	$("input[name='" + iptName + "']:checked").each(function(i) {
		if(ids != "") {
			ids += "_";
		}
		ids += $(this).val();
	});
	if(ids == "") {
		alert("请选择需要删除的选项！");
	} else {
		if(confirm("确定删除选中的所有数据？")) {
			deleteAjaxRequest(url, iptName + "=" + ids, function(result) {
				alert(result.message);
				if(result.status == "true") {
					refreshAfterTime(3000);
				}
			});
		}
	}
}

/**
 * 删除数据，并刷新页面
 * @param url
 */
function deleteData(url) {
	if(confirm("确定删除该数据？")) {
		deleteAjaxRequest(url, null, function(result) {
			alert(result.message);
			if(result.status == true || result.status == "true") {
				refreshAfterTime(1000);
			}
		});
	}
}


// ///////////////////////////文件操作接口////////////////////////////////
/**
 * 上传文件对话框,依赖artDialog插件
 * 
 * @param callFunc
 *            上传完成后的回调函数，有两个参数，第一个是上传是否成功，boolean类型，第二个是上传的文件FileInfo对象JSON字符串
 */
function uploadFile(callFunc) {
	art.dialog({
				id : 'uploadFile',
				title : '上传文件',
				content : "<iframe frameborder=0 style=\"width:305px;height:160px;\" src=\"" + platform_path + "/file/upload?callFunc="
						+ callFunc + "\"></iframe>",
				padding : 0,
				width : 306,
				height : 161,
				lock : true,
				fixed : true,
				max : false,// 禁用最大化
				min : false
			// 禁用最小化
			});
}

/**
 * 根据文件ID删除文件对象及文件本身，依赖jquery，返回操作结果
 * 
 * @param fileId
 *            文件ID
 */
function deleteFile(fileId) {
	deleteFileCallback(fileId, function(result) {
		alert(result.message);
	});
}

function deleteFileCallback(fileId, callback) {
	deleteAjaxRequest(platform_path + '/file/delete/' + fileId,"",function(result){
		callback(result);
	});
}

/**
 * 根据文件ID下载文件，依赖jquery，成功返回文件下载路径，失败提示原因
 * 
 * @param fileId
 *            文件ID
 */
function downloadFile(fileId) {
	postAjaxRequest(platform_path + "/file/download/"+fileId,"",function(result){
		if ("true" == result.status || true == result.status) {
			go(result.message);
		}else {
			alert(result.message);
		}
	});
}

/**
 * 打开某人的聊天窗口 在images添加message-green.png、message-gray.png，web平台images里有 在页面上添加 <a
 * class="chatWin${用户Id }" href="javascript:openChatWin('用户Id','用户名字');"></a><script>getChatWindow('${用户Id}');</script>
 */
function getChatWindow(username) {
	if (null != username && '' != username) {
		$.ajax({
			url : platform_path + '/checkUserOnline',
			data : 'username=' + username,
			type : 'post',
			success : function(result) {
				if (result) {
					$(".chatWin" + username).attr("title", "在线");
					$(".chatWin" + username)
							.html(
									"<img width='20px' height='20px' src='images/message-green.png'/>");
				} else {
					$(".chatWin" + username).attr("title", "离线");
					$(".chatWin" + username)
							.html(
									"<img width='20px' height='20px' src='images/message-gray.png'/>");
				}
			}
		});
	}
}

function openChatWin(id, name) {
	window.top.Windows.openApp("c" + id, name, platform_path + '/tochat?cid=' + id
			+ "&type=0", platform_path + '/GetHeadPhoto?username=' + id, 640, 500);
}

/**
 * 简易聊天对话框（没有发文件和消息记录）,依赖artDialog插件
 * 
 * @param id
 *            账号
 * @param name
 *            用户名
 */
function openSimpleChat(id, name) {
	art.dialog({
		id : "c" + id,
		title : name,
		content : "<iframe frameborder=0 style=\"width:640px;height:500px;\" src=\"" + platform_path + "/tochat?type=0&simple=on&cid="
				+ id + "\"></iframe>",
		width : 640,
		height : 500,
		padding : 0,
		fixed : true,
		drag : false,
		resize : false
	});
}

function bytesToSize(bytes) {
    if (bytes === 0) return '0 B';
    var k = 1000, // or 1024
        sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
        i = Math.floor(Math.log(bytes) / Math.log(k));
   return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
}

/**
 * 图片大小自动适应
 * @param obj 图片对象
 * @param reWidth 宽度
 */
function reSizeImg(obj,reWidth){
	if(obj.readyState=="complete")
	{
		var imgWidth=obj.offsetWidth;
		var imgHeight=obj.offsetHeight;
		if(imgWidth>reWidth)
		{
			p=imgWidth/reWidth;
			cHeight=Math.floor(imgHeight/p);
			obj.width=reWidth;
			obj.height=cHeight;
		}
	}
}